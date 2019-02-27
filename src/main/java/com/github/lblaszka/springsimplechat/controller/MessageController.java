package com.github.lblaszka.springsimplechat.controller;

import com.github.lblaszka.springsimplechat.domain.Message;
import com.github.lblaszka.springsimplechat.exception.MessageException;
import com.github.lblaszka.springsimplechat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.lblaszka.springsimplechat.config.ControllerConstants.*;

@RestController
@RequestMapping( MESSAGE_URL )
public class MessageController
{

    @Autowired
    MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getAll(@PathVariable(CHAT_ROOM_URL_ID) Long idChatRoom )
    {
        try
        {
            return new ResponseEntity<>( messageService.findByIdChatRoom( idChatRoom ), HttpStatus.OK );
        }
        catch ( Exception exc )
        {
            return new ResponseEntity<>( HttpStatus.CONFLICT );
        }
    }

    @GetMapping(MAPPING_MESSAGE_URL_ID)
    public ResponseEntity<Message> getById( @PathVariable(MESSAGE_URL_ID) Long idMessage )
    {
        try
        {
            return new ResponseEntity<>( messageService.getById( idMessage ), HttpStatus.OK );
        }
        catch ( Exception exc )
        {
            return new ResponseEntity<>( HttpStatus.CONFLICT );
        }
    }

    @PostMapping
    public ResponseEntity add(@PathVariable(CHAT_ROOM_URL_ID) Long idChatRoom, @RequestBody Message message )
    {
        if( messageService.add( idChatRoom, message ) )
            return new ResponseEntity( HttpStatus.OK );
        return new ResponseEntity( HttpStatus.CONFLICT );
    }
}
