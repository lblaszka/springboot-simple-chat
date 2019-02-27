package com.github.lblaszka.springsimplechat.controller;

import com.github.lblaszka.springsimplechat.domain.ChatRoom;
import com.github.lblaszka.springsimplechat.repository.ChatRoomRepository;
import com.github.lblaszka.springsimplechat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.lblaszka.springsimplechat.config.ControllerConstants.CHAT_ROOM_URL;
import static com.github.lblaszka.springsimplechat.config.ControllerConstants.CHAT_ROOM_URL_ID;
import static com.github.lblaszka.springsimplechat.config.ControllerConstants.MAPPING_CHAT_ROOM_URL_ID;

@RestController
@RequestMapping( CHAT_ROOM_URL )
public class ChatRoomController
{

    @Autowired
    ChatRoomService chatRoomService;

    @GetMapping
    public List<ChatRoom> getAll()
    {
        return chatRoomService.getAll();
    }

    @GetMapping( MAPPING_CHAT_ROOM_URL_ID )
    public ResponseEntity<ChatRoom> getById(@PathVariable( CHAT_ROOM_URL_ID ) Long idChatRoom )
    {
        try
        {
            return new ResponseEntity<>( chatRoomService.findById( idChatRoom ), HttpStatus.OK );
        }
        catch ( Exception exc )
        {
            return new ResponseEntity<>( HttpStatus.CONFLICT );
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ChatRoom chatRoom )
    {
        if( chatRoomService.create( chatRoom ) )
            return new ResponseEntity( HttpStatus.OK);
        return new ResponseEntity( HttpStatus.CONFLICT );
    }

}
