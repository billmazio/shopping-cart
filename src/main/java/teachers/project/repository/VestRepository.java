package teachers.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import teachers.project.entity.Vest;

import java.time.LocalDate;
import java.util.ArrayList;

public interface VestRepository extends CrudRepository<Vest, Long> {

    ArrayList<Vest> findByVestDate(LocalDate term);

    ArrayList<Vest> findVestsById(Long id);
}
