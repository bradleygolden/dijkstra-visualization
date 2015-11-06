import java.awt.Color;
import java.awt.Graphics;

public class DrawableNode extends Drawable
{
    private int radius;
    private Node node;
    private ScaledPoint point;

    /**
     * Initializes DrawableNode object.
     *
     * @param node Node object to be drawn.
     */
    public DrawableNode(Node node)
    {
        this.radius = 20;
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
        
        g.setColor(node.getColor());
        g.fillOval(point.getX()-radius, point.getY()-radius,      // draw node circle
                2*radius, 2*radius);
        
        g.setColor(Color.BLACK);
        g.drawString(node.getName(), point.getX(), point.getY()); // draw node name
        
        g.setColor(Color.WHITE);
        g.fillRect(point.getX() + 20, point.getY() - 15, 80, 20); // draw back for node distance
        
        g.setColor(Color.BLACK);
        dist = "Distance:" + (node.getValue() == Integer.MAX_VALUE ? "\u221e" : node.getValue());
        g.drawString(dist, point.getX() + 20, point.getY());      // draw distance

    }
    
    /**
     * Returns distance in pixels of this node from coordinates x,y.
     *
     * @param x x coordinate on the frame in pixels.
     * @param y y coordinate on the frame in pixels.
     */
    private int getDistance(int x, int y)
    {       
        return (int)(Math.sqrt((point.getX()-x)*(point.getX()-x) + 
                (point.getY()-y)*(point.getY()-y)));
    }
    
    /**
     * Checks whether mouse is hovering over this node.
     *
     */
    public boolean isMouseOver()
    {
        return getDistance(mouseX, mouseY) < radius;
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
}
