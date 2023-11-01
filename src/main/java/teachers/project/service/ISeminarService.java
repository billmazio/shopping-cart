package teachers.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teachers.project.entity.Seminar;

import java.util.Optional;

public interface ISeminarService {
    Page<Seminar> findPaginated(Pageable pageable, String term);
    void save(Seminar seminar);
    Optional<Seminar> findSeminarById(Long id);
    void delete(Long id);
}
