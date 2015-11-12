/**
 * This class simulates an edge.
 * <p>
 * Is to be used strictly as backend for use with Dijkstra's algorithm
 *
 * @author Amanda Olson
 */
public final class EdgeData
{
    private int    _weight;     // weight of this edge
    private NodeData _directToNode; // destination node of this edge
    
    /**
     * Creates an EdgeData object with a destination node of type NodeData and a edge weight
     * set to edgeWeight
    *
    * @param destination (required) The destination of this edge. Is of type NodeData.
    * @param weight (required) The weight value of this edge.
    */
    public EdgeData(NodeData destination, int weight)
    {
        // do not allow unitialized destination node
        if(destination == null)
        {
            throw new IllegalArgumentException("Unable to create edge, destination node should not be null.");
        }
        _directToNode = destination;
       setWeight(weight); // set the weight of this node
    }
    
    /**
     * Getter for the weight of this edge.
     *
     * @return The weight value of this edge. Value is greater than or equal to zero.
     *
     */
    public int getWeight()
    {
        return _weight;
    }
    
    /**
     * Setter for the weight of this edge.
     *
     * @param weight (required) Sets the weight of the edge. Must be greater than or equal to zero.
     */
    public void setWeight(int weight)
    {
        if(weight <  0)
        {
            throw new IllegalArgumentException("weigth needs to be a positive integer.");
        }
        _weight = weight;
    }
    
    /**
     * Getter for the destination node connected to this edge.
     *
     * @return The node connected to the opposite end of thie edge. Node is of type NodeData.
     */
    public NodeData getDestination()
    {
        return _directToNode;
    }
}
