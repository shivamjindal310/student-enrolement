package school.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import school.domain.Subject;
import school.service.EnrolementService;
import school.service.SubjectService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubjectControllerTest {
    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectService subjectService;

    @Mock
    private EnrolementService enrolementService;

    private Subject subject;

    @BeforeEach
    void setup() {
        subject = new Subject(1L, "physics");
    }

    @Test
    void saveSubjectTest() {
        doReturn(subject).when(subjectService).save((any(Subject.class)));

        Subject subjectRec = subjectController.createSubject(subject);
        verify(subjectService, times(1)).save(any(Subject.class));
        Assertions.assertEquals(subject, subjectRec);
    }

    @Test
    void deleteSubjectSuccessfulTest() {
        doReturn(false).when(enrolementService).checkSubjectEnrolement(any(Long.class));
        doNothing().when(subjectService).removeSubject((any(Long.class)));

        ResponseEntity<String> response = subjectController.deleteSubject(1L);
        Assertions.assertTrue(response.getStatusCode().toString().contains("204"));
    }

    @Test
    void deleteSubjectFailTest() {
        doReturn(true).when(enrolementService).checkSubjectEnrolement(any(Long.class));

        ResponseEntity<String> response = subjectController.deleteSubject(1L);
        Assertions.assertTrue(response.getStatusCode().toString().contains("404"));
    }
}
