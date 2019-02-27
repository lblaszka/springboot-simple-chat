package com.github.lblaszka.springsimplechat.controller;

import com.github.lblaszka.springsimplechat.service.ApplicationUserService;
import com.github.lblaszka.springsimplechat.domain.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class ApplicationUserController
{

    @Autowired
    ApplicationUserService applicationUserService;

    @PostMapping("sign-up")
    public ResponseEntity signUp(@RequestBody ApplicationUser applicationUser )
    {
        if( applicationUserService.save( applicationUser ) )
            return new ResponseEntity( HttpStatus.OK );
        return new ResponseEntity( HttpStatus.CONFLICT );
    }

    @GetMapping("who-i-am")
    public ApplicationUser whoIAm()
    {
        ApplicationUser applicationUser = applicationUserService.getCurrentUser();
        applicationUser.setPassword( null );
        return applicationUser;
    }
}
