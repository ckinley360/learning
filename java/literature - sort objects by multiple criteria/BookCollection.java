
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
 
public class BookCollection {
    
    private ArrayList<Book> books;
    
    public BookCollection() {
        this.books = new ArrayList<>();
    }
    
    public void add(Book book) {
        this.books.add(book);
    }
    
    public int getBookCount() {
        return this.books.size();
    }
    
    public ArrayList<Book> getBooks() {
        return this.books;
    }
    
    public void printBooks() {
        // Print the books in order of (recommended age, name).
        
        Comparator<Book> comparator = Comparator
                .comparing(Book::getRecommendedAge)
                .thenComparing(Book::getName);
        
        Collections.sort(this.books, comparator);
        
        for (Book book:this.books) {
            System.out.println(book);
        }
    }
}
