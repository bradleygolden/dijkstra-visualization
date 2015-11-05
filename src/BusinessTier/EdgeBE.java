/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessTier;

/**
 *
 * @author Amanda Olson
 */
public class EdgeBE
{
    int    edgeWeight;     // path cost of edge
    NodeBE edgeStart;      // starting point of path
    NodeBE edgeEnd;        // destination node of path
    
    /**
    * Creates an EdgeBE object with edgeWeight a valid positive integer
    *     start and end nodes on the valid set of possible nodes
    * @param weight
    * @param start
    * @param end
    */
    public EdgeBE(int weight, NodeBE start, NodeBE end)
    {
        this.edgeWeight   = weight;
        this.edgeStart = start;
        this.edgeEnd = end;
    }

}
