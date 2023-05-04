// --== CS400 Project One File Header ==--
// Name: <Fengwei Wang>
// CSL Username: <fengweiw>
// Email: <fwang283@wisc.edu>
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>
import java.util.LinkedList;

/**
 * Represent an international airport within the world to later be constructed as nodes/vertices
 */
public class Airport implements IAirport{
  private String AirlineCode;
  private String Country;
  private LinkedList<Flight> Flight;

  public Airport(String AirlineCode, String Country, LinkedList<Flight> Flight) {
    this.AirlineCode = AirlineCode;
    this.Country = Country;
    this.Flight = Flight;
  }

  /**
   * Retrieving the code of this airport
   * @return the 3-letter location code
   */
  @Override
  public String getAirlineCode() {
    return AirlineCode;
  }

  /**
   * Retrieving the country where this airport is located
   * @return the country
   */
  @Override
  public String getCountry() {
    return Country;
  }

  /**
   * Shows the lists of flight that can be taken from this airport to another
   * @return a list of flights from this airport
   */
  @Override
  public LinkedList<Flight> getFlights() {
    return Flight;
  }

  /**
   * Add a flight to the list
   * @param Flight the flight to be add
   */
  @Override
  public void addFlights(Flight FlightToAdd) {
    this.Flight.add(FlightToAdd);
  }


}
