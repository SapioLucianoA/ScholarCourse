package MindHub.MindHubBackEndCurse.Repositories;


import MindHub.MindHubBackEndCurse.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student,String> {
    boolean existsByEmail(String email);
    Student findByEmail (String email);

    Optional<Student> findById(String id);

    List<Student> findByNameAndLastNameContaining (String name, String lastName);

    List<Student> findByNameContaining(String name);

    List<Student> findByLastNameContaining(String lastName);

    @Query("SELECT s FROM Student s JOIN s.courseStudents cs WHERE s.lastName = :lastName AND cs.course.courseName = :courseName")
    List<Student> findByLastNameAndCourseName(@Param("lastName") String lastName, @Param("courseName") String courseName);


}
