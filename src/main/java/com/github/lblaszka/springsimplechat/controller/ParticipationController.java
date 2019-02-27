package com.github.lblaszka.springsimplechat.controller;

import com.github.lblaszka.springsimplechat.domain.Participation;
import com.github.lblaszka.springsimplechat.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.lblaszka.springsimplechat.config.ControllerConstants.CHAT_ROOM_URL_ID;
import static com.github.lblaszka.springsimplechat.config.ControllerConstants.PARTICIPATION_URL;

@RestController
@RequestMapping( PARTICIPATION_URL )
public class ParticipationController
{
    @Autowired
    ParticipationService participationService;

    @GetMapping
    public ResponseEntity<List<Participation>> getAll( @PathVariable(CHAT_ROOM_URL_ID) Long idChatRoom )
    {
        if( participationService.isBelongs( idChatRoom ) )
            return new ResponseEntity<>( participationService.getAllFromChatRoom( idChatRoom ), HttpStatus.OK );
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity submit(@PathVariable(CHAT_ROOM_URL_ID) Long idChatRoom )
    {
        if( participationService.submit( idChatRoom ) )
            return new ResponseEntity( HttpStatus.OK );
        return new ResponseEntity( HttpStatus.CONFLICT );
    }

    @DeleteMapping
    public ResponseEntity remove(@PathVariable(CHAT_ROOM_URL_ID) Long idChatRoom )
    {
        if( participationService.remove( idChatRoom ) )
            return new ResponseEntity( HttpStatus.OK );
        return new ResponseEntity( HttpStatus.CONFLICT );
    }
}
