package utils;

import java.util.Random;

/**
 *
 */
public class RandomUtil {
//    public static void main(String[] args) {
//
//        String stra = getRandom(8);
//        System.out.println(stra);
//    }

    // 产生一个随机数字组合
    public static String getRandom(int nums) {
        Random r = new Random();
        int i = 0;
        String str = "";
        String s = null;
        while (i < nums) {
            switch (r.nextInt(10)) {
                case (0):
                    s = "0";
                    break;
                case (1):
                    s = "1";
                    break;
                case (2):
                    s = "2";
                    break;
                case (3):
                    s = "3";
                    break;
                case (4):
                    s = "4";
                    break;
                case (5):
                    s = "5";
                    break;
                case (6):
                    s = "6";
                    break;
                case (7):
                    s = "7";
                    break;
                case (8):
                    s = "8";
                    break;
                case (9):
                    s = "9";
                    break;

            }
            i++;
            str += s;
        }
        return str;
    }
}
