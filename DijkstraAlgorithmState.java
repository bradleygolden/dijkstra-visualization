/*
 * for calculating path to return to the front end
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author Amanda Olson
 * DijkstraAlgorithmState represents one iteration of DijkstraAlgorithm's state
 */
public class DijkstraAlgorithmState
{
    // list of visited node
    // last node is the most recent node visited
    private List<NodeData> _visitedNodes;
    
    // Shortest distance to get to node
    // This hashmap maps node and its shortest step to get there.
    // if node is already visited, the value of map will be the shortest could possibly be found from the starting point
    private HashMap<NodeData, DijkstraAlgorithmStep> _distances;
    
    
    private DijkstraAlgorithmState()
    {
        _distances = new HashMap<NodeData, DijkstraAlgorithmStep>();
        _visitedNodes = new ArrayList<NodeData>();
    }
    
    public DijkstraAlgorithmState(NodeData start)
    {
        this();
        _visitedNodes.add(start);
         
        DijkstraAlgorithmStep step = new DijkstraAlgorithmStep();
        step.startNode = start;
        step.endNode = start;
        step.accumulatedWeight = 0;
        _distances.put(start, step );
    }
    
    public List<NodeData> getVisitedNodes()
    {
        return _visitedNodes;
    }
    
    public  HashMap<NodeData, DijkstraAlgorithmStep>  getAccumulatedDistances()
    {
        return _distances;
    }
    
    public DijkstraAlgorithmState makeCopy()
    {
        DijkstraAlgorithmState copy = new DijkstraAlgorithmState();
        
        // deep copy state
        for(NodeData node : _visitedNodes)
        {
            copy._visitedNodes.add(node);
        }
        
        for(Entry<NodeData, DijkstraAlgorithmStep> map : _distances.entrySet())
        {
            copy._distances.put(map.getKey(),map.getValue().makeCopy());
        }
        return copy;
    }
    
    public NodeData getLastVisitedNode()
    {
        if(getVisitedNodes().isEmpty())
        {
            return null;
        }    
        int size = getVisitedNodes().size();
        NodeData lastVisitedNode = getVisitedNodes().get(size - 1);
        return lastVisitedNode;
    }
    
    public void updateDistances()
    {
        NodeData current = getLastVisitedNode();
        if(current != null)
        {
            List<EdgeData> possibleNewPaths = current.getAllEdges();
            for(EdgeData path : possibleNewPaths)
            {
                // get accumilated weight from current node
                DijkstraAlgorithmStep currentSteps = _distances.get(current);
                
                NodeData destination = path.getDestination();
                int weightForDestination = ( currentSteps == null ? 0 : currentSteps.accumulatedWeight ) + path.getWeight();
                
                if(_distances.containsKey(destination))
                {
                    // get previous shortest path
                    DijkstraAlgorithmStep previousShortestStep = _distances.get(destination);
                    
                    // if previous possible path is not the shortest
                    // we need to update it. So that for given node
                    if(previousShortestStep.accumulatedWeight > weightForDestination)
                    {
                        previousShortestStep.startNode = current;
                        previousShortestStep.accumulatedWeight = weightForDestination;
                    }
                    
                }
                else
                {
                    // create new a step to add newly discovered nodes.
                    DijkstraAlgorithmStep step = new DijkstraAlgorithmStep();
                    step.startNode = current;
                    step.endNode = destination;
                    step.accumulatedWeight = weightForDestination;
                    
                    // update distance
                    _distances.put(destination, step);
                }
            }
        }
    }
    
    public NodeData getNextNearestUnvisitedNode()
    {
        DijkstraAlgorithmStep shortestStep = null;
        for(Entry<NodeData, DijkstraAlgorithmStep> map : _distances.entrySet())
        {
            // try to get shortest step by comparing accumulatedWeight
            // make sure that the shortest step is not already visited
            if( shortestStep == null || _visitedNodes.contains(shortestStep.endNode) ||
               ( shortestStep.accumulatedWeight > map.getValue().accumulatedWeight && _visitedNodes.contains(map.getValue().endNode) == false)) 
            {
                shortestStep = map.getValue();
            }
        }
        return shortestStep.endNode;
    }
}
