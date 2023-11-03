package teachers.project.seminar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import teachers.project.entity.Seminar;
import teachers.project.repository.SeminarRepository;
import teachers.project.service.SeminarServiceImpl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class SeminarServiceImplTest {

    @Mock
    private SeminarRepository seminarRepository;

    @InjectMocks
    private SeminarServiceImpl seminarService;

    @Test
    public void testCreateSeminar() {
        // Arrange
        Seminar seminar = new Seminar(1L, "Math 101", new BigDecimal("200.00"), "Introduction to Math", "MATH001", "Prof. Smith", LocalDate.now());

        // Act
        seminarService.save(seminar);

        // Assert
        verify(seminarRepository, times(1)).save(seminar);
    }

    @Test
    public void testFindSeminarById_WhenNotFound_ShouldReturnEmpty() {
        // Arrange
        long seminarId = 1L;
        when(seminarRepository.findById(seminarId)).thenReturn(Optional.empty());

        // Act
        Optional<Seminar> result = seminarService.findSeminarById(seminarId);

        // Assert
        assertTrue(result.isEmpty(), "Expected no seminar to be found with the given ID");
    }

    @Test
    public void testFindPaginated() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<Seminar> seminars = IntStream.range(0, 10)
                .mapToObj(i -> new Seminar(/* parameters */))
                .collect(Collectors.toList());

        // Assuming findAll returns a full list
        when(seminarRepository.findAll()).thenReturn(seminars);

        // Act
        Page<Seminar> result = seminarService.findPaginated(pageable, null);

        // Assert
        assertNotNull(result);
        assertEquals(10, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertFalse(result.getContent().isEmpty());
    }


}
