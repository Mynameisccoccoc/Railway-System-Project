package railwayreservationsystemproject;

import java.time.LocalDate;

public class Passenger {
    private String firstname;
    private String lastname;
    private LocalDate dob;  // Changed to LocalDate for better date handling

    // Constructor with all details
    public Passenger(String firstname, String lastname, LocalDate dob) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
    }

    // Constructor for ticket cancellation (without dob)
    public Passenger(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Getter methods
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getDob() {
        return dob;
    }
}
