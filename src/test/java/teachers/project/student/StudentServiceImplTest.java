package teachers.project.student;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teachers.project.entity.Order;
import teachers.project.entity.Seminar;
import teachers.project.entity.Student;
import teachers.project.entity.StudentSeminar;
import teachers.project.repository.BillingRepository;
import teachers.project.repository.OrderRepository;
import teachers.project.service.StudentServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testCreateVest() {
        // Arrange
        Student student = new Student(1L, "John", "Doe", "New York", "10001", "1234567890", "john.doe@example.com");
        Seminar seminar = new Seminar(1L, "Math", new BigDecimal("100.00"), "John Doe", "1234567890", "Prof. Smith", LocalDate.now());
        List<Seminar> seminars = Collections.singletonList(seminar);

        // Act
        studentService.createOrder(student, seminars);

        // Assert
        verify(billingRepository, times(1)).save(student);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testFindVestsByStudentId_WhenNoVests_ShouldReturnEmptyList() {
        // Arrange
        long studentId = 1L;

        // Act
        List<StudentSeminar> result = studentService.findOrdersByStudentId(studentId);

        // Assert
        assertTrue(result.isEmpty(), "Expected an empty list when no vests are associated with the student");
    }



    @Test
    public void testFindPaginated() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        when(pageable.getPageSize()).thenReturn(10); // Stubbing getPageSize to return a positive integer
        when(pageable.getPageNumber()).thenReturn(0); // You might also need to stub other methods
        String term = null;

        when(orderRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        Page<StudentSeminar> result = studentService.findPaginated(pageable, term);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
    }

}
