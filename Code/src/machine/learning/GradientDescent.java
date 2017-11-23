package machine.learning;

public class GradientDescent {

	public static void calculateFunction(int[] x, float[] h, int n, float theta0, float theta1) {
		for (int i = 0; i < n; i++) {
			h[i] = theta0 + theta1 * x[i];
		}
	}

	public static float findError(float[] h, int[] y, int n) {
		float sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum + ((h[i] - y[i]) * (h[i] - y[i]));
		}
		return sum / (2 * n);
	}

	public static float FindTemp0(float[] h, int[] y, int n, float theta0, float alpha) {
		float sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum + (h[i] - y[i]);
		}
		return theta0 - (sum * alpha) / n;
	}

	public static float FindTemp1(float[] h, int[] x, int[] y, int n, float theta1, float alpha) {
		float sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum + ((h[i] - y[i]) * x[i]);
		}
		return theta1 - (sum * alpha) / n;
	}

	public static void main(String[] args) {
		int[] x = { 3, 1, 0, 4 };
		int[] y = { 2, 2, 1, 3 };
		int n = x.length;

		float[] h = new float[x.length];

		float error, temp0, temp1;

		float theta0 = 0, theta1 = 1, alpha = (float) 0.05;
		for (int i = 0; i < 5; i++) {
			System.out.println("____________________ " + (i + 1) + " ______________________");
			calculateFunction(x, h, n, theta0, theta1);
			error = findError(h, y, n);
			temp0 = FindTemp0(h, y, n, theta0, alpha);
			temp1 = FindTemp1(h, x, y, n, theta1, alpha);

			System.out.println("Error is " + String.format("%.4f", error));
			System.out.println("temp0 is " + String.format("%.4f", temp0));
			System.out.println("temp1 is " + String.format("%.4f", temp1));

			theta0 = temp0;
			theta1 = temp1;
			System.out.println();
		}
	}
}
