//package com.java.cricket.match;
//
//import com.java.cricket.entities.Players;
//import com.java.cricket.players.PlayersRepository;
//import com.java.cricket.team.TeamsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.stream.Collectors;
//
//@Service
//public class MatchService {
//
//    @Autowired
//    private PlayersRepository playerRepository;
//
//    @Autowired
//    private TeamsRepository teamRepository;
//
//    private Map<String, LiveMatchState> liveMatches = new ConcurrentHashMap<>();
//
//    public void initializeMatch(Long team1Id, Long team2Id) {
//        List<Players> team1Players = playerRepository.findByTeamId(team1Id);
//        List<Players> team2Players = playerRepository.findByTeamId(team2Id);
//
//        List<Players> team1Bowlers = team1Players.stream()
//                .filter(p -> p.getSpeciality().equalsIgnoreCase("Bowler") || p.getSpeciality().equalsIgnoreCase("Both"))
//                .collect(Collectors.toList());
//
//        List<Players> team2Bowlers = team2Players.stream()
//                .filter(p -> p.getSpeciality().equalsIgnoreCase("Bowler") || p.getSpeciality().equalsIgnoreCase("Both"))
//                .collect(Collectors.toList());
//
//        LiveMatchState state = new LiveMatchState();
//        state.setTeam1Batting(team1Players);
//        state.setTeam1Bowling(team2Bowlers);
//        state.setTeam2Batting(team2Players);
//        state.setTeam2Bowling(team1Bowlers);
//        liveMatches.put(team1Id + "-" + team2Id, state);
//    }
//
//    public MatchDTO simulateNextBall(Long team1Id, Long team2Id) {
//        String matchKey = team1Id + "-" + team2Id;
//        LiveMatchState state = liveMatches.get(matchKey);
//
//        MatchDTO dto = new MatchDTO();
//        if (state == null) {
//            dto.setPlayersMesage("Match not initialized");
//            return dto;
//        }
//
//        // End condition
//        boolean isFirstInningsOver = state.getCurrentBall() >= 120 || state.getBattingIndex() >= state.getTeam1Batting().size();
//        boolean isSecondInningsOver = !state.isFirstInnings() && (state.getCurrentBall() >= 120 || state.getBattingIndex() >= state.getTeam2Batting().size());
//
//        if (isSecondInningsOver) {
//            dto.setPlayersMesage("Match Completed");
//
//            dto.setTeam1Score(state.getTeam1Score());
//            dto.setTeam2Score(state.getTeam2Score());
//
//            dto.setTeam1NoOf4s(state.getTeam1NoOf4s());
//            dto.setTeam1NoOf6s(state.getTeam1NoOf6s());
//            dto.setTeam2NoOf4s(state.getTeam2NoOf4s());
//            dto.setTeam2NoOf6s(state.getTeam2NoOf6s());
//
//            dto.setTeam1TotalNoofBalls(120L);  // hardcoded for now
//            dto.setTeam2TotalNoofBalls(state.getNoOfBalls());
//
//            String winnerMessage;
//            if (state.getTeam1Score() > state.getTeam2Score()) {
//                winnerMessage = "Team 1 won by " + (state.getTeam1Score() - state.getTeam2Score()) + " runs.";
//            } else if (state.getTeam2Score() > state.getTeam1Score()) {
//                winnerMessage = "Team 2 won by " + (state.getTeam2Batting().size() - state.getBattingIndex()) + " wickets.";
//            } else {
//                winnerMessage = "Match Tied.";
//            }
//
//            dto.setPlayersMesage("Match Completed. " + winnerMessage);
//            return dto;
//        }
//
//        // Play ball
//        Random random = new Random();
//        int score = random.nextInt(10);
//        String message;
//
//        if (state.isFirstInnings()) {
//            // Team 1 batting
//            if (score <= 6) {
//                state.setTeam1Score(state.getTeam1Score() + score);
//                if (score == 4) state.setTeam1NoOf4s(state.getTeam1NoOf4s() + 1);
//                if (score == 6) state.setTeam1NoOf6s(state.getTeam1NoOf6s() + 1);
//                message = "Ball " + (state.getCurrentBall() + 1) + ": Team 1 scored " + score;
//            } else {
//                state.setBattingIndex(state.getBattingIndex() + 1);
//                message = "Ball " + (state.getCurrentBall() + 1) + ": Team 1 player OUT!";
//            }
//        } else {
//            // Team 2 batting
//            if (score <= 6) {
//                state.setTeam2Score(state.getTeam2Score() + score);
//                if (score == 4) state.setTeam2NoOf4s(state.getTeam2NoOf4s() + 1);
//                if (score == 6) state.setTeam2NoOf6s(state.getTeam2NoOf6s() + 1);
//                message = "Ball " + (state.getCurrentBall() + 1) + ": Team 2 scored " + score;
//            } else {
//                state.setBattingIndex(state.getBattingIndex() + 1);
//                message = "Ball " + (state.getCurrentBall() + 1) + ": Team 2 player OUT!";
//            }
//        }
//
//        state.setCurrentBall(state.getCurrentBall() + 1);
//        state.setNoOfBalls(state.getNoOfBalls() + 1);
//
//        // Switch innings if first innings is over
//        if (state.isFirstInnings() && isFirstInningsOver) {
//            state.setFirstInnings(false);
//            state.setCurrentBall(0);
//            state.setBattingIndex(0);
//            state.setNoOfBalls(0);
//            state.setOver(0);
//            message += " -- End of innings. Switching to Team 2 batting.";
//        }
//
//        dto.setPlayersMesage(message);
//        dto.setTeam1Score(state.getTeam1Score());
//        dto.setTeam2Score(state.getTeam2Score());
//        dto.setTeam1NoOf4s(state.getTeam1NoOf4s());
//        dto.setTeam1NoOf6s(state.getTeam1NoOf6s());
//        dto.setTeam2NoOf4s(state.getTeam2NoOf4s());
//        dto.setTeam2NoOf6s(state.getTeam2NoOf6s());
//        dto.setTeam1TotalNoofBalls(120L);
//        dto.setTeam2TotalNoofBalls(state.getNoOfBalls());
//
//        return dto;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package com.java.cricket.match;
////
////import com.java.cricket.entities.Players;
////import com.java.cricket.players.PlayersRepository;
////import com.java.cricket.team.TeamsRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.*;
////import java.util.concurrent.ConcurrentHashMap;
////import java.util.stream.Collectors;
////
////@Service
////public class MatchService {
////
////    @Autowired
////    private PlayersRepository playerRepository;
////
////    @Autowired
////    private TeamsRepository teamRepository;
////
////    private Map<String, LiveMatchState> liveMatches = new ConcurrentHashMap<>();
////
////    public void initializeMatch(Long team1Id, Long team2Id) {
////        List<Players> batting = playerRepository.findByTeamId(team1Id);
////        List<Players> bowlingRaw = playerRepository.findByTeamId(team2Id);
////
////        List<Players> bowlers = bowlingRaw.stream()
////                .filter(p -> p.getSpeciality().equalsIgnoreCase("Bowler") || p.getSpeciality().equalsIgnoreCase("Both"))
////                .collect(Collectors.toList());
////
////        LiveMatchState matchState = new LiveMatchState();
////        matchState.setBattingList(batting);
////        matchState.setBowlingList(bowlers);
////
////        String matchKey = team1Id + "-" + team2Id;
////        liveMatches.put(matchKey, matchState);
////    }
////
////    public MatchDTO simulateNextBall(Long team1Id, Long team2Id) {
////        String matchKey = team1Id + "-" + team2Id;
////        LiveMatchState state = liveMatches.get(matchKey);
////
////        MatchDTO dto = new MatchDTO();
////
////        if (state == null) {
////            dto.setPlayersMesage("Match Not Initialized");
////            return dto;
////        }
////
////        if (state.getCurrentBall() >= 120 || state.getBattingIndex() >= state.getBattingList().size()) {
////            dto.setTeam1Score(state.getTeamScore());
////            dto.setTeam1NoOf4s(state.getNoOf4s());
////            dto.setTeam1NoOf6s(state.getNoOf6s());
////            dto.setTeam1TotalNoofBalls(state.getNoOfBalls());
////            dto.setPlayersMesage("Match Completed");
////            return dto;
////        }
////
////        Random random = new Random();
////        int score = random.nextInt(10);
////        String message;
////
////        if (score <= 6) {
////            state.setTeamScore(state.getTeamScore() + score);
////            if (score == 4) state.setNoOf4s(state.getNoOf4s() + 1);
////            if (score == 6) state.setNoOf6s(state.getNoOf6s() + 1);
////            message = "Ball " + (state.getCurrentBall() + 1) + ": Scored " + score + " run(s).";
////        } else {
////            message = "Ball " + (state.getCurrentBall() + 1) + ": Player got OUT!";
////            state.setBattingIndex(state.getBattingIndex() + 1);
////        }
////
////        state.setCurrentBall(state.getCurrentBall() + 1);
////        state.setNoOfBalls(state.getNoOfBalls() + 1);
////
////        if (state.getCurrentBall() % 6 == 0) {
////            state.setOver(state.getOver() + 1);
////            message += " -- End of over " + state.getOver();
////        }
////
////        state.getCommentaryList().add(message);
////
////        dto.setPlayersMesage((state.getCurrentBall() >= 120 || state.getBattingIndex() >= state.getBattingList().size())
////                ? "Match Completed"
////                : message);
////
////        dto.setTeam1Score(state.getTeamScore());
////        dto.setTeam1NoOf4s(state.getNoOf4s());
////        dto.setTeam1NoOf6s(state.getNoOf6s());
////        dto.setTeam1TotalNoofBalls(state.getNoOfBalls());
////
////        return dto;
////    }
////}
////
////
////
////
////
////
////
////
////
////
////
//////package com.java.cricket.match;
//////
//////import java.util.ArrayList;
//////import java.util.List;
//////import java.util.Optional;
//////import java.util.Random;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.stereotype.Service;
//////
//////import com.java.cricket.entities.Match;
//////import com.java.cricket.entities.Players;
//////import com.java.cricket.entities.Teams;
//////import com.java.cricket.players.PlayersRepository;
//////import com.java.cricket.team.TeamsRepository;
//////
//////@Service
//////public class MatchService {
//////
//////@Autowired private MatchRepository matchRepository;
//////	
//////	@Autowired private PlayersRepository playerRepository;
//////	
//////	@Autowired private TeamsRepository teamRepository;
//////	
//////	public MatchDTO playMatch(MatchDTO dto) {
//////		// TODO Auto-generated method stub
//////		MatchDTO bowlingTeam = null;
//////		MatchDTO battingTeam = startMatch(dto.getTeam1Id(), dto.getTeam2Id());
//////		System.err.println(" team 2 check :: "+battingTeam);
//////		if(battingTeam != null) {
//////			 bowlingTeam = startMatch(dto.getTeam2Id(), dto.getTeam1Id());
//////		}
//////		
//////		if(battingTeam.getPlayersMesage() == null) {
//////			Match m = new Match();
//////			m.setTeam1_id(dto.getTeam1Id());
//////			m.setTeam1Score(battingTeam.getTeam1Score());
//////			m.setTeam1Noof4s(battingTeam.getTeam1NoOf4s());
//////			m.setTeam1Noof6s(battingTeam.getTeam1NoOf6s());
//////			m.setTeam1TotalNoofBalls(battingTeam.getTeam1TotalNoofBalls());
//////			
//////			m.setTeam2_id(dto.getTeam2Id());
//////			m.setTeam2Score(bowlingTeam.getTeam1Score() );
//////			m.setTeam2Noof4s(bowlingTeam.getTeam1NoOf4s());
//////			m.setTeam2Noof6s(bowlingTeam.getTeam1NoOf6s());
//////			m.setTeam2TotalNoofBalls(bowlingTeam.getTeam1TotalNoofBalls());
//////			matchRepository.save(m);
//////		}
//////		
////////		dto.setTeam1Score(battingTeam.getTeam1Score());
////////		dto.setTeam2Score(bowlingTeam.getTeam2Score());
////////		Optional<Teams> team1Opt = teamRepository.findById(dto.getTeam1Id());
////////		if (team1Opt.isPresent()) {
////////		    Teams team1 = team1Opt.get();
////////		    dto.setTeam1Name(team1.getTeamCountry());
////////		}
////////
////////		Optional<Teams> team2Opt = teamRepository.findById(dto.getTeam2Id());
////////		if (team2Opt.isPresent()) {
////////		    Teams team2 = team2Opt.get();
////////		    dto.setTeam2Name(team2.getTeamCountry());
////////		}
//////		
//////		
//////		dto.setTeam1Score(battingTeam.getTeam1Score());
//////		dto.setTeam1NoOf4s(battingTeam.getTeam1NoOf4s());
//////		dto.setTeam1NoOf6s(battingTeam.getTeam1NoOf6s());
//////		dto.setTeam1TotalNoofBalls(battingTeam.getTeam1TotalNoofBalls());
//////		
//////		
//////		dto.setTeam2Score(bowlingTeam.getTeam1Score());
//////		dto.setTeam2NoOf4s(bowlingTeam.getTeam1NoOf4s());
//////		dto.setTeam2NoOf6s(bowlingTeam.getTeam1NoOf6s());
//////		dto.setTeam2TotalNoofBalls(bowlingTeam.getTeam1TotalNoofBalls());
//////		dto.setPlayersMesage(battingTeam.getPlayersMesage());
//////
//////		// Set team names
//////		Teams team1 = teamRepository.findById(dto.getTeam1Id()).orElse(null);
//////		if (team1 != null) {
//////		    dto.setTeam1Name(team1.getTeamCountry());
//////		}
//////
//////		Teams team2 = teamRepository.findById(dto.getTeam2Id()).orElse(null);
//////		if (team2 != null) {
//////		    dto.setTeam2Name(team2.getTeamCountry());
//////		}
//////
////////		return dto;
//////		return dto;
//////	}
//////
//////	
//////public MatchDTO startMatch(Long batting , Long bowling)  {
//////		
//////			List<Players> pBatting = playerRepository.findByTeamId(batting);
//////			
//////			
//////			List<Players> pBowling = playerRepository.findByTeamId(bowling);
//////		
//////			if(pBatting.isEmpty() || pBowling.isEmpty()) {
//////			System.out.println("the players not there startmatch");
//////				MatchDTO matchDone = new MatchDTO();
//////				matchDone.setPlayersMesage("No");
//////				return matchDone;
//////			}
//////			
//////			List<Players> battingList = new ArrayList<Players>();
//////			List<Players> bowlingList = new ArrayList<Players>();
//////			 
//////			
//////			for(Players playerDetails : pBatting ) {
//////				Players player = new Players();
//////				player.setId(playerDetails.getId());
//////				player.setPlayerName(playerDetails.getPlayerName());
//////				player.setSpeciality(playerDetails.getSpeciality());
//////				player.setTeamId(playerDetails.getTeamId());
//////				
//////				battingList.add(player);
//////			}
////////			
//////			
//////			
//////			for(Players playerDetails : pBowling ) {
//////				if((playerDetails.getSpeciality().equalsIgnoreCase("both") || 
//////						playerDetails.getSpeciality().equalsIgnoreCase("Bowler"))) {
//////				Players player = new Players();
//////				player.setId(playerDetails.getId());
//////				player.setPlayerName(playerDetails.getPlayerName());
//////				player.setSpeciality(playerDetails.getSpeciality());
//////				player.setTeamId(playerDetails.getTeamId());
//////				
//////				bowlingList.add(player);
//////				}
//////				
//////				
//////				
//////			}
//////					
//////			MatchDTO matchDone = null;
//////			try {
//////				matchDone = batting(battingList,bowlingList);
//////			} catch (InterruptedException e) {
//////				// TODO Auto-generated catch block
//////				e.printStackTrace();
//////			}
//////
//////			
//////			return matchDone;
//////		
//////	}
//////	
//////	
//////	public MatchDTO batting(List<Players> batting, List<Players> bowler) throws InterruptedException {
//////		
//////		MatchDTO matchDTO = new MatchDTO();
//////		
//////		
//////		int over = 0;
//////		Random random = new Random();
//////		int bowlerIndex = 0;
//////		int battingIndex = 0;
//////		Long teamScore = 0L;
//////		int player_score = 0;
//////		long noOf4s = 0;
//////		long noOf6s = 0;
//////		long noOfBalls = 0;
//////				
//////		for(int i = 1 ;i <= 120 && battingIndex < batting.size(); i++) {
////////				Thread.sleep(1000);
//////			noOfBalls++;
//////			
//////			
//////			if(i%6 == 0) {
//////				over++;
//////
//////				int bowlerVal = over % bowler.size();
//////			}
//////			System.out.println("batting team : "+ batting.get(0).getTeamId()+"batting.size() :  "+batting.size());
//////					int score = random.nextInt(10);
////////					System.out.println("score value : "+score);
//////					if(score <= 6 ) {
//////						
//////						if(score == 4) {
//////							noOf4s++;
//////						}
//////						if(score ==6) {
//////							noOf6s++;
//////						}
//////						
//////						player_score += score;
////////						Match match = new Match();
////////						match
//////						
//////						Players p = new Players();
//////						
//////						teamScore +=score;
//////					}
//////					
//////					if(score == 9) {
//////
//////						battingIndex++;
//////						
////////						System.err.println("batting index : "+battingIndex);
//////						player_score = 0;
//////					}
//////					
//////				}
//////		
//////		matchDTO.setTeam1NoOf4s(noOf4s);
//////		matchDTO.setTeam1NoOf6s(noOf6s);
//////		matchDTO.setTeam1TotalNoofBalls(noOfBalls);
//////		matchDTO.setTeam1Score(teamScore);
//////		
//////		
////////		System.err.println("Team Score : "+teamScore);
//////		
//////		return matchDTO;
//////		
//////		}
//////
//////
//////}














