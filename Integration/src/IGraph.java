// --== CS400 File Header Information ==--
// Name: Yiming Lian
// Email: ylian7@wisc.edu
// Team: AZ red
// TA: Callie
// Lecturer: 001
// Notes to Grader: None
/**
 * This interface represents a directed graph data structure with positive edge weights.
 * 
 * @param NodeType the data type stored at each graph vertex
 * @param EdgeType the data type stored at each graph edge as a Number whose doubleValue() method
 *  returns a value >=0.0
 */
public interface IGraph extends GraphADT{
  
  /**
   * This method will display the minimum spanning tree on the screen and return the minimum cost.
   */
  public double getMinimumSpanningTree();
  
}
