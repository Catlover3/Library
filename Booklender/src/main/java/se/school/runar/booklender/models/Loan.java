package se.school.runar.booklender.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private long loanId;
    private Book borrower;
    private LibraryCustomer libraryCustomer;
    private LocalDate loandate;
    private boolean lostStatus;

    public Loan(Book borrower, LibraryCustomer libraryCustomer, LocalDate loandate, boolean lostStatus) {
        this.borrower = borrower;
        this.libraryCustomer = libraryCustomer;
        this.loandate = loandate;
        this.lostStatus = lostStatus;
    }

    public boolean isOverdue(){

    }

    public BigDecimal getFine(){

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

    public LibraryCustomer getLibraryCustomer() {
        return libraryCustomer;
    }

    public void setLibraryCustomer(LibraryCustomer libraryCustomer) {
        this.libraryCustomer = libraryCustomer;
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
                Objects.equals(libraryCustomer, loan.libraryCustomer) &&
                Objects.equals(loandate, loan.loandate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, borrower, libraryCustomer, loandate, lostStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loan{");
        sb.append("loanId=").append(loanId);
        sb.append(", borrower=").append(borrower);
        sb.append(", libraryCustomer=").append(libraryCustomer);
        sb.append(", loandate=").append(loandate);
        sb.append(", lostStatus=").append(lostStatus);
        sb.append('}');
        return sb.toString();
    }
}//End of class
