import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class FlightMapper {

  public static void main(String[] args) throws FileNotFoundException {
      Graph graph = new Graph();
      FlightMapperBackend backend = new FlightMapperBackend();
      Scanner userInputScanner = new Scanner(System.in);
      FlightMapperFrontend frontend = new FlightMapperFrontend(userInputScanner,backend);
      frontend.runCommandLoop();
  }
  
}

