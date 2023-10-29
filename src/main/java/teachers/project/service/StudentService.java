package teachers.project.service;

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

@Service
public class StudentService {

    private final VestRepository vestRepository;
    private final BillingRepository billingRepository;

    public StudentService(VestRepository vestRepository, BillingRepository billingRepository) {
        this.vestRepository = vestRepository;
        this.billingRepository = billingRepository;
    }


    //create-pagination of students vest - showing page
    public Page<StudentSeminar> findPaginated(Pageable pageable, String term) {
        return page(pageable, term);
    }
    //showing vest page
    private Page<StudentSeminar> page(Pageable pageable, String term) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        ArrayList<Vest> vests = null;
        List<StudentSeminar> list;
        //control vest
        if (term == null) {
            vests = (ArrayList<Vest>) vestRepository.findAll();
        }else {
            LocalDate date = LocalDate.parse(term);
            vests = (ArrayList<Vest>) vestRepository.findByVestDate(date);
        }
        Map<Student, List<Seminar>> studentSeminarMap = vests.stream().collect(
                Collectors.groupingBy(Vest::getStudent,Collectors.mapping(Vest::getSeminar,Collectors.toList())));

        List<StudentSeminar> studentSeminar  = studentSeminarMap.entrySet().stream()
                .map(entry -> new StudentSeminar(entry.getKey(),entry.getValue())).collect(Collectors.toList());

        if (studentSeminar.size() < startItem) {
            list = Collections.emptyList();
        }else {
            int toIndex = Math.min(startItem + pageSize, studentSeminar.size());
            list = studentSeminar.subList(startItem,toIndex);
        }

        Page<StudentSeminar> orderPage = new PageImpl<>(list, PageRequest.of(currentPage,pageSize),
                studentSeminar.size());
        return orderPage;
    }

    //vest creation and store in student
    @Transactional
    public void createVest(Student student, List<Seminar>seminars) {

        billingRepository.save(student);

        for (Seminar s : seminars) {
            Vest vest = new Vest();
            vest.setStudent(student);
            vest.setVestDate(LocalDate.now());
            vest.setSeminar(s);
            vestRepository.save(vest);
        }
    }

    //showing student id in seminar
    public List<StudentSeminar> findVestsByStudentId(Long id) {

        List<Vest> vests = (List<Vest>) vestRepository.findAll();

        Map<Student, List<Seminar>> studentSeminarMap = vests.stream().collect(Collectors.groupingBy(Vest::getStudent, Collectors.mapping(Vest::getSeminar, Collectors.toList())));


        List<StudentSeminar> studentSeminar = studentSeminarMap.entrySet().stream()
                .map(entry-> new StudentSeminar(entry.getKey(),entry.getValue())).collect(Collectors.toList());

        studentSeminar.stream().filter(s->s.getStudent().getId().equals(id)).findAny().isPresent();

    return studentSeminar;
    }


}
