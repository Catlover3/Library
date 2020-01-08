package se.school.runar.Library.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.school.runar.Library.models.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    TestEntityManager em;

    private Customer customerTest;
    private LocalDate firstJan2018;

    int customerId;


    @BeforeEach
    void setup(){
        BigDecimal ten= new BigDecimal("10");
        firstJan2018 = LocalDate.of(2018,1,1);

        customerTest = new Customer(firstJan2018, "Olof", "olof.svensson@mail.se");

        em.persistAndFlush(customerTest);
        this.customerId = customerTest.getCustomerId();

    }

    @Test
    void find_by_CustomerId(){
        Optional<Customer> customerSearch = customerRepo.findByCustomerId(customerId);
        assertEquals(customerTest, customerSearch.get());
        assertTrue(customerSearch.isPresent());
    }

    @Test
    void find_all_contains_all_objects(){
        List<Customer> customerList;

        customerList = customerRepo.findAll();
        assertTrue(customerList.contains(customerTest));
    }

    @Test
    void find_all_is_right_size() {
        List<Customer> customerList;
        customerList = customerRepo.findAll();
        assertEquals(1, customerList.size());
    }
}
