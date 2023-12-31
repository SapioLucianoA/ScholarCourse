package MindHub.MindHubBackEndCurse.Repositories;

import MindHub.MindHubBackEndCurse.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,String> {
    Person findByEmail(String email);


    boolean existsByEmail(String email);
}
