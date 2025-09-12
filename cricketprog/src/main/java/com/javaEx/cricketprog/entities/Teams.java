package com.javaEx.cricketprog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "teams")
public class Teams extends BaseEntity implements Serializable {

    @Column(name = "team_country")
    private String teamCountry;

}
