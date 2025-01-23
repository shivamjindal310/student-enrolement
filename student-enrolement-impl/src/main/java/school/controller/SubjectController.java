package school.controller;

import lombok.NonNull;
import school.domain.Subject;
import school.service.EnrolementService;
import school.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;
    private final EnrolementService enrolementService;

    public SubjectController(
            @NonNull EnrolementService enrolementService,
            @NonNull SubjectService subjectService
    ) {
        this.subjectService = subjectService;
        this.enrolementService = enrolementService;
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.save(subject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
        if(!enrolementService.checkSubjectEnrolement(id)) {
            subjectService.removeSubject(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(404).body("A Student is enrolled in the subject");
    }
}
