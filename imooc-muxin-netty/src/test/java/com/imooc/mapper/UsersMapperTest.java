package com.imooc.mapper;

import com.imooc.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author peterLee
 * @Date 2018/10/11 下午12:00
 * @Describtion :
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersMapperTest {

    @Autowired
    UsersMapper usersMapper;

    @Test
    public void test(){
        List<Users> users = usersMapper.selectAll();
        System.out.println(users.toString());
    }
}