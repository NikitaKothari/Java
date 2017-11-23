package imgproc;

/**
 * 
 * @author nrk160530
 * 
 *         TODO: verify image matches using a probabilistic model - naive bayes
 *         (for each image transformation)
 * 
 * 
 *         Different scale size RGB model
 * 
 * 
 *         Multiple images Need to implement logger for logs and debugging
 * 
 * 
 *         Implement read files from one folder and merge them
 *
 */
public class Test {
	public static void main(String[] args) {

		String extension = "jpg";

		ExtractFeatureOfImage featureExtractionImage1 = new ExtractFeatureOfImage("0.jpg", "pan1_keypoints.jpg",
				extension);
		ExtractFeatureOfImage featureExtractionImage2 = new ExtractFeatureOfImage("1.jpg", "pan2_keypoints.jpg",
				extension);

		DescribeFeatureOfImage featureDescriptionImage = new DescribeFeatureOfImage(featureExtractionImage1,
				featureExtractionImage2, "pan_ransac.jpg", extension);

		GeneratePanoramaImage panoramaImage = new GeneratePanoramaImage(featureExtractionImage1,
				featureExtractionImage2, featureDescriptionImage, "pan_out.jpg", extension);
	}
}
