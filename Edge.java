public class Edge
{
    private int val; // value of the edge
    private Node start; // node where edge starts
    private Node end; // node where edge ends

    /**
     * Creates an edge object set to default values
     * <p>
     * Edge value is set to 0. Start and end nodes are set to 0 and null.
     */
    public Edge()
    {
        val = 0;
        start = null;
        end = null;
    }

    /**
     * Creates an edge object with a given edge value.
     * <p>
     * Start and ending nodes are set to null.
     *
     * @param val value of the edge
     */
    public Edge(int val)
    {
        this();
        this.val = val;
    }

    /**
     * Creates an edge object with a given start, end, and edge value.
     * 
     * @param start The node where the edge starts. (Not null)
     * @param end The node where the edge ends. (Not null)
     * @param val Value of the edge.
     */
    public Edge(Node start, Node end, int val)
    {
        this(val);
        this.start = start;
        this.end = end;
    }

    /**
     * Setter for the edge value.
     * 
     * @param val Value for the edge.
     */
    public void setVal(int val)
    {
        this.val = val;
    }

    /**
     * Getter for the edge value.
     *
     * @return The current edge value.
     */
    public int getVal()
    {
        return this.val;
    }

    /**
     * Provides the current edge value
     *
     * @return The edge value.
     */
    public String toString()
    {
        return val + "";
    }

    /**
     * Test driver for the Edge class
     */
    public static void main(String[] args)
    {
        Node node1 = new Node(Integer.MAX_VALUE);
        Node node2 = new Node(Integer.MAX_VALUE);

        Edge edge1_2 = new Edge(node1, node2, 10);
        Edge edge2_1 = new Edge(node2, node1, 20);

        System.out.println(edge1_2.toString());
        System.out.println(edge2_1.toString());
    }
}
