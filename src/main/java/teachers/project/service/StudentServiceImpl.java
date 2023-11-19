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

    // Method to find paginated StudentSeminar records.
    @Override
    public Page<StudentSeminar> findPaginated(Pageable pageable, String term) {
        return page(pageable, term);
    }

    // Method to create Vest records for a student and seminars.
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

    // Method to find Vest records for a specific student.
    @Override
    public List<StudentSeminar> findVestsByStudentId(Long id) {
       List<Vest> vests = (List<Vest>) vestRepository.findAll();

        // Grouping Vest records by Student and mapping them to Seminars.
        Map<Student, List<Seminar>> studentSeminarMap = vests.stream()
                .collect(Collectors.groupingBy(Vest::getStudent, Collectors.mapping(Vest::getSeminar, Collectors.toList())));

        // Creating StudentSeminar objects from grouped data.
        List<StudentSeminar> studentSeminars = studentSeminarMap.entrySet().stream()
                .map(entry -> new StudentSeminar(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        // Filtering StudentSeminar records for the specified student.
        return studentSeminars.stream()
                .filter(studentSeminar -> studentSeminar.getStudent().getId().equals(id))
                .collect(Collectors.toList());
    }

    // Helper method for paginating StudentSeminar records.
    private Page<StudentSeminar> page(Pageable pageable, String term) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Vest> vests;
        if (term == null) {
            vests = (List<Vest>) vestRepository.findAll();
        } else {
            LocalDate date = LocalDate.parse(term);
            vests = new ArrayList<>(vestRepository.findByVestDate(date));
        }

        // Grouping Vest records by Student and mapping them to Seminars.
        Map<Student, List<Seminar>> studentSeminarMap = vests.stream()
                .collect(Collectors.groupingBy(Vest::getStudent, Collectors.mapping(Vest::getSeminar, Collectors.toList())));

        // Creating StudentSeminar objects from grouped data.
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
