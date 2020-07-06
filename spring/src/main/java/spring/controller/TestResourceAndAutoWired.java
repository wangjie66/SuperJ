package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import spring.service.FirstService;
import spring.service.SecondService;

import javax.annotation.Resource;

/**
 * @Author : JieWang
 * @Date : Created in 2020年07月03日15:22
 * @Email : wjahstu@163.com
 */
@Controller
public class TestResourceAndAutoWired {

    //@Resource 能找到，当没有找到name的时候，自动按type
    //@Resource(name="first") 能找到
    //@Resource(name="firstService") 没找到
    @Resource
    private FirstService firstService ;

    //@Resource 不能找到，因为name=first对应的是FirstService，与定义的类型不匹配，会报错
    private SecondService first ;

    @Autowired
    @Qualifier("second")
    private SecondService secondService ;
}
