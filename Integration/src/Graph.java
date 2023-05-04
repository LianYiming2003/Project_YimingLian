import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
//--== CS400 File Header Information ==--
//Name: Yiming Lian
//Email: ylian7@wisc.edu
//Team: AZ red
//TA: Callie
//Lecturer: 001
//Notes to Grader: None
public class Graph implements IGraph, Cloneable {

  protected Hashtable<String, Airport> vertices;

  public Graph() {
    FlightLoader loader = new FlightLoader();
    try {
      vertices = loader.loadAirports("travelworld.dot");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    //vertices = new Hashtable<String, Airport>();
  }

  /**
   * Insert a new vertex into the graph.
   * 
   * @param data the data item stored in the new vertex
   * @return true if the data can be inserted as a new vertex, false if it is already in the graph
   * @throws NullPointerException if data is null
   */
  public boolean insertVertex(String code, String country) {
    if (code == null)
      throw new NullPointerException("Cannot add null vertex");
    if (vertices.containsKey(code))
      return false; // duplicate values are not allowed
    vertices.put(code, new Airport(code, country, new LinkedList<>()));
    return true;
  }

  /**
   * Remove a vertex from the graph. Also removes all edges adjacent to the vertex from the graph
   * (all edges that have the vertex as a source or a destination vertex).
   * 
   * @param data the data item stored in the vertex to remove
   * @return true if a vertex with *data* has been removed, false if it was not in the graph
   * @throws NullPointerException if data is null
   */
  public boolean removeVertex(String data) {
    if (data == null)
      throw new NullPointerException("Cannot remove null vertex");
    Airport removeVertex = vertices.get(data);
    if (removeVertex == null)
      return false; // vertex not found within graph
    // search all vertices for edges targeting removeVertex
    for (Airport v : vertices.values()) {
      Flight removeEdge = null;
      for (Flight e : v.getFlights())
        if (e.getDestination() == removeVertex)
          removeEdge = e;
      // and remove any such edges that are found
      if (removeEdge != null)
        v.getFlights().remove(removeEdge);
    }
    // finally remove the vertex and all edges contained within it
    return vertices.remove(data) != null;
  }

  /**
   * Insert a new directed edge with a positive edge weight into the graph.
   * 
   * @param source the data item contained in the source vertex for the edge
   * @param target the data item contained in the target vertex for the edge
   * @param weight the weight for the edge (has to be a positive integer)
   * @return true if the edge could be inserted or its weight updated, false if the edge with the
   *         same weight was already in the graph
   * @throws IllegalArgumentException if either source or target or both are not in the graph, or if
   *                                  its weight is < 0
   * @throws NullPointerException     if either source or target or both are null
   */
  public boolean insertEdge(String source, String target, Double weight) {
    if (source == null || target == null)
      throw new NullPointerException("Cannot add edge with null source or target");
    Airport sourceVertex = this.vertices.get(source);
    Airport targetVertex = this.vertices.get(target);
    if (sourceVertex == null || targetVertex == null)
      throw new IllegalArgumentException("Cannot add edge with vertices that do not exist");
    if (weight.doubleValue() < 0)
      throw new IllegalArgumentException("Cannot add edge with negative weight");
    // handle cases where edge already exists between these verticies
    for (Flight e : sourceVertex.getFlights())
      if (e.getDestination() == targetVertex) {
        if (e.getDuration().doubleValue() == weight.doubleValue())
          return false; // edge already exists
        else
          e.setDuration(weight); // otherwise update weight of existing edge
        return true;
      }
    // otherwise add new edge to sourceVertex
    sourceVertex.getFlights().add(new Flight(targetVertex, weight));
    return true;
  }

  /**
   * Remove an edge from the graph.
   * 
   * @param source the data item contained in the source vertex for the edge
   * @param target the data item contained in the target vertex for the edge
   * @return true if the edge could be removed, false if it was not in the graph
   * @throws IllegalArgumentException if either source or target or both are not in the graph
   * @throws NullPointerException     if either source or target or both are null
   */
  public boolean removeEdge(String source, String target) {
    if (source == null || target == null)
      throw new NullPointerException("Cannot remove edge with null source or target");
    Airport sourceVertex = this.vertices.get(source);
    Airport targetVertex = this.vertices.get(target);
    if (sourceVertex == null || targetVertex == null)
      throw new IllegalArgumentException("Cannot remove edge with vertices that do not exist");
    // find edge to remove
    Flight removeEdge = null;
    for (Flight e : sourceVertex.getFlights())
      if (e.getDestination() == targetVertex)
        removeEdge = e;
    if (removeEdge != null) { // remove edge that is successfully found
      sourceVertex.getFlights().remove(removeEdge);
      return true;
    }
    return false; // otherwise return false to indicate failure to find
  }

  /**
   * Check if the graph contains a vertex with data item *data*.
   * 
   * @param data the data item to check for
   * @return true if data item is stored in a vertex of the graph, false otherwise
   * @throws NullPointerException if *data* is null
   */
  public boolean containsVertex(String data) {
    if (data == null)
      throw new NullPointerException("Cannot contain null data vertex");
    return vertices.containsKey(data);
  }

  /**
   * Check if edge is in the graph.
   * 
   * @param source the data item contained in the source vertex for the edge
   * @param target the data item contained in the target vertex for the edge
   * @return true if the edge is in the graph, false if it is not in the graph
   * @throws NullPointerException if either source or target or both are null
   */
  public boolean containsEdge(String source, String target) {
    if (source == null || target == null)
      throw new NullPointerException("Cannot contain edge adjacent to null data");
    Airport sourceVertex = vertices.get(source);
    Airport targetVertex = vertices.get(target);
    if (sourceVertex == null)
      return false;
    for (Flight e : sourceVertex.getFlights())
      if (e.getDestination() == targetVertex)
        return true;
    return false;
  }

  /**
   * Return the weight of an edge.
   * 
   * @param source the data item contained in the source vertex for the edge
   * @param target the data item contained in the target vertex for the edge
   * @return the weight of the edge (a Number that represents 0 or a positive value)
   * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the
   *                                  graph
   * @throws NullPointerException     if either sourceVertex or targetVertex or both are null
   * @throws NoSuchElementException   if edge is not in the graph
   */
  public Double getWeight(String source, String target) {
    if (source == null || target == null)
      throw new NullPointerException("Cannot contain weighted edge adjacent to null data");
    Airport sourceVertex = vertices.get(source);
    Airport targetVertex = vertices.get(target);
    if (sourceVertex == null || targetVertex == null)
      throw new IllegalArgumentException(
          "Cannot retrieve weight of edge between vertices that do not exist");
    for (Flight e : sourceVertex.getFlights())
      if (e.getDestination() == targetVertex)
        return e.getDuration();
    throw new NoSuchElementException("No directed edge found between these vertices");
  }

  /**
   * Return the number of edges in the graph.
   * 
   * @return the number of edges in the graph
   */
  public int getEdgeCount() {
    int edgeCount = 0;
    for (Airport v : vertices.values())
      edgeCount += v.getFlights().size();
    return edgeCount;
  }

  /**
   * Return the number of vertices in the graph
   * 
   * @return the number of vertices in the graph
   */
  public int getVertexCount() {
    return vertices.size();
  }

  /**
   * Check if the graph is empty (does not contain any vertices or edges).
   * 
   * @return true if the graph does not contain any vertices or edges, false otherwise
   */
  public boolean isEmpty() {
    return vertices.size() == 0;
  }

  /**
   * Path objects store a discovered path of vertices and the overal distance of cost of the
   * weighted directed edges along this path. Path objects can be copied and extended to include new
   * edges and verticies using the extend constructor. In comparison to a predecessor table which is
   * sometimes used to implement Dijkstra's algorithm, this eliminates the need for tracing paths
   * backwards from the destination vertex to the starting vertex at the end of the algorithm.
   */
  protected class Path implements Comparable<Path> {
    public Airport start; // first vertex within path
    public double distance; // sumed weight of all edges in path
    public List<String> dataSequence; // ordered sequence of data from vertices in path
    public Airport end; // last vertex within path

    /**
     * Creates a new path containing a single vertex. Since this vertex is both the start and end of
     * the path, it's initial distance is zero.
     * 
     * @param start is the first vertex on this path
     */
    public Path(Airport start) {
      this.start = start;
      this.distance = 0.0D;
      this.dataSequence = new LinkedList<>();
      this.dataSequence.add(start.getCountry());
      this.end = start;
    }

    /**
     * This extension constructor makes a copy of the path passed into it as an argument without
     * affecting the original path object (copyPath). The path is then extended by the Edge object
     * extendBy. Use the doubleValue() method of extendBy's weight field to get a double
     * representation of the edge's weight.
     * 
     * @param copyPath is the path that is being copied
     * @param extendBy is the edge the copied path is extended by
     */
    public Path(Path copyPath, Flight extendBy) {
      // TODO: Implement this constructor in Step 5.
      this.start = copyPath.start;
      this.distance = copyPath.distance + extendBy.getDuration().doubleValue();
      this.dataSequence = new LinkedList<>();
      for (int i = 0; i < copyPath.dataSequence.size(); i++) {
        this.dataSequence.add(copyPath.dataSequence.get(i));
      }
      this.dataSequence.add(extendBy.getDestination().getCountry());
      this.end = extendBy.getDestination();
    }

    public Path(Flight extendBy) {
      // TODO: Implement this constructor in Step 5.
      this.start = extendBy.DESTINATIONONE;
      this.distance = extendBy.UNDIRECTEDDURATION.doubleValue();
      this.dataSequence = new LinkedList<>();
      this.end = extendBy.DESTINATIONTWO;
    }

    /**
     * Allows the natural ordering of paths to be increasing with path distance. When path distance
     * is equal, the string comparison of end vertex data is used to break ties.
     * 
     * @param other is the other path that is being compared to this one
     * @return -1 when this path has a smaller distance than the other, +1 when this path has a
     *         larger distance that the other, and the comparison of end vertex data in string form
     *         when these distances are tied
     */
    public int compareTo(Path other) {
      int cmp = Double.compare(this.distance, other.distance);
      if (cmp != 0)
        return cmp; // use path distance as the natural ordering
      // when path distances are equal, break ties by comparing the string
      // representation of data in the end vertex of each path
      //ATTENTION,GETAIRLINE OR GETCOUNTRY
      return this.end.getAirlineCode().toString().compareTo(other.end.getAirlineCode().toString());
    }
  }

  /**
   * Uses Dijkstra's shortest path algorithm to find and return the shortest path between two
   * vertices in this graph: start and end. This path contains an ordered list of the data within
   * each node on this path, and also the distance or cost of all edges that are a part of this
   * path.
   * 
   * @param start data item within first node in path
   * @param end   data item within last node in path
   * @return the shortest path from start to end, as computed by Dijkstra's algorithm
   * @throws NoSuchElementException when no path from start to end can be found, including when no
   *                                vertex containing start or end can be found
   */
  protected Path dijkstrasShortestPath(String start, String end) {
    PriorityQueue<Path> pathQueue = new PriorityQueue<Path>();
    PriorityQueue<Path> finalQueue = new PriorityQueue<Path>();
    // find the start vertex and final vertex
    Airport startVertex = vertices.get(start);
    Airport endVertex = vertices.get(end);
    if (startVertex == null || endVertex == null)
      throw new NoSuchElementException(
          "Cannot retrieve weight of edge between vertices that do not exist");

    Path original = new Path(startVertex);
    // add start-start to the finalQueue
    finalQueue.add(original);
    // add all vertices that are related to the start vertex to the pathQueue
    Path next;
    for (Flight e : startVertex.getFlights()) {
      next = new Path(original, e);
      pathQueue.add(next);
    }
    // find the shortest path
    next = pathQueue.peek();
    boolean repeat;
    while (next != null) {
      finalQueue.add(next);
      pathQueue.remove(next);
      original = next;;
      for (Flight e : original.end.getFlights()) {
        repeat = false;
        next = new Path(original, e);
        // if there are repeat path in the pathQueue
        for (Path path : pathQueue) {
        //ATTENTION,GETAIRLINE OR GETCOUNTRY
          if (path.end.getAirlineCode().equals(next.end.getAirlineCode())) {
            if (next.distance <= path.distance) {
              path.distance = next.distance;
              path.dataSequence = next.dataSequence;
              repeat = true;
            }
          }
        }
        for (Path path : finalQueue) {
        //ATTENTION,GETAIRLINE OR GETCOUNTRY
          if (path.end.getAirlineCode().equals(next.end.getAirlineCode())) {
            repeat = true;
          }
        }
        if (!repeat) {
          pathQueue.add(next);
        }
      }
      next = pathQueue.peek();
    }
    // find the wanted path in the finalQueue
    for (Path path : finalQueue) {
      if (path.end.getAirlineCode().equals(end)) {
        return path;
      }
    }
    throw new NoSuchElementException("Does not find shortest path");

  }

  /**
   * Returns the shortest path between start and end. Uses Dijkstra's shortest path algorithm to
   * find the shortest path.
   * 
   * @param start the data item in the starting vertex for the path
   * @param end   the data item in the destination vertex for the path
   * @return list of data item in vertices in order on the shortest path between vertex with data
   *         item start and vertex with data item end, including both start and end
   * @throws NoSuchElementException when no path from start to end can be found including when no
   *                                vertex containing start or end can be found
   */
  public List<String> shortestPath(String start, String end) {
    return dijkstrasShortestPath(start, end).dataSequence;
  }

  /**
   * Returns the cost of the path (sum over edge weights) between start and end. Uses Dijkstra's
   * shortest path algorithm to find the shortest path.
   * 
   * @param start the data item in the starting vertex for the path
   * @param end   the data item in the end vertex for the path
   * @return the cost of the shortest path between vertex with data item start and vertex with data
   *         item end, including all edges between start and end
   * @throws NoSuchElementException when no path from start to end can be found including when no
   *                                vertex containing start or end can be found
   */
  public double getPathCost(String start, String end) {
    return dijkstrasShortestPath(start, end).distance;
  }

  @Override
  /**
   * This method will display the minimum spanning tree on the screen and return the minimum cost.
   */
  public double getMinimumSpanningTree() {
    PriorityQueue<Path> edgeList = new PriorityQueue<Path>();
    ArrayList<Path> array = new ArrayList<>();
    ArrayList<Path> removed = new ArrayList<>();
    ArrayList<Path> repeat = new ArrayList<>();
    PriorityQueue<Path> results = new PriorityQueue<Path>();
    double totalCost = 0.0;
    boolean repeated = false;
    // add all edges into the List and make them undirected
    for (Airport v : vertices.values()) {
//      System.out.println(v.getAirlineCode());
//      System.out.println("**********************");
      for (Flight e : v.getFlights()) {
//        System.out.println("2");
//        System.out.println(e.getDestination().getCountry());
//        System.out.println(e.getDestination().getAirlineCode());
//        System.out.println("###########################");
        for(Path exist: repeat) {
//          System.out.println(exist.start.getAirlineCode()+"+"+exist.end.getAirlineCode());
//          System.out.println(e.getDestination().getAirlineCode()+"+"+v.getAirlineCode());
//          System.out.println("--------------------");
          if(exist.start.getAirlineCode().equals(e.getDestination().getAirlineCode()) && exist.end.getAirlineCode().equals(v.getAirlineCode())) {
            repeated = true;
          }
        }
        if(!repeated) {
          Flight edge = new Flight(v, e.getDestination(), e.getDuration());
          Path connected = new Path(edge);
          edgeList.add(connected);
          //direct
          Flight repeatedge = new Flight(v, e.getDestination(), e.getDuration());
          Path repeatconnected = new Path(repeatedge);
          repeat.add(repeatconnected);
        }
        repeated = false;
      }
    }
    // add first path into the list
    Path next = edgeList.peek();
    results.add(next);
    edgeList.remove(next);
    array.add(next);
    // initialize second path
    next = edgeList.peek();
    while (next != null) {
      // find whether the added path form a cycle
      Airport findVertex = next.start;
      boolean find = false;
      int i = 0;
      while (i < array.size()) {
      //ATTENTION,GETAIRLINE OR GETCOUNTRY
        if (array.get(i).start.getAirlineCode().equals(findVertex.getAirlineCode())) {

          if (array.get(i).end.getAirlineCode().equals(next.end.getAirlineCode())) {

            find = true;
            break;
          } else {
            findVertex = array.get(i).end;
            removed.add(array.get(i));
            array.remove(i);
            i = 0;

          }
        } else if (array.get(i).end.getAirlineCode().equals(findVertex.getAirlineCode())) {

          if (array.get(i).start.getAirlineCode().equals(next.end.getAirlineCode())) {

            find = true;
            break;
          } else {
            findVertex = array.get(i).start;
            removed.add(array.get(i));
            array.remove(i);
            i = 0;

          }
        } else {
          i = i + 1;
        }
      }
      // if does not form cycle, add to the result loop
      if (find == false) {
        results.add(next);
        array.add(next);
      }
      for (int j = 0; j < removed.size(); j++) {
        array.add(removed.get(j));
      }
      removed = new ArrayList<>();
      edgeList.remove(next);
      next = edgeList.peek();
    }
    for (Path path : results) {

      totalCost += path.distance;
    }
    return totalCost;
  }
}
