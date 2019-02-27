package com.github.lblaszka.springsimplechat.service;

import com.github.lblaszka.springsimplechat.domain.ApplicationUser;
import com.github.lblaszka.springsimplechat.domain.ApplicationUserConfidential;
import com.github.lblaszka.springsimplechat.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserService
{
    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public List<ApplicationUserConfidential> findAll()
    {
        List<ApplicationUser> userList = applicationUserRepository.findAll();
        List<ApplicationUserConfidential> applicationUserConfidentialList = new LinkedList<>();

        for( ApplicationUser applicationUser : userList )
        {
            applicationUserConfidentialList.add( new ApplicationUserConfidential( applicationUser ) );
        }

        return applicationUserConfidentialList;
    }

    public ApplicationUserConfidential findById(Long id )
    {
        Optional<ApplicationUser> user = applicationUserRepository.findById( id );
        return new ApplicationUserConfidential( user.get() );
    }

    public ApplicationUserConfidential findByUsername( String username )
    {
        ApplicationUser user =  applicationUserRepository.findByUsername( username );
        return new ApplicationUserConfidential( user );
    }

    public ApplicationUser getCurrentUser()
    {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername
            (
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
            );
        return applicationUser;
    }

    public ApplicationUserConfidential getCurrentUserCofidential()
    {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername
                (
                        (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                );
        return new ApplicationUserConfidential( applicationUser );
    }

    public boolean save( ApplicationUser user )
    {

        if( user.getUsername() == null || user.getUsername().length() < 3 )
            return false;

        if( applicationUserRepository.findByUsername(user.getUsername() ) != null )
            return false;

        if( user.getPassword() == null || user.getPassword().length() < 6 )
            return false;

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save( user );
        return true;
    }

    private void hiddenPassword( ApplicationUser user )
    {
        user.setPassword(null);
    }

    public ApplicationUser getWithHiddenConfidentialData( ApplicationUser applicationUserWithConfidentialData )
    {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setId( applicationUserWithConfidentialData.getId() );
        applicationUser.setUsername( applicationUserWithConfidentialData.getUsername() );
        applicationUser.setLogin( null );
        applicationUser.setPassword( null );

        return applicationUser;
    }
}
