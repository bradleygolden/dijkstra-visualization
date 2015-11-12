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
    
    /**
    * Creates a DijkstraAlgorithmState object set to default values.
    * <p>
    * DijkstraAlgorithmState _distances is set to an empty HashMap,
    * with NodeData as the type of keys maintained by this map,
    * and with DijkstraAlgorithmStep as the type of mapped values
    * DijkstraAlgorithmState _visitedNodes is set to an empty ArrayList of NodeData 
    */
    private DijkstraAlgorithmState()
    {
        _distances = new HashMap<NodeData, DijkstraAlgorithmStep>();
        _visitedNodes = new ArrayList<NodeData>();
    }
    

    /**
    * Creates a DijkstraAlgorithmState object that takes a parameter start
    * and adds it to the list of visited nodes.
    * <p>
    * @param start
    */
    public DijkstraAlgorithmState(NodeData start)
    {
        this();
        _visitedNodes.add(start);     // add 
         
        DijkstraAlgorithmStep step = new DijkstraAlgorithmStep();
        
        step.startNode = start;
        step.endNode = start;
        
        step.accumulatedWeight = 0;   // current accumulated weight set to 0
        
        _distances.put(start, step);  // add start point and step to map
    }
    
    /**
    * Return a list of node data of all nodes currently visited 
    * <p>
    * @return _visitedNodes
    */
    public List<NodeData> getVisitedNodes()
    {
        return _visitedNodes;
    }
    
    /**
    * Return the map of total distances
    * <p>
    * @return _distances
    */
    public  HashMap<NodeData, DijkstraAlgorithmStep>  getAccumulatedDistances()
    {
        return _distances;
    }
    
    /**
    * Return a copy of the current state of the graph within the algorithm
    * <p>
    * @return _distances.get(getLastVisitedNode()).startNode.getName()
    */
    public String getLastStartNode() 
    {
        return  _distances.get(getLastVisitedNode()).startNode.getName();
    }

    /**
    * Return the current weight (distance) of the traversed path
    * <p>
    * @return _distances.get(getLastVisitedNode()).accumulatedWeight
    */
    public int getLastAccumulatedWeight()
    {
        return _distances.get(getLastVisitedNode()).accumulatedWeight;
    }

    /**
    * Return the current end node of the traversed path.
    * <p>
    * @return _distances.get(getLastVisitedNode()).endNode.getName()
    */
    public String getLastEndNode()
    {
        return _distances.get(getLastVisitedNode()).endNode.getName();
    }

    /**
    * Make and return a copy of the current state of the graph.
    * <p>
    * @return copy
    */
    public DijkstraAlgorithmState makeCopy()
    {
        DijkstraAlgorithmState copy = new DijkstraAlgorithmState();
        
        // for each NodeData contained in list of visited nodes create a deep copy
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
    
    /**
    * Return the previous node visited by the Dijkstra algorithm
    * <p>
    * @return _distances.get(getLastVisitedNode()).endNode.getName()
    */
    public NodeData getLastVisitedNode()
    {
	int size;						// total number of nodes visited
        NodeData lastVisitedNode;			        // node last added to end of visited node list

        // if the list of visited nodes is empty, return null
        if(getVisitedNodes().isEmpty())
        {
            return null;
        }    
	
	// else last node visited is the node at location size - 1
        size = getVisitedNodes().size();
        lastVisitedNode = getVisitedNodes().get(size - 1);

        return lastVisitedNode;
    }
    
    /**
    * Update distances within a graph
    * <p>
    */
    public void updateDistances()
    {
	NodeData current;			 // current node within path   
	List<EdgeData> possibleNewPaths;         // list of traversable edge data of a node
	DijkstraAlgorithmStep currentSteps;      // current history of graph
	int weightForDestination;                // weight of accumulated steps up to destination node

	current = getLastVisitedNode();

	// if current exists ...
        if(current != null)
        {
            possibleNewPaths = current.getAllEdges();
            
            // for each path within the list of possible traversable paths ...
            for(EdgeData path : possibleNewPaths)
            {
                currentSteps = _distances.get(current);         // get accumulated history up to current node
                NodeData destination = path.getDestination();   // set destination given shortest weighted edge
		
		// set an updated accumulated weight of the current traversed path plus the cost of moving to destination
                weightForDestination = ( currentSteps == null ? 0 : currentSteps.accumulatedWeight ) + path.getWeight();

                // if the map contains a key for the destination node ...
                if(_distances.containsKey(destination))
                {
                    // get the previous shortest path
                    DijkstraAlgorithmStep previousShortestStep = _distances.get(destination);
                    
                    // if previous possible path is not the shortest of the two ...
                    // we need to update it to the current result, so that for given node:
                    if(previousShortestStep.accumulatedWeight > weightForDestination)
                    {
                        previousShortestStep.startNode = current;
                        previousShortestStep.accumulatedWeight = weightForDestination;
                    }
                    
                }
                else
                {
                    // create new a step to add newly discovered nodes
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
    
    /**
    * Return node with the next shortest possible step(distance) to take
    * <p>
    * @return shortestStep.endNode
    */
    public NodeData getNextNearestUnvisitedNode()
    {
        DijkstraAlgorithmStep shortestStep;            // best possible move 

        shortestStep = null;                           // set current best possible move to null   
        
        // for each entry of the hasp map within the entry set of _distances ...
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
