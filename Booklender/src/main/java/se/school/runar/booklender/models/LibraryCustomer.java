package se.school.runar.booklender.models;

import java.time.LocalDate;
import java.util.Objects;

public class LibraryCustomer {
    private int userID;
    private LocalDate regDate;
    private String name;
    private String email;


    public LibraryCustomer(LocalDate regDate, String name, String email) {
        this.regDate = regDate;
        this.name = name;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }


    public LocalDate getRegDate() {
        return regDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryCustomer libraryCustomer = (LibraryCustomer) o;
        return userID == libraryCustomer.userID &&
                Objects.equals(regDate, libraryCustomer.regDate) &&
                Objects.equals(name, libraryCustomer.name) &&
                Objects.equals(email, libraryCustomer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, regDate, name, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LibraryCustomer{");
        sb.append("userID=").append(userID);
        sb.append(", regDate=").append(regDate);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}//End of class
