
import com.hariti.asmaa.FranceTour.entities.Stage;
import com.hariti.asmaa.FranceTour.repositories.StageRepository;
import com.hariti.asmaa.FranceTour.services.StageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StageServiceTest {

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageService stageService;

    private Stage stage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stage = new Stage();
        stage.setId(1L);
        stage.setName("Mountain Stage");
        // Set other necessary properties
    }

    @Test
    void testSaveStage() {
        when(stageRepository.save(any(Stage.class))).thenReturn(stage);

        Stage savedStage = stageService.saveStage(stage);

        assertNotNull(savedStage);
        assertEquals("Mountain Stage", savedStage.getName());
        verify(stageRepository, times(1)).save(stage);
    }

    @Test
    void testGetAllStages() {
        List<Stage> stages = Arrays.asList(stage, new Stage());
        when(stageRepository.findAll()).thenReturn(stages);

        List<Stage> foundStages = stageService.getAllStages();

        assertNotNull(foundStages);
        assertEquals(2, foundStages.size());
        verify(stageRepository, times(1)).findAll();
    }

    @Test
    void testGetStageById() {
        when(stageRepository.findById(1L)).thenReturn(Optional.of(stage));

        Optional<Stage> foundStage = stageService.getStageById(1L);

        assertTrue(foundStage.isPresent());
        assertEquals("Mountain Stage", foundStage.get().getName());
        verify(stageRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStageByIdNotFound() {
        when(stageRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Stage> foundStage = stageService.getStageById(2L);

        assertFalse(foundStage.isPresent());
        verify(stageRepository, times(1)).findById(2L);
    }

    @Test
    void testDeleteStageById() {
        doNothing().when(stageRepository).deleteById(1L);

        stageService.deleteStageById(1L);

        verify(stageRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateStage() {
        when(stageRepository.save(any(Stage.class))).thenReturn(stage);

        Stage updatedStage = stageService.updateStage(stage);

        assertNotNull(updatedStage);
        assertEquals("Mountain Stage", updatedStage.getName());
        verify(stageRepository, times(1)).save(stage);
    }
}