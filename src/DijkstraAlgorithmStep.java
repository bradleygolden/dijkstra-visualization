/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author  Amanda Olson
 * DijkstraAlgorithmStep represents that path of node to another
 * TODO : create accessors for all of the class's properties
 */
public class DijkstraAlgorithmStep {
    public NodeData startNode;
    public NodeData endNode;
    public int accumulatedWeight;
    
    public DijkstraAlgorithmStep makeCopy()
    {
        DijkstraAlgorithmStep copy = new DijkstraAlgorithmStep();
        copy.startNode = startNode;
        copy.endNode = endNode;
        copy.accumulatedWeight = accumulatedWeight;
        return copy;
    }
}
