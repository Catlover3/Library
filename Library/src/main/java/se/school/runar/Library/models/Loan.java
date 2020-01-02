package se.school.runar.Library.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Loan {
    private long loanId;
    private Book book;
    private Customer customer;
    private LocalDate loandate;
    private boolean lostStatus;

    public Loan(Book book, Customer customer, LocalDate loandate, boolean lostStatus) {
        this.book = book;
        this.customer = customer;
        this.loandate = loandate;
        this.lostStatus = lostStatus;
    }
    public LocalDate getDueDate(){
        LocalDate dueDate = loandate.plusDays(book.getLoanTimeInDays());
        return dueDate;
    }

    public boolean isOverdue(){
        LocalDate todaysDate = LocalDate.now();
        if ((todaysDate.isAfter(loandate.plusDays(book.getLoanTimeInDays())) )){

            lostStatus = true;
            return true;
        }
        return false;
    }

    public BigDecimal getFine(){
        if(isOverdue() == true){
            long daysPassed = ChronoUnit.DAYS.between(loandate, getDueDate());
            BigDecimal fine = new BigDecimal("10");
            BigDecimal days = BigDecimal.valueOf(daysPassed);
            BigDecimal sumOfFine = fine.multiply(days);

            BigDecimal maxFine = new BigDecimal(1000);
            if (sumOfFine.compareTo(maxFine) == 1){// 1 beyder att summan är större än jämförelsevärdet inom parentesen
                sumOfFine.valueOf(1000);
            }
            return  sumOfFine;

        }
        else{
            throw new IllegalArgumentException("the book is not overdue");
        }
    }

    public boolean extendLoan(int days){

    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public Book getBorrower() {
        return borrower;
    }

    public void setBorrower(Book borrower) {
        this.borrower = borrower;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getLoandate() {
        return loandate;
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
                Objects.equals(borrower, loan.borrower) &&
                Objects.equals(customer, loan.customer) &&
                Objects.equals(loandate, loan.loandate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, borrower, customer, loandate, lostStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loan{");
        sb.append("loanId=").append(loanId);
        sb.append(", borrower=").append(borrower);
        sb.append(", customer=").append(customer);
        sb.append(", loandate=").append(loandate);
        sb.append(", lostStatus=").append(lostStatus);
        sb.append('}');
        return sb.toString();
    }
}//End of class