package cn.lcy.test;


import cn.lcy.domain.User;
import cn.lcy.service.UserService;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class TestSpring {

    @Test
    public void run1(){
        // 加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 获取对象
        UserService as = (UserService) ac.getBean("userService");
        User user= new User();
        user.setName("王五");
        user.setNumber("222222");

        // 调用方法
        as.add(user);



    }

    @Test
    public void test1() {
        // 加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 获取对象
        UserService as = (UserService) ac.getBean("userService");


        User user = as.getUserById(1);
        System.out.println(user.getName());

    }


}
