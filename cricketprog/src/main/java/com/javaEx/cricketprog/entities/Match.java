package com.javaEx.cricketprog.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter 
@Entity(name = "matchresults")
public class Match extends BaseEntity implements Serializable{

	@Column(name = "team2_id")
    public Long team1_id;

	@Column(name = "team2_id")
    public Long team2_id;

    @Column(name = "team1_score")
    public Long team1Score;

    @Column(name = "team2_score")
    public Long team2Score;
}

