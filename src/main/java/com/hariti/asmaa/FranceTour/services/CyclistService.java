package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.repositories.CyclistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CyclistService {
    private final CyclistRepository cyclistRepository;

    @Autowired
    public CyclistService(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }

    public Optional<Cyclist> findCyclistById(Long cyclistId) {
        return cyclistRepository.findById(cyclistId);

    }

    public Page<Cyclist> findAllCyclists(Pageable pageable) {
        return cyclistRepository.findAll(pageable);
    }

    public Cyclist save(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }

    public Cyclist updateCyclist(Cyclist cyclist) {
        return cyclistRepository.save(cyclist);
    }

    public void deleteCyclist(Long cyclist) {
        cyclistRepository.deleteById(cyclist);

    }

    public List<Cyclist> getCyclistsInCompetition(Long competitionId) {
        if (competitionId == null) {
            throw new IllegalArgumentException("Competition ID cannot be null");
        }
        return cyclistRepository.findByGeneralResults_CompetitionId(competitionId);
    }

    public Cyclist findCyclistByName(String name) {
        return cyclistRepository.findCyclistByFirstName(name);
    }

}
