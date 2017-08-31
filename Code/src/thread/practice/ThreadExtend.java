package thread.practice;

public class ThreadExtend extends Thread {
	public int count = 0;

	public void run() {
		System.out.println("Runnable interface started");
		try {
			while (count < 5) {
				Thread.sleep(1000);
				count++;
			}
		} catch (InterruptedException e) {
			System.out.println("Runnable interface interrupted");
		}
		System.out.println("Runnable interface finished");
	}

	public static void main(String[] args) {
		ThreadExtend instance = new ThreadExtend();
		instance.start();

		/* waits until above thread counts to S (slowly) */
		while (instance.count != 5) {
			try {
				Thread.sleep(256);
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}
	}
}
