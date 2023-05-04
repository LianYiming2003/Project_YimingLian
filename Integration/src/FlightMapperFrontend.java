import java.util.List;
import java.util.Scanner;

public class FlightMapperFrontend implements IFlightMapperFrontend {

    boolean start = true;
    Scanner sc;
    private FlightMapperBackend FlightBackend;
    String origin;
    String destination;

    public FlightMapperFrontend(Scanner input, FlightMapperBackend backend) {
        sc = input;
        FlightBackend = backend;
    }

    @Override
    public void runCommandLoop() {
        displayMainMenu();
        String userInput;
        if (sc.hasNext() == true) {
            userInput = sc.nextLine().trim();
            switch (userInput) {
            case "1":
                setOrigin();
                break;
            case "2":
                setDestination();
                break;
            case "3":
                addDestination();
                break;
            case "4":
                addFlight();
                break;
            case "5":
                removeDestination();
                break;
            case "6":
                removeFlight();
                break;
            case "7":
                searchWithCurrentFilter();
                break;
            case "8":
                displayTravelWorldDestination();
                break;
            case "9":
                avoidCountry();
                break;
            case "10":
                resetFilters();
                break;
            case "11":
                resetOriDes();
                break;
            case "12":
                System.out.println("Goodbye!");
                sc.close();
                break;
            default:
                System.out.println("Invalid input, please try again.");
                runCommandLoop();
            }
        }

    }

    @Override
    public void displayMainMenu() {
        if (start) {
            System.out.println("Welcome to the Flight Mapper Application!");
            System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
            start = false;
        }
        System.out.println();
        System.out
            .println("You are in the Main Menu:\n" + "\t 1) Set origin\n" + "\t 2) Set destination\n"
                + "\t 3) Add destination\n" + "\t 4) Add route\n" + "\t 5) Remove destination\n"
                + "\t 6) Remove route\n" + "\t 7) Route between origin and destination\n"
                + "\t 8) Travel the world from the origin\n" + "\t 9) Add avoiding country\n"
                + "\t 10) Reset all filters\n" + "\t 11) Reset origin and destination\n"
                + "\t 12) Exit Application\n");
    }

    @Override
    public void searchWithCurrentFilter() {
        System.out.println("Shortest route between " + origin + " and " + destination + ":");
        System.out.println(FlightBackend.findRoute());
        runCommandLoop();
    }

    @Override
    public void setOrigin() { //code
        System.out.println("You are setting an origin.");
        System.out.println("Enter your CODE of origin (type “quit” to exit to main menu):");
        String userInput = sc.nextLine().trim();
        while (!FlightBackend.setOrigin(userInput) && !userInput.equals("quit")) {
            System.out.println("Invalid input, please try again.");
            System.out.println("Enter your origin (type “quit” to exit to main menu):");
            userInput = sc.nextLine().trim();
        }
        if (!userInput.equals("quit")) {
            origin = userInput;
            System.out.println("Origin " + origin + " set successfully");
        }
        runCommandLoop();
    }

    @Override
    public void setDestination() { //code
        System.out.println("You are setting a destination.");
        System.out.println("Enter your CODE of destination (type \"quit\" to exit to main menu):");
        String userInput = sc.nextLine().trim();
        while (!FlightBackend.setDestination(userInput) && !userInput.equals("quit")) {
            System.out.println("Invalid input, please try again.");
            System.out.println("Enter your destination (type \"quit\" to exit to main menu):");
            userInput = sc.nextLine().trim();
        }
        if (!userInput.equals("quit")) {
            destination = userInput;
            System.out.println("Destination " + destination + " set successfully");
        }
        runCommandLoop();
    }

    @Override
    public void addDestination() {
        System.out.println("You are adding a destination.");
        System.out
            .println("Enter the destination you want to add:\n(type \"quit\" to exit to main menu)");
        String userInput = sc.nextLine().trim();
        String[] userStrings = userInput.split("_");
        String code = userStrings[0];
        String country = "";
        for (int i=1; i<userStrings.length; i++) {
            country += (userStrings[i] + " ");
        }
        while (!FlightBackend.addCountry(code, country) && !userInput.equals("quit")) {
            System.out.println("Invalid input, please try again.");
            System.out.println(
                "Enter the destination you want to add:\n(type \"quit\" to exit to main menu)");
            userInput = sc.nextLine().trim();
        }
        if (!userInput.equals("quit")) {
            System.out.println("Destination " + country + " added successfully");
        }
        runCommandLoop();
    }

    @Override
    public void removeDestination() {
        System.out.println("You are removing a destination.");
        System.out.println(
            "Enter the destination you want to remove:\n(type \"quit\" to exit to main menu)");
        String userInput = sc.nextLine().trim();
        while (!FlightBackend.removeCountry(userInput) && !userInput.equals("quit")) {
            System.out.println("Invalid input, please try again.");
            System.out.println(
                "Enter the destination you want to remove:\n(type \"quit\" to exit to main menu)");
            userInput = sc.nextLine().trim();
        }
        if (!userInput.equals("quit")) {
            System.out.println("Destination " + userInput + " removed successfully");
        }
        runCommandLoop();
    }

    @Override
    public void addFlight() {
        System.out.println("You are adding a flight.");
        System.out
            .println("Enter the duration of your flight:\n(type \"quit\" to exit to main menu)");
        String userInput = sc.nextLine().trim();
        while (!FlightBackend.addFlight(Double.parseDouble(userInput))
            && !userInput.equals("quit")) {
            System.out.println("Invalid input, please try again.");
            System.out
                .println("Enter the duration of your flight:\n(type \"quit\" to exit to main menu)");
            userInput = sc.nextLine().trim();
        }
        if (!userInput.equals("quit")) {
            System.out.println("Flight added successfully");
        }
        runCommandLoop();
    }

    @Override
    public void removeFlight() {
        System.out.println("You are removing a flight.");
        if (!FlightBackend.removeFlight()) {
            System.out.println("Invalid input, please try to set your origin or destination again.");
        }
        else {
            System.out.println("Flight removed successfully");
        }
        runCommandLoop();
    }

    @Override
    public void displayTravelWorldDestination() {
        System.out.println(
            "Brave traveler! Here is the shortest route to travel the world:");
        System.out.println(FlightBackend.travelWorld());
        runCommandLoop();
    }

    @Override
    public void avoidCountry() {
        System.out.println("You are setting the country to avoid.");
        System.out.println("Enter the CODE of the country to avoid:\n(type \"quit\" to exit to main menu)");
        String userInput = sc.nextLine().trim();
        while (!FlightBackend.avoidCountry(userInput) && !userInput.equals("quit")) {
            System.out.println("Invalid input, please try again.");
            System.out.println(
                "Enter the destination you want to remove:\n(type \"quit\" to exit to main menu)");
            userInput = sc.nextLine().trim();
        }
        if (!userInput.equals("quit")) {
            System.out.println(userInput + " will be avoided");
        }
        runCommandLoop();
    }

    @Override
    public void resetFilters() {
        FlightBackend.resetFilter();
        System.out.println("Country filter reset successfully");
        runCommandLoop();
    }

    @Override
    public void resetOriDes() {
        FlightBackend.resetOriDes();
        this.origin = null;
        this.destination = null;
        System.out.println("Origin and destination reset successfully");
        runCommandLoop();
    }

}