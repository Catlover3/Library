package se.school.runar.Library.data;

import org.springframework.data.repository.CrudRepository;
import se.school.runar.Library.models.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepo extends CrudRepository <Loan, Long> {

    Optional <Loan> findByLoanId(long id);
    List<Loan> findAll();
    List<Loan> findByCustomerCustomerId(int id);
    List<Loan> findByBookBookId(int id);
    List<Loan> findByLostStatus(boolean lostStatus);

}
