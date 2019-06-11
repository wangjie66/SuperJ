package utils;

/**
 * 身份证格式转换工具
 * Created by Administrator on 2017/10/23.
 */
public class IDCardUtil {

    /**
     *
     * @param century  19xx 年用 19，20xx 年用 20
     * @param idCardNo15 待转换的 15 位身份证号码
     * @return
     */
    public static String from15to18(int century, String idCardNo15) {

        String centuryStr =Integer.toString(century);
        if(century <0 || centuryStr.length() != 2)
            throw new IllegalArgumentException("世纪数无效！应该是两位的正整数。");
        if(!(isIdCardNo(idCardNo15) && idCardNo15.length() == 15))
            throw new IllegalArgumentException("旧的身份证号格式不正确！");

        int[] weight = new int[] {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};

        // 通过加入世纪码, 变成 17 为的新号码本体.
        String newNoBody = idCardNo15.substring(0, 6) + centuryStr + idCardNo15.substring(6);

        //下面算最后一位校验码

        int checkSum = 0;
        for(int i=0; i< 17; i++) {
            int ai = Character.getNumericValue(newNoBody.charAt(i)); // 位于 i 位置的数值
            checkSum = checkSum + ai * weight[i];
        }

        int checkNum = checkSum % 11;
        String checkChar = null;

        switch(checkNum) {
            case 0: checkChar = "1"; break;
            case 1: checkChar = "0"; break;
            case 2: checkChar = "X"; break;
            default: checkChar ="" + (12 - checkNum);
        }

        return newNoBody + checkChar;

    }


    public static String from18to15(String idCardNo18) {

        if(!(isIdCardNo(idCardNo18) && idCardNo18.length() == 18))
            throw new IllegalArgumentException("身份证号参数格式不正确！");

        return idCardNo18.substring(0, 6) + idCardNo18.substring(8, 17);
    }

    /**
     * 判断给定的字符串是不是符合身份证号的要求
     * @param str
     * @return
     */
    public static boolean isIdCardNo(String str) {

        if(str == null)
            return false;

        int len = str.length();
        if(len != 15 && len != 18)
            return false;

        for(int i=0; i<len-1; i++) {
            try {
                Character.getNumericValue(str.charAt(i));
            }
            catch(NumberFormatException e) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {

        System.out.println(from15to18(19, "340621881120883"));
        System.out.println(from18to15("340621198811208833"));
    }

}
