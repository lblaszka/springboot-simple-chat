package com.github.lblaszka.springsimplechat.service;

import com.github.lblaszka.springsimplechat.domain.Participation;
import com.github.lblaszka.springsimplechat.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService
{
    ParticipationRepository participationRepository;
    ApplicationUserService applicationUserService;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository, ApplicationUserService applicationUserService)
    {
        this.participationRepository = participationRepository;
        this.applicationUserService = applicationUserService;
    }

    public List<Participation> getAllFromChatRoom(Long idChatRoom )
    {
        return participationRepository.findByIdChatRoom( idChatRoom );
    }

    public boolean isBelongs( Long idChatRoom )
    {
        Long idUser = applicationUserService.getCurrentUser().getId();

        List<Participation> participationList = participationRepository.findByIdChatRoom( idChatRoom );

        for( Participation participation : participationList )
        {
            if( participation.getIdUser() == idUser )
                return true;
        }

        return false;
    }

    public boolean submit( Long idChatRoom )
    {
        Long idUser = applicationUserService.getCurrentUser().getId();

        if( this.isBelongs( idChatRoom ) )
            return false;

        Participation participation = new Participation();
        participation.setIdChatRoom( idChatRoom );
        participation.setIdUser( idUser );

        participationRepository.save( participation );
        return true;
    }

    public boolean remove( Long idChatRoom )
    {
        Long idUser = applicationUserService.getCurrentUser().getId();

        List<Participation> participationList = participationRepository.findByIdChatRoom( idChatRoom );

        for( Participation participation : participationList )
        {
            if( participation.getIdUser() == idUser )
            {
                participationRepository.delete( participation );
                return true;
            }
        }

        return false;

    }

}
