package spring.controller;

import org.springframework.stereotype.Controller;
import spring.service.FirstService;

import javax.annotation.Resource;

/**
 * @Author : JieWang
 * @Date : Created in 2020年07月03日15:22
 * @Email : wjahstu@163.com
 */
@Controller
public class TestResourceAndAutoWired {

    //@Resource 能找到，没有name的时候，自动按type
    //@Resource(name="first") 能找到
    //@Resource(name="firstService") 没找到
    @Resource
    private FirstService firstService ;
}
