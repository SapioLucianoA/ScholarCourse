package MindHub.MindHubBackEndCurse.Repositories;

import MindHub.MindHubBackEndCurse.Models.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
}
