package com.javaEx.cricketprog.entities;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity(name = "players")
public class Players extends BaseEntity implements Serializable{



    @Column(name = "player_name")
    public String playerName;

    @Column(name = "speciality")
    public String speciality;

    
    @Column(name = "team_id")
    public Long teamId;

}
