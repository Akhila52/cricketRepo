package com.javaEx.cricketprog.players;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaEx.cricketprog.entities.Players;
import com.javaEx.cricketprog.team.TeamsRepository;


@Service
public class PlayersService {

	
	  @Autowired
	    private PlayersRepository playerRepository;

	    @Autowired
	    private TeamsRepository teamRepository;

	    public Boolean savePlayers(List<PlayersDTO> playerDTOs) {
	        List<Players> players = new ArrayList<>();

	        for (PlayersDTO dto : playerDTOs) {
	            Players player = new Players();
	            player.setPlayerName(dto.getPlayerName());
	            player.setSpeciality(dto.getSpeciality());
	            player.setTeamId(dto.getTeamId());
	            players.add(player);
	        }

	        List<Players> plist = playerRepository.saveAll(players);
	        
	        if(plist.size() == playerDTOs.size()) {
	        	return true;
	        }
	        
	        return false;
	    }

		public List<PlayersDTO> getPlayersByTeamID(Long teamId) {
			// TODO Auto-generated method stub
			
			List<Players> playersList = playerRepository.findByTeamId(teamId);
			
			List<PlayersDTO> playersDTOs = new ArrayList<PlayersDTO>();
			
			if(playersList.isEmpty()) {
				return playersDTOs;
			}
			for(Players players : playersList) {
				PlayersDTO playerDto = new PlayersDTO();
				
				playerDto.setId(players.getId());
				playerDto.setPlayerName(players.getPlayerName());
				playerDto.setSpeciality(players.getSpeciality());
				playerDto.setTeamId(players.getTeamId());
				playersDTOs.add(playerDto);
				
			}
			
			
			return playersDTOs;
		}
	
	
}
