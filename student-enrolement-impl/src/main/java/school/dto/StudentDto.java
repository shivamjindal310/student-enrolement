package school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import school.domain.Student;
import school.domain.Subject;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentDto {
    @NonNull
    private Student student;
    private List<Subject> subjects;
}
