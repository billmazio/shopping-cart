package seminars.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import seminars.project.repository.SeminarRepository;
import seminars.project.entity.Seminar;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class SeminarServiceImpl implements ISeminarService{

    private final SeminarRepository seminarRepository;
    @Autowired
    public SeminarServiceImpl(SeminarRepository seminarRepository) {
        this.seminarRepository = seminarRepository;
    }

    // Method to find paginated Seminar records.
    public Page<Seminar> findPaginated(Pageable pageable, String term) {
        return page(pageable, term);
    }

    // Helper method for paginating Seminar records.
    private Page<Seminar> page(Pageable pageable , String term) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        ArrayList<Seminar> seminars;
        List<Seminar> list;

        if (term == null) {
            // If 'term' is null, retrieve all seminars.
            seminars = (ArrayList<Seminar>) seminarRepository.findAll();
        }else {
            // If 'term' is provided, retrieve seminars by name containing the term.
            seminars = (ArrayList<Seminar>) seminarRepository.findByNameContaining(term);
        }

        if (seminars.size() < startItem) {
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, seminars.size());
            list = seminars.subList(startItem, toIndex);
        }
        // Create a paginated Page object for seminars.
        Page<Seminar> seminarPage = new PageImpl<>(list, PageRequest.of(currentPage,pageSize),seminars.size());

       return seminarPage;

    }

    @Override
    public void save(Seminar seminar) {
        // Save a seminar record.
        seminarRepository.save(seminar);
    }


    @Override
    public Optional<Seminar> findSeminarById(Long id) {
        // Find a seminar record by its ID.
        Optional<Seminar> seminar = seminarRepository.findById(id);
        return seminar;
    }
    @Override
    public void delete(Long id) {
        // Delete a seminar record by its ID.
        seminarRepository.deleteById(id);
    }



}
