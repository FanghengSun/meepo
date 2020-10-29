package com.fangheng.meepo;

import com.fangheng.meepo.entity.User;
import com.fangheng.meepo.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
class MeepoApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void insert() {

        User user = new User();
        user
                .setUserName("小蓝")
                .setPassword("123456")
                .setEmail("fw@gmail.com")
                .setPhone("13158585858")
                .setFullName("李小蓝");
        Boolean res = userService.save(user);
        Assertions.assertEquals(true, res);
    }

    @Test
    public void list() {
        User user = new User();
        user
                .setUserName("小蓝")
                .setPassword("12345")
                .setEmail("fw@gmail.com")
                .setPhone("13158585858")
                .setFullName("李小蓝");
        userService.save(user);
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }
}

