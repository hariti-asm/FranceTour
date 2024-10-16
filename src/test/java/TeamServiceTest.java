import com.hariti.asmaa.FranceTour.entities.Team;
import com.hariti.asmaa.FranceTour.repositories.TeamRepository;
import com.hariti.asmaa.FranceTour.services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class TeamServiceTest {
    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Team team;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        team = new Team();
        team.setId(1L);
        team.setName("Test Team");
    }
    @Test
    void testSave() {
        when(teamRepository.save(any(Team.class))).thenReturn(team);

        Team savedTeam = teamService.save(team);

        assertNotNull(savedTeam);
        assertEquals("Test Team", savedTeam.getName());
        verify(teamRepository, times(1)).save(team);
    }
    @Test
    void testFindByName() {
        when(teamRepository.findByName("Test Team")).thenReturn(team);

        Team foundTeam = teamService.findByName("Test Team");

        assertNotNull(foundTeam);
        assertEquals("Test Team", foundTeam.getName());
        verify(teamRepository, times(1)).findByName("Test Team");
    }

    @Test
    void testDelete() {
        doNothing().when(teamRepository).delete(team);

        teamService.delete(team);

        verify(teamRepository, times(1)).delete(team);
    }
    @Test
    void testFindAll() {
        List<Team> teams = Arrays.asList(team, new Team());
        when(teamRepository.findAll()).thenReturn(teams);

        List<Team> foundTeams = teamService.findAll();

        assertNotNull(foundTeams);
        assertEquals(2, foundTeams.size());
        verify(teamRepository, times(1)).findAll();
    }
    @Test
    void testFindById() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Optional<Team> foundTeam = teamService.findById(1L);

        assertTrue(foundTeam.isPresent());
        assertEquals("Test Team", foundTeam.get().getName());
        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(teamRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Team> foundTeam = teamService.findById(2L);

        assertFalse(foundTeam.isPresent());
        verify(teamRepository, times(1)).findById(2L);
    }
    @Test
    void testDeleteById() {
        doNothing().when(teamRepository).deleteById(1L);

        teamService.deleteById(1L);

        verify(teamRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdate() {
        when(teamRepository.save(any(Team.class))).thenReturn(team);

        teamService.update(team);

        verify(teamRepository, times(1)).save(team);
    }

}
