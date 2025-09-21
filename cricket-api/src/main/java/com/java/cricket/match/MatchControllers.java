package com.java.cricket.match;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket/match")
public class MatchControllers {

	@Autowired
	private MatchService matchService;
	
//	@GetMapping("/playMatch")
//	@CrossOrigin(origins = "*")
//	public MatchDTO playMatch(MatchDTO dto ){
//		System.out.println("front end is hitting with "+dto.getTeam1Id());
//		return matchService.playMatch(dto);
//	}
	
	
	
	 @PostMapping("/init")
	    public String initializeMatch(@RequestParam Long team1Id, @RequestParam Long team2Id) {
	        matchService.initializeMatch(team1Id, team2Id);
	        return "Match initialized between Team " + team1Id + " and Team " + team2Id;
	    }

	    @GetMapping("/nextBall")
	    public MatchDTO simulateNextBall(@RequestParam Long team1Id, @RequestParam Long team2Id) {
	        return matchService.simulateNextBall(team1Id, team2Id);
	    }
	
	
}
