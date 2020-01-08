package se.school.runar.Library.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

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
        assertFalse(loanTestTodaysDate.getLostStatus());
        assertFalse(loanTestTodaysDate.isOverdue());
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
    void getFine(){//This test will stop working once the value goes over 1000 which will happen around 02-20
        long daysPassed = ChronoUnit.DAYS.between(loanTestLoanDateExceeded.getDueDate(), todaysDate);
        long fine = daysPassed*10;
        BigDecimal expectedFine = new BigDecimal(fine);
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
        LocalDate today = LocalDate.now();

        assertNotEquals(today, loanTest.getLoandate());
        loanTest.setLoanDate(today);
        assertEquals(today, loanTest.getLoandate() );
    }

    @Test
    public void copyOf_testObject_equals_is_true(){
        Loan copy = new Loan(bookTest, customerTest, todaysDate, false);
        assertEquals(copy, loanTestTodaysDate);
        assertEquals(copy.hashCode(), loanTestTodaysDate.hashCode());
    }

    @Test
    public void hashcode_works(){
        Loan copy = new Loan(bookTest, customerTest, todaysDate, false);
        assertEquals(copy.hashCode(), loanTestTodaysDate.hashCode());
    }

    @Test
    public void toString_contains_correct_information() {
        String toString = loanTest.toString();
        assertTrue(
                toString.contains("Game of Thrones") &&
                        toString.contains("7") &&
                        toString.contains("10") &&
                        toString.contains("Fantasy with swords, shields and dragons") &&
                        toString.contains("2018-01-01")&&
                        toString.contains("Olof") &&
                        toString.contains("olof.svensson@mail.se") &&
                        toString.contains("2019-12-29")
        );
    }


}//End of class
