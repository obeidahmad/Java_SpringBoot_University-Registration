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
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "The database generated ID", required = true)
    private Long id;

    @Column(name = "semester", nullable = false)
    @ApiModelProperty(notes = "The semester during which this class is given", required = true)
    private String semester;

    @Column(name = "day", nullable = false)
    @ApiModelProperty(notes = "The day during which this class is given", required = true)
    private String day;

    @Column(name = "session", nullable = false)
    @ApiModelProperty(notes = "The session during which this class is given", required = true)
    private Integer session;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    @ApiModelProperty(notes = "The instructor who teach this class")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @ApiModelProperty(notes = "The course taught in this class")
    private Course course;

    @ManyToMany
    @JoinTable(name = "student_enroll_in_class",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @ToString.Exclude
    @ApiModelProperty(notes = "The students enrolled in this class")
    private List<Student> enrolledStudents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Class aClass = (Class) o;
        return Objects.equals(id, aClass.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, semester, day, session, instructor, course);
    }
}
