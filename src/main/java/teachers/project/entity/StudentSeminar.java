package teachers.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class StudentSeminar {

    private Student student;
    private List<Seminar> seminars;


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
