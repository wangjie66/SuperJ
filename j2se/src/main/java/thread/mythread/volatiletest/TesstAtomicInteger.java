package thread.mythread.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : JieWang
 * @Date : Created in 2018年07月16日16:53
 * @Email : jiewang11@iflytek.com
 */
public class TesstAtomicInteger implements Runnable{

    private static final AtomicInteger ai = new AtomicInteger(0);
    private static final AtomicInteger ai2 = new AtomicInteger(0);
    private static int tai = 0 ;
    private static int tai2 = 0 ;
    private static int tai3 = 0 ;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new TesstAtomicInteger()) ;
        t1.start();
        Thread.sleep(1000);
        for(int i =0 ;i<1000 ;i++){
            tai2+=i ;
        }
        System.out.println("tai2:"+tai2);
        System.out.println("tai:"+tai);
        System.out.println("tai3:"+tai3);
    }

    public void run() {
        for(int i =0 ;i<1000 ;i++){
            tai+=ai.get() ;
            ai.incrementAndGet() ;
        }
        for(int i =0 ;i<1000 ;i++){
            System.out.println(i+":"+ai2.getAndSet(i));
            tai3+=ai2.getAndSet(i);
        }
    }
}
