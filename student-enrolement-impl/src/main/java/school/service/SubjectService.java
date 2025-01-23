package school.service;

import lombok.NonNull;
import school.domain.Subject;
import school.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(
            SubjectRepository subjectRepository
            ) {
        this.subjectRepository = subjectRepository;
    }

    // save or update the subject
    public Subject save(@NonNull Subject subject) {
        return subjectRepository.save(subject);
    }

    public void removeSubject(@NonNull Long id) {
        subjectRepository.deleteById(id);
    }

    public Optional<Subject> findSubjectById(@NonNull Long id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> getAllSubjectsForGivenStudent(@NonNull List<Long> subjects) {
        return subjectRepository.findAllById(subjects);
    }
}
