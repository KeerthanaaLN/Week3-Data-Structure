import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next, prev;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }
}

class MovieDoublyLinkedList {
    Movie head, tail;

    public void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos == 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
        } else {
            newMovie.next = temp.next;
            newMovie.prev = temp;
            temp.next.prev = newMovie;
            temp.next = newMovie;
        }
    }

    public void removeByTitle(String title) {
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }
        if (temp == null) return;
        if (temp == head) head = temp.next;
        if (temp == tail) tail = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
        if (temp.next != null) temp.next.prev = temp.prev;
    }

    public void searchByDirectorOrRating(String director, Double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if ((director != null && temp.director.equalsIgnoreCase(director)) ||
                (rating != null && temp.rating == rating)) {
                System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No match found.");
    }

    public void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }

    public void displayForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.prev;
        }
    }
}

public class MovieManager {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MovieDoublyLinkedList list = new MovieDoublyLinkedList();

        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by Title");
            System.out.println("5. Search by Director or Rating\n6. Update Rating\n7. Display Forward\n8. Display Reverse\n9. Exit");
            System.out.print("Enter choice: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3: {
                    System.out.print("Title: ");
                    String title = in.nextLine();
                    System.out.print("Director: ");
                    String director = in.nextLine();
                    System.out.print("Year: ");
                    int year = in.nextInt();
                    System.out.print("Rating: ");
                    double rating = in.nextDouble();
                    if (choice == 1) list.addAtBeginning(title, director, year, rating);
                    else if (choice == 2) list.addAtEnd(title, director, year, rating);
                    else {
                        System.out.print("Position: ");
                        int pos = in.nextInt();
                        list.addAtPosition(pos, title, director, year, rating);
                    }
                    break;
                }
                case 4:
                    System.out.print("Enter Title to remove: ");
                    String title = in.nextLine();
                    list.removeByTitle(title);
                    break;
                case 5:
                    System.out.print("Search by Director (leave blank if not): ");
                    String director = in.nextLine();
                    System.out.print("Search by Rating (enter -1 if not): ");
                    double rating = in.nextDouble();
                    list.searchByDirectorOrRating(director.isEmpty() ? null : director, rating == -1 ? null : rating);
                    break;
                case 6:
                    System.out.print("Enter Title to update: ");
                    title = in.nextLine();
                    System.out.print("New Rating: ");
                    double newRating = in.nextDouble();
                    list.updateRating(title, newRating);
                    break;
                case 7:
                    list.displayForward();
                    break;
                case 8:
                    list.displayReverse();
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
