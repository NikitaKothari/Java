package imgproc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

public class ImageUtility {
	public static BufferedImage convertMatToBufferedImage(Mat matImg) {
		MatOfByte bytemat = new MatOfByte();
		Highgui.imencode(".png", matImg, bytemat);
		byte[] bytes = bytemat.toArray();
		InputStream in = new ByteArrayInputStream(bytes);
		try {
			BufferedImage img = ImageIO.read(in);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage getBufferedImage(Mat img) {
		return convertMatToBufferedImage(img);
	}

	public static void drawImage(String outPath, String extension, Mat img) {
		File outputfile = new File(outPath);
		try {
			ImageIO.write(ImageUtility.getBufferedImage(img), extension, outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
