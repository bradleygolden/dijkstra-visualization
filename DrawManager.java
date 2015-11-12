import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * Manages drawable objects, and mouse events responsible<p>
 * for dragging nodes and clicking edge buttons.
 * @date 11/11/2015
 * @project Data structure visualization - Dijkstra's Algorithm
 * @author Maciej Szpakowski
 */
public class DrawManager extends JPanel implements MouseMotionListener, MouseListener
{	
	private static Point mouse = new Point();	
	
	private Font defFont;             // default font for drawing strings
    private DrawableNode draggedNode; // point that is being dragged	
	private Drawable[] drawables;     // array of drawable objects
	public static boolean showMap;
	
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
        defFont = new Font("SansSerif", Font.BOLD, 15);
        showMap = false;

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
    	
    	if(TopPanel.getGraph() == null) // don't draw anything if there is nothing to draw
    	{
    		return;
    	}
    	
    	ScaledPoint.updateWindow(getHeight(), getWidth());
    	Drawable.setPath(TopPanel.getGraph().getPath());
    	initDrawables(TopPanel.getGraph());
    	
    	if(DrawManager.showMap) // draw legend if applicable
    	{
    		drawMap(g);
    	}
    	
    	g.setFont(defFont);
        
        for(Drawable d : drawables) // draw all drawables
        {
            d.draw(g);
        }
    }
    
    /**
     * Draws the legend (meaning of the visuals).
     * @param g Graphics object used to draw it.
     */
    private void drawMap(Graphics g)
    {
    	Point point;             // position of currently drawn component
    	GradientPaint gradient;  // gradient for button
    	
    	point = new Point(30,30);
    	
    	// header
    	g.drawString("L E G E N D", 10, 10);    	
    	g.drawString("All nodes can be dragged with mouse.", 10, 25);
    	
    	// nodes
    	point.y += 30;
    	drawGenericNode(g, DrawableNode.NODE_UNVISITED_COLOR, point, "\u221e", 1, Color.BLACK);
    	g.drawString("Unvisited node", point.x+70, point.y);
    	
    	point.y += 50;
    	drawGenericNode(g, DrawableNode.NODE_VISITED_COLOR, point, "10", 1, Color.BLACK);
    	g.drawString("Visited node", point.x+70, point.y);
    	
    	point.y += 50;
    	drawGenericNode(g, DrawableNode.NODE_PATH_COLOR, point, "5", 1, Color.BLACK);
    	g.drawString("Path node", point.x+70, point.y);
    	
    	point.y += 50;
    	drawGenericNode(g, DrawableNode.NODE_UNVISITED_COLOR, point, "5", 3, 
    			DrawableNode.NODE_START_COLOR);
    	g.drawString("Start node", point.x+70, point.y);
    	
    	point.y += 50;
    	drawGenericNode(g, DrawableNode.NODE_UNVISITED_COLOR, point, "5", 3, 
    			DrawableNode.NODE_END_COLOR);
    	g.drawString("Destination node", point.x+70, point.y);
    	
    	// edge buttons
    	point.y += 50;
		gradient = new GradientPaint(point.x, point.y-DrawableEdge.DEF_BUTTON_HEIGHT/2, 
				GButton.GRADIENT_START_COLOR, point.x, point.y + DrawableEdge.DEF_BUTTON_HEIGHT/2, 
				GButton.GRADIENT_END_COLOR);
		((Graphics2D)g).setPaint(gradient);
		g.fillRect(point.x-DrawableEdge.DEF_BUTTON_WIDTH/2, 
				point.y-DrawableEdge.DEF_BUTTON_HEIGHT/2, 
				DrawableEdge.DEF_BUTTON_WIDTH, DrawableEdge.DEF_BUTTON_HEIGHT);
		((Graphics2D)g).setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		g.drawRect(point.x-DrawableEdge.DEF_BUTTON_WIDTH/2, 
				point.y-DrawableEdge.DEF_BUTTON_HEIGHT/2, 
				DrawableEdge.DEF_BUTTON_WIDTH, DrawableEdge.DEF_BUTTON_HEIGHT);
		g.drawString("10", point.x , point.y );
		g.drawString("Edge weight (click to change)", point.x+70, point.y);
		
		point.y += 50;
		((Graphics2D)g).setColor(Color.WHITE);
		g.fillRect(point.x-DrawableEdge.DEF_BUTTON_WIDTH/2, 
				point.y-DrawableEdge.DEF_BUTTON_HEIGHT/2, 
				DrawableEdge.DEF_BUTTON_WIDTH, DrawableEdge.DEF_BUTTON_HEIGHT);
		((Graphics2D)g).setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		g.drawRect(point.x-DrawableEdge.DEF_BUTTON_WIDTH/2, 
				point.y-DrawableEdge.DEF_BUTTON_HEIGHT/2, 
				DrawableEdge.DEF_BUTTON_WIDTH, DrawableEdge.DEF_BUTTON_HEIGHT);
		g.drawString("10", point.x , point.y );
		g.drawString("Edge weight", point.x+70, point.y);
		
		// edges
		point.y += 50;
		g.setColor(DrawableEdge.PATH_COLOR);
        ((Graphics2D)g).setStroke(new BasicStroke(4));
        g.drawLine(point.x-10, point.y-10, point.x+20, point.y-10);
        g.setColor(Color.BLACK);
        g.drawString("Path edge", point.x+70, point.y);
        
        point.y += 50;
		g.setColor(DrawableEdge.NONPATH_COLOR);
        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.drawLine(point.x-10, point.y-10, point.x+20, point.y-10);
        g.setColor(Color.BLACK);
        g.drawString("Non-path edge", point.x+70, point.y);
    }
    
    /**
     * Draw a node.
     * @param g Graphics object use to draw.
     * @param color color of the node.
     * @param point where to draw it.
     * @param distance what's its distance.
     * @param thickness thickness of the frame > 0.
     * @param frameColor color of the frame.
     */
    private void drawGenericNode(Graphics g, Color color, Point point, String distance, 
    		int thickness, Color frameColor)
    {
    	// draw node circle
    	g.setColor(color);
        g.fillOval(point.x-DrawableNode.RADIUS, point.y-DrawableNode.RADIUS,
                2*DrawableNode.RADIUS, 2*DrawableNode.RADIUS);
        
        // draw circumference
        g.setColor(frameColor);        
        ((Graphics2D)g).setStroke(new BasicStroke(thickness));
        g.drawOval(point.x-DrawableNode.RADIUS, point.y-DrawableNode.RADIUS,  
                2*DrawableNode.RADIUS, 2*DrawableNode.RADIUS);
        
        // draw node name
        g.setColor(Color.BLACK);
        g.drawString("A", point.x, point.y); 
        
        // draw back for node distance
        g.setColor(Color.WHITE);
        g.fillRect(point.x + DrawableNode.DIST_BOX_OFFSET_X, 
        		point.y + DrawableNode.DIST_BOX_OFFSET_Y, 
        		DrawableNode.DIST_BOX_WIDTH, DrawableNode.DIST_BOX_HEIGTH);  
        
        // draw distance
        g.setColor(Color.BLACK);
        g.drawString(distance, point.x + DrawableNode.DIST_BOX_OFFSET_X, point.y);  
    }
    
    /**
     * Gets the current position of the mouse in the panel.
     * @return Point which contains current position of mouse in the panel.
     */
    public static Point getMouse()
    {
    	return mouse;
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
     * Handles mouse moved event. It repaints.
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
     * Handles mouse pressed event. Looks for a node to drag.
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
     * Handles mouse released event. Sets dragged node to null.
     *
     * @param e mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
    	draggedNode = null;
    }
    
    /**
     * Handles mouse clicked event. Clicks edge buttons.
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
     * It doesn't do anything. Exists only to satisty interface.
     *
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {        
    }

    /**
     * Handles mouse exited event. It sets dragged node to null.
     *
     * @param e mouse event
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
    	draggedNode = null;
    }
}
