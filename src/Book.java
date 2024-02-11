public class Book {
    public String title;
    public String author;
    public String isbn;
    public String publicationDate;
    public Student borrower;

    public Book(String title, String author, String isbn, String publicationDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
    }

    public boolean isBorrowed() {
        return borrower != null;
    }

    public void borrowBook(Student student) {
        borrower = student;
    }

    public void returnBook() {
        borrower = null;
    }
}
