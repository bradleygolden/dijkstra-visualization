import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author Bradley Golden, Amanda Olson, Cody Roberts, Maciej Szpakowski
 * <p>
 * @project  Project 3 - Data Structure Visualization
 * <p>
 * @date November 12, 2015
 * <p>
 * Backend for the Graph class. Use for all backend processing
 * while using Dijkstra's algorithm.
 */
public class GraphData
{
    // collection of nodes within this graph
    private List<NodeData> _nodes;

    // workaround to get edgecount without going through the collection
    private int _edgeCount;

    public GraphData()
    {
        _nodes = new ArrayList<NodeData>();
    }

    public void addNode(String name)
    {
        NodeData node = new NodeData(name);
        addNode(node);
    }

    public void addNode(NodeData node)
    {
        if(node != null)
        {
            // see if given node already exist
            NodeData existingNode = findNodeWithName(node.getName());
            if(existingNode == null)
            {
                _nodes.add(node);
            }
        }
    }

    public void addEdgeToNode(String mainNodeName, String destinationNodeName, int weight)
    {
        NodeData mainNode = findNodeWithName(mainNodeName);
        if(mainNode == null)
        {
            throw new NullPointerException("Main node must exist, in order to create edge");
        }

        NodeData destinationNode = findNodeWithName(destinationNodeName);
        if(destinationNode == null)
        {
            destinationNode = new NodeData(destinationNodeName);
            _nodes.add(destinationNode);
        }

        // to make graph become indirrected graph
        // we add edge from mainNode to destination and destination to mainnode
        EdgeData edge = new EdgeData(destinationNode, weight);
        EdgeData reverseEdge = new EdgeData(mainNode, weight);
        mainNode.addOrUpdateEdge(edge);
        destinationNode.addOrUpdateEdge(reverseEdge);
        _edgeCount++;
    }

    private NodeData findNodeWithName(String name)
    {
        for( NodeData node : _nodes)
        {
            if(node.getName().equalsIgnoreCase(name))
            {
                return node;
            }
        }
        return null;
    }

    /**
    * Run Algor and capture all state
    */
    public List<DijkstraAlgorithmState> performDijkstraAlgorithm(String startName, String destination) throws Exception
    {
        List<DijkstraAlgorithmState> states = new ArrayList<DijkstraAlgorithmState>();

        NodeData statNode = findNodeWithName(startName);
        NodeData endNode = findNodeWithName(destination);

        if(statNode == null || endNode == null)
        {
            throw new Exception("Will not be able to solve, state node and end node must be in the graph");
        }

        // add initial state
        DijkstraAlgorithmState initialState = new DijkstraAlgorithmState(statNode);
        states.add(initialState);

        // keep going until current state reach destination
        DijkstraAlgorithmState currentState =  initialState.makeCopy();
        while(currentState.getVisitedNodes().contains(endNode) == false)
              //|| )
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
    public Graph createView()
    {
        Random rand = new Random();
        Graph view = new Graph(_nodes.size(), _edgeCount * 2, "Sample");

        int index = 0;
        HashMap<NodeData, Integer> indexCache = new  HashMap<>();
        // set everything to infinity aka has not been calculated

        for(NodeData node : _nodes)
        {
           view.addNode(node.getName());
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

}
