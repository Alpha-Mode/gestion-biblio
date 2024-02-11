import java.util.ArrayList;
import java.util.List;

public class Student {
    public String name;
    public String studentId;
    public String email;
    public List<Book> borrowedBooks;

    public Student(String name, String studentId, String email) {
        this.name = name;
        this.studentId = studentId;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }
}
