package com.java.cricket.match;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.cricket.entities.Match;
import com.java.cricket.entities.Players;
import com.java.cricket.entities.Teams;
import com.java.cricket.players.PlayersRepository;
import com.java.cricket.team.TeamsRepository;

@Service
public class MatchService {

	@Autowired private MatchRepository matchRepository;
	
	@Autowired private PlayersRepository playerRepository;
	
	@Autowired private TeamsRepository teamRepository;
	
	public MatchDTO playMatch(MatchDTO dto) {
		// TODO Auto-generated method stub
		Long bowlingTeam = 0L;
		Long battingTeam = startMatch(dto.getTeam1Id(), dto.getTeam2Id());
		if(battingTeam != 0) {
			 bowlingTeam = startMatch(dto.getTeam2Id(), dto.getTeam1Id());
		}
		
		if(battingTeam==2 || bowlingTeam==2) {
			return null;
		}
		Match m = new Match();
		m.setTeam1_id(dto.getTeam1Id());
		m.setTeam1Score(battingTeam);
		m.setTeam2_id(dto.getTeam2Id());
		m.setTeam2Score(bowlingTeam);
		matchRepository.save(m);
		
		dto.setTeam1Score(battingTeam);
		dto.setTeam2Score(bowlingTeam);
		Optional<Teams> team1Opt = teamRepository.findById(dto.getTeam1Id());
		if (team1Opt.isPresent()) {
		    Teams team1 = team1Opt.get();
		    dto.setTeam1Name(team1.getTeamCountry());
		}

		Optional<Teams> team2Opt = teamRepository.findById(dto.getTeam2Id());
		if (team2Opt.isPresent()) {
		    Teams team2 = team2Opt.get();
		    dto.setTeam2Name(team2.getTeamCountry());
		}

		
		
		return dto;
	}

	
public Long startMatch(Long batting , Long bowling)  {
		
			List<Players> pBatting = playerRepository.findByTeamId(batting);
			
			
			List<Players> pBowling = playerRepository.findByTeamId(bowling);
		
			
			if(pBatting==null || pBowling==null) {
				return 2l;
			}
			List<Players> battingList = new ArrayList<Players>();
			List<Players> bowlingList = new ArrayList<Players>();
			
			
			for(Players playerDetails : pBatting ) {
				Players player = new Players();
				player.setId(playerDetails.getId());
				player.setPlayerName(playerDetails.getPlayerName());
				player.setSpeciality(playerDetails.getSpeciality());
				player.setTeamId(playerDetails.getTeamId());
				
				battingList.add(player);
			}
//			
			
			
			for(Players playerDetails : pBowling ) {
				if((playerDetails.getSpeciality().equalsIgnoreCase("both") || 
						playerDetails.getSpeciality().equalsIgnoreCase("Bowler"))) {
				Players player = new Players();
				player.setId(playerDetails.getId());
				player.setPlayerName(playerDetails.getPlayerName());
				player.setSpeciality(playerDetails.getSpeciality());
				player.setTeamId(playerDetails.getTeamId());
				
				bowlingList.add(player);
				}
			}
					
			Long matchDone = 0L;
			try {
				matchDone = batting(battingList,bowlingList);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			return matchDone;
		
	}
	
	
	public Long batting(List<Players> batting, List<Players> bowler) throws InterruptedException {
		
		int over = 0;
		Random random = new Random();
		int bowlerIndex = 0;
		int battingIndex = 0;
		Long teamScore = 0L;
		int player_score = 0;
        int sixers=0;
        int fours=0;
        
				
		for(int i = 1 ;i <= 120 && battingIndex < batting.size(); i++) {
//				Thread.sleep(1000);
			if(i%6 == 0) {
				over++;

				int bowlerVal = over % bowler.size();
//				System.out.println("bowlerVal -------------------------: "+bowlerVal +"   :   "+bowler.size()+"  :::   "+over+" :: teamscore :: "+teamScore);
			}
					
					int score = random.nextInt(10);
					System.out.println("score value : "+score);
					if(score <= 6 ) {
						
						player_score += score;
						
						teamScore +=score;
						
					}
					
					if(score == 9) {

						battingIndex++;
						
//						System.err.println("batting index : "+battingIndex);
						player_score = 0;
					}
					
					
					if(score == 4) {
						fours++;
					}
					if(score ==6) {
						sixers++;
					}
				}
		
//		System.err.println("Team Score : "+teamScore);
		
		return teamScore;
		}


}
