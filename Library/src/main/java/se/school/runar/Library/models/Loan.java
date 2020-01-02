package se.school.runar.Library.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Loan {
    private long loanId;
    private Book book;
    private Customer customer;
    private LocalDate loanDate;
    private boolean lostStatus;
    private int loanExtensionInDays;

    public Loan(Book book, Customer customer, LocalDate loanDate, boolean lostStatus) {
        this.book = book;
        this.customer = customer;
        this.loanDate = loanDate;
        this.lostStatus = lostStatus;
    }
    public LocalDate getDueDate(){
        int actualLoanTimeInDays = book.getLoanTimeInDays() + loanExtensionInDays;
        return loanDate.plusDays(actualLoanTimeInDays);
    }

    public boolean isOverdue(){
        LocalDate todaysDate = LocalDate.now();
        if ((todaysDate.isAfter(getDueDate()) )){

            lostStatus = true;
            return true;
        }
        return false;
    }

    public BigDecimal getFine(){
        if(isOverdue()){
            long daysPassed = ChronoUnit.DAYS.between(loanDate, getDueDate());
            BigDecimal fine = book.getFinePerDay();
            BigDecimal sumOfFine = fine.multiply(BigDecimal.valueOf(daysPassed));

            BigDecimal maxFine = new BigDecimal(1000);
            if (sumOfFine.compareTo(maxFine) == 1){// 1 beyder att summan är större än jämförelsevärdet inom parentesen
                sumOfFine.valueOf(1000);
                return  sumOfFine;
            }
            return  sumOfFine;

        }
        else{
            throw new IllegalArgumentException("the book is not overdue");
        }
    }

    public boolean extendLoan(int days){
        if(loanExtensionInDays >= 0){
            loanExtensionInDays += days;
            return true;
        }
        loanExtensionInDays += days;
        return  false;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getLoandate() {
        return loanDate;
    }

    public boolean isLostStatus() {
        return lostStatus;
    }

    public void setLostStatus(boolean lostStatus) {
        this.lostStatus = lostStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId &&
                lostStatus == loan.lostStatus &&
                Objects.equals(book, loan.book) &&
                Objects.equals(customer, loan.customer) &&
                Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, book, customer, loanDate, lostStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loan{");
        sb.append("loanId=").append(loanId);
        sb.append(", book=").append(book);
        sb.append(", customer=").append(customer);
        sb.append(", loandate=").append(loanDate);
        sb.append(", lostStatus=").append(lostStatus);
        sb.append('}');
        return sb.toString();
    }
}//End of class