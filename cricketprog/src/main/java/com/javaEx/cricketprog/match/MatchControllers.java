package com.javaEx.cricketprog.match;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket/match")
public class MatchControllers {

	@Autowired
	private MatchService matchService;
	
	@GetMapping("/playMatch")
	public MatchDTO playMatch(MatchDTO dto ){
		return matchService.playMatch(dto);
	}
	
}
