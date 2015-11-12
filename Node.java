import java.awt.Color;

/**
 * This class simulates a single node within a graph.
 */
public class Node 
{
    private int val; // value of the node
    private ScaledPoint point; // point where the current node is on the screen
    private String name; // the name of this node
    private NodeData backendNode; // this node is associated with a backendNode counterpart

    /**
     * Constructor
     * <p>
     * Creates a Node object with point = ScaledPoint object, val = infinity, name = name, 
     * backendNode = NodeData object.
     *
     * @param name (required) The name of the node object. Can be any string.
     */
    public Node(String name)
    {
        this.point = new ScaledPoint();  
        this.val = Integer.MAX_VALUE;
        this.name = name;
        this.backendNode = new NodeData(name);
    }

    /**
     * Setter for the node value.
     *
     * @param val New value to set node to. Must be initialzed. The value can be any
     * integer value.
     */
    public void setValue(int val)
    {
        this.val = val;
    }

    /**
     * Getter for the node value.
     *
     * @return The Node's current value. This value can be any integer value.
     */
    public int getValue()
    {
        return this.val;
    }

    /**
     * Setter for the node's scaled point.
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

    /**
     * Getter for the backend node associated with this Node object.
     *
     * @return A NodeData object.
     */
    public NodeData getData()
    {
        return this.backendNode;
    }

    /**
     * Prints a string of the current node's value.
     *
     * @return Current node value as a string.
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
