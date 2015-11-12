import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Amanda Olson
 */
public class NodeData
{
    private String _name;               // name of the node
    private List<EdgeData> _edges;      // list of edges that belong to node

    // constructor given @param name sets node _name to name
    public NodeData(String name)
    {
        // catch null/empty exception on name
        // throw an error if the input is invalid
        if(name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Unable to create node, node should be created with non null or empty name.");
        }

        _name = name;
        _edges = new ArrayList<EdgeData>();
    }
    
    // return the name of the node
    public String getName()
    {
        return _name;
    }
    
    // return a list of all edges that belong to the node
    public List<EdgeData> getAllEdges()
    {
        return _edges;
    }
    
    // add or update an edge within the node list
    public void addOrUpdateEdge(EdgeData edgeToAdd)
    {
        // if the edge is not null and the destination node is not null
        if(edgeToAdd != null && edgeToAdd.getDestination() != null)
        {
            // find possible existing edge connection
            EdgeData edge = findEdgeThatPointsToGivenNodeName(edgeToAdd.getDestination().getName());
            
            // if no edge connection found, we can add this as a new edge
            if(edge == null)
            {
                _edges.add(edgeToAdd);
            }
            // otherwise set new weight of edge connection
            else
            {
                edge.setWeight(edgeToAdd.getWeight());
            }
        }
    }
    
    // return an edge that points to a node where @param name is the node's name
    private EdgeData findEdgeThatPointsToGivenNodeName(String name)
    {
        // traverse the list of edges
        for( EdgeData edge : _edges )
        {
            // if there is a match return the existing edge
            if(edge.getDestination().getName().equalsIgnoreCase(name))
            {
                return edge;
            }
        }
        // otherwise we have exhausted all possibilities, return null
        return null;
    }
}
