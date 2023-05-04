// Name: Archer Parkin & Fengwei Wang
// Group: AZ
// Role: Data Wrangler

import java.util.LinkedList;

/**
 * The interface to represent an international airport within the world to later be constructed as nodes/vertices
 */
public interface IAirport {

    /**
     * Retrieving the code of this airport
     * @return the 3-letter location code
     */
    String getAirlineCode();

    /**
     * Retrieving the country where this airport is located
     * @return the country
     */
    String getCountry();

    /**
     * Shows the lists of flight that can be taken from this airport to another
     * @return a list of flights from this airport
     */
    LinkedList<Flight> getFlights();
    
    /**
     * Add a flight to the list
     * @param Flight the flight to be add
     */
    void addFlights(Flight Flight);

}
