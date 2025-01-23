package school.service;

import lombok.NonNull;
import school.domain.Enrolement;
import school.repository.EnrolementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolementService {

    private final EnrolementRepository enrolementRepository;

    public EnrolementService(EnrolementRepository enrolementRepository) {
        this.enrolementRepository = enrolementRepository;
    }

    // save or update the enrolement
    public Enrolement save(@NonNull Enrolement enrolement) {
        return enrolementRepository.save(enrolement);
    }

    public void removeEnrolement(@NonNull Long id) {
        enrolementRepository.deleteById(id);
    }

    public List<Enrolement> getAllEnrolements(@NonNull Long id) {
        return enrolementRepository.findByStudentId(id);
    }
    public boolean checkSubjectEnrolement(@NonNull Long id) {
        return enrolementRepository.existsBySubjectId(id);
    }
    public void removeStudentEnrolement(@NonNull Long id) {
        enrolementRepository.deleteByStudentId(id);
    }
}
