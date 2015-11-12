import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Amanda Olson
 */
public class GraphData 
{
    private List<NodeData> _nodes;      // collection of nodes within this graph
    private int _edgeCount;             // workaround to get edge count without going through the collection
    
    // default constructor
    public GraphData()
    {
        _nodes = new ArrayList<NodeData>();
    }
    
    // add a node to list of nodes with _name set to @param name
    public void addNode(String name)
    {
        NodeData node = new NodeData(name);
        addNode(node);
    }
    
    // given @param node, as long as node is valid and not an already existing node add it to the list of nodes
    public void addNode(NodeData node)
    {
        if(node != null)
        {
            // see if given node already exists
            NodeData existingNode = findNodeWithName(node.getName());
            if(existingNode == null)
            {
                _nodes.add(node);
            }
        }
    }
    
    // add an edge to a node, @param mainNodeName is the name of the start node, @param destinationNodeName is the name of destination
    // weight is the value of the distance of the edge between the two nodes 
    public void addEdgeToNode(String mainNodeName, String destinationNodeName, int weight)
    {
        // check that start node is valid
        NodeData mainNode = findNodeWithName(mainNodeName);
        // if the start node DNE, then throw and exception error
        if(mainNode == null)
        {
            throw new NullPointerException("Main node must exist, in order to create edge");
        }
        
        // perform this same check on the destination node
        NodeData destinationNode = findNodeWithName(destinationNodeName);
        // if the destionation node DNE, add this node to the graph
        if(destinationNode == null)
        {
            destinationNode = new NodeData(destinationNodeName);
            _nodes.add(destinationNode);
        }
       
        // TO USE FOR UNDIRECTED GRAPHS
        // we add an edge from start node to destination and destination to start node
        EdgeData edge = new EdgeData(destinationNode, weight);
        EdgeData reverseEdge = new EdgeData(mainNode, weight);

        mainNode.addOrUpdateEdge(edge);
        destinationNode.addOrUpdateEdge(reverseEdge);
        
        _edgeCount++;
    }
    
    // return the node whose _name is @param name
    // handles instances where name and _name are same regardless of caseUpper/caseLower differences
    private NodeData findNodeWithName(String name)
    {
        // traverse all nodes contained in node list
        for( NodeData node : _nodes)
        {
            // if the node is found return the node
            if(node.getName().equalsIgnoreCase(name))
            {
                return node;
            }
        }
        // otherwise we have exhausted all possibilities within the list, return null
        return null;
    }
    
    // run algorithm and capture all states
    public List<DijkstraAlgorithmState> performDijkstraAlgorithm(String startName, String destination) throws Exception
    {
        List<DijkstraAlgorithmState> states = new ArrayList<DijkstraAlgorithmState>();
        
        NodeData startNode = findNodeWithName(startName);
        NodeData endNode = findNodeWithName(destination);
        
        if(startNode == null || endNode == null)
        {
            throw new Exception("Will not be able to solve, state node and end node must be in the graph");
        }
        
        // add initial state
        DijkstraAlgorithmState initialState = new DijkstraAlgorithmState(statNode);
        states.add(initialState);
        
        // keep going until current state reaches destination
        DijkstraAlgorithmState currentState =  initialState.makeCopy();
        while(currentState.getVisitedNodes().contains(endNode) == false)
        {
            // populate all possible paths
            currentState.updateDistances();
            // get the nearest unvisited node
            NodeData data = currentState.getNextNearestUnvisitedNode();
            // add node to visited node;
            currentState.getVisitedNodes().add(data);
            // add state of algorithm into the return collection
            states.add(currentState);
            
            // create a new copy of state where we can work from
            currentState = currentState.makeCopy();
        }
        
        return states;
    }
    
    // testing data
    public static GraphData createSampleGraphData()
    {
        GraphData logicalGraph = new GraphData();
        logicalGraph.addNode("A");
        logicalGraph.addNode("B");
        logicalGraph.addNode("C");
        logicalGraph.addNode("D");
        logicalGraph.addNode("E");
        logicalGraph.addNode("F");
        
        logicalGraph.addEdgeToNode("A", "B", 1);
        logicalGraph.addEdgeToNode("A", "C", 4);
        logicalGraph.addEdgeToNode("A", "E", 15);
        logicalGraph.addEdgeToNode("A", "F", 1);
        logicalGraph.addEdgeToNode("B", "C", 2);
        logicalGraph.addEdgeToNode("B", "D", 9);
        logicalGraph.addEdgeToNode("C", "E", 7);
        logicalGraph.addEdgeToNode("E", "F", 1);
        logicalGraph.addEdgeToNode("D", "F", 1);
        
        return logicalGraph;
    }
    
    // this should not be in here, should be refractored
    /* public Graph createView()
    {
        Random rand = new Random();
        Graph view = new Graph(_nodes.size(), _edgeCount * 2, "Sample");
        
        int index = 0;
        HashMap<NodeData, Integer> indexCache = new  HashMap<>();
        // set everything to infinity aka has not been calculated
        
        for(NodeData node : _nodes)
        {
           view.addNode(Integer.MAX_VALUE , node.getName());
           indexCache.put(node, index);
           index++;
        }

        for(NodeData node : _nodes)
        {
           index = indexCache.get(node);
           for(EdgeData edge : node.getAllEdges())
           {
                int destinationIndex = indexCache.get(edge.getDestination());
                view.addEdge(view.nodes[index], view.nodes[destinationIndex], edge.getWeight());
           }
        }

        for(index = 0; index < view.nodes.length; index++)
        {
            view.nodes[index].getScaledPoint().setXY(rand.nextDouble(), rand.nextDouble());
        }
        
        return view;
    }
    */
    
}
