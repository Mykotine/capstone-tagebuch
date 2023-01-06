package com.example.backend;

import com.example.backend.domain.Role;
import com.example.backend.domain.User;
import com.example.backend.repos.UserRepository;
import com.example.backend.sevice.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UnitTests {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;



    @Test
    public void addUser(){
        User user = new User();
        boolean isUserCreated = userService.addUser(user);
        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
        public void addUserFailTest(){
        User user = new User();
        user.setUsername("Dorothea");
        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("Dorothea");
        boolean isUserCreated = userService.addUser(user);

        Mockito.verify(userRepository,Mockito.times(0)).save(ArgumentMatchers.any(User.class));

        Assert.assertFalse(isUserCreated);

    }


}
