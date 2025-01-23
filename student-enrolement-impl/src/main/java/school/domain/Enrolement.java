package school.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "enrolement",
        uniqueConstraints = @UniqueConstraint(columnNames = {"subject_id", "student_id"})
)
@Getter
@Setter
public class Enrolement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "student_id")
    private Long studentId;

    @Column(nullable = false, name = "subject_id")
    private Long subjectId;
}
