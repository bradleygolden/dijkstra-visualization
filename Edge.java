import java.awt.Color;

/**
 * This class simulates a single edge between two nodes.
 */
public class Edge
{
    private int val; // value of the edge
    private Node start; // node where edge starts
    private Node end; // node where edge ends
    private EdgeData backendEdge; // backend edge associated with this edge
    private EdgeData backendEdgeReverse; // for undirected graph. must be used with backendEdge

    /**
     * Constructor
     * <p>
     * Creates an edge object with a given start, end, and edge value.
     * 
     * @param start The node where the edge starts. (Not null)
     * @param end The node where the edge ends. (Not null)
     * @param val Value of the edge. Value can be any integer.
     */
    public Edge(Node start, Node end, int val)
    {
        this.val = val;
        this.start = start;
        this.end = end;

        NodeData tempStart = start.getData(); // get backend node related with starting node
        NodeData tempEnd = end.getData(); // get backend node related with ending node

        // initialized two directoed edges
        // this simulates an undirected edge
        this.backendEdge = new EdgeData(tempStart, val);
        this.backendEdgeReverse = new EdgeData(tempEnd, val);

        // create an undirected node and adds is to the backend
        tempStart.addOrUpdateEdge(backendEdge);
        tempEnd.addOrUpdateEdge(backendEdgeReverse);
    }

    /**
     * Setter for the edge value.
     * 
     * @param val Value for the edge. Must be initialized. Can be any integer value.
     */
    public void setVal(int val)
    {
        this.val = val; // change the value of this edge
    }

    /**
     * Getter for the edge value.
     *
     * @return The current edge value. Value can be any integer.
     */
    public int getVal()
    {
        return this.val;
    }

    /**
     * Getter for the start node of the edge.
     *
     * @return The current edge's start node (type Node).
     */
    public Node getStart()
    {
        return this.start;
    }

    /**
     * Getter for the end node of the edge.
     *
     * @return The current edge's end point (type Node).
     */
    public Node getEnd()
    {
        return this.end;
    }

    /**
     * Setter for the start node of the edge.
     *
     * @param start The start of the edge (type Node). Must be initialized.
     */
    public void setStart(Node start)
    {
        this.start = start;
    }

    /**
     * Setter for the end node of the edge.
     *
     * @param start The end of the edge (type Node). Must be initialized.
     */
    public void setEnd(Node end)
    {
        this.end = end;
    }

    /**
     * Provides the current edge value.
     *
     * @return The edge value as a string of any integer value.
     */
    public String toString()
    {
        return val + "";
    }

    /**
     * Test driver for the Edge class.
     */
    public static void main(String[] args)
    {
        Node node1 = new Node("A");
        Node node2 = new Node("B");

        Edge edge1_2 = new Edge(node1, node2, 10);
        Edge edge2_1 = new Edge(node2, node1, 20);

        System.out.println(edge1_2.toString());
        System.out.println(edge2_1.toString());

        System.out.println(edge1_2.getStart().toString());
        System.out.println(edge1_2.getEnd().toString());
    }
}
