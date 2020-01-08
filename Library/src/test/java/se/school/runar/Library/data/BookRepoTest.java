package se.school.runar.Library.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.school.runar.Library.models.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BookRepoTest {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    TestEntityManager em;

    private Book bookTest;
    long loanId;


    @BeforeEach
    void setup(){
        BigDecimal ten= new BigDecimal("10");
        bookTest = new Book("Game of Thrones", true, false, 7, ten, "Fantasy with swords, shields and dragons");

        em.persistAndFlush(bookTest);
        this.loanId = bookTest.getBookId();

    }

    @Test
    void findByAvailable_function_works() {
        List<Book> listTest;
        listTest = bookRepo.findByAvailable(true);
        assertTrue(listTest.contains(bookTest));
    }

    @Test
    void findByReserved_function_works() {
        List<Book> listTest;
        listTest = bookRepo.findByReserved(false);
        assertTrue(listTest.contains(bookTest));
    }

    @Test
    void findByTitle_function_works() {
        List<Book> listTest;
        listTest = bookRepo.findByTitleContainingIgnoreCase("AmE");
        assertTrue(listTest.contains(bookTest));
    }



}//End of class
