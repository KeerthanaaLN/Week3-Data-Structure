import java.util.Scanner;

class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head = null;
    private Ticket tail = null;

    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);

        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head; // Circular link
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head; // Maintain the circular link
        }

        System.out.println("Ticket booked successfully.");
    }

    public void removeTicket(int ticketID) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }

        Ticket current = head;
        Ticket previous = null;

        do {
            if (current.ticketID == ticketID) {
                if (previous != null) {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                } else {
                    // Removing the head node
                    head = head.next;
                    tail.next = head; // Maintain circular link
                }
                System.out.println("Ticket removed successfully.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName +
                    ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Booking Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    public void searchTicketByCustomerName(String customerName) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        boolean found = false;
        do {
            if (current.customerName.equalsIgnoreCase(customerName)) {
                System.out.println("Ticket ID: " + current.ticketID + ", Movie: " + current.movieName +
                        ", Seat: " + current.seatNumber + ", Booking Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No tickets found for customer: " + customerName);
        }
    }

    public void searchTicketByMovieName(String movieName) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        boolean found = false;
        do {
            if (current.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName +
                        ", Seat: " + current.seatNumber + ", Booking Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No tickets found for movie: " + movieName);
        }
    }

    public void totalTicketsBooked() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        int count = 0;
        Ticket current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);

        System.out.println("Total tickets booked: " + count);
    }
}

public class TicketReservationApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();

        while (true) {
            System.out.println("\n1. Book Ticket\n2. Remove Ticket\n3. Display All Tickets\n4. Search by Customer\n5. Search by Movie\n6. Total Tickets\n7. Exit");
            System.out.print("Enter choice: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int ticketID = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = in.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movieName = in.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seatNumber = in.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String bookingTime = in.nextLine();
                    system.addTicket(ticketID, customerName, movieName, seatNumber, bookingTime);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to remove: ");
                    int removeTicketID = in.nextInt();
                    system.removeTicket(removeTicketID);
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name to search: ");
                    String searchCustomerName = in.nextLine();
                    system.searchTicketByCustomerName(searchCustomerName);
                    break;
                case 5:
                    System.out.print("Enter Movie Name to search: ");
                    String searchMovieName = in.nextLine();
                    system.searchTicketByMovieName(searchMovieName);
                    break;
                case 6:
                    system.totalTicketsBooked();
                    break;
                case 7:
                    in.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
