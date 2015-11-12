import java.util.List;

/**
 * Used to simulate a graph containing nodes and edges.
 */
public class Graph
{
    // start and end is chosen and changed by the user
    private static String startNode = "A"; // starting point in the graph
    private static String endNode = "A"; // ending point in the graph
    // cannot initalize this static variable until a new graph is created
    private static GraphData backendGraph; // graph strictly used for backend (algorithm) processing

    //
    // middle tier related instance variables
    //
    protected Node[] nodes; // all nodes in the graph
    protected Edge[] edges; // all edges in the graph

    //
    // backend tier related instance variables
    //
    // BACKEND_GRAPH contains all information about the graph pertaining to the backend and  
    // is only to be used in conjunction with Data classes.

    // graph related instance variables
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
    //public Graph()
    //{
        //nodes = null;
        //maxNodes = 0;
        //maxEdges = 0;
        //currNumNodes = 0;
        //currNumEdges = 0;
        //name = "Default Name";
        //states = null;
        //currentStateIndex = 0;
    /*}*/

    /**
     * Creates a Graph object with the number of nodes and edges pre-defined
     *
     * @param numNodes The number of nodes the graph contains. Must be greater than 0.
     * @param numEdges The number of edges the graph contains. Must be greater than 0.
     * @param name The name of the graph as a string. Must be initialized.
     * TODO
     */
    public Graph(int numNodes, int numEdges, String name)
    {
        //this();
        nodes = new Node[numNodes];
        edges = new Edge[numEdges];
        maxNodes = numNodes;
        maxEdges = numEdges;
        this.name = name;
        backendGraph = new GraphData();
    }

