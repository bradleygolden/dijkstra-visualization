public class Node
{
    private int val; // value of the node
    private ScaledPoint point; // point where the current node is on the screen

    /**
     * Creates a Node object with val = 0.
     */
    public Node()
    {
        val = 0;
        point = new ScaledPoint(); 
    }

    /**
     * Creates a Node object with a predefined value.
     *
     * @param val Value of the current node.
     */
    public Node(int val)
    {
        this();
        this.val = val; // the set value of the node
    }

    /**
     * Setter for the Node value
     *
     * @param val New value to set node to.
     */
    public void setValue(int val)
    {
        this.val = val;
    }

    /**
     * Getter for the Node value
     *
     * @return The Node's value.
     */
    public int getValue()
    {
        return this.val;
    }

    /**
     * Setter for the Node's scaled point.
     *
     * @param point A ScaledPoint type
     */
    public void setScaledPoint(ScaledPoint point)
    {
        this.point = point;
    }

    /**
     * Getter for the Node's scaled point.
     *
     * @return Scaled point for this node.
     */
    public ScaledPoint getScaledPoint()
    {
        return this.point;
    }

    /**
     * Prints a string of the current node's value.
     *
     * @return Current node value.
     */
    public String toString()
    {
        return this.val + "";
    }

    /**
     * Test driver for the Node class.
     */
    public static void main(String[] args)
    {
        Node node1 = new Node(10);
        System.out.println(node1.toString());
    }
}