package com.java.cricket.match;

import com.java.cricket.entities.Players;
import com.java.cricket.players.PlayersRepository;
import com.java.cricket.team.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private PlayersRepository playerRepository;

    @Autowired
    private TeamsRepository teamRepository;

    // Stores ongoing matches by team1Id-team2Id
    private Map<String, LiveMatchState> liveMatches = new ConcurrentHashMap<>();

    public void initializeMatch(Long team1Id, Long team2Id) {
        List<Players> team1Players = playerRepository.findByTeamId(team1Id);
        List<Players> team2Players = playerRepository.findByTeamId(team2Id);

        List<Players> team1Bowlers = team1Players.stream()
                .filter(p -> p.getSpeciality().equalsIgnoreCase("Bowler") || p.getSpeciality().equalsIgnoreCase("Both"))
                .collect(Collectors.toList());

        List<Players> team2Bowlers = team2Players.stream()
                .filter(p -> p.getSpeciality().equalsIgnoreCase("Bowler") || p.getSpeciality().equalsIgnoreCase("Both"))
                .collect(Collectors.toList());

        LiveMatchState state = new LiveMatchState();
        state.setTeam1Batting(team1Players);
        state.setTeam1Bowling(team2Bowlers);
        state.setTeam2Batting(team2Players);
        state.setTeam2Bowling(team1Bowlers);

        state.setFirstInnings(true);
        state.setCurrentBall(0);

        liveMatches.put(team1Id + "-" + team2Id, state);
    }

    public MatchDTO simulateNextBall(Long team1Id, Long team2Id) {
        String matchKey = team1Id + "-" + team2Id;
        LiveMatchState state = liveMatches.get(matchKey);

        MatchDTO dto = new MatchDTO();
        if (state == null) {
            dto.setPlayersMesage("Match not initialized");
            return dto;
        }

        if (state.isFirstInnings()) {
            if (state.getCurrentBall() < 120) {
                int score = simulateBallScore();
                updateTeam1Score(state, score);
                state.setCurrentBall(state.getCurrentBall() + 1);

                dto.setPlayersMesage("Team 1 - Ball " + state.getCurrentBall() + ": Scored " + score + " run(s).");
            }

            if (state.getCurrentBall() == 120) {
                state.setFirstInnings(false);
                state.setCurrentBall(0); // Reset for team 2
                dto.setPlayersMesage("Team 1 innings complete. Score: " + state.getTeam1Score());
            }
        } else {
            if (state.getCurrentBall() < 120) {
                int score = simulateBallScore();
                updateTeam2Score(state, score);
                state.setCurrentBall(state.getCurrentBall() + 1);

                dto.setPlayersMesage("Team 2 - Ball " + state.getCurrentBall() + ": Scored " + score + " run(s).");
            }

            if (state.getCurrentBall() == 120) {
                // Match completed
                dto.setTeam1Score(state.getTeam1Score());
                dto.setTeam2Score(state.getTeam2Score());

                dto.setTeam1NoOf4s(state.getTeam1NoOf4s());
                dto.setTeam1NoOf6s(state.getTeam1NoOf6s());
                dto.setTeam2NoOf4s(state.getTeam2NoOf4s());
                dto.setTeam2NoOf6s(state.getTeam2NoOf6s());

                dto.setTeam1TotalNoofBalls(120L);
                dto.setTeam2TotalNoofBalls(120L);

                String result;
                if (state.getTeam1Score() > state.getTeam2Score()) {
                    result = "Team 1 won by " + (state.getTeam1Score() - state.getTeam2Score()) + " runs.";
                } else if (state.getTeam2Score() > state.getTeam1Score()) {
                    result = "Team 2 won by " + (state.getTeam2Score() - state.getTeam1Score()) + " runs.";
                } else {
                    result = "Match tied.";
                }

                dto.setPlayersMesage("Match Completed. " + result);
            }
        }

        // Update live score in DTO regardless of state
        dto.setTeam1Score(state.getTeam1Score());
        dto.setTeam2Score(state.getTeam2Score());
        dto.setTeam1NoOf4s(state.getTeam1NoOf4s());
        dto.setTeam1NoOf6s(state.getTeam1NoOf6s());
        dto.setTeam2NoOf4s(state.getTeam2NoOf4s());
        dto.setTeam2NoOf6s(state.getTeam2NoOf6s());
        dto.setTeam1TotalNoofBalls(120L);
        dto.setTeam2TotalNoofBalls(state.isFirstInnings() ? 0L : state.getCurrentBall());

        return dto;
    }

    private int simulateBallScore() {
        Random random = new Random();
        return random.nextInt(7); // 0 to 6 runs
    }

    private void updateTeam1Score(LiveMatchState state, int score) {
        state.setTeam1Score(state.getTeam1Score() + score);
        if (score == 4) state.setTeam1NoOf4s(state.getTeam1NoOf4s() + 1);
        if (score == 6) state.setTeam1NoOf6s(state.getTeam1NoOf6s() + 1);
    }

    private void updateTeam2Score(LiveMatchState state, int score) {
        state.setTeam2Score(state.getTeam2Score() + score);
        if (score == 4) state.setTeam2NoOf4s(state.getTeam2NoOf4s() + 1);
        if (score == 6) state.setTeam2NoOf6s(state.getTeam2NoOf6s() + 1);
    }
}




