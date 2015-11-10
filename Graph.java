import java.util.Arrays;
import java.util.List;
import java.awt.Color;

/**
 * Used to simulate a graph containing nodes and edges.
 */
public class Graph
{
    protected Node[] nodes; // all nodes in the graph
    protected Edge[] edges; // all edges in the graph
    private static String STARTNODE = "";
    private static String ENDNODE = "";
    private int maxNodes; // max number of nodes in the graph
    private int maxEdges; // max number of edges in the graph
    private int currNumNodes; // current number of nodes in the graph
    private int currNumEdges; // current number of edges in the graph
    private String name; // the name of this graph
    private List<DijkstraAlgorithmState> states; // a list of dijkstra algorithm states
    private int currentStateIndex; // the index of the current state of the graph
    protected String path = "";

    /**
     * Creates a Graph object with default values
     */
    public Graph()
    {
        nodes = null;
        edges = null;
        maxNodes = 0;
        maxEdges = 0;
        currNumNodes = 0;
        currNumEdges = 0;
        name = "Default Name";
        states = null;
        currentStateIndex = 0;
    }

    /**
     * Creates a Graph object with the number of nodes and edges pre-defined
     *
     * @param numNodes The number of nodes the graph contains. Must be greater than 0.
     * @param numEdges The number of edges the graph contains. Must be greater than 0.
     * @param name The name of the graph as a string. Must be initialized.
     */
    public Graph(int numNodes, int numEdges, String name)
    {
        this();
        nodes = new Node[numNodes];
        edges = new Edge[numEdges];
        maxNodes = numNodes;
        maxEdges = numEdges;
        this.name = name;
    }

    /**
     * Adds a node to the current graph.
     * 
     * @param val The value of the node to be added. Must be initialized.
     * @param name The name of the current node as a string. Must be initialized.
     */
    public void addNode(int val, String name)
    {
        if (currNumNodes == maxNodes) // Reached maximum nodes defined in constructor
        {
            System.out.println("You cannot add more nodes than you defined in the" +
                    "constructor.");
            return;
        }

        Node newNode = new Node(val, name); // create a new node
        nodes[currNumNodes] = newNode; // add node to graph
        currNumNodes++; // increment number of nodes in the graph
    } // end addNode

    /**
     * Adds an edge to the current graph.
     * <p>
     * Returns if the maximium amount of predefined edges has been exceeded.
     * 
     * @param start The starting node of the edge to be added. Must be initialized.
     * @param end The end node of the edge to be added. Must be initialized.
     * @param val The weight of the edge to be added. Must be initialized.
     */
    public void addEdge(Node start, Node end, int val)
    {
        if (currNumEdges == maxEdges) // Reached maximum edges defined in constructor
        {
            System.out.println("You have reached maximum ammount of edges");
            return;
        }

        Edge newEdge = new Edge(start, end, val);
        edges[currNumEdges] = newEdge;
        currNumEdges++;
    }

    /**
     * Getter for nodes in the graph.
     *
     * @return An array of nodes of type Node.
     */
    public Node[] getNodes()
    {
        return nodes;
    } // end getNodes

    /**
     * Setter for nodes in the graph.
     *
     * @param nodes An array of nodes of type Node. Must be initialized.
     */
    public void setNodes(Node[] nodes)
    {
        this.nodes = nodes;
    }

    /**
     * Getter for edges in the graph.
     *
     * @return An array of edges of type Edge.
     */
    public Edge[] getEdges()
    {
        return this.edges;
    }

    /**
     * Setter for edges in the graph.
     *
     * @param edges An array of edges of of type Edge. Must be initialized.
     */
    public void setEdges(Edge[] edges)
    {
        this.edges = edges;
    }

    /**
     * Setter for the name of the graph.
     *
     * @param name The name of the current graph as a string. Must be initialized.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for the name of the graph.
     *
     * @return The name of the graph as a string.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Prints out the current nodes and edges in the graph.
     */
    public String toString()
    {
        String temp = "";

        // display the info for each node
        for (Node n : nodes)
        {
            temp += "[" + n.toString() + "]: ";


            // display the info for each edge
            for (Edge e : edges)
                if ( n == e.getStart() )
                    temp += "-" + e.toString() + "-[" + e.getEnd().toString() + "] ";

            temp += "\n";
        }

        return temp;
    }

