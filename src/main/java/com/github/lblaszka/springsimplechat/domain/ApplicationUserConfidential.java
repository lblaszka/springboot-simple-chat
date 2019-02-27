package com.github.lblaszka.springsimplechat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUserConfidential
{
    Long id;
    String username;

    public ApplicationUserConfidential(ApplicationUser applicationUser )
    {
        this.id = applicationUser.getId();
        this.username = applicationUser.getUsername();
    }
}
