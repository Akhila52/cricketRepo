package com.java.cricket.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.java.cricket.entities.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {


}
