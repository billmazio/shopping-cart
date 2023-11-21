package seminars.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import seminars.project.entity.Student;

@Repository
public interface BillingRepository extends CrudRepository<Student, Long> {

}