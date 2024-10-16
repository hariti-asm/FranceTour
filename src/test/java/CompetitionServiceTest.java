import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.repositories.CompetitionRepository;
import com.hariti.asmaa.FranceTour.services.CompetitionService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionService competitionService;

    private Competition competition;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        competition = new Competition();
        competition.setName("test Competition");
        competition.setStartDate(LocalDate.of(2009, 9, 9));
        competition.setEndDate(LocalDate.of(2009, 12, 9));
        competition.setLocation("Morocco");
    }

    @Test
    public void testSaveCompetitionSuccess() {
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);

        Competition savedCompetition = competitionService.saveCompetition(competition);

        assertNotNull(savedCompetition);
        assertEquals("test Competition", savedCompetition.getName());
        assertEquals(LocalDate.of(2009, 9, 9), savedCompetition.getStartDate());
        assertEquals(LocalDate.of(2009, 12, 9), savedCompetition.getEndDate());
        assertEquals("Morocco", savedCompetition.getLocation());

        verify(competitionRepository).save(any(Competition.class));
    }

    @Test
    public void testSaveCompetitionNullCompetition() {
        when(competitionRepository.save(any(Competition.class))).thenReturn(null);

        Competition savedCompetition = competitionService.saveCompetition(null);

        assertNull(savedCompetition);
        verify(competitionRepository, never()).save(any(Competition.class));
    }

    @Test
    public void testSaveCompetitonInvalidDate() {
        competition.setStartDate(LocalDate.of(2009, 9, 9));
        competition.setEndDate(LocalDate.of(2009, 9, 9));

        assertThrows(IllegalArgumentException.class, () -> competitionService.saveCompetition(competition));

        verify(competitionRepository, never()).save(any(Competition.class));
    }
    @Test
    public void testFindCompetitionByName() {
        when(competitionRepository.findCompetitionByName(competition.getName())).thenReturn(Optional.of(competition));
        Optional <Competition> competitionOptional = competitionService.findCompetitionByName(competition.getName());
        assertTrue(competitionOptional.isPresent());
        assertEquals(competition, competitionOptional.get());
        verify(competitionRepository, times(1)).findCompetitionByName(competition.getName());
    }
    @Test
    public void testFindCompetitionByNameWithSensitiveCase(){
        // Arrange
        String lowerCaseName = competition.getName().toLowerCase();
        when(competitionRepository.findCompetitionByName(lowerCaseName))
                .thenReturn(Optional.of(competition));
        Optional<Competition> competitionOptional = competitionService.findCompetitionByName(lowerCaseName);
        assertTrue(competitionOptional.isPresent());
        assertEquals(competition, competitionOptional.get());
        verify(competitionRepository, times(1)).findCompetitionByName(lowerCaseName);
    }
    @Test
    public void testFindCompetitionByInvalidName() {
        // Arrange
        when(competitionRepository.findCompetitionByName(anyString())).thenReturn(Optional.empty());

        // Act
        Optional<Competition> competitionOptional = competitionRepository.findCompetitionByName(anyString());

        // Assert
        assertFalse(competitionOptional.isPresent());

        verify(competitionRepository, times(1)).findCompetitionByName(anyString());
    }

}
