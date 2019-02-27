package com.github.lblaszka.springsimplechat.repository;

import com.github.lblaszka.springsimplechat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>
{
    List<Message> findByIdChatRoom(Long idChatRoom );
}
