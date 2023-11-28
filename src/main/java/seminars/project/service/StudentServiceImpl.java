package seminars.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seminars.project.repository.OrderRepository;
import seminars.project.entity.Order;
import seminars.project.entity.Seminar;
import seminars.project.entity.Student;
import seminars.project.entity.StudentSeminar;
import seminars.project.repository.BillingRepository;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {

    private final OrderRepository orderRepository;
    private final BillingRepository billingRepository;

    @Autowired
    public StudentServiceImpl(OrderRepository orderRepository, BillingRepository billingRepository) {
        this.orderRepository = orderRepository;
        this.billingRepository = billingRepository;
    }

@Override
public Page<Student> findPaginated(Pageable pageable, String term) {
    return page(pageable, term);
}

    // Method to create Order records for a student and seminars.
    @Override
    @Transactional
    public void createOrder(Student student, List<Seminar> seminars) {
        billingRepository.save(student);

        for (Seminar seminar : seminars) {
            Order order = new Order();
            order.setStudent(student);
            order.setOrderDate(LocalDate.now());
            order.setSeminar(seminar);
            orderRepository.save(order);
        }
    }

    // Method to find Order records for a specific student.
    @Override
    public List<StudentSeminar> findOrdersByStudentId(Long id) {
       List<Order> orders = (List<Order>) orderRepository.findAll();

        // Grouping Order records by Student and mapping them to Seminars.
        Map<Student, List<Seminar>> studentSeminarMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getStudent, Collectors.mapping(Order::getSeminar, Collectors.toList())));

        // Creating StudentSeminar objects from grouped data.
        List<StudentSeminar> studentSeminars = studentSeminarMap.entrySet().stream()
                .map(entry -> new StudentSeminar(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        // Filtering StudentSeminar records for the specified student.
        return studentSeminars.stream()
                .filter(studentSeminar -> studentSeminar.getStudent().getId().equals(id))
                .collect(Collectors.toList());
    }

    // Helper method for paginating Student records.
    private Page<Student> page(Pageable pageable, String term) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Student> students;

        if (term == null) {
            // If 'term' is null, retrieve all students.
            students = (List<Student>) billingRepository.findAll();
        } else {
            // Implement your filtering logic based on 'term'.
            // For example, you can search for students by name or other criteria.
            LocalDate date = LocalDate.parse(term.trim());
            students = getStudentsByOrderDate(date);
        }

        List<Student> list;
        if (students.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, students.size());
            list = students.subList(startItem, toIndex);
        }

        // Create a paginated Page object for students.
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), students.size());
    }
    private List<Student> getStudentsByOrderDate(LocalDate date) {
        List<Order> orders = orderRepository.findByOrderDate(date);

        // Extract students from the orders for the given date.
        List<Student> students = orders.stream()
                .map(Order::getStudent)
                .collect(Collectors.toList());

        return students;
    }

}


