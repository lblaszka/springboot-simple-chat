package com.github.lblaszka.springsimplechat.service;

import com.github.lblaszka.springsimplechat.domain.ApplicationUser;
import com.github.lblaszka.springsimplechat.domain.ApplicationUserConfidential;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith( SpringRunner.class )
@SpringBootTest
public class ApplicationUserServiceTest
{
    final String USERNAME_CORRECT_1 = "username1";
    final String USERNAME_CORRECT_2 = "username2";
    final String PASSWORD_CORRENT = "password";

    final String USERNAME_INCORRECT = "12";
    final String PASSWORD_INCORRECT = "12345";

    @Autowired
    ApplicationUserService applicationUserService;

    @BeforeClass
    public static void beforeMessage()
    {
        System.out.println("Application User Class tests");
    }

    @Test
    public void checkValidatorNewUsersTest()
    {
        int userNumber = applicationUserService.findAll().size();
        ApplicationUser user_incorrect1 = new ApplicationUser(  );

        ApplicationUser user_incorrect2 = new ApplicationUser(  );
        user_incorrect2.setUsername( USERNAME_INCORRECT );
        user_incorrect2.setPassword( PASSWORD_CORRENT );

        ApplicationUser user_incorrect3 = new ApplicationUser(  );
        user_incorrect3.setUsername( USERNAME_CORRECT_1 );
        user_incorrect3.setPassword( PASSWORD_INCORRECT );

        ApplicationUser user_correct = new ApplicationUser(  );
        user_correct.setUsername( USERNAME_CORRECT_1 );
        user_correct.setPassword( PASSWORD_CORRENT );


        Assert.assertFalse( applicationUserService.save( user_incorrect1 ) );
        Assert.assertFalse( applicationUserService.save( user_incorrect2 ) );
        Assert.assertFalse( applicationUserService.save( user_incorrect3 ) );
        Assert.assertTrue( applicationUserService.save( user_correct ) );
        Assert.assertFalse( applicationUserService.save( user_correct ) );


        Assert.assertNotNull( applicationUserService.findByUsername( user_correct.getUsername() ) );
        Assert.assertEquals( applicationUserService.findAll().size(), userNumber + 1 );
    }

    @Test
    public void checkSavingDatasTest()
    {
        int userNumber = applicationUserService.findAll().size();
        ApplicationUser user = new ApplicationUser(  );
        user.setUsername( USERNAME_CORRECT_2 );
        user.setPassword( PASSWORD_CORRENT );


        Assert.assertTrue( applicationUserService.save( user ) );


        ApplicationUserConfidential applicationUserConfidential = applicationUserService.findByUsername( user.getUsername() );
        Assert.assertNotNull( applicationUserConfidential );
        Assert.assertEquals( applicationUserConfidential.getUsername(), user.getUsername() );
        Assert.assertEquals( applicationUserConfidential.getId(), new Long( userNumber + 1 ) );
    }
}
