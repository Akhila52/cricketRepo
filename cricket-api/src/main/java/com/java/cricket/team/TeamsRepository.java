package com.java.cricket.team;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.cricket.entities.Teams;


@Repository
public interface TeamsRepository extends JpaRepository<Teams, Long> {

	Optional<Teams> findById(Long team1Id);
	
	
	
	
}
