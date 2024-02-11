import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private Library library;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.library = new Library();
    }

    public void displayMenu() {
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Supprimer un livre");
        System.out.println("3. Rechercher pour un livre");
        System.out.println("4. Emprunter un livre");
        System.out.println("5. Retourner un livre");
        System.out.println("6. Sortie");
        System.out.print("Entrez votre choix: ");
    }

    public void processUserInput() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    System.out.println("Sortir...");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 6);
    }

    private void addBook() {
        System.out.print("Entrez le titre du livre: ");
        String title = scanner.nextLine();
        System.out.print("Entrez l'auteur du livre: ");
        String author = scanner.nextLine();
        System.out.print("Entrez l'ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Entrez la date de publication(AAAA-MM-JJ): ");
        String publicationDate = scanner.nextLine();

        Book book = new Book(title, author, isbn, publicationDate);
        library.addBook(book);
        System.out.println("Livre ajouté avec succès!");
    }

      private void removeBook() {
        System.out.print("Saisissez le titre du livre à supprimer: ");
        String title = scanner.nextLine();

        Book bookToRemove = library.searchBook(title);
        if (bookToRemove != null) {
        library.removeBook(bookToRemove);
            System.out.println("Livre supprimé avec succès!");
        } else {
            System.out.println("Livre introuvable.");
        }
    }

    private void searchBook() {
        System.out.print("Entrez le titre du livre pour rechercher: ");
        String title = scanner.nextLine();

        Book book = library.searchBook(title);
        if (book != null) {
            System.out.println("Livre trouvé:");
            System.out.println("Titre: " + book.title);
            System.out.println("Auteur: " + book.author);
            System.out.println("ISBN: " + book.isbn);
            System.out.println("Date de publication: " + book.publicationDate);
            if (book.isBorrowed()) {
                System.out.println("Actuellement emprunté par: " + book.borrower.name);
                System.out.println("E-mail: " + book.borrower.email);
                System.out.println("ID: " + book.borrower.studentId);
            } else {
                System.out.println("Actuellement disponible.");
            }
        } else {
            System.out.println("Livre introuvable.");
        }
    }

    private void borrowBook() {
        System.out.print("Entrez le titre du livre à emprunter: ");
        String title = scanner.nextLine();
        Book bookToBorrow = library.searchBook(title);

        if (bookToBorrow != null) {
            if (bookToBorrow.isBorrowed()) {
                Student borrower = bookToBorrow.borrower;
                System.out.println("Désolé, le livre est déjà emprunté par:");
                System.out.println("Nom: " + borrower.name);
                System.out.println("E-mail: " + borrower.email);
                System.out.println("ID: " + borrower.studentId);
            } else {
                String name, email, studentId;
                boolean validEmail = false;
                System.out.print("Entrez le nom de l'étudiant: ");
                name = scanner.nextLine();
                do {
                    System.out.print("Entrez l'adresse e-mail de l'étudiant: ");
                    email = scanner.nextLine();
                    if (isValidEmail(email)) {
                        validEmail = true;
                    } else {
                        System.out.println("Format d'email invalide. Veuillez saisir un email valide.");
                    }
                } while (!validEmail);
                System.out.print("Entrez ID d'étudiant: ");
                studentId = scanner.nextLine();
                Student student = new Student(name, studentId, email);

                library.borrowBook(bookToBorrow, student);
            }
        } else {
            System.out.println("Livre introuvable.");
        }
    }

    private boolean isValidEmail(String email) {

        return email.contains("@") && email.contains(".");
    }

    private void returnBook() {
        System.out.print("Entrez le titre du livre pour retourner: ");
        String title = scanner.nextLine();
        Book bookToReturn = library.searchBook(title);

        if (bookToReturn != null) {
            if (bookToReturn.isBorrowed()) {
                System.out.print("Entrez le nom de l'étudiant: ");
                String name = scanner.nextLine();
                System.out.print("Entrez l'adresse e-mail de l'étudiant: ");
                String email = scanner.nextLine();
                System.out.print("Entrez ID d'étudiant: ");
                String studentId = scanner.nextLine();

                boolean found = false;
                for (Book borrowedBook : library.books) {
                    if (borrowedBook.title.equals(bookToReturn.title) && borrowedBook.borrower != null &&
                            borrowedBook.borrower.name.equals(name) &&
                            borrowedBook.borrower.email.equals(email) &&
                            borrowedBook.borrower.studentId.equals(studentId)) {
                        library.returnBook(borrowedBook, borrowedBook.borrower);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Livre non emprunté par cet élève.");
                }
            } else {
                System.out.println("Le livre n'est pas emprunté.");
            }
        } else {
            System.out.println("Livre introuvable.");
        }
    }
}