    /**
     * Adds a node to the current graph.
     * 
     * @param val The value of the node to be added. Must be initialized.
     * @param name The name of the current node as a string. Must be initialized.
     */
    public void addNode(String name)
    {
        Node newNode; // new node to be added to business logic tier

        if (currNumNodes == maxNodes) // Reached maximum nodes defined in constructor
        {
            System.out.println("You cannot add more nodes than you defined in the" +
                    "constructor.");
            return;
        }

        newNode = new Node(name); // create a new logic node

        // add node to backendGraph
        backendGraph.addNode(newNode.getData());

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
        Edge newEdge;

        if (currNumEdges == maxEdges) // Reached maximum edges defined in constructor
        {
            System.out.println("You have reached maximum ammount of edges");
            return;
        }

        newEdge = new Edge(start, end, val);

        // add edge to backendGraph
        backendGraph.addEdgeToNode(start.getName(), end.getName(), val);

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
     * Collects the names of the nodes in the graph
     *
     * @return String[] a list of the graph's node names
     */
    public String[] getNodeNames()
    {
        String[] nodeNames = new String[maxNodes + 1];
        
        int i = 1;
        nodeNames[0] = ""; // empty to start
        for (Node n : nodes)
        {
           nodeNames[i] = n.getName();
           i++;
        }

        return nodeNames;
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
    public void setStates()
    {
        // populate all states
        try {
            states = backendGraph.performDijkstraAlgorithm(startNode, endNode);
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

    /**
     * Resets the path so algorithm can be restarted with new parameters
     */
    public void resetPath()
    {
        currentStateIndex = 0;
        path = "";
        for (Node n : nodes)
        {
            n.setValue(Integer.MAX_VALUE);
        }
    }

    /**
     * Updates visual distances
     */
    public void updateGraph() 
    {
        String lastVisitedNode = states.get(currentStateIndex).getLastVisitedNode().getName();
        for (Node n : nodes)
        {
            if (n.getName().equals(lastVisitedNode))
            {
                // Set visual cues
                n.setValue(states.get(currentStateIndex).getLastAccumulatedWeight());

                break;
            }
        }
    }

    /**
     * Parses path to find find current solution
     *
     * @return string of the current solution
     */
    public String getPath()
    {
        if (path.length() < 2)
        {
            return "";
        }

        int n = path.length() - 2;
        String result = path.substring(n, n+2);

        for (int i = n - 2; i >= 0; i-=2)
        {
            if (path.charAt(i+1) == result.charAt(result.length() - 2))
            {
                result = result + path.substring(i, i+2);
            }
        }

        return result;
    }

    /**
     * Sets starting node of algorithm
     */
    public void setStart(String start)
    {
        startNode = start;
    }

    /**
     * Sets ending node of algorithm
     */
    public void setEnd(String end)
    {
        endNode = end;
    }

    /**
     * A fixed graph with 5 nodes and 7 edges. 
     * <p>
     * This is to be used in conjunction with Gui.java
     *
     * @return A graph object.
     * 
     */
    //public static Graph graph1(string startNode, string endNode)
    public static Graph graph1()
    {
    	Graph graph;

        graph = null;
        graph = new Graph(5, 7, "Graph1");

        // add 5 nodes
        for (char i = 'A'; i < 'F'; i++)
        {
            graph.addNode(i + "");
        }

        startNode = "A";
        endNode = "A";

        // add 7 edges
        graph.addEdge(graph.nodes[0], graph.nodes[1], 8);
        graph.addEdge(graph.nodes[1], graph.nodes[2], 4);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 0);
        graph.addEdge(graph.nodes[2], graph.nodes[0], 9);
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
        /*      // draw the graph*/
        Graph graph = new Graph(6, 8, "Graph2");

        // add 6 nodes
        for (char i = 'A'; i < 'G'; i++)
        {
            graph.addNode(i + "");
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

        startNode = "A";
        endNode = "E";

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
        for (char i = 'A'; i < 'G'; i++)
        {
            graph.addNode(i + "");
        }

        // add 9 edges to middle tier node
        graph.addEdge(graph.nodes[0], graph.nodes[1], 10);
        graph.addEdge(graph.nodes[0], graph.nodes[2], 4);
        graph.addEdge(graph.nodes[0], graph.nodes[3], 7);
        graph.addEdge(graph.nodes[1], graph.nodes[3], 4);
        graph.addEdge(graph.nodes[2], graph.nodes[3], 9);
        graph.addEdge(graph.nodes[2], graph.nodes[4], 5);
        graph.addEdge(graph.nodes[3], graph.nodes[4], 8);
        graph.addEdge(graph.nodes[3], graph.nodes[5], 7);
        graph.addEdge(graph.nodes[4], graph.nodes[5], 6);

        startNode = "A";
        endNode = "E";

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
     * 
     * @param n number of nodes between 2 and 25
     * @return
     */
    public static Graph generateGraph(int n)
    {
    	Graph graph;
    	int bridgeEdges;
    	int index;
    	int index2;
    	
    	bridgeEdges = (int)(Math.random()*(n*n-n-2) + 1) / 2;
        graph = new Graph(n,n-1 + bridgeEdges , "Generic graph");

        // add first connection
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge(graph.nodes[0], graph.nodes[1], (int)(Math.random()*20+1));
        
        // add n-1 connections
        for (char i = 2; i < n; i++)
        {
            graph.addNode((char)(i + 'A') + "");
            
            // select random previous node
            // and connect it with last added node
            index = (int)(Math.random()*i);
            graph.addEdge(graph.nodes[index], graph.nodes[i], (int)(Math.random()*20+1));
        }        
        
        for(int i=0; i < graph.getNodes().length; i++)
        {
        	graph.nodes[i].getScaledPoint().setXY(0.2+(Math.sin(i*Math.PI*2/graph.getNodes().length)+1)/3, 
        			0.2+(Math.cos(i*Math.PI*2/graph.getNodes().length)+1)/3);
        }
        
        while(bridgeEdges > 0)
        {
        	index = (int)(Math.random()*n);
        	index2 = (int)(Math.random()*n);
        	
        	if(index != index2 && !graph.hasEdge(graph.nodes[index], graph.nodes[index2]))
        	{
        		graph.addEdge(graph.nodes[index], graph.nodes[index2], (int)(Math.random()*20+1));
        		bridgeEdges--;
        	}
        }

        startNode = "A";
        endNode = "A";
        graph.setStates();

        return graph;
    }
    
    private boolean hasEdge(Node n1, Node n2)
    {    	
    	for(Edge e : edges)
    	{
    		if(e == null)
    		{
    			continue;
    		}
    		
    		if((e.getEnd() == n1 && e.getStart() == n2) || 
    		   (e.getEnd() == n2 && e.getStart() == n1))
    		{
    			return true;
    		}
    	}
    	
    	return false;
    }

    public static Graph regenerateGraph(Graph graph)
    {
        Edge[] tempEdges;
        
        tempEdges = new Edge[graph.maxEdges];
        
        for (int i = 0; i < graph.maxEdges; i++)
        {
            tempEdges[i] = new Edge(graph.edges[i].getStart(), 
                    graph.edges[i].getEnd(), graph.edges[i].getVal());
        }
        
        graph.edges = new Edge[graph.maxEdges];
        graph.currNumEdges = 0;

        for(Edge e : tempEdges)
        {
        	graph.addEdge(e.getEnd(), e.getStart(), e.getVal());
        }

        graph.setStates();
        return graph;
    }
}
