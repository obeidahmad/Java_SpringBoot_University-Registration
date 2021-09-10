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
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "The database generated ID", required = true)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @ApiModelProperty(notes = "The instructor's first name", required = true)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @ApiModelProperty(notes = "The instructor's last name", required = true)
    private String lastName;

    @OneToMany(mappedBy = "instructor")
    @ToString.Exclude
    @ApiModelProperty(notes = "A list of classes given by the instructor")
    private List<Class> classes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, classes);
    }
}
