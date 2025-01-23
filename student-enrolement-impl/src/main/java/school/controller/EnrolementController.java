package school.controller;


import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.domain.Enrolement;
import school.domain.Student;
import school.domain.Subject;
import school.service.EnrolementService;
import school.service.StudentService;
import school.service.SubjectService;

@RestController
@RequestMapping("/api/enrolement")
public class EnrolementController {

    private final EnrolementService enrolementService;
    private final SubjectService subjectService;
    private final StudentService studentService;

    public EnrolementController(
            @NonNull EnrolementService enrolementService,
            @NonNull SubjectService subjectService,
            @NonNull StudentService studentService
    ) {
        this.enrolementService = enrolementService;
        this.subjectService = subjectService;
        this.studentService = studentService;
    }
    @PostMapping
    public Enrolement addEnrolement(@RequestBody Enrolement enrolement) {
        //check if student and subject exists
        try {
            Student studentRequested = studentService
                    .findStudentById(enrolement.getStudentId())
                    .orElseThrow(
                            () -> new IllegalStateException("No value present!")
                    );

        } catch (Exception e) {
            return null;
        }
        try {
            Subject subjectRequested = subjectService
                    .findSubjectById(enrolement.getSubjectId())
                    .orElseThrow(
                            () -> new IllegalStateException("No value present!")
                    );

        } catch (Exception e) {
            return null;
        }
        return enrolementService.save(enrolement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Enrolement> deleteEnrolement(@PathVariable Long id) {
        enrolementService.removeEnrolement(id);
        return ResponseEntity.noContent().build();
    }
}
