package thread.thread.interrupt;

/**
 * @Author : JieWang
 * @Date : Created in 2019年07月31日11:36
 * @Email : wjahstu@163.com
 */
public class ThreadInterruptForStates {

    public static void main(String[] args) throws Exception {
        runnableState();
    }

    private static void newState(){
        Thread thread = new MyThread();
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    private static void terminatedState()  throws InterruptedException{
        Thread thread = new MyThread();
        thread.start();
        thread.join();
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

    private static void runnableState()  throws InterruptedException{
        Thread thread = new MyWhileTrueThread();
        thread.start();
        System.out.println(thread.getState());
        thread.interrupt();
        Thread.sleep(1000);//等到thread线程被中断之后
        System.out.println(thread.isInterrupted());
        System.out.println(thread.getState());
    }

    private static void runnableBreakState()  throws InterruptedException{
        Thread thread = new MyWhileTrueBreakThread();
        thread.start();
        System.out.println(thread.getState());
        thread.interrupt();
        Thread.sleep(1000);//等到thread线程被中断之后
        System.out.println(thread.isInterrupted());
        System.out.println(thread.getState());
    }

    private static void blockedState()  throws InterruptedException{
        Thread thread = new MyWhileTrueBlockedThread();
        thread.start();
        Thread thread2 = new MyWhileTrueBlockedThread();
        thread2.start();
        Thread.sleep(1000);//等到thread线程被中断之后
        System.out.println(thread.getState());
        System.out.println(thread2.getState());

        thread2.interrupt();
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.getState());
    }

    private static void watingState()  throws InterruptedException{
        Thread thread = new MyWatingThread();
        thread.start();
        Thread.sleep(500);
        System.out.println(thread.getState());
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println(thread.isInterrupted());

    }


}
