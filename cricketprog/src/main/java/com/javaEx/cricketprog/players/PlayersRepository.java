package com.javaEx.cricketprog.players;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaEx.cricketprog.entities.Players;

public interface PlayersRepository extends JpaRepository<Players, Long> {

	
	Optional<Players> findById(Long batting);

	List<Players> findByTeamId(Long batting);

}
