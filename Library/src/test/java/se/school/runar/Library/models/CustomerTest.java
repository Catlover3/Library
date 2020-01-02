package se.school.runar.Library.models;

import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerTest {
    private Book book;
    private Customer customer;
    private Loan loan;
    private LocalDate customerRegistrationDate;
    private LocalDate loanDate;
    private boolean lostStatus;
    private int loanExtensionInDays;


    @BeforeEach
    void setup(){
        BigDecimal fine= new BigDecimal("10");
        customerRegistrationDate = LocalDate.now();
        loanDate = LocalDate.of(2019, 12, 16);

        Book bookTest = new Book("Game of Thrones", true, false, 30, fine, "Fantasy with swords, shields and dragons");
        Customer customerTest = new Customer(customerRegistrationDate, "Olof", "olof.svensson@mail.se");
        Loan loan = new Loan(bookTest, customerTest, loanDate, false);

//        this.book = book;
//        this.customerTest = customerTest;
//        this.loanDate = loanDate;
//        this.lostStatus = lostStatus;

    }
}//End of class
