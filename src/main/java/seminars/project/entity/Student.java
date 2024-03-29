package seminars.project.entity;



import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "{billing.name.notBlank}")
    @Size(max = 50, message = "{billing.name.maxSize}")
    private String name;

    @Column(name = "surname", nullable = false)
    @NotBlank(message = "{billing.surname.notBlank}")
    @Size(max = 50, message = "{billing.surname.maxSize}")
    private String surname;


    @Column(name = "city", nullable = false)
    @NotBlank(message = "{billing.city.notBlank}")
    @Size(max = 60, message = "{billing.city.maxSize}")
    private String city;

    @Column(name = "postal_code", nullable = false)
    @NotBlank(message = "{billing.postalCode.notBlank}")
    @Size(max = 18, message = "{billing.postalCode.maxSize}")
    private String postalCode;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "{billing.phoneNumber.notBlank}")
    @Size(max = 15, message = "{billing.phoneNumber.maxSize}")
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "{billing.email.notBlank}")
    @Size(max = 254, message = "{billing.email.maxSize}")
    @Email
    private String email;



    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Order> orders;

    public Student(Long id, String name, String surname, String city, String postalCode, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
