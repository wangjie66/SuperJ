package utils;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author : JieWang
 * @Date : Created in 2020年05月08日11:01
 * @Email : wjahstu@163.com
 */
public class FileUtilsByReadByLine {

    public static int[] toArrayByFileReader1(String name) {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList();
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对ArrayList中存储的字符串进行处理
        int length = arrayList.size();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            String s = arrayList.get(i);
            array[i] = Integer.parseInt(s);
        }
        // 返回数组
        return array;
    }

    public static  boolean m1(){
        for(int i =0 ;i<10 ; i++){
            if(i==5){
                return true ;
            }
            System.out.println(i);
        }
        return false;
    }

    @Test
    public static void m2(){
        System.out.println(m1());
    }

    public static void main(String[] args) {
        //toArrayByFileReader1("sss") ;
        System.out.println(m1());
    }
}
