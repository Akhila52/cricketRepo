package com.java.cricket.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "teams")
public class Teams extends BaseEntity implements Serializable {

    @Column(name = "team_country", nullable = false)
    private String teamCountry;

    // Uncomment if you add Player entity
    // @OneToMany(mappedBy = "team")
    // private List<Player> players;
}
