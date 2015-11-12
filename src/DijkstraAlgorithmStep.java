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
