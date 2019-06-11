package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : JieWang
 * @Date : Created in 2019年05月30日21:55
 * @Email : jiewang11@iflytek.com
 */
public class SimplePattern {

    public static void main(String[] args) {

        String str="我的手机号码是13075512700，我的手机号码是15385125452，我的手机号18328394922";
        String regex="1[358]\\d{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str) ;
        while (matcher.find()){
            System.out.println(matcher.group());
        }

    }

}
