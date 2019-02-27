package com.github.lblaszka.springsimplechat.repository;

import com.github.lblaszka.springsimplechat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>
{

}
