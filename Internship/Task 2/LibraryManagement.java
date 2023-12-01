import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagement {

    public static class Book {
        private int bookId;
        private String title;
        private boolean available;

        public Book(int bookId, String title) {
            this.bookId = bookId;
            this.title = title;
            this.available = true;
        }

        public int getBookId() {
            return bookId;
        }

        public String getTitle() {
            return title;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "Book ID: " + bookId + "\nTitle: " + title + "\nAvailable: " + available + "\n";
        }
    }

    public static class Borrower {
        private int borrowerId;
        private String name;

        public Borrower(int borrowerId, String name) {
            this.borrowerId = borrowerId;
            this.name = name;
        }

        public int getBorrowerId() {
            return borrowerId;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Borrower ID: " + borrowerId + "\nName: " + name + "\n";
        }
    }

    public static class LibraryTransaction {
        private Book book;
        private Borrower borrower;
        private boolean isReturn;

        public LibraryTransaction(Book book, Borrower borrower, boolean isReturn) {
            this.book = book;
            this.borrower = borrower;
            this.isReturn = isReturn;
        }

        public Book getBook() {
            return book;
        }

        public Borrower getBorrower() {
            return borrower;
        }

        public boolean isReturn() {
            return isReturn;
        }

        @Override
        public String toString() {
            return "Transaction: " + (isReturn ? "Return" : "Loan") + "\n" + book + borrower + "\n";
        }
    }

    public static class LibraryManagementSys {
        private LinkedList<Book> books;
        private LinkedList<Borrower> borrowers;
        private Map<Integer, LibraryTransaction> transactions;

        public LibraryManagementSys() {
            this.books = new LinkedList<>();
            this.borrowers = new LinkedList<>();
            this.transactions = new HashMap<>();
        }

        public void addBook(Book book) {
            books.add(book);
            System.out.println("Book added successfully.");
        }

        public void removeBook(int bookId) {
            for (Book book : books) {
                if (book.getBookId() == bookId) {
                    books.remove(book);
                    System.out.println("Book removed successfully.");
                    return;
                }
            }
            System.out.println("Book not found.");
        }

        public void displayBooks() {
            if (books.isEmpty()) {
                System.out.println("No books available in the library.");
            } else {
                System.out.println("Books in the Library:");
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        }

        public void addBorrower(Borrower borrower) {
            borrowers.add(borrower);
            System.out.println("Borrower added successfully.");
        }

        public void removeBorrower(int borrowerId) {
            for (Borrower borrower : borrowers) {
                if (borrower.getBorrowerId() == borrowerId) {
                    borrowers.remove(borrower);
                    System.out.println("Borrower removed successfully.");
                    return;
                }
            }
            System.out.println("Borrower not found.");
        }

        public void displayBorrowers() {
            if (borrowers.isEmpty()) {
                System.out.println("No borrowers registered in the library.");
            } else {
                System.out.println("Borrowers in the Library:");
                for (Borrower borrower : borrowers) {
                    System.out.println(borrower);
                }
            }
        }

        public void loanBook(int bookId, int borrowerId) {
            Book book = findBook(bookId);
            Borrower borrower = findBorrower(borrowerId);

            if (book != null && borrower != null && book.isAvailable()) {
                LibraryTransaction transaction = new LibraryTransaction(book, borrower, false);
                transactions.put(bookId, transaction);
                book.setAvailable(false);
                System.out.println("Book loaned successfully.");
            } else {
                System.out.println("Unable to loan the book. Check book availability and borrower details.");
            }
        }

        public void returnBook(int bookId) {
            Book book = findBook(bookId);

            if (book != null && !book.isAvailable() && transactions.containsKey(bookId)) {
                LibraryTransaction transaction = transactions.get(bookId);
                transaction.getBook().setAvailable(true);
                transactions.remove(bookId);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Unable to return the book. Check book details and transaction history.");
            }
        }

        private Book findBook(int bookId) {
            for (Book book : books) {
                if (book.getBookId() == bookId) {
                    return book;
                }
            }
            return null;
        }

        private Borrower findBorrower(int borrowerId) {
            for (Borrower borrower : borrowers) {
                if (borrower.getBorrowerId() == borrowerId) {
                    return borrower;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LibraryManagementSys librarySystem = new LibraryManagementSys();

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display Books");
            System.out.println("4. Add Borrower");
            System.out.println("5. Remove Borrower");
            System.out.println("6. Display Borrowers");
            System.out.println("7. Loan Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");
            System.out.print("Enter your choice (1-9): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    librarySystem.addBook(new Book(bookId, bookTitle));
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    int removeBookId = scanner.nextInt();
                    librarySystem.removeBook(removeBookId);
                    break;
                case 3:
                    librarySystem.displayBooks();
                    break;
                case 4:
                    System.out.print("Enter borrower ID: ");
                    int borrowerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter borrower name: ");
                    String borrowerName = scanner.nextLine();
                    librarySystem.addBorrower(new Borrower(borrowerId, borrowerName));
                    break;
                case 5:
                    System.out.print("Enter borrower ID to remove: ");
                    int removeBorrowerId = scanner.nextInt();
                    librarySystem.removeBorrower(removeBorrowerId);
                    break;
                case 6:
                    librarySystem.displayBorrowers();
                    break;
                case 7:
                    System.out.print("Enter book ID to loan: ");
                    int loanBookId = scanner.nextInt();
                    System.out.print("Enter borrower ID for the loan: ");
                    int loanBorrowerId = scanner.nextInt();
                    librarySystem.loanBook(loanBookId, loanBorrowerId);
                    break;
                case 8:
                    System.out.print("Enter book ID to return: ");
                    int returnBookId = scanner.nextInt();
                    librarySystem.returnBook(returnBookId);
                    break;
                case 9:
                    System.out.println("Exiting Library Management System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
        }
    }
}
