package thread.thread.testThread;

public class MySynchronized {

	public String a  ;

	public static  MySynchronized mlock  = new MySynchronized();

	public static void main(String[] args) {
		try {
			 MySynchronized mySynchronized = new MySynchronized();
			 MySynchronized mySynchronized2 = new MySynchronized();
			//thread1(mySynchronized) ;
			thread1(mlock) ;
			Thread.sleep(1000);
			mlock.a = "333" ;
			System.out.println(mlock.a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    static void	thread1( final MySynchronized m){
		new Thread("thread1") {
			public void run() {
				synchronized (m) {
					try {
						System.out.println(this.getName()+" start");
						//int i =1/0;   //如果发生异常，jvm会将锁释放
						Thread.sleep(5000);
						System.out.println(this.getName()+"醒了");
						System.out.println(this.getName()+" end");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		new Thread("thread2") {
			public void run() {
				synchronized (m) {         //争抢同一把锁时，线程1没释放之前，线程2只能等待
//					synchronized (mySynchronized2) {    //如果不是一把锁，可以看到两句话同时打印
					System.out.println(this.getName()+" start");
					System.out.println(this.getName()+" end");

				}
			}
		}.start();
	}

}
