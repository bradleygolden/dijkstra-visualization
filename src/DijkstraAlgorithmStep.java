/**
 *
 * @author  Amanda Olson
 * DijkstraAlgorithmStep represents that path of node to another
 */
public class DijkstraAlgorithmStep 
{
    public NodeData startNode;		// initial start position
    public NodeData endNode;		// destination position
    public int accumulatedWeight;	// current weight of traversed path
    
    /**
    * Make and return a copy of the current single move path
    * <p>
    * @return copy
    */
    public DijkstraAlgorithmStep makeCopy()
    {
        DijkstraAlgorithmStep copy;     // copy of single move path
	
	
	copy = new DijkstraAlgorithmStep();
        copy.startNode = startNode;
        copy.endNode = endNode;
        copy.accumulatedWeight = accumulatedWeight;
        

	return copy;
    }
}
