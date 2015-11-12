import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JOptionPane;

/**
 * Wraps logic for drawing the graph's edge on screen.
 * @date 11/11/2015
 * @project Data structure visualization - Dijkstra's Algorithm
 * @author Maciej Szpakowski
 */
public class DrawableEdge extends Drawable
{
    public static final int DEF_BUTTON_WIDTH = 40;        // default button's width
    public static final int DEF_BUTTON_HEIGHT = 22;       // default button's height
    public static final int PATH_THICKNESS = 4;           // path edge's thickness
    public static final int NONPATH_THICKNESS = 2;        // non-path edge's thickness
    public static final Color PATH_COLOR = Color.MAGENTA; // edge's color if it's part of the path
    public static final Color NONPATH_COLOR = Color.GRAY; // default color of the edge
    public static final int MIN_WEIGHT = 0;               // the least edge's weight
    public static final int MAX_WEIGHT = 9999;            // the greatest edge's weight
    
    private static boolean buttonsEnabled = true; // flag that allows change edges weights
    
    private Edge edge;      // edge object to be drawn
    private GButton button; // button that displays edge's weight and allows user
                            // to change it
    
    /**
     * Initializes DrawableEdge.
     *
     * @param edge Edge object represented by this DrawableEdge.
     */
    public DrawableEdge(Edge edge)
    {
        this.edge = edge;
        button = new GButton(edge.getVal() + "", DEF_BUTTON_WIDTH, DEF_BUTTON_HEIGHT);
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
        Point transform;    // vector that offsets the button to the middle
        
        start = edge.getStart().getScaledPoint();
        end = edge.getEnd().getScaledPoint();

        if(isPath()) // decide the color based on whether it's part of the path or not
        {
            g.setColor(PATH_COLOR);
            ((Graphics2D)g).setStroke(new BasicStroke(PATH_THICKNESS));
        }
        else
        {
            g.setColor(NONPATH_COLOR);
            ((Graphics2D)g).setStroke(new BasicStroke(NONPATH_THICKNESS));
        }
        
        // draw the edge itself        
        g.drawLine(start.getX(),start.getY(), end.getX(),end.getY());
        
        // these lines compute the vector that offsets
        // the button so it's in between two nodes that edge connects
        // equation: r = v1 + (v2 - v1)/2
        transform = new Point();
        transform.x = end.getX() + (start.getX() - end.getX())/2;
        transform.y = end.getY() + (start.getY() - end.getY())/2;
        
        // move the button and draw it
        button.setLocation(transform.x, transform.y);
        button.draw(g);
    }
    
    /**
     * Checks if the edge belongs to the current path.
     * @return result of the check.
     */
    private boolean isPath()
    {
        char startName;       // name of the one node that is connected to the edge
        char endName;         // name of the other node that is connected to the edge
        String curPathEdge;   // string used to iterate over current path
        
        startName = edge.getStart().getName().charAt(0);
        endName = edge.getEnd().getName().charAt(0);        
        
        for(int i=0;i<path.length();i+=2) // iterate over current path
        {
            curPathEdge = path.substring(i, i+2); // current edge is a string of two chars
                                                  // which are the names of the nodes
                        
            if(curPathEdge.indexOf(startName) != -1 && 
               curPathEdge.indexOf(endName) != -1)     // the edge belong to path if 
                                                       // names of the nodes it connects
                                                       // are found in curPathEdge
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Tries to click the button of the edge. When button is clicked,
     * user can input a new value for the edge.
     * @return Returns whether the button was actually clicked.
     * It was if the mouse was over it.
     */
    public boolean clickButton()
    {
        String input;  // string returned by input dialog
        int newWeight; // weight received from user
                
        if(buttonsEnabled && button.isMouseOver()) // button can be clicked only it cursor is over it
                                                   // and flag is enabled
        {
            input = JOptionPane.showInputDialog(
            		String.format("New weight [%d ~ %d]", MIN_WEIGHT,MAX_WEIGHT));
            
            try
            {
                newWeight = new Integer(input);
            }
            catch(NumberFormatException e)
            {
                return true;
            }
            
            if(newWeight >= MIN_WEIGHT && newWeight <= MAX_WEIGHT) // allow only certain range
            {
                edge.setVal(newWeight);
                button.setText(input);
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Sets buttonsEnabled flag, which enables or disables edge buttons.
     * @param enabled new value of buttonsEnabled.
     */
    public static void enableButtons(boolean enabled)
    {
        buttonsEnabled = enabled;
    }
    
    /**
     * Returns whether edge buttons are enabled or not.
     * @return buttonsEnabled.
     */
    public static boolean getEnabledFlag()
    {
        return buttonsEnabled;
    }
}
