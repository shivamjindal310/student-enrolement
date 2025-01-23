package school.service;

import lombok.NonNull;
import school.domain.Student;
import school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // save or update the student
    public Student save(@NonNull Student student) {
        System.out.println("saving student");
        return studentRepository.save(student);
    }

    public Optional<Student> findStudentById(@NonNull Long id) {
        return studentRepository.findById(id);
    }

    public void removeStudent(@NonNull Long id) {
        studentRepository.deleteById(id);
    }
}
