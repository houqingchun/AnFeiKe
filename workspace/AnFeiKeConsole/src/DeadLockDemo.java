public class DeadLockDemo {
	/*
	 * This method request two locks, first String and then Integer
	 */
	public void method1() {
		synchronized (String.class) {
			System.out.println("Aquired lock on String.class object");

			synchronized (Integer.class) {
				System.out.println("Aquired lock on Integer.class object");
			}
		}
	}

	/*
	 * This method also requests same two lock but in exactly Opposite order
	 * i.e. first Integer and then String. This creates potential deadlock, if
	 * one thread holds String lock and other holds Integer lock and they wait
	 * for each other, forever.
	 */
	public void method2() {
		synchronized (Integer.class) {
			System.out.println("Aquired lock on Integer.class object");

			synchronized (String.class) {
				System.out.println("Aquired lock on String.class object");
			}
		}
	}

	public static void main(String[] args){
		Thread a = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DeadLockDemo d = new DeadLockDemo();
				for (int i = 0; i < 1000; i++){
					d.method1();
					d.method2();
				}
			}
			
		});
		
		Thread b = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DeadLockDemo d = new DeadLockDemo();
				for (int i = 0; i < 1000; i++){
					d.method1();
					d.method2();
				}
			}
			
		});
		
		a.start();
		b.start();
	}
}
