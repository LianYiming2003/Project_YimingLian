import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class FlightMapperBackend implements IFlightMapperBackend {

  public ArrayList<String> avoidCodeList = new ArrayList<String>();
  public LinkedList<Flight> removedFlightList = new LinkedList<Flight>();
  public ArrayList<String> avoidCountryList = new ArrayList<String>();


  public String origin = "none";
  public String destination = "none";

  Graph graph = new Graph();
  Graph currGraph = new Graph();

  @Override
  public boolean setOrigin(String ori) {
    if (ori == null || ori == "") {
      return false;
    }
    if (graph.containsVertex(ori) == false) {
      return false;
    } else {
      origin = ori;
      return true;
    }
  }

  @Override
  public boolean setDestination(String des) {
    if (des == null || des == "") {
      return false;
    }
    if (graph.containsVertex(des) == false) {
      return false;
    } else {
      destination = des;
      return true;
    }
  }

  @Override
  public void resetOriDes() {
    origin = "none";
    destination = "none";
  }

  @Override
  public boolean addCountry(String code, String country) {
    if (code != null && code != "" && country != null && country != ""
        && graph.containsVertex(code) == false) {
      graph.insertVertex(code, country);
      currGraph.insertVertex(code, country);
      return true;
    }
    return false;
  }

  @Override
  public boolean addFlight(double duration) {
    if ((Double) duration != null && graph.containsVertex(origin)
        && graph.containsVertex(destination) && graph.containsEdge(origin, destination) == false) {
      graph.insertEdge(origin, destination, duration);
      currGraph.insertEdge(origin, destination, duration);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeCountry(String country) {
    if (country != null && country != "" && graph.containsVertex(country)) {
      graph.removeVertex(country);
      currGraph.removeVertex(country);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeFlight() {
    if (graph.containsEdge(origin, destination)) {
      graph.removeEdge(origin, destination);
      currGraph.removeEdge(origin, destination);
      return true;
    }
    return false;
  }

  @Override
  public String findRoute() {
    if (graph.containsVertex(destination) && graph.containsVertex(origin)) {
      return graph.dijkstrasShortestPath(origin, destination).dataSequence.toString() + "Duration: "
          + graph.dijkstrasShortestPath(origin, destination).distance;
    }
    return "incorrect origin or destination";
  }

  @Override
  public String travelWorld() {
    double totalDuration = graph.getMinimumSpanningTree();
    if (totalDuration < 0) {
      return "Error during travel world";
    } else {
      return "Total duration: " + totalDuration;
    }
  }

  public Hashtable<String, LinkedList> removedFlightList1 = new Hashtable();
  public Hashtable<String, Flight> removedFlightList2 = new Hashtable();

  @Override
  public boolean avoidCountry(String code) {
    // TODO Auto-generated method stub
    if (code != null && code != "" && graph.containsVertex(code)) {
      avoidCodeList.add(code);
      avoidCountryList.add(graph.vertices.get(code).getCountry());

      removedFlightList.addAll(graph.vertices.get(code).getFlights());

      removedFlightList1.put(code, graph.vertices.get(code).getFlights());

      for (Airport airport : graph.vertices.values()) {
        for (Flight flight : airport.getFlights()) {
          if (flight.getDestination().getAirlineCode().equals(code)) {
            removedFlightList2.put(airport.getAirlineCode(), flight);
          }
        }
      }
      graph.removeVertex(code);
    }
    return true;
  }

  @Override
  public void resetFilter() {
    for (int i = 0; i < avoidCodeList.size(); i++) {
      graph.insertVertex(avoidCodeList.get(i), avoidCountryList.get(i));
    }
    for (String key : removedFlightList1.keySet()) {
      LinkedList<Flight> list = removedFlightList1.get(key);
      for (Flight flight : list) {
        graph.insertEdge(key, flight.getDestination().getAirlineCode(), flight.getDuration());
      }
    }
    for (String key : removedFlightList2.keySet()) {
      graph.insertEdge(key, removedFlightList2.get(key).getDestination().getAirlineCode(),
          removedFlightList2.get(key).getDuration());
    }
  }
}