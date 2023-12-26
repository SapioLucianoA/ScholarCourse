package MindHub.MindHubBackEndCurse.Repositories;

import MindHub.MindHubBackEndCurse.Models.Admin;
import MindHub.MindHubBackEndCurse.Models.Student;
import MindHub.MindHubBackEndCurse.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student,String> {
    boolean existsByEmail(String email);
    Student findByEmail (String email);


    @Override
    Optional<Student> findById(String id);
    List<Student> findByNameAndLastName (String name, String lastName);
}
