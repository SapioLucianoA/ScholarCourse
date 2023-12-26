package MindHub.MindHubBackEndCurse.Repositories;

import MindHub.MindHubBackEndCurse.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Admin,String> {
    Admin findByEmail(String email);

    boolean existsByEmail(String email);

    Admin findByEmailAndLastName(String name, String lastName);
    Optional<Admin> findById(String id);
}
