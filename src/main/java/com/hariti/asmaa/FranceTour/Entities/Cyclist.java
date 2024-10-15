package com.hariti.asmaa.FranceTour.Entities;

import com.hariti.asmaa.FranceTour.Entities.Embeddebales.GeneralResult;
import com.hariti.asmaa.FranceTour.Entities.Embeddebales.StageResult;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String team;

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StageResult> stageResults = new HashSet<>();

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GeneralResult> generalResults = new HashSet<>();


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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Set<StageResult> getStageResults() {
        return stageResults;
    }

    public void setStageResults(Set<StageResult> stageResults) {
        this.stageResults = stageResults;
    }

    public Set<GeneralResult> getGeneralResults() {
        return generalResults;
    }

    public void setGeneralResults(Set<GeneralResult> generalResults) {
        this.generalResults = generalResults;
    }
}