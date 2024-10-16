import com.hariti.asmaa.FranceTour.entities.Team;
import com.hariti.asmaa.FranceTour.repositories.TeamRepository;
import com.hariti.asmaa.FranceTour.services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

}
