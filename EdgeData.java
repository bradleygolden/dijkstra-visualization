/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amanda Olson
 */
public final class EdgeData
{
    private int    _weight;     // path cost of edge
    private NodeData _directToNode;        // destination node of path
    
    /**
    * Creates an EdgeBE object with edgeWeight a valid positive integer
    *     start and end nodes on the valid set of possible nodes
    * @param weight
    * @param destination
    */
    public EdgeData(NodeData destination, int weight)
    {
        if(destination == null)
        {
            throw new IllegalArgumentException("Unable to create edge, destination node should not be null.");
        }
        _directToNode = destination;
       setWeight(weight);
    }
    
    public int getWeight()
    {
        return _weight;
    }
    
    public void setWeight(int weight)
    {
        if(weight <  0)
        {
            throw new IllegalArgumentException("weigth needs to be a positive integer.");
        }
        _weight = weight;
    }
    
    public NodeData getDestination()
    {
        return _directToNode;
    }
}
