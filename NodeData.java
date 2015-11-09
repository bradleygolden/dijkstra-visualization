import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Amanda Olson
 */
public class NodeData
{
    private String _name;
    private List<EdgeData> _edges;

    public NodeData(String name)
    {
        if(name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Unable to create node, node should be created with non null or empty name.");
        }
        _name = name;
        _edges = new ArrayList<EdgeData>();
    }
    
    public String getName()
    {
        return _name;
    }
    
    public List<EdgeData> getAllEdges()
    {
        return _edges;
    }
    
    public void addOrUpdateEdge(EdgeData edgeToAdd)
    {
        if(edgeToAdd != null && edgeToAdd.getDestination() != null)
        {
            EdgeData edge = findEdgeThatPointsToGivenNodeName(edgeToAdd.getDestination().getName());
            if(edge == null)
            {
                _edges.add(edgeToAdd);
            }
            else
            {
                edge.setWeight(edgeToAdd.getWeight());
            }
        }
    }
    
    private EdgeData findEdgeThatPointsToGivenNodeName(String name)
    {
        for( EdgeData edge : _edges )
        {
            if(edge.getDestination().getName().equalsIgnoreCase(name))
            {
                return edge;
            }
        }
        return null;
    }
}
