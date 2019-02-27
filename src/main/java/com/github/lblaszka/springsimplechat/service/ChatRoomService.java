package com.github.lblaszka.springsimplechat.service;

import com.github.lblaszka.springsimplechat.domain.ChatRoom;
import com.github.lblaszka.springsimplechat.exception.ChatRoomException;
import com.github.lblaszka.springsimplechat.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService
{
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository)
    {
        this.chatRoomRepository = chatRoomRepository;
    }

    public List<ChatRoom> getAll()
    {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findById( Long idChatRoom ) throws ChatRoomException
    {
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById( idChatRoom );
        if( chatRoomOptional.isPresent() == false )
            throw new ChatRoomException();

        return chatRoomOptional.get();
    }

    public boolean create( ChatRoom chatRoom )
    {
        if( chatRoom.getLabel() == null || chatRoom.getLabel().length() < 3 )
            return false;

        chatRoomRepository.save( chatRoom );
        return true;
    }
}
