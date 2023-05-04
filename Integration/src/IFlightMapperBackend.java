/**
 * This interface enable the frontend to utilize the Dijkstra's
 * Algorithm developed by the Algorithm Engineer
 */
public interface IFlightMapperBackend{
    /**
     * set origin country
     *
     * @param ori origin country
     * @return true if successfully set, false otherwise
     */
    public boolean setOrigin(String ori);

    /**
     * set destination country
     *
     * @param des destination country
     * @return true if successfully set, false otherwise
     */
    public boolean setDestination(String des);

    /**
     * reset origin and destination country
     */
    public void resetOriDes();

    /**
     * Add a country to the map
     *
     * @param country country to be added as vertex in graph
     * @return true if added successfully, false otherwise
     */
    public boolean addCountry(String code, String country);

    /**
     * Add a flight to the map
     *
     * @param duration duration of flight in hours
     * @return true if added successfully, false otherwise
     */
    public boolean addFlight(double duration);

    /**
     * Remove a country to the map
     *
     * @param country country to be removed as vertex in graph
     * @return true if removed successfully, false otherwise
     */
    public boolean removeCountry(String country);

    /**
     * Remove a flight to the map
     *
     * @return true if removed successfully, false otherwise
     */
    public boolean removeFlight();


    /**
     * Find the shortest route between origin and destination
     *
     * @return description of route
     */
    public String findRoute();

    /**
     * Find the shortest route to travel all countries reachable from origin
     *
     * @return description of route
     */
    public String travelWorld();
    /**
    * Avoid a country temporarily
    *
    * @param country country to avoid
    * @return true if successfully avoided, false otherwise
    */
    public boolean avoidCountry(String country);


   /**
   * Reset avoiding countries
   */
   public void resetFilter();
}