package com.java.cricket.players;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cricket/players")
@CrossOrigin(origins = "http://localhost:4200")
public class PlayersControllers {

	
	@Autowired private PlayersService playersService;
	
	
	@PostMapping( "/addPlayers")
	public Boolean addPlayers(@RequestBody List<PlayersDTO> playersList){
//		System.err.println("team id for fetching players ::: "+teamId+ " playersService.getPlayersByTeamID(teamId) : "+playersService.getPlayersByTeamID(teamId).size());
		return playersService.savePlayers(playersList);
	}
	
	
	@GetMapping("/getPlayersByTeamID")
//	@CrossOrigin(origins = "*")
	public List<PlayersDTO> getPlayersByTeamID(@RequestParam Long teamId){
		System.err.println("team id for fetching players ::: "+teamId+ " playersService.getPlayersByTeamID(teamId) : "+playersService.getPlayersByTeamID(teamId).size());
		return playersService.getPlayersByTeamID(teamId);
	}
	
	
}
