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
     * Prints out the current nodes and edges in the graph.
     */
    public String toString()
    {
        String temp = "";
        for ( Node n : nodes )
            temp += n.toString() + " ";

        for ( Edge e : edges )
            temp += e.toString() + " ";

        return temp;
    }

    /**
     * Test driver for Graph class
     */
    public static void main(String[] args)
    {
        Graph graph = new Graph(3, 3);

        graph.addNode(Integer.MAX_VALUE);
        graph.addNode(Integer.MAX_VALUE);
        graph.addNode(Integer.MAX_VALUE);

        graph.addEdge(graph.nodes[0], graph.nodes[1], 10);
        graph.addEdge(graph.nodes[1], graph.nodes[2], 20);
        graph.addEdge(graph.nodes[2], graph.nodes[0], 10);

        System.out.println(graph.toString());
    }
}
