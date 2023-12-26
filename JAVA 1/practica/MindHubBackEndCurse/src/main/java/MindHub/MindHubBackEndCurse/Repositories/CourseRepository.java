package MindHub.MindHubBackEndCurse.Repositories;

import MindHub.MindHubBackEndCurse.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CourseRepository extends JpaRepository<Course,String> {
}
