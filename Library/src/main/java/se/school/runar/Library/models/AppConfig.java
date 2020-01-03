package se.school.runar.Library.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Book getBook(){
        return new Book();
    }

    @Bean
    public Customer getCustomer(){
        return new Customer();
    }

    @Bean
    public Loan getLoan(){
        return new Loan();
    }

}//End of class
