package school.repository;

import school.domain.Enrolement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolementRepository extends JpaRepository<Enrolement, Long> {
    // Custom query methods (if needed)
    List<Enrolement> findByStudentId(Long studentId);
    boolean existsBySubjectId(Long subjectId);
    void deleteByStudentId(Long studentId);
}
