import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class DrawManager extends JPanel implements MouseMotionListener, MouseListener
{
	private static Point mouse = new Point();
    private DrawableNode draggedNode; // point that is being dragged	
	private Drawable[] drawables;     // array of drawable objects
	
	
	/**
     * Default constructor initializes DrawManager object.
     *
     */
	public DrawManager()
	{
        draggedNode = null;
        drawables = null;
        addMouseListener(this);
        addMouseMotionListener(this);

        setBackground(new Color(255,255,153));
	}
	
	/**
     * Initializes all drawables based on the graph argument.
     * 
     * @param graph currently chosen graph that will be drawn on the screen.
     */
    public void initDrawables(Graph graph)
    {
        int index; // index of drawable object in drawable array
        
        index = 0;
        drawables = new Drawable[graph.getNodes().length + graph.getEdges().length];
        
        for(Edge e : graph.getEdges()) // init all edges
            drawables[index++] = new DrawableEdge(e);
        
        for(Node n : graph.getNodes()) // init all nodes
            drawables[index++] = new DrawableNode(n);
    }
    
    /**
     * Draws the graph to the screen. This function is invoked by JAVA engine.
     */
    @Override
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);

    	//
    	// T H I S    I S    G A R B A G E
    	//
    	initDrawables(Gui.graph);
    	ScaledPoint.updateWindow(getHeight(), getWidth());
    	Drawable.setPath(Gui.graph.getPath());
        
        for(Drawable d : drawables) // draw all drawables
        {
            d.draw(g);
        }
    }
    
    public static Point getMouse()
    {
    	return mouse;
    }
    
    /**
     * Handles mouse clicks.
     * Currently it handles clicks on buttons that are on the edges.
     */
    public void mouseClicked()
    {        
        for(Drawable d : drawables) // find first GButton that collides with mouse
        {
            if(d instanceof DrawableEdge && ((DrawableEdge) d).clickButton()) // try to click button
            {            	
                break;
            }               
        }
    }
    
    /**
     * Handles mouse dragged event. It is used to drag nodes.
     *
     * @param e mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
    	DrawManager.mouse.x = e.getX();
    	DrawManager.mouse.y = e.getY();
    	if(draggedNode != null) // make sure it's not null
        {
            draggedNode.setPosition(e.getX(), e.getY());            
        }
    	repaint();
    }

    /**
     * Handles mouse moved event.
     *
     * @param e mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
    	DrawManager.mouse.x = e.getX();
    	DrawManager.mouse.y = e.getY();
    	repaint();
    }
    
    /**
     * Handles mouse pressed event.
     *
     * @param e mouse event
     */
    @Override
    public void mousePressed(MouseEvent e)
    {        
    	for(Drawable d : drawables) // find first node that collides with mouse
        {
            if(d instanceof DrawableNode &&       // this if statement detects draggedNode
               ((DrawableNode) d).isMouseOver())
            {
                draggedNode = (DrawableNode)d;
                break;
            }               
        }
    }

    /**
     * Handles mouse released event.
     *
     * @param e mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
    	draggedNode = null;
    }
    
    /**
     * Handles mouse clicked event.
     *
     * @param e mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {      
    	for(Drawable d : drawables) // find first GButton that collides with mouse
        {
            if(d instanceof DrawableEdge && ((DrawableEdge) d).clickButton()) // try to click button
            {            	
                break;
            }               
        }
    	repaint();
    }

    /**
     * Handles mouse entered event.
     *
     * @param e mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {        
    }

    /**
     * Handles mouse exited event.
     *
     * @param e mouse event
     */
    @Override
    public void mouseExited(MouseEvent arg0)
    {
    	draggedNode = null;
    }
}
