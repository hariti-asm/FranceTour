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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CompetitionServiceTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionService competitionService;

    private Competition competition;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Correct method to initialize mocks
        competition = new Competition();
        competition.setName("test Competition");
        competition.setStartDate(LocalDate.of(2009, 9, 9));
        competition.setEndDate(LocalDate.of(2009, 12, 9));
        competition.setLocation("Morocco");
    }

    @Test
    public void testSaveCompetition() {
        // ARRANGE
        when(competitionRepository.save(any(Competition.class))).thenReturn(competition); // Return mock result

        // ACT
        Competition savedCompetition = competitionService.saveCompetition(competition);

        // ASSERT
        assertNotNull(savedCompetition);
        assertEquals("test Competition", savedCompetition.getName());
        assertEquals(LocalDate.of(2009, 9, 9), savedCompetition.getStartDate());
        assertEquals(LocalDate.of(2009, 12, 9), savedCompetition.getEndDate());
        assertEquals("Morocco", savedCompetition.getLocation());

        verify(competitionRepository).save(any(Competition.class));
    }
}
