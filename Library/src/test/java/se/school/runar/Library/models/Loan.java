package se.school.runar.Library.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import se.school.runar.Library.models.*;

public class Loan {
    private long loanId;
    private Book book;
    private Customer customer;
    private Loan loanTest;
    private LocalDate customerRegistrationDate;
    private LocalDate loanDate;
    private boolean lostStatus;
    private int loanExtensionInDays;

    public Loan() {
    }

    @BeforeEach
        void setup(){
        BigDecimal fine= new BigDecimal("10");
        customerRegistrationDate = LocalDate.now();
        loanDate = LocalDate.of(2019, 12, 16);

        loanId = 1;
        Book bookTest = new Book("Game of Thrones", true, false, 30, fine, "Fantasy with swords, shields and dragons");
        Customer customerTest = new Customer(customerRegistrationDate, "Olof", "olof.svensson@mail.se");
        Loan loanTest = new Loan(bookTest, customerTest, loanDate, false);

    }
    @Test
    void testSetters(){

    }


}//End of class
