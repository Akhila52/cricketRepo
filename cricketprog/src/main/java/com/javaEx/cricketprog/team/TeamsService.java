package com.javaEx.cricketprog.team;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaEx.cricketprog.entities.Teams;

@Service
public class TeamsService {

	
	@Autowired
    private TeamsRepository teamRepository;

    public List<TeamsDTO> getAllTeams() {
        List<Teams> teams = teamRepository.findAll();
        List<TeamsDTO> teamDTOs = new ArrayList<>();

        for (Teams team : teams) {
            TeamsDTO dto = new TeamsDTO();
            dto.setId(team.getId());
            dto.setTeamCountry(team.getTeamCountry());
            teamDTOs.add(dto);
        }

        return teamDTOs;
    }

}