    /**
     * Runs dijkstra's algorithm and produces the graph states into states instance
     * variable.
     *
     * @param baseGraph A default graph of type GraphData. Graph is non empty.
     * @param start The node where the algorithm starts. Letter A-Z.
     * @param end The node where the algorithm ends. Letter A-Z.
     */
    public void setStates(GraphData baseGraph, String start, String end)
    {
        // populate all states
        try {
            states = baseGraph.performDijkstraAlgorithm(start, end);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
    }

    public boolean nextState()
    {
        if (currentStateIndex < states.size() - 1)
        {
            currentStateIndex++;
            path += states.get(currentStateIndex).getLastStartNode() + states.get(currentStateIndex).getLastEndNode();
            return true;
        }

        return false;
    }

    public boolean prevState()
    {
        if (currentStateIndex > 0)
        {
            currentStateIndex--;
            int pathLength = path.length();
            path = path.substring(0, pathLength - 2);
            return true;
        }

        return false;
    }

    public void updateGraph() {
        String lastVisitedNode = states.get(currentStateIndex).getLastVisitedNode().getName();
        for (Node n : nodes)
        {
            if (n.getName().equals(lastVisitedNode))
            {
                // Set visual cues
                n.setColor(Color.GREEN);
                n.setValue(states.get(currentStateIndex).getLastAccumulatedWeight());

                System.out.println(path);
                break;
            }
        }
    }

    /**
     * A fixed graph with 5 nodes and 7 edges. 
     * <p>
     * This is to be used in conjunction with Gui.java
     *
     * @return A graph object.
     * 
     */
    public static Graph graph1()
    {

        // create backend graph
        GraphData logicalGraph = new GraphData();

        logicalGraph.addNode("A");
        logicalGraph.addNode("B");
        logicalGraph.addNode("C");
        logicalGraph.addNode("D");
        logicalGraph.addNode("E");

        logicalGraph.addEdgeToNode("A", "B", 8);
        logicalGraph.addEdgeToNode("B", "C", 4);
        logicalGraph.addEdgeToNode("B", "D", 0);
        logicalGraph.addEdgeToNode("C", "A", 5);
        logicalGraph.addEdgeToNode("C", "E", 12);
        logicalGraph.addEdgeToNode("D", "C", 6);
        logicalGraph.addEdgeToNode("E", "D", 4);

        STARTNODE = "A";
        ENDNODE = "E";

        // draw the graph
        Graph graph = new Graph(5, 7, "Graph1");

        // get all states for Dijkstra's Algorithm
        graph.setStates(logicalGraph, STARTNODE, ENDNODE); 

        // add 5 nodes
        for (char i = 'A'; i < 'F'; i++)
        {
            graph.addNode(Integer.MAX_VALUE, i + "");
        }

        // add 7 edges
        graph.addEdge(graph.nodes[0], graph.nodes[1], 8);
        graph.addEdge(graph.nodes[1], graph.nodes[2], 4);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 0);
        graph.addEdge(graph.nodes[2], graph.nodes[0], 5);
        graph.addEdge(graph.nodes[2], graph.nodes[4], 12);
        graph.addEdge(graph.nodes[3], graph.nodes[2], 6);
        graph.addEdge(graph.nodes[4], graph.nodes[3], 4);

        // set scaled points for nodes
        graph.nodes[0].getScaledPoint().setXY(0.2, 0.8);
        graph.nodes[1].getScaledPoint().setXY(0.8, 0.8);
        graph.nodes[2].getScaledPoint().setXY(0.2, 0.4);
        graph.nodes[3].getScaledPoint().setXY(0.8, 0.4);
        graph.nodes[4].getScaledPoint().setXY(0.5, 0.3);

        return graph;
    }

    /**
     * A fixed graph with 6 nodes and 8 edges.
     * <p>
     * This is to be used in conjunction with Gui.java
     *
     * @return A graph object.
     * 
     */
    public static Graph graph2()
    {
        Graph graph = new Graph(6, 8, "Graph2");

        // add 6 nodes
        for (int i = 0; i < 6; i++)
        {
            graph.addNode(Integer.MAX_VALUE, Integer.toString(i));
        }

        // add 8 edges
        graph.addEdge(graph.nodes[0], graph.nodes[1], 10);
        graph.addEdge(graph.nodes[0], graph.nodes[4], 4);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 4);
        graph.addEdge(graph.nodes[2], graph.nodes[0], 9);
        graph.addEdge(graph.nodes[2], graph.nodes[3], 2);
        graph.addEdge(graph.nodes[2], graph.nodes[4], 10);
        graph.addEdge(graph.nodes[3], graph.nodes[5], 8);
        graph.addEdge(graph.nodes[4], graph.nodes[5], 6);

        // set scaled points for nodes
        graph.nodes[0].getScaledPoint().setXY(0.2, 0.8);
        graph.nodes[1].getScaledPoint().setXY(0.8, 0.8);
        graph.nodes[2].getScaledPoint().setXY(0.5, 0.5);
        graph.nodes[3].getScaledPoint().setXY(0.8, 0.5);
        graph.nodes[4].getScaledPoint().setXY(0.2, 0.2);
        graph.nodes[5].getScaledPoint().setXY(0.8, 0.2);

        return graph;
    }

    /**
     * A fixed graph with 6 nodes and 9 edges.
     * <p>
     * This is to be used in conjunction with Gui.java
     *
     * @return A graph object.
     * 
     */
    public static Graph graph3()
    {
        Graph graph = new Graph(6, 9, "Graph3");

        // add 6 nodes
        for (int i = 0; i < 6; i++)
        {
            graph.addNode(Integer.MAX_VALUE, Integer.toString(i));
        }

        // add 9 edges
        graph.addEdge(graph.nodes[0], graph.nodes[1], 10);
        graph.addEdge(graph.nodes[0], graph.nodes[2], 4);
        graph.addEdge(graph.nodes[0], graph.nodes[3], 7);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 4);
        graph.addEdge(graph.nodes[2], graph.nodes[3], 9);
        graph.addEdge(graph.nodes[2], graph.nodes[4], 5);
        graph.addEdge(graph.nodes[3], graph.nodes[4], 8);
        graph.addEdge(graph.nodes[3], graph.nodes[5], 7);
        graph.addEdge(graph.nodes[4], graph.nodes[5], 6);

        // set scaled points for nodes
        graph.nodes[0].getScaledPoint().setXY(0.2, 0.8);
        graph.nodes[1].getScaledPoint().setXY(0.8, 0.8);
        graph.nodes[2].getScaledPoint().setXY(0.1, 0.5);
        graph.nodes[3].getScaledPoint().setXY(0.9, 0.5);
        graph.nodes[4].getScaledPoint().setXY(0.5, 0.2);
        graph.nodes[5].getScaledPoint().setXY(0.9, 0.2);

        return graph;
    }

    /**
     * Test driver for the graph class.
     */
    public static void main(String[] args)
    {
        Graph graph = graph1();
        System.out.println(graph.toString());
    }
}
