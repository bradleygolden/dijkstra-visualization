import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Amanda Olson
 * DijkstraAlgorithmState represents one iteration of DijkstraAlgorithm's state
 */
public class DijkstraAlgorithmState
{
    private List<NodeData> _visitedNodes;                         // list of visited nodes
                                                                  // the last node in the list is the most recent node visited
    
    private HashMap<NodeData, DijkstraAlgorithmStep> _distances;  // shortest distance to get to node
                                                                  // this hashmap maps node and its shortest step to get there
                                                                  // if node is already visited, the value of map
                                                                  // will be the shortest possible found from the starting point
    
<<<<<<< HEAD:DijkstraAlgorithmState.java
    
    // default constructor
=======
>>>>>>> origin/master:DijkstraAlgorithmState.java
    private DijkstraAlgorithmState()
    {
        _distances = new HashMap<NodeData, DijkstraAlgorithmStep>();
        _visitedNodes = new ArrayList<NodeData>();
    }
    
    // constructor that takes a start point and add it to the visited list
    public DijkstraAlgorithmState(NodeData start)
    {
        this();
        _visitedNodes.add(start);
         
        DijkstraAlgorithmStep step = new DijkstraAlgorithmStep();
        
        step.startNode = start;
        step.endNode = start;
        
        step.accumulatedWeight = 0;   // current accumulated weight set to 0
        
        _distances.put(start, step);  // add start point and step to map
    }
    
    // return a list of node data of all nodes currently visited
    public List<NodeData> getVisitedNodes()
    {
        return _visitedNodes;
    }
    
    // return the map of total distances
    public  HashMap<NodeData, DijkstraAlgorithmStep>  getAccumulatedDistances()
    {
        return _distances;
    }
<<<<<<< HEAD:DijkstraAlgorithmState.java
    
    // make a copy of the current state of the graph within the algorithm
=======

    public String getLastStartNode() 
    {
        return  _distances.get(getLastVisitedNode()).startNode.getName();
    }

    public int getLastAccumulatedWeight()
    {
        return _distances.get(getLastVisitedNode()).accumulatedWeight;
    }

    public String getLastEndNode()
    {
        return _distances.get(getLastVisitedNode()).endNode.getName();
    }

>>>>>>> origin/master:DijkstraAlgorithmState.java
    public DijkstraAlgorithmState makeCopy()
    {
        DijkstraAlgorithmState copy = new DijkstraAlgorithmState();
        
        // deep copy state
        for(NodeData node : _visitedNodes)
        {
            copy._visitedNodes.add(node);
        }
        
        // for each entry of the set possible for Dijkstra, add each entry for map
        for(Entry<NodeData, DijkstraAlgorithmStep> map : _distances.entrySet())
        {
            copy._distances.put(map.getKey(),map.getValue().makeCopy());
        }

        return copy;
    }
    
    // return the previous node traveled by Dijkstra
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
    
    // update distances
    public void updateDistances()
    {
        NodeData current = getLastVisitedNode();
        if(current != null)
        {
            List<EdgeData> possibleNewPaths = current.getAllEdges();
            for(EdgeData path : possibleNewPaths)
            {
                // get accumuilated weight from current node
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
    
    // return node with the next shortest possible step(distance) to take
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
