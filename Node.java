import java.awt.Color;

/**
 * This class simulates a single node within a graph.
 */
public class Node 
{
    private static final Color DEFAULT_NODE_COLOR = Color.pink; // the default color of nodes
    private int val; // value of the node
    private ScaledPoint point; // point where the current node is on the screen
    private Color color; // the color of the current node
    private String name; // the name of this node

    private NodeData backendNode;

    public Node()
    {
        // TODO - delete this constructor
        color = DEFAULT_NODE_COLOR;
    }

    /**
     * TODO
     * Creates a Node object with a value.
     *
     * @param val Value of the current node.
     */
    public Node(String name)
    {
        this.point = new ScaledPoint(); 
        this.val = Integer.MAX_VALUE;
        this.name = name;
        this.backendNode = new NodeData(name);
    }

    /**
     * Setter for the Node value.
     *
     * @param val New value to set node to. Must be initialzed.
     */
    public void setValue(int val)
    {
        this.val = val;
    }

    /**
     * Getter for the Node value.
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
     * @param point A ScaledPoint type. Must be initialzed.
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
     * Getter for the Node's color.
     *
     * @return The nodes current color as a Color object.
     */
    public Color getColor()
    {
        return this.color;
    }

    /**
     * Setter for the Node's color.
     *
     * @param color An initialized Color object. Must be initialized.
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * Setter for the Node's name.
     *
     * @param name The name of the node. Must be initialized.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for the Node's name.
     *
     * @return The nodes name as a string.
     */
    public String getName()
    {
        return name;
    }

    public NodeData getData()
    {
        return this.backendNode;
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
        Node node1 = new Node("A");
        System.out.println(node1.toString());
    }
}
