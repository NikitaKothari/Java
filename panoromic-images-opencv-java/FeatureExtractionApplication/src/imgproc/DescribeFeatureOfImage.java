package imgproc;

import java.util.ArrayList;
import java.util.List;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.Features2d;

/**
 * This class extract feature out of image using SIFT
 * 
 * @author nikitakothari
 *
 */
public class DescribeFeatureOfImage {

	ExtractFeatureOfImage img1, img2;
	Mat imgMatches;
	Mat descriptors1, descriptors2;
	MatOfDMatch matchingMat;

	public DescribeFeatureOfImage(ExtractFeatureOfImage img1, ExtractFeatureOfImage img2, String outPath,
			String extension) {
		this.img1 = img1;
		this.img2 = img2;
		imgMatches = new Mat();

		descriptors1 = new Mat();
		descriptors2 = new Mat();

		matchingMat = new MatOfDMatch();

		describeFeature();

		// draw image
		ImageUtility.drawImage(outPath, extension, imgMatches);

	}

	private void describeFeature() {
		DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SIFT);

		// Compute descriptors
		descriptorExtractor.compute(img1.getMatImage(), img1.getKeyPoints(), descriptors1);
		descriptorExtractor.compute(img2.getMatImage(), img2.getKeyPoints(), descriptors2);

		// Match descriptor using FLANN matcher
		// find k - nearest neighbors for each feature -> descriptors
		DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
		MatOfDMatch matches = new MatOfDMatch();
		matcher.match(descriptors1, descriptors2, matches);

		// Calculate max and min distances between keypoints
		double maxDist = 0;
		double minDist = 100;
		for (int i = 0; i < matches.rows(); i++) {
			double dist = matches.toArray()[i].distance;
			if (dist < minDist)
				minDist = dist;
			if (dist > maxDist)
				maxDist = dist;
		}
		System.out.println("Max dist : " + maxDist);
		System.out.println("Min dist : " + minDist);

		// Draw good matches (i.e. distance < 3*min_dist ) --> set a threshold
		// value
		// Modification need for multiple images
		// Calculate matching point with respective each image and then merge
		// image
		// which has most common points
		List<DMatch> matchingList = new ArrayList<DMatch>();
		for (int i = 0; i < matches.rows(); i++) {
			if (matches.toArray()[i].distance <= 3 * minDist)
				matchingList.add(matches.toArray()[i]);
		}
		matchingMat.fromList(matchingList);

		// Use RANSAC to remove outlier
		MatOfPoint2f ptMat1 = new MatOfPoint2f();
		MatOfPoint2f ptMat2 = new MatOfPoint2f();

		List<Point> ptList1 = new ArrayList<Point>();
		List<Point> ptList2 = new ArrayList<Point>();

		for (int i = 0; i < matchingMat.toArray().length; i++) {
			// queryIdx - refers to keypoints1
			ptList1.add(img1.getKeyPoints().toArray()[matchingMat.toArray()[i].queryIdx].pt);
			// trainIdx - refers to keypoints2
			ptList2.add(img2.getKeyPoints().toArray()[matchingMat.toArray()[i].trainIdx].pt);
		}

		ptMat1.fromList(ptList1);
		ptMat2.fromList(ptList2);

		Mat mask = new Mat();
		// Mask - after processing it will contain 1 for matching point
		Mat H = Calib3d.findHomography(ptMat2, ptMat1, Calib3d.RANSAC, 5, mask);

		List<DMatch> goodMatchesListRANSAC = new ArrayList<DMatch>();
		MatOfDMatch goodMatchesRANSAC = new MatOfDMatch();
		for (int i = 0; i < matchingMat.toArray().length; i++) {
			if (mask.get(i, 0)[0] == 1) {
				goodMatchesListRANSAC.add(matchingMat.toArray()[i]);
			}
		}
		goodMatchesRANSAC.fromList(goodMatchesListRANSAC);
		MatOfByte matchesMask = new MatOfByte();

		// It will combine images on the basis of good matching points and draw
		// lines over it

		Features2d.drawMatches(img1.getMatImage(), img1.getKeyPoints(), img2.getMatImage(), img2.getKeyPoints(),
				goodMatchesRANSAC, imgMatches, Scalar.all(-1), Scalar.all(-1), matchesMask,
				Features2d.NOT_DRAW_SINGLE_POINTS);

		if (matchingMat.toArray().length == 0)
			System.out.println("No Good Match");
		else {
			System.out.println("Number of features matched using RANSAC: " + matchingMat.toArray().length);
		}
	}

	public MatOfDMatch getMatchingMat() {
		return matchingMat;
	}

}
