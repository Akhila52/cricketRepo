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
    private Long team1TotalNoofBalls;
    private Long team2TotalNoofBalls;
    private Long team1NoOf6s;
    private Long team1NoOf4s;
    private Long team2NoOf6s;
    private Long team2NoOf4s;
    private boolean playersExist=false;
    private String playersMesage;
}
