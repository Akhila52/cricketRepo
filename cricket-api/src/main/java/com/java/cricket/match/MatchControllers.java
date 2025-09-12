package com.java.cricket.match;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket/match")
public class MatchControllers {

	@Autowired
	private MatchService matchService;
	
	@GetMapping("/playMatch")
	@CrossOrigin(origins = "*")
	public MatchDTO playMatch(MatchDTO dto ){
		System.out.println("front end is hitting with "+dto.getTeam1Id());
		return matchService.playMatch(dto);
	}
	
}
