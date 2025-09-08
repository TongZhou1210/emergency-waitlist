package mts;

class t1 implements Runnable{
	
	public void run() {
		System.out.println("Threadt t1 is running...");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		t1 obj1 = new t1();
		Thread t = new Thread (obj1);

		System.out.println("Threadt t1 is going to run...");
		t.start();

	}

}
