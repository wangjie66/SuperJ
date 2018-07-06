package practice;

/**
 * @Author : JieWang
 * @Date : Created in 2018年06月15日14:36
 * @Email : wjahstu@163.com
 */
public class ReturnInFor {

    public static void main(String[] args) {
        System.out.println(m1());
        System.out.println(m2());
        System.out.println(m3());
    }

    public static String m1() {
        int[] s = {1,2,3,4,5};
        for (int i = 0; i <s.length ; i++) {
            if(i==3){
                return "3" ;
            }
        }
        return "1" ;
    }

    public static boolean m2() {
        int[] s = {1,2,3,4,5};
        for (int i = 0; i <s.length ; i++) {
            if(i==3){
                return true ;
            }
        }
        return false ;
    }

    public static boolean m3() {
        int[] s = {1,2,3,4,5};
        for (int i = 0; i <s.length ; i++) {
            if(i==3){
                return false ;
            }
        }
        return true ;
    }


}
