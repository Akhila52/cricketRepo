package com.javaEx.cricketprog.team;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaEx.cricketprog.entities.Teams;


@Repository
public interface TeamsRepository extends JpaRepository<Teams, Integer> {

	Optional<Teams> findById(Long team1Id);
	
	
	
	
}
