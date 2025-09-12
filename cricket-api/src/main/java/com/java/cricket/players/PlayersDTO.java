package com.java.cricket.players;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayersDTO {
    public Long id;
    public String playerName;
    public String speciality;
    public Long teamId;
    public Long age;

//    public PlayerDTO() {}
//    public PlayerDTO(Integer id, String playerName, String speciality, Integer teamId) {
//        this.id = id;
//        this.playerName = playerName;
//        this.speciality = speciality;
//        this.teamId = teamId;
//    }
}
