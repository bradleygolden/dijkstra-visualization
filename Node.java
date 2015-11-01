public class Node
{
    private int val; // value of the node

    /**
     * Creates a Node object with val = 0.
     */
    public Node()
    {
        val = 0;
    }

    /**
     * Creates a Node object with a predefined value.
     *
     * @param val Value of the current node.
     */
    public Node(int val)
    {
        this.val = val;
    }

    /**
     * Setter for the Node value
     *
     * @param val New value to set node to.
     */
    public void setVal(int val)
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
