package com.github.lblaszka.springsimplechat.repository;

import com.github.lblaszka.springsimplechat.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository< Participation, Long >
{
    List<Participation> findByIdChatRoom(Long idChatRoom );
}
