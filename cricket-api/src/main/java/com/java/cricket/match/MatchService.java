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
		MatchDTO bowlingTeam = null;
		MatchDTO battingTeam = startMatch(dto.getTeam1Id(), dto.getTeam2Id());
		System.err.println(" team 2 check :: "+battingTeam);
		if(battingTeam != null) {
			 bowlingTeam = startMatch(dto.getTeam2Id(), dto.getTeam1Id());
		}
		
		if(battingTeam.getPlayersMesage() == null) {
			Match m = new Match();
			m.setTeam1_id(dto.getTeam1Id());
			m.setTeam1Score(battingTeam.getTeam1Score());
			m.setTeam1Noof4s(battingTeam.getTeam1NoOf4s());
			m.setTeam1Noof6s(battingTeam.getTeam1NoOf6s());
			m.setTeam1TotalNoofBalls(battingTeam.getTeam1TotalNoofBalls());
			
			m.setTeam2_id(dto.getTeam2Id());
			m.setTeam2Score(bowlingTeam.getTeam1Score() );
			m.setTeam2Noof4s(bowlingTeam.getTeam1NoOf4s());
			m.setTeam2Noof6s(bowlingTeam.getTeam1NoOf6s());
			m.setTeam2TotalNoofBalls(bowlingTeam.getTeam1TotalNoofBalls());
			matchRepository.save(m);
		}
		
//		dto.setTeam1Score(battingTeam.getTeam1Score());
//		dto.setTeam2Score(bowlingTeam.getTeam2Score());
//		Optional<Teams> team1Opt = teamRepository.findById(dto.getTeam1Id());
//		if (team1Opt.isPresent()) {
//		    Teams team1 = team1Opt.get();
//		    dto.setTeam1Name(team1.getTeamCountry());
//		}
//
//		Optional<Teams> team2Opt = teamRepository.findById(dto.getTeam2Id());
//		if (team2Opt.isPresent()) {
//		    Teams team2 = team2Opt.get();
//		    dto.setTeam2Name(team2.getTeamCountry());
//		}
		
		
		dto.setTeam1Score(battingTeam.getTeam1Score());
		dto.setTeam1NoOf4s(battingTeam.getTeam1NoOf4s());
		dto.setTeam1NoOf6s(battingTeam.getTeam1NoOf6s());
		dto.setTeam1TotalNoofBalls(battingTeam.getTeam1TotalNoofBalls());
		
		
		dto.setTeam2Score(bowlingTeam.getTeam1Score());
		dto.setTeam2NoOf4s(bowlingTeam.getTeam1NoOf4s());
		dto.setTeam2NoOf6s(bowlingTeam.getTeam1NoOf6s());
		dto.setTeam2TotalNoofBalls(bowlingTeam.getTeam1TotalNoofBalls());
		dto.setPlayersMesage(battingTeam.getPlayersMesage());

		// Set team names
		Teams team1 = teamRepository.findById(dto.getTeam1Id()).orElse(null);
		if (team1 != null) {
		    dto.setTeam1Name(team1.getTeamCountry());
		}

		Teams team2 = teamRepository.findById(dto.getTeam2Id()).orElse(null);
		if (team2 != null) {
		    dto.setTeam2Name(team2.getTeamCountry());
		}

//		return dto;
		return dto;
	}

	
public MatchDTO startMatch(Long batting , Long bowling)  {
		
			List<Players> pBatting = playerRepository.findByTeamId(batting);
			
			
			List<Players> pBowling = playerRepository.findByTeamId(bowling);
		
			if(pBatting.isEmpty() || pBowling.isEmpty()) {
			System.out.println("the players not there startmatch");
				MatchDTO matchDone = new MatchDTO();
				matchDone.setPlayersMesage("No");
				return matchDone;
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
					
			MatchDTO matchDone = null;
			try {
				matchDone = batting(battingList,bowlingList);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			return matchDone;
		
	}
	
	
	public MatchDTO batting(List<Players> batting, List<Players> bowler) throws InterruptedException {
		
		MatchDTO matchDTO = new MatchDTO();
		
		
		int over = 0;
		Random random = new Random();
		int bowlerIndex = 0;
		int battingIndex = 0;
		Long teamScore = 0L;
		int player_score = 0;
		long noOf4s = 0;
		long noOf6s = 0;
		long noOfBalls = 0;
				
		for(int i = 1 ;i <= 120 && battingIndex < batting.size(); i++) {
//				Thread.sleep(1000);
			noOfBalls++;
			
			
			if(i%6 == 0) {
				over++;

				int bowlerVal = over % bowler.size();
			}
			System.out.println("batting team : "+ batting.get(0).getTeamId()+"batting.size() :  "+batting.size());
					int score = random.nextInt(10);
//					System.out.println("score value : "+score);
					if(score <= 6 ) {
						
						if(score == 4) {
							noOf4s++;
						}
						if(score ==6) {
							noOf6s++;
						}
						
						player_score += score;
//						Match match = new Match();
//						match
						
						Players p = new Players();
						
						teamScore +=score;
					}
					
					if(score == 9) {

						battingIndex++;
						
//						System.err.println("batting index : "+battingIndex);
						player_score = 0;
					}
					
				}
		
		matchDTO.setTeam1NoOf4s(noOf4s);
		matchDTO.setTeam1NoOf6s(noOf6s);
		matchDTO.setTeam1TotalNoofBalls(noOfBalls);
		matchDTO.setTeam1Score(teamScore);
		
		
//		System.err.println("Team Score : "+teamScore);
		
		return matchDTO;
		
		}


}
