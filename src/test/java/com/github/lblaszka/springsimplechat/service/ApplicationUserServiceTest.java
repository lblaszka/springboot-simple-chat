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
    public void saveTest()
    {
        ApplicationUser applicationUser = new ApplicationUser(  );
        Assert.assertFalse( applicationUserService.save( applicationUser ) );

        applicationUser.setUsername( USERNAME_INCORRECT );
        applicationUser.setPassword( PASSWORD_INCORRECT );
        Assert.assertFalse( applicationUserService.save( applicationUser ) );

        applicationUser.setUsername( USERNAME_CORRECT_1 );
        Assert.assertFalse( applicationUserService.save( applicationUser ) );

        applicationUser.setUsername( USERNAME_INCORRECT );
        applicationUser.setPassword( PASSWORD_CORRENT );
        Assert.assertFalse( applicationUserService.save( applicationUser ) );

        applicationUser.setUsername( USERNAME_CORRECT_1 );
        Assert.assertTrue( applicationUserService.save( applicationUser ) );
        Assert.assertFalse( applicationUserService.save( applicationUser ) );

        ApplicationUserConfidential applicationUserConfidential = applicationUserService.findById( 1L );
        Assert.assertNotNull( applicationUserConfidential.getId() );
        Assert.assertEquals( applicationUserConfidential.getId(), new Long(1) );
        Assert.assertEquals( applicationUserConfidential.getUsername(), USERNAME_CORRECT_1 );

    }

    @Test
    public void findByUsernameTest()
    {
        ApplicationUser applicationUser = new ApplicationUser(  );

        applicationUser.setUsername( USERNAME_CORRECT_2 );
        applicationUser.setPassword( PASSWORD_CORRENT );

        Assert.assertTrue( applicationUserService.save( applicationUser ) );

        ApplicationUserConfidential applicationUserConfidential = applicationUserService.findByUsername( applicationUser.getUsername() );

        Assert.assertNotNull( applicationUserConfidential.getId() );
        Assert.assertNotEquals( applicationUserConfidential.getId(), new Long(1) );
        Assert.assertEquals( applicationUser.getUsername(), applicationUserConfidential.getUsername() );
    }

    @Test
    public void findAllTest()
    {
        List<ApplicationUserConfidential> applicationUserConfidentialList = new ArrayList<>(  );
        applicationUserConfidentialList.add( new ApplicationUserConfidential( 1L, USERNAME_CORRECT_1 ) );
        applicationUserConfidentialList.add( new ApplicationUserConfidential( 2L, USERNAME_CORRECT_2 ) );

        List<ApplicationUserConfidential> applicationUserConfidentialListFromRepo;
        applicationUserConfidentialListFromRepo = applicationUserService.findAll();

        Assert.assertEquals( applicationUserConfidentialList.get( 0 ), applicationUserConfidentialListFromRepo.get( 0 ) );
        Assert.assertEquals( applicationUserConfidentialList.get(1), applicationUserConfidentialListFromRepo.get( 1 ) );
    }
}
