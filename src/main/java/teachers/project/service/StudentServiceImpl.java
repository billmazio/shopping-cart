package teachers.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teachers.project.entity.Seminar;
import teachers.project.entity.Student;
import teachers.project.entity.StudentSeminar;
import teachers.project.entity.Vest;
import teachers.project.repository.BillingRepository;
import teachers.project.repository.VestRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements IStudentService {

    private final VestRepository vestRepository;
    private final BillingRepository billingRepository;

    @Autowired
    public StudentServiceImpl(VestRepository vestRepository, BillingRepository billingRepository) {
        this.vestRepository = vestRepository;
        this.billingRepository = billingRepository;
    }

    @Override
    public Page<StudentSeminar> findPaginated(Pageable pageable, String term) {
        return page(pageable, term);
    }

    @Override
    @Transactional
    public void createVest(Student student, List<Seminar> seminars) {
        billingRepository.save(student);

        for (Seminar seminar : seminars) {
            Vest vest = new Vest();
            vest.setStudent(student);
            vest.setVestDate(LocalDate.now());
            vest.setSeminar(seminar);
            vestRepository.save(vest);
        }
    }

    @Override
    public List<StudentSeminar> findVestsByStudentId(Long id) {
       List<Vest> vests = (List<Vest>) vestRepository.findAll();


        Map<Student, List<Seminar>> studentSeminarMap = vests.stream()
                .collect(Collectors.groupingBy(Vest::getStudent, Collectors.mapping(Vest::getSeminar, Collectors.toList())));

        List<StudentSeminar> studentSeminars = studentSeminarMap.entrySet().stream()
                .map(entry -> new StudentSeminar(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return studentSeminars.stream()
                .filter(studentSeminar -> studentSeminar.getStudent().getId().equals(id))
                .collect(Collectors.toList());
    }

    private Page<StudentSeminar> page(Pageable pageable, String term) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

//        ArrayList<Vest> vests;
//        if (term == null) {
//            vests = (ArrayList<Vest>) vestRepository.findAll();
//        } else {
//            LocalDate date = LocalDate.parse(term);
//            vests = (ArrayList<Vest>) vestRepository.findByVestDate(date);
//        }
        List<Vest> vests;
        if (term == null) {
            vests = (List<Vest>) vestRepository.findAll();
        } else {
            LocalDate date = LocalDate.parse(term);
            vests = new ArrayList<>(vestRepository.findByVestDate(date));
        }

        Map<Student, List<Seminar>> studentSeminarMap = vests.stream()
                .collect(Collectors.groupingBy(Vest::getStudent, Collectors.mapping(Vest::getSeminar, Collectors.toList())));

        List<StudentSeminar> studentSeminarList = studentSeminarMap.entrySet().stream()
                .map(entry -> new StudentSeminar(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        List<StudentSeminar> list;
        if (studentSeminarList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, studentSeminarList.size());
            list = studentSeminarList.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), studentSeminarList.size());
    }
}
