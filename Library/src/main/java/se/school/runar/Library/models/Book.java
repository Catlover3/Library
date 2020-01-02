package se.school.runar.Library.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanInDays;
    private BigDecimal finePerDay;
    private String description;

    public Book(String title, boolean available, boolean reserved, int maxLoanInDays, BigDecimal finePerDay, String description) {
        this.title = title;
        this.available = available;
        this.reserved = reserved;
        this.maxLoanInDays = maxLoanInDays;
        this.finePerDay = finePerDay;
        this.description = description;
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

    public int getMaxLoanInDays() {
        return maxLoanInDays;
    }

    public void setMaxLoanInDays(int maxLoanInDays) {
        this.maxLoanInDays = maxLoanInDays;
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
                maxLoanInDays == book.maxLoanInDays &&
                Objects.equals(title, book.title) &&
                Objects.equals(finePerDay, book.finePerDay) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, available, reserved, maxLoanInDays, finePerDay, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("bookId=").append(bookId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", available=").append(available);
        sb.append(", reserved=").append(reserved);
        sb.append(", maxLoanInDays=").append(maxLoanInDays);
        sb.append(", finePerDay=").append(finePerDay);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}//End of class