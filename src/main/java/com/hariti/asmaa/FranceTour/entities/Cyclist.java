package com.hariti.asmaa.FranceTour.entities;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "cyclists")
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;

    private String firstName;
    private String lastName;
    private Integer age;
    private String nationality;

    @ManyToOne(fetch = FetchType.LAZY) // Marks the relationship to Team as Many-to-One
    @JoinColumn(name = "team_id") // Foreign key column for the team
    private Team team;

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true) // One Cyclist to Many StageResults
    private Set<StageResult> stageResults = new HashSet<>();

    @OneToMany(mappedBy = "cyclist", cascade = CascadeType.ALL, orphanRemoval = true) // One Cyclist to Many GeneralResults
    private Set<GeneralResult> generalResults = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cyclist cyclist = (Cyclist) o;
        return Objects.equals(id, cyclist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cyclist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
