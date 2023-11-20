package teachers.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teachers.project.entity.Seminar;
import teachers.project.entity.Student;
import teachers.project.entity.StudentSeminar;
import java.util.List;

public interface IStudentService {
    Page<StudentSeminar> findPaginated(Pageable pageable, String term);
    void createOrder(Student student, List<Seminar> seminars);
    List<StudentSeminar> findOrdersByStudentId(Long id);
}
