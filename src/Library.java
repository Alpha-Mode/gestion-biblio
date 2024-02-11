import java.util.ArrayList;
import java.util.List;

public class Library {
    public List<Book> books;
    public List<Student> students;

    public Library() {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    // Methods to add, remove, and search books
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(Book book, Student student) {
        if (!book.isBorrowed()) {
            book.borrowBook(student);
            student.borrowedBooks.add(book);
            System.out.println("Livre emprunté avec succès !");
        } else {
            System.out.println("Le livre est déjà emprunté par " + book.borrower.name);
        }
    }

    public void returnBook(Book book, Student student) {
        if (book.isBorrowed() && book.borrower.equals(student)) {
            book.returnBook();
            student.borrowedBooks.remove(book);
            System.out.println("Livre retourné avec succès!");
        } else {
            System.out.println("Livre non emprunté par cet élève.");
        }
    }
}

