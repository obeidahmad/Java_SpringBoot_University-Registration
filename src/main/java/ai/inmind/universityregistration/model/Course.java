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
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "The database generated ID", required = true)
    private Long id;

    @Column(name = "name", nullable = false)
    @ApiModelProperty(notes = "The course's name", required = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "prerequisite",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id_prerequisite"))
    @ToString.Exclude
    @ApiModelProperty(notes = "The course's prerequisites")
    private List<Course> prerequisites;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    @ApiModelProperty(notes = "The classes in which this course is given")
    private List<Class> classes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, prerequisites, classes);
    }
}
