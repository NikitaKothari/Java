package imgproc;

import java.io.PrintWriter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

public class ExtractFeatureOfImage {

	private Mat image;
	private Mat imageKeypoint;

	private MatOfKeyPoint keypoints;

	public ExtractFeatureOfImage(String imgPath, String outPath, String extension) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		image = Highgui.imread(imgPath);
		keypoints = new MatOfKeyPoint();
		imageKeypoint = new MatOfKeyPoint();

		detectKeypointSift();

		ImageUtility.drawImage(outPath, extension, imageKeypoint);
	}

	private void detectKeypointSift() {
		FeatureDetector detector = FeatureDetector.create(FeatureDetector.SIFT);
		detector.detect(image, keypoints);
		// It will draw key points on the top of give image
		KeyPoint[] m = keypoints.toArray();
		try {
			PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
			for (KeyPoint i : m) {
				int octave;
				int layer;
				float scale;
				octave = i.octave & 255;
				layer = (i.octave >> 8) & 255;
				octave = octave < 128 ? octave : (-128 | octave);
				scale = octave >= 0 ? 1.f / (1 << octave) : (float) (1 << -octave);
				writer.println("i.octave");
				writer.print(i.octave);
				writer.println("layer");
				writer.print(layer);
				writer.println("octave_extracted");
				writer.print(octave);
				writer.println("scale");
				writer.print(scale);
				writer.print("size");
				writer.print(i.size);
			}
			writer.close();
		} catch (Exception e) {

		}
		Features2d.drawKeypoints(image, keypoints, imageKeypoint);
	}

	public Mat getMatImage() {
		return image;
	}

	public MatOfKeyPoint getKeyPoints() {
		return keypoints;
	}

}
