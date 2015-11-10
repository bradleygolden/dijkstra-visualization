import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JOptionPane;

public class DrawableEdge extends Drawable
{
    private Edge edge;     // edge object to be drawn 
    private int thickness; // thickness of the edge
    private GButton button;
    
    /**
     * initializes DrawableEdge
     *
     * @param edge Edge object to be drawn.
     */
    public DrawableEdge(Edge edge)
    {
        thickness = 3;
        this.edge = edge;
        button = new GButton(Integer.toString(edge.getVal()), 0, 0, 40, 22);
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
        Point transform;
        
        start = edge.getStart().getScaledPoint();
        end = edge.getEnd().getScaledPoint();
        
        ((Graphics2D)g).setStroke(new BasicStroke(thickness));
        g.setColor(edge.getColor());
        g.drawLine(start.getX(),start.getY(), end.getX(),end.getY());
        
        transform = new Point();
        transform.x = start.getX() - end.getX();
        transform.y = start.getY() - end.getY();        
        
        button.setLocation(end.getX() + transform.x / 2, end.getY() + transform.y / 2);
        button.draw(g);
    }
    
    public boolean clickButton()
    {
    	String input;
    	int newWeight;
    	
    	if(button.isMouseOver())
    	{
    		input = JOptionPane.showInputDialog("New weight");
    		try
    		{
    			newWeight = new Integer(input);
    		}
    		catch(NumberFormatException e)
    		{
    			return true;
    		}
    		if(newWeight > 0 && newWeight < 10000)
    		{
    			edge.setVal(newWeight);
    			button.setText(input);
    		}
    		return true;
    	}
    	return false;
    }
}
