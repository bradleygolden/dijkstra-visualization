import java.util.Arrays;

/**
 * Used to simulate a graph containing nodes and edges.
 */
public class Graph
{
    protected Node[] nodes; // all nodes in the graph
    protected Edge[] edges; // all edges in the graph
    private int maxNodes; // max number of nodes in the graph
    private int maxEdges; // max number of edges in the graph
    private int currNumNodes; // current number of nodes in the graph
    private int currNumEdges; // current number of edges in the graph

    /**
     * Creates a Graph object with default values
     */
    public Graph()
    {
        nodes = new Node[0];
        edges = new Edge[0];
        maxNodes = 0;
        maxEdges = 0;
        currNumNodes = 0;
        currNumEdges = 0;
    }

    /**
     * Creates a Graph object with the number of nodes and edges pre-defined
     *
     * @param numNodes The number of nodes the graph contains. Must be greater than 0.
     * @param numEdges The number of edgs the graph contains. Must be greater than 0.
     */
    public Graph(int numNodes, int numEdges)
    {
        this();
        nodes = new Node[numNodes];
        edges = new Edge[numEdges];
        maxNodes = numNodes;
        maxEdges = numEdges;
    }

    /**
     * Adds a node to the current graph.
     * 
     * @param val The value of the node to be added.
     */
    public void addNode(int val)
    {
        if (currNumNodes == maxNodes) // Reached maximum nodes defined in constructor
        {
            System.out.println("You cannot add more nodes than you defined in the" +
                    "constructor.");
            return;
        }

        Node newNode = new Node(val); // create a new node
        nodes[currNumNodes] = newNode; // add node to graph
        currNumNodes++; // increment number of nodes in the graph
    } // end addNode

    /**
     * Adds a edge to the current graph.
     * 
     * @param start The starting node of the edge to be added.
     * @param end The end node of the edge to be added.
     * @param val The weight of the edge to be added.
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
     * @param nodes An array of nodes of type Node.
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
     * @param An array of edges of of type Edge.
     */
    public void setEdges(Edge[] edges)
    {
        this.edges = edges;
    }

    /**
     * Prints out the current nodes and edges in the graph.
     */
    public String toString()
    {
        String temp = "";

        for (Node n : nodes)
        {
            temp += "[" + n.toString() + "]: ";

            for (Edge e : edges)
                if ( n == e.getStart() )
                    temp += "-" + e.toString() + "-[" + e.getEnd().toString() + "] ";

            temp += "\n";
        }

        return temp;
    }

    /**
     * Test driver for Graph class
     * <p>
     * This is to be used in conjuction with Gui.java
     *
     * @return A graph object.
     */
    public static Graph testGraph()
    {
        Graph graph = new Graph(3, 3);

        graph.addNode(Integer.MAX_VALUE);
        graph.addNode(Integer.MAX_VALUE);
        graph.addNode(Integer.MAX_VALUE);

        graph.addEdge(graph.nodes[0], graph.nodes[1], 10);
        graph.addEdge(graph.nodes[1], graph.nodes[2], 20);
        graph.addEdge(graph.nodes[2], graph.nodes[0], 10);
        
        graph.nodes[0].getScaledPoint().setXY(0.1, 0.6);
        graph.nodes[1].getScaledPoint().setXY(0.5, 0.6);
        graph.nodes[2].getScaledPoint().setXY(0.2, 0.8);
        
        return graph;
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
        Graph graph = new Graph(5, 7);

        for (int i = 0; i < 5; i++)
        {
            graph.addNode(Integer.MAX_VALUE);
        }

        graph.addEdge(graph.nodes[0], graph.nodes[1], 10);
        graph.addEdge(graph.nodes[1], graph.nodes[2], 4);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 9);
        graph.addEdge(graph.nodes[2], graph.nodes[0], 5);
        graph.addEdge(graph.nodes[2], graph.nodes[4], 10);
        graph.addEdge(graph.nodes[3], graph.nodes[2], 6);
        graph.addEdge(graph.nodes[4], graph.nodes[3], 4);

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
        Graph graph = new Graph(6, 8);

        for (int i = 0; i < 6; i++)
        {
            graph.addNode(Integer.MAX_VALUE);
        }

        graph.addEdge(graph.nodes[0], graph.nodes[1], 10);
        graph.addEdge(graph.nodes[0], graph.nodes[4], 4);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 4);
        graph.addEdge(graph.nodes[2], graph.nodes[0], 9);
        graph.addEdge(graph.nodes[2], graph.nodes[3], 2);
        graph.addEdge(graph.nodes[2], graph.nodes[4], 10);
        graph.addEdge(graph.nodes[3], graph.nodes[5], 8);
        graph.addEdge(graph.nodes[4], graph.nodes[5], 6);

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
        Graph graph = new Graph(6, 9);

        for (int i = 0; i < 6; i++)
        {
            graph.addNode(Integer.MAX_VALUE);
        }

        graph.addEdge(graph.nodes[0], graph.nodes[1], 10);
        graph.addEdge(graph.nodes[0], graph.nodes[2], 4);
        graph.addEdge(graph.nodes[0], graph.nodes[3], 7);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 4);
        graph.addEdge(graph.nodes[2], graph.nodes[3], 9);
        graph.addEdge(graph.nodes[2], graph.nodes[4], 5);
        graph.addEdge(graph.nodes[3], graph.nodes[4], 8);
        graph.addEdge(graph.nodes[3], graph.nodes[5], 7);
        graph.addEdge(graph.nodes[4], graph.nodes[5], 6);

        graph.nodes[0].getScaledPoint().setXY(0.2, 0.8);
        graph.nodes[1].getScaledPoint().setXY(0.8, 0.8);
        graph.nodes[2].getScaledPoint().setXY(0.1, 0.5);
        graph.nodes[3].getScaledPoint().setXY(0.9, 0.5);
        graph.nodes[4].getScaledPoint().setXY(0.5, 0.2);
        graph.nodes[5].getScaledPoint().setXY(0.9, 0.2);

        return graph;
    }
}
