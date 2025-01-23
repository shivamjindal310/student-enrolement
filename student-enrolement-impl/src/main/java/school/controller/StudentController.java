package school.controller;

import lombok.NonNull;
import school.domain.Enrolement;
import school.domain.Student;
import school.domain.Subject;
import school.dto.StudentDto;
import school.service.EnrolementService;
import school.service.StudentService;
import school.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final EnrolementService enrolementService;

    public StudentController(
            @NonNull StudentService studentService,
            @NonNull SubjectService subjectService,
            @NonNull EnrolementService enrolementService
            ) {
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.enrolementService = enrolementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        return studentService.findStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> getStudentData(@PathVariable Long id) {
        Optional<Student> student = studentService.findStudentById(id);
        Student studentRequested;
        try {
            studentRequested = student.orElseThrow(() -> new IllegalStateException("No value present!"));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Invalid Student Id");
        }
        StudentDto studentDto = new StudentDto(
                studentRequested,
                null
        );
        List<Enrolement> enrolementsOfStudent = enrolementService.getAllEnrolements(id);
        List<Long> subjectIds = enrolementsOfStudent.stream()
                .map(Enrolement::getSubjectId) // Map each subject to its ID
                .toList();

        List<Subject> subjects = subjectService.getAllSubjectsForGivenStudent(subjectIds);
        studentDto.setSubjects(subjects);
        return ResponseEntity.ok(studentDto);
    }


    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return studentService.findStudentById(student.getId())
                .map(studentUpdated -> {
                    studentUpdated.setName(student.getName());
                    return ResponseEntity.ok(studentService.save(studentUpdated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        if(!enrolementService.getAllEnrolements(id).isEmpty()) {
            enrolementService.removeStudentEnrolement(id);
        }
        if(studentService.findStudentById(id).isPresent()) {
            studentService.removeStudent(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
