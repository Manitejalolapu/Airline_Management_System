import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Passenger {
    private String name;
    private String passportNumber;
    private String seatNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.seatNumber = null;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}


class Flight {
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private int capacity;
    private int seatsBooked;
    private ArrayList<Passenger> passengers;

    public Flight(String flightNumber, String departureCity, String arrivalCity, int capacity) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.capacity = capacity;
        this.seatsBooked = 0;
        this.passengers = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void bookSeat(Passenger passenger) {
        if (seatsBooked < capacity) {
            seatsBooked++;
            passengers.add(passenger);
            passenger.setSeatNumber(generateSeatNumber());
            System.out.println("Seat booked successfully. Seat Number: " + passenger.getSeatNumber());
        } else {
            System.out.println("Sorry, no seats available on this flight.");
        }
    }

    private String generateSeatNumber() {
        char row = (char) ('A' + seatsBooked / 6);
        int seat = seatsBooked % 6 + 1;
        return row + String.valueOf(seat);
    }
}

// Airline class to manage flights and operations
class Airline {
    private HashMap<String, Flight> flights;

    public Airline() {
        this.flights = new HashMap<>();
    }

    // Add a new flight to the airline
    public void addFlight(String flightNumber, String departureCity, String arrivalCity, int capacity) {
        if (!flights.containsKey(flightNumber)) {
            flights.put(flightNumber, new Flight(flightNumber, departureCity, arrivalCity, capacity));
            System.out.println("Flight added successfully!");
        } else {
            System.out.println("Flight with this number already exists.");
        }
    }

    // Display all flights in the airline
    public void displayFlights() {
        System.out.println("Flights available:");
        for (Flight flight : flights.values()) {
            System.out.println("Flight Number: " + flight.getFlightNumber() +
                    ", Departure: " + flight.getDepartureCity() +
                    ", Arrival: " + flight.getArrivalCity() +
                    ", Available Seats: " + (flight.getCapacity() - flight.getSeatsBooked()));
        }
    }

    public void bookTicket(String flightNumber, String passengerName, String passportNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            Passenger passenger = new Passenger(passengerName, passportNumber);
            flight.bookSeat(passenger);
        } else {
            System.out.println("Flight not found.");
        }
    }

    public void displayPassengers(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            System.out.println("Passengers on Flight " + flightNumber + ":");
            for (Passenger passenger : flight.getPassengers()) {
                System.out.println("Name: " + passenger.getName() +
                        ", Passport Number: " + passenger.getPassportNumber() +
                        ", Seat Number: " + passenger.getSeatNumber());
            }
        } else {
            System.out.println("Flight not found.");
        }
    }
}

public class AirlineManagementSystem {
    public static void main(String[] args) {
        Airline airline = new Airline();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAirline Management System");
            System.out.println("1. Add Flight");
            System.out.println("2. Display Flights");
            System.out.println("3. Book Ticket");
            System.out.println("4. Display Passengers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Flight Number: ");
                    String flightNumber = scanner.nextLine();
                    System.out.print("Enter Departure City: ");
                    String departureCity = scanner.nextLine();
                    System.out.print("Enter Arrival City: ");
                    String arrivalCity = scanner.nextLine();
                    System.out.print("Enter Capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); 
                    airline.addFlight(flightNumber, departureCity, arrivalCity, capacity);
                    break;
                case 2:
                    airline.displayFlights();
                    break;
                case 3:
                    System.out.print("Enter Flight Number to book ticket: ");
                    String bookFlightNumber = scanner.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String passengerName = scanner.nextLine();
                    System.out.print("Enter Passport Number: ");
                    String passportNumber = scanner.nextLine();
                    airline.bookTicket(bookFlightNumber, passengerName, passportNumber);
                    break;
                case 4:
                    System.out.print("Enter Flight Number to display passengers: ");
                    String displayFlightNumber = scanner.nextLine();
                    airline.displayPassengers(displayFlightNumber);
                    break;
                case 5:
                    System.out.println("Exiting Airline Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
