package com.java.cricket.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter 
@Entity
@Table(name = "matchresults")
public class Match extends BaseEntity implements Serializable{

	@Column(name = "team1_id")
	private Long team1_id;

	@Column(name = "team2_id")
	private Long team2_id;

	@Column(name = "team1_score")
	private Long team1Score;

	@Column(name = "team2_score")
	private Long team2Score;

	@Column(name = "team1_total_noofballs")
	private Long team1TotalNoofBalls;

	@Column(name = "team2_total_noofballs")
	private Long team2TotalNoofBalls;

	@Column(name = "team1_noof_6")
	private Long team1Noof6s;

	@Column(name = "team1_noof_4")
	private Long team1Noof4s;

	@Column(name = "team2_noof_6")
	private Long team2Noof6s;

	@Column(name = "team2_noof_4")
	private Long team2Noof4s;
}

