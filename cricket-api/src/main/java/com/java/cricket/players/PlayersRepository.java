package com.java.cricket.players;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.cricket.entities.Players;
@Repository
public interface PlayersRepository extends JpaRepository<Players, Long> {

	
	Optional<Players> findById(Long batting);

	List<Players> findByTeamId(Long batting);

}
