package se.school.runar.Library.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private LocalDate regDate;
    private String name;

    @Column(unique = true)
    private String email;


    public Customer(LocalDate regDate, String name, String email) {
        this.regDate = regDate;
        this.name = name;
        this.email = email;
    }

    public Customer() {

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
        Customer customer = (Customer) o;
        return userID == customer.userID &&
                Objects.equals(regDate, customer.regDate) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, regDate, name, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("userID=").append(userID);
        sb.append(", regDate=").append(regDate);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}//End of class