package concurrentHashMap;

import java.util.Map;

/**
 * @Author : JieWang
 * @Date : Created in 2019年02月27日21:51
 * @Email : jiewang11@iflytek.com
 */
public class Test {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        User u = new User("wj");
        sb.append("hello") ;
        m1(sb);
        m2(u);
        System.out.println(sb.toString());
        System.out.println(u.getName());

    }

    public static void m1(StringBuilder sb){
        sb.append("word") ;
      //sb = new StringBuilder("world") ;
    }

    public static void m2(User user){
      // user.setName("lkh1");


       user = new User("lkh2");
    }
}

class User {

    public String name ;
    public String age ;

    public User(String name){
        this.name = name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
