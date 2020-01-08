package se.school.runar.Library.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.school.runar.Library.models.Book;
import se.school.runar.Library.models.Customer;
import se.school.runar.Library.models.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LoanRepoTest {

    @Autowired
    LoanRepo loanRepo;

    @Autowired
    TestEntityManager em;

    private Book bookTest;
    private Customer customerTest;
    private Loan loanTest;
    private Loan loanTestLoanDateExceeded;
    private Loan loanTestTodaysDate;
    private Loan loanTestTwoYearsAgo;

    LocalDate todaysDate = LocalDate.now();
    private LocalDate customerRegistrationDate;
    private LocalDate loanDate;
    private LocalDate firstNov2019;
    private LocalDate twentyNineDec2019;
    private LocalDate firstJan2018;
    private boolean lostStatus;
    private int loanExtensionInDays;

    long loanId;
    int customerId;
    int boookId;

    @BeforeEach
    void setup(){
        BigDecimal ten= new BigDecimal("10");
        customerRegistrationDate = LocalDate.now();
        twentyNineDec2019 = LocalDate.of(2019, 12, 29);
        firstNov2019 = LocalDate.of(2019, 11, 1);
        firstJan2018 = LocalDate.of(2018,1,1);


        bookTest = new Book("Game of Thrones", true, false, 7, ten, "Fantasy with swords, shields and dragons");
        customerTest = new Customer(firstJan2018, "Olof", "olof.svensson@mail.se");
        loanTest = new Loan(bookTest, customerTest, twentyNineDec2019, false);
        loanTestTodaysDate = new Loan(bookTest, customerTest, todaysDate, false);
        loanTestLoanDateExceeded = new Loan(bookTest, customerTest, firstNov2019, false);
        loanTestTwoYearsAgo = new Loan(bookTest, customerTest, firstJan2018, false);

        em.persistAndFlush(bookTest);
        em.persistAndFlush(customerTest);
        em.persistAndFlush(loanTest);
        em.persistAndFlush(loanTestTodaysDate);
        em.persistAndFlush(loanTestLoanDateExceeded);
        em.persistAndFlush(loanTestTwoYearsAgo);
        this.loanId = loanTest.getLoanId();
        this.customerId = customerTest.getCustomerId();
        this.boookId = bookTest.getBookId();
    }

    @Test
    void find_by_LoanId(){
         Optional<Loan> loanSearch = loanRepo.findByLoanId(loanId);
         assertEquals(loanTest, loanSearch.get());
         assertTrue(loanSearch.isPresent());
    }

    @Test
    void find_by_CustomerId_works(){
        List<Loan> loanSearch = loanRepo.findByCustomerCustomerId(customerId);
        loanSearch = loanRepo.findByCustomerCustomerId(customerId);
        assertTrue(loanSearch.contains(loanTest));
    }

    @Test
    void find_by_bookId_works(){
        List<Loan> loanSearch;
        loanSearch = loanRepo.findByBookBookId(boookId);
        assertTrue(loanSearch.contains(loanTest));
    }
    @Test
    void find_by_loststatus_works(){
        List<Loan> loanSearch;
        loanSearch = loanRepo.findByLostStatus(false);
        assertTrue(loanSearch.contains(loanTest));
    }

    @Test
    void find_all_contains_all_objects(){
        List<Loan> loanList;

        loanList = loanRepo.findAll();
        assertTrue(loanList.contains(loanTest));
        assertTrue(loanList.contains(loanTestTodaysDate));
        assertTrue(loanList.contains(loanTestLoanDateExceeded));
        assertTrue(loanList.contains(loanTestTwoYearsAgo));
    }

    @Test
    void find_all_is_right_size() {
        List<Loan> loanList;
        loanList = loanRepo.findAll();
        assertEquals(4, loanList.size());
    }



}//End of class
