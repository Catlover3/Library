package se.school.runar.Library.models;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class Book {
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int loanTimeInDays;
    private BigDecimal finePerDay;
    private String description;

    public Book(String title, boolean available, boolean reserved, int loanTimeInDays, BigDecimal finePerDay, String description) {
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.loanTimeInDays = loanTimeInDays;
        this.finePerDay = finePerDay;
        this.description = description;
    }

    public Book() {

    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getLoanTimeInDays() {
        return loanTimeInDays;
    }

    public void setLoanTimeInDays(int loanTimeInDays) {
        this.loanTimeInDays = loanTimeInDays;
    }

    public BigDecimal getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(BigDecimal finePerDay) {
        this.finePerDay = finePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId &&
                available == book.available &&
                reserved == book.reserved &&
                loanTimeInDays == book.loanTimeInDays &&
                Objects.equals(title, book.title) &&
                Objects.equals(finePerDay, book.finePerDay) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, available, reserved, loanTimeInDays, finePerDay, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("bookId=").append(bookId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", available=").append(available);
        sb.append(", reserved=").append(reserved);
        sb.append(", maxLoanInDays=").append(loanTimeInDays);
        sb.append(", finePerDay=").append(finePerDay);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}//End of class