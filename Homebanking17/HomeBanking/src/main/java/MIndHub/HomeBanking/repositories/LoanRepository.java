package MIndHub.HomeBanking.repositories;

import MIndHub.HomeBanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository <Loan, String> {
}
