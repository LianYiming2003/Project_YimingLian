// --== CS400 Project One File Header ==--
// Name: <Fengwei Wang>
// CSL Username: <fengweiw>
// Email: <fwang283@wisc.edu>
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>
/**
 * Represent a flight's destination and duration to later be constructed as edges
 */
public class Flight implements IFlight {
  private  Airport DESTINATION;
  private  Double DURATION;
  
  public Airport DESTINATIONONE;
  public Airport DESTINATIONTWO;
  public Double UNDIRECTEDDURATION;

  public Flight(Airport destination, Double duration) {
    this.DESTINATION = destination;
    this.DURATION = duration;
  }

 // undirected graph
 public Flight(Airport targetOne, Airport targetTwo, Double weight) {
   this.DESTINATIONONE = targetOne;
   this.DESTINATIONTWO = targetTwo;
   this.UNDIRECTEDDURATION = weight;
 }
  /**
   * Retrieving the destination of which airport this flight will navigate to
   * @return the airport to where this flight will land
   */
  @Override
  public Airport getDestination() {
    return DESTINATION;
  }

  /**
   * Retrieving the time in hours (numeric) for the length of the flight
   * @return the number of hours taken to travel
   */
  @Override
  public Double getDuration() {
    return DURATION;
  }
  
  public void setDuration(Double weight) {
    DURATION = weight;
  }

}
