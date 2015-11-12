/**
 *
 * @author Amanda Olson
 */
public final class EdgeData
{
    private int    _weight;                // path cost of edge
    private NodeData _directToNode;        // destination node of path
    
    /**
    * Creates an EdgeData object with edgeWeight a valid positive integer
    *     start and end nodes on the valid set of possible nodes
    * @param weight
    * @param destination
    */
    public EdgeData(NodeData destination, int weight)
    {
        // catch null exception to add an edge without a destination
        // throw an error if the destination is set to null since edges can't exist without a start and end
        // as they are dependant on nodes themselves (to complete a vertex)
        if(destination == null)
        {
            throw new IllegalArgumentException("Unable to create edge, destination node should not be null.");
        }

        _directToNode = destination;
       setWeight(weight);
    }
    
    // return the weight of the edge
    public int getWeight()
    {
        return _weight;
    }
    
    // set the weight of the edge
    public void setWeight(int weight)
    {
        // beacsue Dijkstra's only handles positive values, catch all negative weight
        // throw an error if the value is negative
        if(weight <  0)
        {
            throw new IllegalArgumentException("Weight needs to be a positive integer.");
        }
        _weight = weight;
    }
    
    // get the node destination of current edge
    public NodeData getDestination()
    {
        return _directToNode;
    }
}
