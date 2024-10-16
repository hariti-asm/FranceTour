package com.hariti.asmaa.FranceTour.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cyclist> cyclists = new HashSet<>();

    public void removeCyclist(Cyclist cyclist) {
        cyclists.remove(cyclist);
        cyclist.setTeam(null);
    }
}