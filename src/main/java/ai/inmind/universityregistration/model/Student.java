package ai.inmind.universityregistration.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "The database generated ID", required = true)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @ApiModelProperty(notes = "The student's first name", required = true)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @ApiModelProperty(notes = "The student's last name", required = true)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "student_enroll_in_class",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    @ToString.Exclude
    @ApiModelProperty(notes = "A list of classes in which the student is enrolled")
    private List<Class> enrolledIn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, enrolledIn);
    }
}
