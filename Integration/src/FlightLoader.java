// --== CS400 Project One File Header ==--
// Name: <Fengwei Wang>
// CSL Username: <fengweiw>
// Email: <fwang283@wisc.edu>
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

/**
 * Instances can be used to load airport data from a DOT file. Note: Make sure the file path is
 * within the same directory
 */
public class FlightLoader implements IFlightLoader {

  /**
   * This method loads the list of flights from a DOT file.
   * 
   * @param filepathToDOT path to the DOT file
   * @return a list of flight objects
   * @throws FileNotFoundException if path does not exist
   */
  @Override
  public Hashtable<String, Airport> loadAirports(String filepathToDOT)
      throws FileNotFoundException {
    Hashtable<String, Airport> airportTable = new Hashtable<String, Airport>();
    File dotFile = new File(filepathToDOT);
    BufferedReader reader = null;
    String line = "";
    try {
      reader = new BufferedReader(new FileReader(filepathToDOT));
      line = reader.readLine();
      while ((line = reader.readLine()) != null) {
        if (line.equals("}")) {
          break;
        }
        String[] s = line.split("\"");
        // System.out.println(s[1]);
        Double duration = (double) Integer.parseInt(s[1]);
        String str = line.split("\\[")[0];
        String[] flight = str.split("->");
        // System.out.println(flight[1]);
        String startInfo = flight[0];
        String destinationInfo = flight[1];
        LinkedList<Flight> Flights = new LinkedList<Flight>();
        Airport airport = new Airport(startInfo.substring(4, 7), startInfo.substring(8), Flights);
        String key = startInfo.substring(4, 7);
        if (!airportTable.containsKey(key)) {
          airportTable.put(key, airport);
        }
      }
    } catch (FileNotFoundException e1) {
      throw new FileNotFoundException();
    } catch (Exception e2) {
      e2.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
      } catch (NullPointerException e1) {
      }
    }
    BufferedReader reader2 = null;
    line = "";
    try {
      reader2 = new BufferedReader(new FileReader(filepathToDOT));
      line = reader2.readLine();
      while ((line = reader2.readLine()) != null) {
        if (line.equals("}")) {
          break;
        }
        String[] s = line.split("\"");
        // System.out.println(s[1]);
        Double duration = (double) Integer.parseInt(s[1]);
        String str = line.split("\\[")[0];
        String[] flight = str.split("->");
        // System.out.println(flight[1]);
        String startInfo = flight[0];
        String destinationInfo = flight[1];
        LinkedList<Flight> Flights = new LinkedList<Flight>();
        Airport airport = new Airport(startInfo.substring(4, 7), startInfo.substring(8), Flights);
        String key = startInfo.substring(4, 7);
        Flight newFlight = new Flight(airportTable.get(destinationInfo.substring(0, 3)), duration);
        //System.out.println(newFlight.getDestination().getAirlineCode());
        airportTable.get(key).addFlights(newFlight);
      }
    } catch (FileNotFoundException e1) {
      throw new FileNotFoundException();
    } catch (Exception e2) {
      e2.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
      } catch (NullPointerException e1) {
      }
    }
    return airportTable;
  }

}