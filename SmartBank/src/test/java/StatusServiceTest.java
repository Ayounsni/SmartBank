import com.smartbank.models.entities.Status;
import com.smartbank.repository.interfaces.IStatusRepository;
import com.smartbank.services.implementations.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StatusServiceTest {

    @Mock
    private IStatusRepository statusRepository;

    @InjectMocks
    private StatusService statusService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPersist_Success() {
        Status status = new Status();
        status.setNom("En attente");

        when(statusRepository.persist(status)).thenReturn(status);

        Status result = statusService.persist(status);

        assertNotNull(result);
        assertEquals("En attente", result.getNom());

        verify(statusRepository).persist(status);
    }

    @Test
    public void testPersist_NullObject() {
        Status status = null;

        Status result = statusService.persist(status);

        assertNull(result);

        verify(statusRepository, never()).persist(any(Status.class));
    }

    @Test
    public void testFindAllWithStatuses() {
        List<Status> mockStatuses = Arrays.asList(new Status(1L, "En attente"), new Status(2L, "Confirmer"));
        when(statusRepository.findAll()).thenReturn(mockStatuses);

        List<Status> result = statusService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("En attente", result.getFirst().getNom());
        verify(statusRepository).findAll();
    }

    @Test
    public void testFindAllWithEmptyList() {
        when(statusRepository.findAll()).thenReturn(Collections.emptyList());

        List<Status> result = statusService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(statusRepository).findAll();
    }

    @Test
    public void testFindAllReturnsNull() {
        when(statusRepository.findAll()).thenReturn(null);

        List<Status> result = statusService.findAll();

        assertNull(result);
        verify(statusRepository).findAll();
    }



    @Test
    public void testFindById() {
        Status mockStatus = new Status(1L, "En attente");

        when(statusRepository.findById(1L)).thenReturn(mockStatus);

        Status result = statusService.findById(1L);

        assertNotNull(result);
        assertEquals("En attente", result.getNom());

        verify(statusRepository).findById(1L);
    }
}
