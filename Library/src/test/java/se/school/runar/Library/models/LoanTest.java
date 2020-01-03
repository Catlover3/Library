package se.school.runar.Library.models;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {
    @Autowired
    private Book bookTest;
    @Autowired
    private Customer customerTest;
    @Autowired
    private Loan loanTest;
    @Autowired
    private Loan loanTestLoanDateExceeded;
    @Autowired
    private Loan loanTestTodaysDate;
    @Autowired
    private Loan loanTestTwoYearsAgo;

    LocalDate todaysDate = LocalDate.now();
    private LocalDate customerRegistrationDate;
    private LocalDate loanDate;
    private LocalDate firstNov2019;
    private LocalDate twentyNineDec2019;
    private LocalDate firstJan2018;
    private boolean lostStatus;
    private int loanExtensionInDays;

    @BeforeEach
        void setup(){
        BigDecimal ten= new BigDecimal("10");
        customerRegistrationDate = LocalDate.now();
        twentyNineDec2019 = LocalDate.of(2019, 12, 29);
        firstNov2019 = LocalDate.of(2019, 11, 1);
        firstJan2018 = LocalDate.of(2018,1,1);


        bookTest = new Book("Game of Thrones", true, false, 7, ten, "Fantasy with swords, shields and dragons");
        customerTest = new Customer(customerRegistrationDate, "Olof", "olof.svensson@mail.se");
        loanTest = new Loan(bookTest, customerTest, twentyNineDec2019, false);
        loanTestTodaysDate = new Loan(bookTest, customerTest, todaysDate, false);
        loanTestLoanDateExceeded = new Loan(bookTest, customerTest, firstNov2019, false);
        loanTestTwoYearsAgo = new Loan(bookTest, customerTest, firstJan2018, false);
    }
    @Test
    void getDueDate(){
        LocalDate testedValue = loanTestTodaysDate.getDueDate();
        LocalDate expectedValue = todaysDate.plusDays(7);
        assertEquals(expectedValue, testedValue);
    }

    @Test
    void extendLoan_adds_extension_Days_correctly(){
        loanTestTodaysDate.extendLoan(3);
        LocalDate testedValue = loanTestTodaysDate.getDueDate();
        LocalDate expectedValue = todaysDate.plusDays(7 + 3);
        assertEquals(expectedValue, testedValue);
    }

    @Test
    void addExtensionDays_first_add_must_be_true(){
        assertTrue(loanTestTodaysDate.extendLoan(3));
    }

    @Test
    void addExtensionDays_second_add_must_be_false(){
        loanTestTodaysDate.extendLoan(3);
        assertFalse(loanTestTodaysDate.extendLoan(5));
    }

    @Test
    void addExtensionDays_adds_to_sum(){
        LocalDate expected = todaysDate.plusDays(15);
        loanTestTodaysDate.extendLoan(3);
        loanTestTodaysDate.extendLoan(5);
        assertEquals(expected, loanTestTodaysDate.getDueDate());
    }

    @Test
    void loan_is_not_Overdue(){
        assertFalse(loanTest.getLostStatus());
        assertFalse(loanTest.isOverdue());
    }

    @Test
    void Overdue_loststatus_true(){
        assertTrue(loanTestLoanDateExceeded.isOverdue());
        assertTrue(loanTestLoanDateExceeded.getLostStatus());
    }
    @Test
    void Overdue_loststatus_false(){
        assertFalse(loanTestLoanDateExceeded.getLostStatus());
    }

    @Test
    void _Overdue_loststatus_false(){
        LocalDate todaysDate = LocalDate.now();
        LocalDate afterTodaysDate = LocalDate.of(2020,8,4);
    }

    @Test
    void getFine(){
        BigDecimal expectedFine = new BigDecimal(560);
        assertEquals(expectedFine, loanTestLoanDateExceeded.getFine());
    }

    @Test
    void getFine_fine_is_not_negative(){
        BigDecimal expectedFine = new BigDecimal(-1);
        assertNotEquals(expectedFine, loanTest.getFine());
        assertNotEquals(expectedFine, loanTestLoanDateExceeded.getFine());
    }

    @Test
    void getFine_should_not_go_over_1000(){
        BigDecimal expectedFine = new BigDecimal(1000);
        assertEquals(expectedFine, loanTestTwoYearsAgo.getFine());
    }

    @Test
    void setLoandate_DateTime(){
        //LocalDate ofToday = LocalDate.of(2020,8,26);
        LocalDate today = LocalDate.now();
        loanTest.setLoanDate(today);
        assertEquals(today, loanTest.getLoandate() );
    }

//    @Test
//    void test_LocalDate(){
//        LocalDate today = LocalDate.now();
//        LocalDate ofToday = LocalDate.of(2020,01,03);
//        LocalDate parseToday = LocalDate.parse("2020-01-03");
//        assertEquals(today, ofToday);
//        assertEquals(today, parseToday);
//        assertEquals(ofToday, parseToday);
//    }


}//End of class
