package seminars.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seminars.project.entity.Student;

@Repository
public interface BillingRepository extends JpaRepository<Student, Long> {

}