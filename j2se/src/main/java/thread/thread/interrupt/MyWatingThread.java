package thread.thread.interrupt;

/**
 * @Author : JieWang
 * @Date : Created in 2019年07月31日11:38
 * @Email : wjahstu@163.com
 */
public class MyWatingThread extends Thread{

    @Override
    public void run(){
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("i am waiting but facing interruptexception now");
            }
        }
    }
}
