package railwayreservationsystemproject;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketBooking {
    private Map<String, Train> trainMap;  // Map to store all trains
    private List<Passenger> passengers;   // List to store all passengers

    // Constructor to initialize the TicketBooking class
    public TicketBooking() {
        trainMap = new HashMap<>();
        passengers = new ArrayList<>();
    }

    // Method to add a train to the system
    public void addTrain(Train train) {
        trainMap.put(train.getTrainNumber(), train);  // Adding train to map
    }

    // Method to book a ticket for a passenger on a specific train
    public void bookTicket(String trainNumber, Passenger passenger) {
        Train train = trainMap.get(trainNumber);  // Find train by its number

        if (train != null && train.getAvailableSeats() > 0) {  // Check if train exists and seats are available
            passengers.add(passenger);  // Add passenger to list
            train.setAvailableSeats(train.getAvailableSeats() - 1);  // Decrease available seats by 1
            System.out.println("Ticket booked for " + passenger.getFirstname() + " " + passenger.getLastname() +
                               " on train " + train.getTrainNumber() + " (" + train.getTrainName() + ") departing at " + train.getDepartureTime());
        } else {
            System.out.println("Ticket booking failed. Invalid train number or no available seats.");
        }
    }

    // Method to display all train schedules
    public void displayTrainSchedules() {
        System.out.println("---------------------------------------");
        System.out.println("Train Schedules:");
        for (Train train : trainMap.values()) {
            System.out.println(train.getTrainNumber() + " - " + train.getTrainName() +
                               " (Departure Time: " + train.getDepartureTime() + ") Available seats: " + train.getAvailableSeats());
        }
    }

    // Method to display all passenger details
    public void displayPassengerDetails() {
        System.out.println("---------------------------------------");
        System.out.println("Passenger Details:");
        int i = 1;
        for (Passenger passenger : passengers) {
            System.out.println(i + ". Name: " + passenger.getFirstname() + " " + passenger.getLastname() + 
                               "\n   Date Of Birth: " + passenger.getDob());
            i++;
        }
        System.out.println("---------------------------------------");
    }

    // Method to cancel a ticket based on passenger and train info
    public void cancelTicket(String firstName, String lastName, String trainNumber) {
        boolean foundPassenger = false;
        Passenger foundPassengerObj = null;

        // Look for passenger in the list
        for (Passenger passenger : passengers) {
            if (passenger.getFirstname().equals(firstName) && passenger.getLastname().equals(lastName)) {
                foundPassenger = true;
                foundPassengerObj = passenger;
                break;
            }
        }

        if (foundPassenger) {
            Train train = trainMap.get(trainNumber);  // Get the train for cancellation
            if (train != null) {
                passengers.remove(foundPassengerObj);  // Remove the passenger
                train.setAvailableSeats(train.getAvailableSeats() + 1);  // Increase seat count
                System.out.println("Ticket canceled for " + firstName + " " + lastName);
            } else {
                System.out.println("Train with number " + trainNumber + " not found.");
            }
        } else {
            System.out.println("Passenger not found.");
        }
    }

    // Method to get a train by its number (this was missing in the previous version)
    public Train getTrainByNumber(String trainNumber) {
        return trainMap.get(trainNumber);  // Retrieve the train from the map by its train number
    }
}
