import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// --== CS400 File Header Information ==--
// Name: Yiming Lian
// Email: ylian7@wisc.edu
// Team: AZ red
// TA: Callie
// Lecturer: 001
// Notes to Grader: None
public class AlgorithmEngineerTests {

  private Graph airport;

  /**
   * Instantiate graph.
   */
  @BeforeEach
  public void createGraph() {
    airport = new Graph();
  }

  /**
   * Checks the distance/total weight cost from the Airport CPT to DOH.
   */
  @Test
  public void test1() {
    // System.out.println(airport.getPathCost("CPT", "DOH"));
    assertTrue(airport.getPathCost("CPT", "DOH") == 13.0);
  }

  /**
   * Checks the insert method could insert airport successfully
   */
  @Test
  public void test2() {
    airport.insertVertex("ABC", "Madison");
    airport.insertEdge("DOH", "ABC", 4.0);
    // System.out.println(airport.getPathCost("CPT", "ABC"));
    assertTrue(airport.getPathCost("CPT", "ABC") == 17.0);
  }

  /**
   * Checks the ordered sequence of data within Airport from the CPT to DOH.
   */
  @Test
  public void test3() {
    // System.out.println(airport.shortestPath("CPT", "DOH"));
    assertTrue(airport.shortestPath("CPT", "DOH").toString().equals("[South_Africa, Qatar]"));
  }

  /**
   * Checks the ordered sequence of data within Airport from the CPT to ABC.
   */
  @Test
  public void test4() {
    airport.insertVertex("ABC", "Madison");
    airport.insertEdge("DOH", "ABC", 4.0);
    // System.out.println(airport.shortestPath("CPT", "ABC"));
    assertTrue(
        airport.shortestPath("CPT", "ABC").toString().equals("[South_Africa, Qatar, Madison]"));
  }

  /**
   * Checks the getminimumspanningtree could return correct minimum cost
   */
  @Test
  public void test5() {
    assertTrue(airport.getMinimumSpanningTree() == 156.0);
  }

  /**
   * test reset menu could work successfully
   */
  @Test
  public void CodeReviewOfFrontend1() {// test reset menu could work successfully
    FlightMapperBackend backendMapper = new FlightMapperBackend();
    Scanner sc = new Scanner("10\n");
    FlightMapperFrontend frontendMapper = new FlightMapperFrontend(sc, backendMapper);
    TextUITester tester = new TextUITester("10\n");
    frontendMapper.runCommandLoop();
    String output = tester.checkOutput();
    assertTrue(output.startsWith("Welcome to the Flight Mapper Application!")
        && output.contains("Country filter reset successfully"));
  }

  /**
   * test minimum spanning tree menu could work successfully
   */
  @Test
  public void CodeReviewOfFrontend2() {// test minimum spanning tree menu could work successfully
    FlightMapperBackend backendMapper = new FlightMapperBackend();
    Scanner sc = new Scanner("8\n");
    FlightMapperFrontend frontendMapper = new FlightMapperFrontend(sc, backendMapper);
    TextUITester tester = new TextUITester("8\n");
    frontendMapper.runCommandLoop();
    String output = tester.checkOutput();
    assertTrue(output.startsWith("Welcome to the Flight Mapper Application!")
        && output.contains("Brave traveler! Here is the shortest route to travel the world:"));
  }

  /**
   * test shortest path program run successfully
   */
  @Test
  public void Integration1() {// test shortest path program run successfully
    FlightMapperBackend backendMapper = new FlightMapperBackend();
    Scanner sc = new Scanner("1\nLAX\n2\nCPT\n7\n");
    FlightMapperFrontend frontendMapper = new FlightMapperFrontend(sc, backendMapper);
    TextUITester tester = new TextUITester("1\nLAX\n2\nCPT\n7\n");
    frontendMapper.runCommandLoop();
    String output = tester.checkOutput();
    assertTrue(output.startsWith("Welcome to the Flight Mapper Application!")
        && output.contains("[USA, United_Kingdom, France, Morocco, South_Africa]Duration: 23.0"));
  }

  /**
   * test filter program could successfully filter airport
   */
  @Test
  public void Integration2() {// test filter program could successfully filter airport
    FlightMapperBackend backendMapper = new FlightMapperBackend();
    Scanner sc = new Scanner("9\nRAK\n1\nLAX\n2\nCPT\n7\n");
    FlightMapperFrontend frontendMapper = new FlightMapperFrontend(sc, backendMapper);
    TextUITester tester = new TextUITester("9\nRAK\n1\nLAX\n2\nCPT\n7\n");
    frontendMapper.runCommandLoop();
    String output = tester.checkOutput();
    assertTrue(output.startsWith("Welcome to the Flight Mapper Application!")
        && output.contains("[USA, United_Kingdom, Greece, Qatar, South_Africa]Duration: 24.0"));
  }
}
