package com.github.lblaszka.springsimplechat.service;

import com.github.lblaszka.springsimplechat.domain.Message;
import com.github.lblaszka.springsimplechat.exception.MessageException;
import com.github.lblaszka.springsimplechat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService
{
    private MessageRepository messageRepository;
    private ApplicationUserService applicationUserService;
    private ParticipationService participationService;

    @Autowired
    public MessageService(MessageRepository messageRepository, ApplicationUserService applicationUserService, ParticipationService participationService)
    {
        this.messageRepository = messageRepository;
        this.applicationUserService = applicationUserService;
        this.participationService = participationService;
    }


    public List<Message> getAll( Long idChatRoom ) throws MessageException
    {
        if( participationService.isBelongs( idChatRoom ) == false )
            throw new MessageException();

        return messageRepository.findAll();
    }

    public List<Message> findByIdChatRoom( Long idChatRoom ) throws MessageException
    {
        if( participationService.isBelongs( idChatRoom ) == false )
            throw new MessageException();

        return messageRepository.findByIdChatRoom( idChatRoom );
    }


    public boolean add(Long idChatRoom, Message message)
    {
        if( participationService.isBelongs(idChatRoom) == false )
            return false;
        if( message == null )
            return false;
        if( message.getValue() == null || message.getValue().length() < 1 )
            return false;

        Long userId = applicationUserService.getCurrentUser().getId();

        message.setIdChatRoom( idChatRoom );
        message.setIdUser( userId );
        message.setDateTime( LocalDateTime.now() );
        messageRepository.save( message );
        return true;
    }


    public Message getById( Long idMessage ) throws MessageException
    {
        Optional<Message> message = messageRepository.findById( idMessage );

        if( message.isPresent() == false )
            throw new MessageException();

        Long idChatRoom = message.get().getIdChatRoom();

        if( participationService.isBelongs( idChatRoom ) == false )
            throw  new MessageException();

        return message.get();
    }
}
