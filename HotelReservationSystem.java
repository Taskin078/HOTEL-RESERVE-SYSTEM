import java.util.ArrayList;
import java.util.Scanner;

// Room class to store room details
class Room {
    int roomNumber;
    String roomType;  // Standard, Deluxe, Suite
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String roomType, double price, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public void displayRoomDetails() {
        System.out.println("Room Number: " + roomNumber + ", Type: " + roomType + ", Price: $" + price + ", Available: " + isAvailable);
    }
}

// Reservation class to store booking details
class Reservation {
    String guestName;
    Room room;
    int days;
    double totalPrice;

    public Reservation(String guestName, Room room, int days) {
        this.guestName = guestName;
        this.room = room;
        this.days = days;
        this.totalPrice = room.price * days;
        room.isAvailable = false;  // Mark room as booked
    }

    public void displayReservation() {
        System.out.println("Reservation for: " + guestName);
        System.out.println("Room Number: " + room.roomNumber + ", Room Type: " + room.roomType);
        System.out.println("Total Price for " + days + " days: $" + totalPrice);
    }
}

// HotelReservationSystem class to manage the reservation system
public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initializing some rooms
        rooms.add(new Room(101, "Standard", 100.0, true));
        rooms.add(new Room(102, "Deluxe", 150.0, true));
        rooms.add(new Room(103, "Suite", 200.0, true));
        rooms.add(new Room(104, "Standard", 100.0, true));
        rooms.add(new Room(105, "Suite", 200.0, true));

        // Main menu loop
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to search for available rooms
    private static void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                room.displayRoomDetails();
            }
        }
    }

    // Method to make a reservation
    private static void makeReservation(Scanner scanner) {
        System.out.println("\nEnter your name: ");
        String guestName = scanner.nextLine();
        
        System.out.println("Enter the room number you'd like to reserve: ");
        int roomNumber = scanner.nextInt();
        
        Room roomToReserve = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                roomToReserve = room;
                break;
            }
        }

        if (roomToReserve == null) {
            System.out.println("Room not available or invalid room number.");
            return;
        }

        System.out.println("Enter number of days to stay: ");
        int days = scanner.nextInt();

        // Creating a reservation
        Reservation reservation = new Reservation(guestName, roomToReserve, days);
        reservations.add(reservation);

        // Simulating payment processing
        processPayment(reservation.totalPrice);

        System.out.println("Reservation successful!");
    }

    // Method to view all reservations
    private static void viewReservations() {
        System.out.println("\nCurrent Reservations:");
        for (Reservation reservation : reservations) {
            reservation.displayReservation();
        }
    }

    // Simulating a basic payment processing
    private static void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + "...");
        System.out.println("Payment successful!");
    }
}
