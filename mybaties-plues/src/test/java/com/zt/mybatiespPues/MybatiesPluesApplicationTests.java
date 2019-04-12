package com.zt.mybatiespPues;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.entity.User;
import com.zt.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatiesPluesApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insertTest(){
        User user =  new User();
        user.setName("john");
        user.setAge(23);
        user.setEmail("john@163.com");
        user.setSchoolid(2);
        int i = userMapper.insert(user);
        Assert.assertEquals(1,i);
    }

    @Test
    public void updateTest(){
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda()
                    .eq(User::getId,1));
        user.setName("sansan");
        userMapper.updateById(user);
    }

    @Test
    public void selectList() {
       /* List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);*/

       /*List<User> userList = userMapper.selectList(
               new QueryWrapper<User>().lambda()
                .eq(User::getAge, "21"));
        userList.forEach(System.out::println);*/

        List<User> userList = userMapper.selectList(
                new QueryWrapper<User>().lambda()
                        .gt(User::getAge, "21"));
        userList.forEach(System.out::println);
    }



}
