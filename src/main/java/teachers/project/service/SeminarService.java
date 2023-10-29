package teachers.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import teachers.project.entity.Seminar;
import teachers.project.repository.SeminarRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class SeminarService {

    private final SeminarRepository seminarRepository;

    public SeminarService(SeminarRepository seminarRepository) {
        this.seminarRepository = seminarRepository;
    }

    //create pagination of seminars
    public Page<Seminar> findPaginated(Pageable pageable, String term) {
        return page(pageable, term);
    }

    //showing seminars page
    private Page<Seminar> page(Pageable pageable , String term) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        ArrayList<Seminar> seminars;
        List<Seminar> list;

        if (term == null) {
            seminars = (ArrayList<Seminar>) seminarRepository.findAll();
        }else {
            seminars = (ArrayList<Seminar>) seminarRepository.findByNameContaining(term);
        }

        if (seminars.size() < startItem) {
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, seminars.size());
            list = seminars.subList(startItem, toIndex);
        }
        Page<Seminar> seminarPage = new PageImpl<>(list, PageRequest.of(currentPage,pageSize),seminars.size());

       return seminarPage;

    }

    public void save(Seminar seminar) {
        seminarRepository.save(seminar);
    }

    public Optional<Seminar> findSeminarById(Long id) {
        Optional<Seminar> seminar = seminarRepository.findById(id);
        return seminar;
    }

    public void delete(Long id) {
        seminarRepository.deleteById(id);
    }



}
