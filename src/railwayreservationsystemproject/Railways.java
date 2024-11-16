package railwayreservationsystemproject;

import java.time.LocalDate;
import java.util.Scanner;

public class Railways {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        TicketBooking ticketBooking = new TicketBooking();  // Create ticket booking system

        // Adding sample trains
        ticketBooking.addTrain(new Train("123", "Express One", "10:00 AM", 50));
        ticketBooking.addTrain(new Train("456", "Express Two", "02:30 PM", 50));
        ticketBooking.addTrain(new Train("789", "Express Three", "11:00 AM", 50));
        ticketBooking.addTrain(new Train("1221", "Express Four", "04:30 PM", 50));

        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Welcome to Railway Reservation System: ");
            System.out.println("1. Book Ticket");
            System.out.println("2. Check Train Schedules");
            System.out.println("3. Display Passenger Details");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");
            System.out.println("---------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Book a ticket
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter train number: ");
                    String trainNumber = scanner.nextLine();

                    Train train = ticketBooking.getTrainByNumber(trainNumber);  // Call the new method
                    if (train != null && train.getAvailableSeats() > 0) {
                        System.out.print("Enter the number of seats you want to book: ");
                        int seats = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        if (train.getAvailableSeats() >= seats) {
                            for (int i = 0; i < seats; i++) {
                                System.out.println("Enter Passenger " + (i + 1) + " Details");
                                System.out.print("First name: ");
                                String firstName = scanner.nextLine();
                                System.out.print("Last name: ");
                                String lastName = scanner.nextLine();
                                System.out.print("Date of birth (YYYY-MM-DD): ");
                                String dob = scanner.nextLine();
                                LocalDate dateOfBirth = LocalDate.parse(dob);
                                Passenger passenger = new Passenger(firstName, lastName, dateOfBirth);
                                ticketBooking.bookTicket(trainNumber, passenger);
                            }
                        } else {
                            System.out.println("Sorry, not enough seats available.");
                        }
                    } else {
                        System.out.println("Invalid train number or no available seats.");
                    }
                    break;

                case 2: // Show train schedules
                    ticketBooking.displayTrainSchedules();
                    break;

                case 3: // Display passenger details
                    ticketBooking.displayPassengerDetails();
                    break;

                case 4: // Cancel ticket
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter first name of passenger to cancel ticket: ");
                    String cancelFName = scanner.nextLine();
                    System.out.print("Enter last name of passenger to cancel ticket: ");
                    String cancelLName = scanner.nextLine();
                    System.out.print("Enter the train number: ");
                    String cancelTrainNumber = scanner.nextLine();
                    ticketBooking.cancelTicket(cancelFName, cancelLName, cancelTrainNumber);
                    break;

                case 5: // Exit the program
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
