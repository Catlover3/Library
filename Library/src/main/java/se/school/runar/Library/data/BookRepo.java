package se.school.runar.Library.data;

import org.springframework.data.repository.CrudRepository;
import se.school.runar.Library.models.Book;
import se.school.runar.Library.models.Loan;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends CrudRepository<Book, Integer> {


    Optional<Book> findByBookId(int id);
    List<Book> findAll();
    List<Book> findByAvailable(boolean available);
    List<Book> findByReserved(boolean reserved);
    List<Book> findByTitleContainingIgnoreCase(String title);

}
