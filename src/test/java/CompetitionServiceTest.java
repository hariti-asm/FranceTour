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
    @DisplayName("Test saving competition successfully")
    public void testSaveCompetitionSuccess() {
        when(competitionRepository.findCompetitionByName(anyString())).thenReturn(null);
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition);

        Competition savedCompetition = competitionService.saveCompetition(competition);

        assertNotNull(savedCompetition);
        assertEquals("Test Competition", savedCompetition.getName());
        assertEquals(LocalDate.of(2009, 9, 9), savedCompetition.getStartDate());
        assertEquals(LocalDate.of(2009, 12, 9), savedCompetition.getEndDate());
        assertEquals("Morocco", savedCompetition.getLocation());

        verify(competitionRepository).save(any(Competition.class));
    }

    @Test
    @DisplayName("Test saving competition with null competition")
    public void testSaveCompetitionNullCompetition() {
        assertThrows(IllegalArgumentException.class, () -> competitionService.saveCompetition(null));
        verify(competitionRepository, never()).save(any(Competition.class));
    }

    @Test
    @DisplayName("Test saving competition with invalid date")
    public void testSaveCompetitionInvalidDate() {
        competition.setStartDate(LocalDate.of(2009, 9, 9));
        competition.setEndDate(LocalDate.of(2009, 9, 8));

        assertThrows(IllegalArgumentException.class, () -> competitionService.saveCompetition(competition));
        verify(competitionRepository, never()).save(any(Competition.class));
    }

    @Test
    @DisplayName("Test finding competition by name")
    public void testFindCompetitionByName() {
        when(competitionRepository.findCompetitionByName(competition.getName().toLowerCase())).thenReturn(competition);

        Competition competitionOptional = competitionService.findCompetitionByName(competition.getName());

        assertTrue(competitionOptional != null);
        assertEquals(competition, competitionOptional);
        verify(competitionRepository, times(1)).findCompetitionByName(competition.getName().toLowerCase());
    }

    @Test
    @DisplayName("Test finding competition by name with case sensitivity")
    public void testFindCompetitionByNameWithSensitiveCase() {
        String lowerCaseName = competition.getName().toLowerCase();
        when(competitionRepository.findCompetitionByName(lowerCaseName)).thenReturn(null);

        Competition competitionOptional = competitionService.findCompetitionByName(competition.getName());

        assertTrue(competitionOptional != null);
        assertEquals(competition, competitionOptional);
        verify(competitionRepository, times(1)).findCompetitionByName(lowerCaseName);
    }

    @Test
    @DisplayName("Test finding competition with invalid name")
    public void testFindCompetitionByInvalidName() {
        String invalidName = "Non-existent Competition";
        when(competitionRepository.findCompetitionByName(invalidName.toLowerCase())).thenReturn(null);

        Competition competitionOptional = competitionService.findCompetitionByName(invalidName);

        assertFalse(competitionOptional != null);
        verify(competitionRepository, times(1)).findCompetitionByName(invalidName.toLowerCase());
    }

    @Test
    @DisplayName("Test deleting competition")
    public void testDeleteCompetition() {
        long competitionId = 1L;

        competitionService.deleteCompetition(competitionId);

        verify(competitionRepository, times(1)).deleteById(competitionId);
    }

    @Test
    @DisplayName("Test finding competition by ID")
    public void testFindCompetitionById() {
        long competitionId = 1L;
        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(competition));

        Optional<Competition> result = competitionService.findCompetitionById(competitionId);

        assertTrue(result.isPresent());
        assertEquals(competition, result.get());
        verify(competitionRepository, times(1)).findById(competitionId);
    }

//    @Test
//    @DisplayName("Test updating competition successfully")
//    public void testUpdateCompetitionSuccess() {
//        Long competitionId = 1L;
//        LocalDate startDate = LocalDate.of(2023, 1, 1);
//        LocalDate endDate = LocalDate.of(2023, 1, 10);
//        Competition existingCompetition = new Competition(competitionId, "Old Name", startDate, startDate.plusDays(2), "Old Location");
//        Competition newCompetition = new Competition(competitionId, "New Name", startDate, endDate, "New Location");
//
//        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(existingCompetition));
//        when(competitionRepository.save(any(Competition.class))).thenReturn(newCompetition);
//
//        Competition updatedCompetition = competitionService.updateCompetition(newCompetition);
//
//        assertNotNull(updatedCompetition);
//        assertEquals("New Name", updatedCompetition.getName());
//        assertEquals(startDate, updatedCompetition.getStartDate());
//        assertEquals(endDate, updatedCompetition.getEndDate());
//        assertEquals("New Location", updatedCompetition.getLocation());
//
//        verify(competitionRepository, times(1)).findById(competitionId);
//        verify(competitionRepository, times(1)).save(any(Competition.class));
//    }

//    @Test
//    @DisplayName("Test updating non-existent competition")
//    public void testUpdateCompetitionNotFound() {
//        Long competitionId = 1L;
//        LocalDate startDate = LocalDate.of(2023, 1, 1);
//        LocalDate endDate = LocalDate.of(2023, 1, 10);
//        Competition newCompetition = new Competition(competitionId, "New Name", startDate, endDate, "New Location");
//
//        when(competitionRepository.findById(competitionId)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            competitionService.updateCompetition(newCompetition);
//        });
//
//        assertEquals("Competition not found with id: " + competitionId, exception.getMessage());
//
//        verify(competitionRepository, times(1)).findById(competitionId);
//        verify(competitionRepository, never()).save(any(Competition.class));
//    }
}