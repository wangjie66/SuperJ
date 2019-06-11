package concurrentHashMap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : JieWang
 * @Date : Created in 2019年02月26日12:37
 * @Email : jiewang11@iflytek.com
 */
public class ConcurrentHasHMap {

    public  static HashMap<String,Integer> map = new HashMap<String, Integer>() ;
    public  static ConcurrentHashMap<String,Integer> cmap = new ConcurrentHashMap<String, Integer>() ;

    public static  void add(String key,String tname){
        Integer value = ConcurrentHasHMap.map.get(key) ;
        System.out.println(tname + " in . "+value);
        if(value == null){
            map.put(key,1);
        }else {
            map.put(key,value+1);
        }
    }

    public static  void add2(String key,String tname){
        Integer value = ConcurrentHasHMap.cmap.get(key) ;
        System.out.println(tname + " in ."+value);
        if(value == null){
            cmap.put(key,1);
        }else {
            cmap.put(key,value+1);
        }

    }

    public static void m1(){
        Runner a1 = new Runner("a1") ;
        new Thread(a1).start();
        Runner a2 = new Runner("a2") ;
        new Thread(a2).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result:"+map.get("hh"));
    }

    public static void m2(){
        Runner2 a1 = new Runner2("a1") ;
        new Thread(a1).start();
        Runner2 a2 = new Runner2("a2") ;
        new Thread(a2).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result:"+cmap.get("hh"));
    }


    public static void main(String[] args) {
       m1() ;
       //m2() ;
    }
}


class Runner implements Runnable{

    public String name ;
    public Runner( String name ){
        this.name = name ;
    }

    private synchronized  void add(String key,String tname){
        Integer value = ConcurrentHasHMap.map.get(key) ;
        System.out.println(tname + " in . "+value);
        if(value == null){
            ConcurrentHasHMap.map.put(key,1);
        }else {
            ConcurrentHasHMap.map.put(key,value+1);
        }
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            ConcurrentHasHMap.add("hh",name+":"+(i+1));
        }
    }
}
class Runner2 implements Runnable{

    public String name ;
    public Runner2( String name ){
        this.name = name ;
    }

    private  void add2(String key,String tname){
        Integer value = ConcurrentHasHMap.cmap.get(key) ;
        System.out.println(tname + " in . "+value);
        if(value == null){
            ConcurrentHasHMap.cmap.put(key,1);
        }else {
            ConcurrentHasHMap.cmap.put(key,value+1);
        }

    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            ConcurrentHasHMap.add2("hh",name+":"+(i+1));
        }
    }
}
