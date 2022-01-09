package com.vognev.textilewebproject.service;

import com.vognev.textilewebproject.model.MyUser;
import com.vognev.textilewebproject.model.Role;
import com.vognev.textilewebproject.repository.MyUserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * textilewebproject  07/10/2021-7:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private MyUserRepository myUserRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private MyMailSender mailSender;

    @Test
   public void addUser() {
        MyUser user = new MyUser();
        user.setEmail("some@mail.com");
      boolean isUserCreated =  userService.addUser(user);
      Assert.assertTrue(isUserCreated);
      Assert.assertNotNull(user.getActivationCode());
      Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(myUserRepository,Mockito.times(1)).save(user);
        Mockito.verify(mailSender,Mockito.times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                        );

        // ArgumentMatchers.eq("Activation code "),  ArgumentMatchers.contains("Welcome to Textile"))  можно аменить на
        // ;
    }
    @Test
    public void addUserFailTest(){
        MyUser user = new MyUser();

        user.setUsername("John");

        Mockito.doReturn(new MyUser())
                .when(myUserRepository)
                .findByUsername("John");

        boolean isUserCreated =  userService.addUser(user);
        Assert.assertFalse(isUserCreated);

        Mockito.verify(myUserRepository,Mockito.times(0)).save(ArgumentMatchers.any(MyUser.class));
        Mockito.verify(mailSender,Mockito.times(0))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    @Test
    void activateUser() {

        MyUser user = new MyUser();
        user.setActivationCode("12221");

            Mockito.doReturn(user)
                .when(myUserRepository)
                .findByActivationCode("activate");

      boolean isUserActivated = userService.activateUser("activate");

        Assert.assertTrue(isUserActivated);
        Assert.assertNull(user.getActivationCode());

        Mockito.verify(myUserRepository,Mockito.times(1)).save(user);
    }
@Test
    public void activateUserFailTest(){
        boolean isUserActivated = userService.activateUser("activate me");

        Assert.assertFalse(isUserActivated);

    Mockito.verify(myUserRepository,Mockito.times(0)).save(ArgumentMatchers.any(MyUser.class));

}
}