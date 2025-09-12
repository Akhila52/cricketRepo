package com.java.cricket.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cricket/teams")
public class TeamsControllers {

	
	@Autowired
    private TeamsService teamService;

    @GetMapping
    public List<TeamsDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

	
	
}
