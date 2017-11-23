package imgproc;

import java.util.ArrayList;
import java.util.List;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * 
 * @author nrk160530
 *
 */
public class GeneratePanoramaImage {

	ExtractFeatureOfImage img1, img2;
	DescribeFeatureOfImage imgMatch;

	Mat imgResult;

	MatOfPoint2f ptMat1;
	MatOfPoint2f ptMat2;

	public GeneratePanoramaImage(ExtractFeatureOfImage img1, ExtractFeatureOfImage img2,
			DescribeFeatureOfImage imgMatch, String outPath, String extension) {
		this.img1 = img1;
		this.img2 = img2;
		this.imgMatch = imgMatch;

		ptMat1 = new MatOfPoint2f();
		ptMat2 = new MatOfPoint2f();

		imgResult = new Mat();
		getKeypointsFromMatchingMat();
		computePanoramaImage();

		// Draw image
		ImageUtility.drawImage(outPath, extension, imgResult);

	}

	private void getKeypointsFromMatchingMat() {
		List<Point> ptList1 = new ArrayList<Point>();
		List<Point> ptList2 = new ArrayList<Point>();

		for (int i = 0; i < imgMatch.getMatchingMat().toArray().length; i++) {
			// queryIdx - refers to keypoints1
			ptList1.add(img1.getKeyPoints().toArray()[imgMatch.getMatchingMat().toArray()[i].queryIdx].pt);
			// trainIdx - refers to keypoints2
			ptList2.add(img2.getKeyPoints().toArray()[imgMatch.getMatchingMat().toArray()[i].trainIdx].pt);
		}

		// Matching points from 1st image
		ptMat1.fromList(ptList1);
		// Matching points from 2nd image
		ptMat2.fromList(ptList2);
	}

	/**
	 * Bundle adjustment
	 */
	private void computePanoramaImage() {
		// Find the Homography Matrix
		// Finds a perspective transformation between two planes.
		// Common point to attach images
		Mat H = Calib3d.findHomography(ptMat2, ptMat1, Calib3d.RANSAC, 5);
		// Applies a perspective transformation to an image.
		Imgproc.warpPerspective(img2.getMatImage(), imgResult, H,
				new Size(img1.getMatImage().cols() + img2.getMatImage().cols(), img2.getMatImage().rows()));
		Mat half = new Mat(imgResult, new Rect(0, 0, img1.getMatImage().cols(), img1.getMatImage().rows()));
		img1.getMatImage().copyTo(half);
	}
}
