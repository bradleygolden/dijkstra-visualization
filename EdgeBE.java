/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amanda Olson
 */
public class EdgeBE
{
    private int    edgeWeight;     // path cost of edge
    private NodeBE edgeStart;      // source node of path
    private NodeBE edgeEnd;        // destination node of path
    
    /**
    * Creates an EdgeBE object with edgeWeight a valid positive integer
    *     start and end nodes on the valid set of possible nodes
    * @param weight
    * @param source
    * @param destination
    */
    public EdgeBE(int weight, NodeBE source, NodeBE destination)
    {
        this.edgeWeight   = weight;
        this.edgeStart = source;
        this.edgeEnd = destination;
    }

}
