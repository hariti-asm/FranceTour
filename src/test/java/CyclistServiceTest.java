
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.repositories.CyclistRepository;
import com.hariti.asmaa.FranceTour.services.CyclistService;
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

public class CyclistServiceTest {

    @Mock
    private CyclistRepository cyclistRepository;

    @InjectMocks
    private CyclistService cyclistService;

    private Cyclist cyclist;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cyclist = new Cyclist();
        cyclist.setId(1L);
        cyclist.setFirstName("John");
        cyclist.setLastName("Doe");
    }

    @Test
    void testFindCyclistById() {
        when(cyclistRepository.findById(1L)).thenReturn(Optional.of(cyclist));

        Optional<Cyclist> foundCyclist = cyclistService.findCyclistById(1L);

        assertTrue(foundCyclist.isPresent());
        assertEquals("John", foundCyclist.get().getFirstName());
        verify(cyclistRepository, times(1)).findById(1L);
    }

    @Test
    void testFindCyclistByIdNotFound() {
        when(cyclistRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Cyclist> foundCyclist = cyclistService.findCyclistById(2L);

        assertFalse(foundCyclist.isPresent());
        verify(cyclistRepository, times(1)).findById(2L);
    }

    @Test
    void testFindAllCyclists() {
        List<Cyclist> cyclists = Arrays.asList(cyclist, new Cyclist());
        when(cyclistRepository.findAll()).thenReturn(cyclists);

        List<Cyclist> foundCyclists = cyclistService.findAllCyclists();

        assertNotNull(foundCyclists);
        assertEquals(2, foundCyclists.size());
        verify(cyclistRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);

        Cyclist savedCyclist = cyclistService.save(cyclist);

        assertNotNull(savedCyclist);
        assertEquals("John", savedCyclist.getFirstName());
        verify(cyclistRepository, times(1)).save(cyclist);
    }

    @Test
    void testUpdateCyclist() {
        when(cyclistRepository.save(any(Cyclist.class))).thenReturn(cyclist);

        Cyclist updatedCyclist = cyclistService.updateCyclist(cyclist);

        assertNotNull(updatedCyclist);
        assertEquals("John", updatedCyclist.getFirstName());
        verify(cyclistRepository, times(1)).save(cyclist);
    }

    @Test
    void testDeleteCyclist() {
        doNothing().when(cyclistRepository).delete(cyclist);

        cyclistService.deleteCyclist(cyclist);

        verify(cyclistRepository, times(1)).delete(cyclist);
    }
}