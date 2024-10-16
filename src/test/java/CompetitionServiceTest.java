import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.repositories.CompetitionRepository;
import com.hariti.asmaa.FranceTour.services.CompetitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        competition.setName("Test Competition");
        competition.setStartDate(LocalDate.of(2009, 9, 9));
        competition.setEndDate(LocalDate.of(2009, 12, 9));
        competition.setLocation("Morocco");
    }

    @Test
    @DisplayName("successfully tested")
    public void testSaveCompetitionSuccess() {
        // Arrange
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);

        // Act
        Competition savedCompetition = competitionService.saveCompetition(competition);

        // Assert
        assertNotNull(savedCompetition);
        assertEquals("Test Competition", savedCompetition.getName());
        assertEquals(LocalDate.of(2009, 9, 9), savedCompetition.getStartDate());
        assertEquals(LocalDate.of(2009, 12, 9), savedCompetition.getEndDate());
        assertEquals("Morocco", savedCompetition.getLocation());

        verify(competitionRepository).save(any(Competition.class));
    }

    @Test
    @DisplayName("test competition with null competition")
    public void testSaveCompetition_NullCompetition() {
        // Arrange
        when(competitionRepository.save(any(Competition.class))).thenReturn(null);

        // Act
        Competition savedCompetition = competitionService.saveCompetition(null);

        // Assert
        assertNull(savedCompetition);
        verify(competitionRepository, never()).save(any(Competition.class));
    }

    @Test
    @DisplayName("test competition with invalid date")

    public void testSaveCompetition_InvalidDate() {
        // Arrange
        competition.setStartDate(LocalDate.of(2009, 9, 9));
        competition.setEndDate(LocalDate.of(2009, 9, 8));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> competitionService.saveCompetition(competition));
        verify(competitionRepository, never()).save(any(Competition.class));
    }

    @Test
    @DisplayName("test find competition by name")

    public void testFindCompetitionByName() {
        // Arrange
        when(competitionRepository.findCompetitionByName(competition.getName())).thenReturn(Optional.of(competition));

        // Act
        Optional<Competition> competitionOptional = competitionService.findCompetitionByName(competition.getName());

        // Assert
        assertTrue(competitionOptional.isPresent());
        assertEquals(competition, competitionOptional.get());
        verify(competitionRepository, times(1)).findCompetitionByName(competition.getName());
    }

    @Test
    @DisplayName("test competition  by name with sensitive case")

    public void testFindCompetitionByName_WithSensitiveCase() {
        // Arrange
        String lowerCaseName = competition.getName().toLowerCase();
        when(competitionRepository.findCompetitionByName(lowerCaseName)).thenReturn(Optional.of(competition));

        // Act
        Optional<Competition> competitionOptional = competitionService.findCompetitionByName(lowerCaseName);

        // Assert
        assertTrue(competitionOptional.isPresent());
        assertEquals(competition, competitionOptional.get());
        verify(competitionRepository, times(1)).findCompetitionByName(lowerCaseName);
    }

    @Test
    @DisplayName("test competition with invalid competition name")

    public void testFindCompetitionBy_InvalidName() {
        // Arrange
        when(competitionRepository.findCompetitionByName(anyString())).thenReturn(Optional.empty());

        // Act
        Optional<Competition> competitionOptional = competitionService.findCompetitionByName("Non-existent Competition");

        // Assert
        assertFalse(competitionOptional.isPresent());
        verify(competitionRepository, times(1)).findCompetitionByName("Non-existent Competition");
    }

    @Test
    public void deleteCompetitionTest() {
        // Given
        long competitionId = 1L;

        // When
        competitionService.deleteCompetition(competitionId);

        // Then
        verify(competitionRepository, times(1)).deleteById(competitionId);
    }
    @Test
    public void findCompetitionByIdTest() {
        long competitionId = 1L;
        competitionService.findCompetitionById(competitionId);
        verify(competitionRepository, times(1)).findById(competitionId);

    }
}