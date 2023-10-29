package teachers.project.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "seminars")
public class Seminar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "{seminar.name.notBlank}")
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull(message = "{seminar.price.notBlank}")
    private BigDecimal price;

    @Column(name = "teachers", nullable = false)
    @NotBlank(message = "{seminar.teachers.notBlank}")
    private String teachers;

    @Column(name = "code", nullable = false)
    @NotBlank(message = "{seminar.code.notBlank}")
    @Pattern(regexp = "\\d{10}|\\d{13}", message = "{seminar.code.size}")
    private String code;

    @Column(name = "scientific_manager", nullable = false)
    @NotBlank(message = "{seminar.scientific_manager.notBlank}")
    private String scientificManager;

    @Column(name = "published_on", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "{seminar.date.notNull}")
    private LocalDate publishedOn;

    @OneToMany(mappedBy = "seminar", cascade = CascadeType.ALL)
    private List<Vest> vests;
}