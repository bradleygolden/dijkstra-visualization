import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This simulates a single node in a graph.
 * <p>
 * This class is used strictly for the backend when using Dijkstra's algorithm.
 *
 * @author Amanda Olson
 */
public class NodeData
{
    private String _name; // the name of this node
    private List<EdgeData> _edges; // list of backend edges associated with this node

    /**
     * Constructor
     * <p>
     * Creates a NodeData object with name = name.
     *
     * @param name (required) The name of this node. Can be any string value.
     */
    public NodeData(String name)
    {
        _name = name;
        _edges = new ArrayList<EdgeData>();
    }
    
    /**
     * Getter for the name of this node.
     *
     * @return The name of the node as a string.
     */
    public String getName()
    {
        return _name;
    }
    
    /**
     * Getter for all edges assocaited with this node
     *
     * @return A list of edges of type EdgeData. Is empty if no edges are connected
     * with this node.
     */
    public List<EdgeData> getAllEdges()
    {
        return _edges;
    }
    
    /**
     * Adds an edge to this node or updates an edge associated with this node.
     *
     * @param edgeToAdd (required) An edge of type EdgeData. 
     */
    public void addOrUpdateEdge(EdgeData edgeToAdd)
    {
        if(edgeToAdd != null && edgeToAdd.getDestination() != null)
        {
            // look for an existing edge given the provided edgeToAdd parameter
            // and return that edge if it exists
            EdgeData edge = findEdgeThatPointsToGivenNodeName(edgeToAdd.getDestination().getName());
            
            // if the edge is null, the edge to add is new and will be connected with this node 
            if(edge == null)
            {
                _edges.add(edgeToAdd); // add edge to _edges list
            }
            // edge already exists, therefore only change the weight
            else
            {
                edge.setWeight(edgeToAdd.getWeight()); // update existing edge's weight
            }
        }
    }
    
    /**
     * Finds an edge given the name of an edge.
     *
     * @param name (required) The name of an edge that is pre-existing or not.
     * @return An edge that has a name that matches the name parameter. Edge 
     * is of type EdgeData.
     */
    private EdgeData findEdgeThatPointsToGivenNodeName(String name)
    {
        // iterate through all backend edges
        for( EdgeData edge : _edges )
        {
            // check the name of a single edge
            if(edge.getDestination().getName().equalsIgnoreCase(name))
            {
                return edge; // a match has been found, return an edge of type EdgeData
            }
        }
        return null; // no edge found
    }
}
