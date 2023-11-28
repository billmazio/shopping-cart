package seminars.project.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@NoArgsConstructor
public class StudentSeminar {

    private Student student;
    private List<Seminar> seminars;


    public StudentSeminar(Student student, List<Seminar> seminars) {
        this.student = student;
        this.seminars = seminars;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Seminar> getSeminars() {
        return seminars;
    }

    public void setSeminars(List<Seminar> seminars) {
        this.seminars = seminars;
    }
}
