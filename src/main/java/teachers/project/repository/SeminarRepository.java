package teachers.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import teachers.project.entity.Seminar;

import java.util.List;

public interface SeminarRepository extends CrudRepository<Seminar, Long> {

    @Query(value = "SELECT * FROM seminars WHERE name LIKE %:term%", nativeQuery = true)

    List<Seminar> findByNameContaining(@Param("term") String term);



}
