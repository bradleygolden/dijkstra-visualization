import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawableNode extends Drawable
{
	public static final int DIST_BOX_OFFSET_X = 20;  // x pos. of the distance box relative to node
	public static final int DIST_BOX_OFFSET_Y = -15; // y pos. of the distance box relative to node
	public static final int DIST_BOX_HEIGTH = 20;    // height of the distance box
	public static final int DIST_BOX_WIDTH = 40;     // width of the distance box
	public static final int RADIUS = 20;             // default radius of the node
	public static final int RADIUS_BIG = 25;         // default radius when node is highlighted
	public static final Color NODE_UNVISITED_COLOR = new Color(200,200,200);
	public static final Color NODE_VISITED_COLOR = new Color(200,255,200);
	public static final Color NODE_PATH_COLOR = new Color(50,255,50);
	
	private static String startNode = "";
	private static String endNode = "";
	
    private Node node;
    private ScaledPoint point;
    
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
        int drawRadius;
        int thickness;
        Color frameColor;

        drawRadius = isMouseOver() ? RADIUS_BIG : RADIUS;
        
        g.setColor(getColor());
        g.fillOval(point.getX()-drawRadius, point.getY()-drawRadius,      // draw node circle
                2*drawRadius, 2*drawRadius);        
        
        if(node.getName().equals(startNode))
        {
        	thickness = 3;
        	frameColor = Color.GREEN;
        }
        else if(node.getName().equals(endNode))
        {
        	thickness = 3;
        	frameColor = Color.BLUE;
        }
        else
        {
        	thickness = 1;
        	frameColor = Color.BLACK;
        }
        
        g.setColor(frameColor);
        ((Graphics2D)g).setStroke(new BasicStroke(thickness));
        g.drawOval(point.getX()-drawRadius, point.getY()-drawRadius,      // draw circumference
                2*drawRadius, 2*drawRadius);
        
        g.setColor(Color.BLACK);
        g.drawString(node.getName(), point.getX(), point.getY()); // draw node name
        
        g.setColor(Color.WHITE);
        g.fillRect(point.getX() + DIST_BOX_OFFSET_X, point.getY() + DIST_BOX_OFFSET_Y, 
        		DIST_BOX_WIDTH, DIST_BOX_HEIGTH);             // draw back for node distance
        
        g.setColor(Color.BLACK);
        dist = "" + (node.getValue() == Integer.MAX_VALUE ? "\u221e" : node.getValue());
        g.drawString(dist, point.getX() + DIST_BOX_OFFSET_X, point.getY());      // draw distance

    }
    
    private Color getColor()
    {
    	if(node.getValue() == Integer.MAX_VALUE)
    	{
    		return NODE_UNVISITED_COLOR;
    	}
    	else
    	{
    		return NODE_VISITED_COLOR;
    	}
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
        return (int)(Math.sqrt((point.getX()-x)*(point.getX()-x) + 
                (point.getY()-y)*(point.getY()-y)));
    }
    
    /**
     * Checks whether mouse is hovering over this node.
     * @return result of the check.
     */
    public boolean isMouseOver()
    {
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
    
    public static void setStart(String start)
    {
    	startNode = start;
    }
    
    public static void setEnd(String end)
    {
    	endNode = end;
    }
}
