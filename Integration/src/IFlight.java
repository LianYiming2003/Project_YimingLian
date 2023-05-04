// Name: Archer Parkin & Fengwei Wang
// Group: AZ
// Role: Data Wrangler

/**
 * The interface to represent a flight's destination and duration to later be constructed as edges
 */
public interface IFlight {

    /**
     * Retrieving the destination of which airport this flight will navigate to
     * @return the airport to where this flight will land
     */
    Airport getDestination();

    /**
     * Retrieving the time in hours (numeric) for the length of the flight
     * @return the number of hours taken to travel
     */
    Double getDuration();

}
