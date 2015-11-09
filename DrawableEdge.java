import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawableEdge extends Drawable
{
    private Edge edge;     // edge object to be drawn 
    private int thickness; // thickness of the edge
    
    /**
     * initializes DrawableEdge
     *
     * @param edge Edge object to be drawn.
     */
    public DrawableEdge(Edge edge)
    {
        thickness = 3;
        this.edge = edge;
    }
    
    /**
     * Draws the edge.
     *
     * @param g Initialized Graphics object used to draw.
     */
    @Override
    public void draw(Graphics g)
    {
        ScaledPoint start;  // starting point of an edge
        ScaledPoint end;    // ending point of an edge
        
        start = edge.getStart().getScaledPoint();
        end = edge.getEnd().getScaledPoint();
        
        ((Graphics2D)g).setStroke(new BasicStroke(thickness));
        g.setColor(edge.getColor());
        g.drawLine(start.getX(),start.getY(), end.getX(),end.getY());
    }
}
