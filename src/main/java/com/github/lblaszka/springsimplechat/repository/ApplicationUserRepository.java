package com.github.lblaszka.springsimplechat.repository;

import com.github.lblaszka.springsimplechat.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>
{
    ApplicationUser findByUsername(String username);
}
