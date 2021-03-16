package com.mlxg;


import com.mlxg.mapper.UserMapper;
import com.mlxg.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = BirdApplication.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetUser() {
        User user = userMapper.selectByPrimaryKey("1");
        System.out.println(user);
    }
}
