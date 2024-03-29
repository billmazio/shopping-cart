package seminars.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seminars.project.entity.Seminar;

import java.util.List;
@Repository
public interface SeminarRepository extends JpaRepository<Seminar, Long> {

    @Query(value = "SELECT * FROM seminars WHERE name LIKE %:term%", nativeQuery = true)

    List<Seminar> findByNameContaining(@Param("term") String term);



}
