package thread.thread.interrupt;

/**
 * @Author : JieWang
 * @Date : Created in 2019年07月31日11:38
 * @Email : wjahstu@163.com
 */
public class MyWhileTrueBreakThread extends Thread{

    @Override
    public void run(){
        while(true){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("exit MyThread");
                break;
            }
        }
    }
}
