package thread.mythread.pool;

import java.util.Random;
import java.util.concurrent.Callable;

public class TaskCallable2 implements Callable<String>{

	private int s;
	Random r = new Random();
	public TaskCallable2(int s){
		this.s = s;
	}

	public String call() {
		 return ""+(++s);
	}

}
