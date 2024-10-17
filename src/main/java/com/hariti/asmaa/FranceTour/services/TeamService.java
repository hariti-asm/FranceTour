package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.entities.Team;
import com.hariti.asmaa.FranceTour.repositories.TeamRepository;

import java.util.List;
import java.util.Optional;

public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team findByName(String name) {
        return teamRepository.findByName(name);
    }

    public void delete(Team team) {
        teamRepository.delete(team);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    public void update(Team team) {
        teamRepository.save(team);
    }
}
