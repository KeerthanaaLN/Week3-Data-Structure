import java.util.Scanner;

class Book {
    String title, author, genre;
    int bookId;
    boolean isAvailable;
    Book next, prev;

    Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
    }
}

class Library {
    Book head = null, tail = null;

    public void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        newBook.next = head;
        if (head != null)
            head.prev = newBook;
        else
            tail = newBook;
        head = newBook;
    }

    public void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
            return;
        }
        tail.next = newBook;
        newBook.prev = tail;
        tail = newBook;
    }

    public void addAtPosition(int pos, String title, String author, String genre, int bookId, boolean isAvailable) {
        if (pos <= 1 || head == null) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        Book temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++)
            temp = temp.next;

        if (temp.next == null) {
            addAtEnd(title, author, genre, bookId, isAvailable);
            return;
        }

        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;
    }

    public void removeByBookId(int bookId) {
        if (head == null) return;

        if (head.bookId == bookId) {
            if (head == tail)
                head = tail = null;
            else {
                head = head.next;
                head.prev = null;
            }
            return;
        }

        Book temp = head;
        while (temp != null && temp.bookId != bookId)
            temp = temp.next;

        if (temp == null) return;

        if (temp == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    public void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    public void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    public void updateAvailability(int bookId, boolean availability) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = availability;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        Book temp = head;
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }
        Book temp = tail;
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total books: " + count);
    }

    private void printBook(Book book) {
        System.out.println("ID: " + book.bookId + ", Title: " + book.title + ", Author: " + book.author +
                ", Genre: " + book.genre + ", Available: " + (book.isAvailable ? "Yes" : "No"));
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n1. Add Book\n2. Remove Book\n3. Search by Title\n4. Search by Author");
            System.out.println("5. Update Availability\n6. Display All (Forward)\n7. Display All (Reverse)");
            System.out.println("8. Count Books\n9. Exit");
            System.out.print("Enter choice: ");
            int ch = in.nextInt();
            in.nextLine();

            switch (ch) {
                case 1: {
                    System.out.print("Title: ");
                    String title = in.nextLine();
                    System.out.print("Author: ");
                    String author = in.nextLine();
                    System.out.print("Genre: ");
                    String genre = in.nextLine();
                    System.out.print("Book ID: ");
                    int bookId = in.nextInt();
                    System.out.print("Available (true/false): ");
                    boolean isAvailable = in.nextBoolean();
                    System.out.print("Insert Position (1 - Beginning, 2 - End, 3 - Specific): ");
                    int pos = in.nextInt();
                    if (pos == 1)
                        library.addAtBeginning(title, author, genre, bookId, isAvailable);
                    else if (pos == 2)
                        library.addAtEnd(title, author, genre, bookId, isAvailable);
                    else {
                        System.out.print("Enter position index: ");
                        int position = in.nextInt();
                        library.addAtPosition(position, title, author, genre, bookId, isAvailable);
                    }
                    break;
                }
                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    library.removeByBookId(in.nextInt());
                    break;
                case 3:
                    System.out.print("Enter Book Title: ");
                    library.searchByTitle(in.nextLine());
                    break;
                case 4:
                    System.out.print("Enter Author Name: ");
                    library.searchByAuthor(in.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    int bookId = in.nextInt();
                    System.out.print("Set Availability (true/false): ");
                    boolean available = in.nextBoolean();
                    library.updateAvailability(bookId, available);
                    break;
                case 6:
                    library.displayForward();
                    break;
                case 7:
                    library.displayReverse();
                    break;
                case 8:
                    library.countBooks();
                    break;
                case 9:
                    in.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
