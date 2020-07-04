package thread.thread.interrupt;

/**
 * @Author : JieWang
 * @Date : Created in 2019年07月31日11:38
 * @Email : wjahstu@163.com
 */
public class MyWhileTrueBlockedThread extends Thread{

    public synchronized static void doSomething(){
        while(true){
            //do something
        }
    }

    @Override
    public void run(){
        doSomething();
    }
}
