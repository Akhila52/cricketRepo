package com.javaEx.cricketprog.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaEx.cricketprog.entities.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {


}
