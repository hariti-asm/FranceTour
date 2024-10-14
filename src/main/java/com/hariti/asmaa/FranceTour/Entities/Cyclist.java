package com.hariti.asmaa.FranceTour.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cyclists")
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String team;

    @ManyToMany(mappedBy = "cyclists")
    private List<Competition> competitions = new ArrayList<>();

    @ManyToMany(mappedBy = "cyclists")
    private List<Phase> phases = new ArrayList<>();

    // Constructors...

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public void addCompetition(Competition competition) {
        competitions.add(competition);
        competition.getCyclists().add(this);
    }

    public void removeCompetition(Competition competition) {
        competitions.remove(competition);
        competition.getCyclists().remove(this);
    }

    public void addPhase(Phase phase) {
        phases.add(phase);
        phase.getCyclists().add(this);
    }

    public void removePhase(Phase phase) {
        phases.remove(phase);
        phase.getCyclists().remove(this);
    }


    @Override
    public String toString() {
        return "Cyclist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", team='" + team + '\'' +
                '}';
    }
}