// Name: Archer Parkin & Fengwei Wang
// Group: AZ
// Role: Data Wrangler

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;

/**
 * Instances of this interface can be used to load airport data from a DOT file.
 * Note: Make sure the file path is within the same directory
 */
public interface IFlightLoader {

    /**
     * This method loads the list of flights from a DOT file.
     * @param filepathToDOT path to the DOT file
     * @return a list of flight objects
     * @throws FileNotFoundException if path does not exist
     */
    Hashtable<String, Airport> loadAirports(String filepathToDOT) throws FileNotFoundException;

}
