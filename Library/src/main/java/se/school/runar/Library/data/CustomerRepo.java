package se.school.runar.Library.data;

import org.springframework.data.repository.CrudRepository;
import se.school.runar.Library.models.Customer;
import se.school.runar.Library.models.Loan;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends CrudRepository <Customer, Integer> {

    Optional <Customer> findByCustomerId(int id);
    List<Customer> findAll();
    List<Customer> findByEmail (String email);

}
