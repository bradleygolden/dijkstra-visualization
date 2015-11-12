import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Wraps logic for drawing the graph's node on screen.
 * @date 11/11/2015
 * @project Data structure visualization - Dijkstra's Algorithm
 * @author Maciej Szpakowski
 */
public class DrawableNode extends Drawable
{
	public static final int DIST_BOX_OFFSET_X = 20;  // x pos. of the distance box relative to node
	public static final int DIST_BOX_OFFSET_Y = -15; // y pos. of the distance box relative to node
	public static final int DIST_BOX_HEIGTH = 20;    // height of the distance box
	public static final int DIST_BOX_WIDTH = 40;     // width of the distance box
	public static final int RADIUS = 20;             // default radius of the node
	public static final int RADIUS_BIG = 25;         // default radius when node is highlighted
	public static final int FRAME_THICK = 3;         // thicnkess of the thick frame
	public static final int FRAME_THIN = 1;          // thickness of the thin frame
	public static final Color NODE_UNVISITED_COLOR = new Color(200,200,200); // unvisited node color
	public static final Color NODE_VISITED_COLOR = new Color(200,255,200);   // visited node color
	public static final Color NODE_PATH_COLOR = new Color(50,255,50);        // path node color
	public static final Color NODE_START_COLOR = Color.GREEN;    // frame color of start node
	public static final Color NODE_END_COLOR = Color.BLUE;       // frame color of end node

	private static String startNode = ""; // name of the node where the path starts
	private static String endNode = "";   // name of the destination node
	
    private Node node;         // reference to node object being drawn
    private ScaledPoint point; // reference to scaled point in node
    
    /**
     * Initializes DrawableNode object.
     *
     * @param node Node object to be drawn.
     */
    public DrawableNode(Node node)
    {
    	this.node = node;
        this.point = node.getScaledPoint();
    }

    /**
     * Draws the node.
     *
     * @param g Initialized Graphics object used to draw.
     */
    @Override
    public void draw(Graphics g)
    {
        String dist;        // distance being drawn
        int drawRadius;     // radius of the node being drawn
        int thickness;      // thickness of the frame around the node
        Color frameColor;   // color of the frame around the node
        Color nodeColor;    // color of the node

        drawRadius = isMouseOver() ? RADIUS_BIG : RADIUS;
        
        if(Drawable.path.indexOf(node.getName()) != -1) // choose color of the node depending on
        	                                             // the state of the node
        {
        	nodeColor = NODE_PATH_COLOR;
        }
        else if(node.getValue() != Integer.MAX_VALUE)
    	{
        	nodeColor = NODE_VISITED_COLOR;
    	}        
    	else
    	{
    		nodeColor = NODE_UNVISITED_COLOR;
    	}
        
        // draw node circle
        g.setColor(nodeColor);
        g.fillOval(point.getX()-drawRadius, point.getY()-drawRadius,
                2*drawRadius, 2*drawRadius);        
        
        if(node.getName().equals(startNode)) // choose color and thickness for frame depending on
        	                                 // if the node is starting or ending node on the path
        {
        	thickness = FRAME_THICK;
        	frameColor = NODE_START_COLOR;
        }
        else if(node.getName().equals(endNode))
        {
        	thickness = FRAME_THICK;
        	frameColor = NODE_END_COLOR;
        }
        else
        {
        	thickness = FRAME_THIN;
        	frameColor = Color.BLACK;
        }
        
        // draw frame around the node
        g.setColor(frameColor);
        ((Graphics2D)g).setStroke(new BasicStroke(thickness));
        g.drawOval(point.getX()-drawRadius, point.getY()-drawRadius,
                2*drawRadius, 2*drawRadius);
        
        // draw node name
        g.setColor(Color.BLACK);
        g.drawString(node.getName(), point.getX()-5, point.getY()+5);
        
        // draw back for node distance
        g.setColor(Color.WHITE);
        g.fillRect(point.getX() + DIST_BOX_OFFSET_X, point.getY() + DIST_BOX_OFFSET_Y, 
        		DIST_BOX_WIDTH, DIST_BOX_HEIGTH);
        
        // draw node distance
        g.setColor(Color.BLACK);
        dist = "" + (node.getValue() == Integer.MAX_VALUE ? "\u221e" : node.getValue());
        g.drawString(dist, point.getX() + DIST_BOX_OFFSET_X, point.getY());

    }
    
    /**
     * Returns distance in pixels of this node from coordinates x,y.
     *
     * @param x x coordinate on the frame in pixels.
     * @param y y coordinate on the frame in pixels.
     * @return distance in pixels.
     */
    private int getDistance(int x, int y)
    {
    	// standard 2D distance algorithm
        return (int)(Math.sqrt((point.getX()-x)*(point.getX()-x) + 
                (point.getY()-y)*(point.getY()-y)));
    }
    
    /**
     * Checks whether mouse is hovering over this node.
     * @return result of the check.
     */
    public boolean isMouseOver()
    {
    	// standatd collision between point and circle algorithm in 2D
        return getDistance(DrawManager.getMouse().x, DrawManager.getMouse().y) < RADIUS;
    }
    
    /**
     * Sets the frame position of the ScaledPoint associated with this node.
     *
     * @param x x coordinate in frame in pixels.
     * @param y y coordinate in frame in pixels.
     */
    public void setPosition(int x, int y)
    {
        point.setWinXY(x, y);
    }
    
    /**
     * Sets DrawableNode.startNode.
     * @param start new value of DrawableNode.startNode.
     */
    public static void setStart(String start)
    {
    	startNode = start;
    }
    
    /**
     * Sets DrawableNode.endNode.
     * @param end new value of DrawableNode.endNode.
     */
    public static void setEnd(String end)
    {
    	endNode = end;
    }
}
