package se.school.runar.Library.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanTest {
    private Book book;
    private Customer customer;
    private Loan loanTest;
    private Loan loanTestLoanDateExceeded;
    private LocalDate customerRegistrationDate;
    private LocalDate loanDate;
    private boolean lostStatus;
    private int loanExtensionInDays;

    @BeforeEach
        void setup(){
        BigDecimal fine= new BigDecimal("10");
        customerRegistrationDate = LocalDate.now();
        LocalDate fourthAug2020 = LocalDate.of(2020, 8, 4);
        loanDate = LocalDate.of(2019, 12, 16);

        Book bookTest = new Book("Game of Thrones", true, false, 1, fine, "Fantasy with swords, shields and dragons");
        Customer customerTest = new Customer(customerRegistrationDate, "Olof", "olof.svensson@mail.se");
        loanTest = new Loan(bookTest, customerTest, loanDate, false);
        loanTestLoanDateExceeded = new Loan(bookTest, customerTest, fourthAug2020, false);

    }
    @Test
    void getDueDate(){
        LocalDate test = loanTest.getDueDate();
        LocalDate expectedValue = LocalDate.of(2019,12,17);
        assertEquals(expectedValue, test);
    }

    @Test
    void getFine(){
        BigDecimal expectedFine = new BigDecimal(500);
        assertEquals(expectedFine, loanTestLoanDateExceeded.getFine());
    }

    @Test
    void setLoandate_DateTime(){
        //LocalDate ofToday = LocalDate.of(2020,8,26);
        LocalDate today = LocalDate.now();
        loanTest.setLoanDate(today);
        assertEquals(today, loanTest.getLoandate() );
    }

    @Test
    void test_LocalDate(){
        LocalDate today = LocalDate.now();
        LocalDate ofToday = LocalDate.of(2020,01,02);
        LocalDate parseToday = LocalDate.parse("2020-01-02");
        assertEquals(today, ofToday);
        assertEquals(today, parseToday);
        assertEquals(ofToday, parseToday);
    }


}//End of class
