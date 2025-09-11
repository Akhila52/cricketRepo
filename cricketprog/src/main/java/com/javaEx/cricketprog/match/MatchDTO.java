package com.javaEx.cricketprog.match;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MatchDTO {
    private Long id;
    private Long team1Id;
    private Long team2Id;
    private Long team1Score;
    private Long team2Score;
    private String team1Name;
    private String team2Name;

//    public MatchResultDTO() {}
//    public MatchResultDTO(Integer id, Integer team1Id, Integer team2Id, Integer team1Score, Integer team2Score) {
//        this.id = id;
//        this.team1Id = team1Id;
//        this.team2Id = team2Id;
//        this.team1Score = team1Score;
//        this.team2Score = team2Score;
//    }
}
