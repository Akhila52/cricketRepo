package com.java.cricket.match;

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

}
