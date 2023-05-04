import java.util.List;

/**
 * This interface provides the methods needed for the frontend portion of the FlightMapper app
 */
public interface IFlightMapperFrontend {

    /**
     * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
     */
    public void runCommandLoop();

    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():

    /**
     * Prints command options to System.out
     */
    public void displayMainMenu(); // prints command options to System.out

    /**
     * Searches for the shortest flight route to the destination and displays the results
     */
    public void searchWithCurrentFilter();
    
    /**
     * Set the current origin
     */
    public void setOrigin();

    /**
     * Set the current destination
     */
    public void setDestination();

    /**
     * Provides a new destination for the backend to add to the graph
     */
    public void addDestination();

    /**
     * Remove a existing destination for the backend to remove from the graph
     */
    public void removeDestination();

    /**
     * Add a new flight from one country to another for the backend to add to the graph
     */
    public void addFlight();

    /**
     * Remove a existing flight from one country to another for the backend to remove from the graph
     */
    public void removeFlight();

    /**
     * Displays the shortest time it takes to travel to all destinations from the set origin
     */
    public void displayTravelWorldDestination();

    /**
    * Avoid a country temporarily
    */
    public void avoidCountry();
    
    /**
     * Resets avoided countries
     */
    public void resetFilters();
    
    /**
     * Reset origin and destination country
     */
    public void resetOriDes();
    
}
