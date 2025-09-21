package com.java.cricket.match;

import com.java.cricket.entities.Players;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter

public class LiveMatchState {
    private List<Players> team1Batting;
    private List<Players> team2Batting;
    private List<Players> team1Bowling;
    private List<Players> team2Bowling;

    private boolean isFirstInnings = true;

    private int currentBall = 0;
    private int battingIndex = 0;
    private int over = 0;
    private long noOfBalls = 0;

    private long team1Score = 0;
    private long team2Score = 0;

    private long team1NoOf4s = 0;
    private long team1NoOf6s = 0;

    private long team2NoOf4s = 0;
    private long team2NoOf6s = 0;

    private List<String> commentaryList = new ArrayList<>();
}
