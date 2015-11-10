import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class DrawManager
{
	public static final int BACKBUFFER_HEIGHT = 2000; // height of backbuffer
	public static final int BACKBUFFER_WIDTH = 3000;  // width of backbuffer

	private Image backBuffer;         // back buffer to draw graph
    private DrawableNode draggedNode; // point that is being dragged	
	private Drawable[] drawables;     // array of drawable objects
	
	/**
     * Default constructor initializes DrawManager object.
     *
     */
	public DrawManager()
	{
        backBuffer = new BufferedImage(BACKBUFFER_WIDTH, BACKBUFFER_HEIGHT, 
        		                       BufferedImage.TYPE_INT_RGB);
        draggedNode = null;
        drawables = null;
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
     * Draws the graph to the screen.
     * @param g graphics object which will drawn the graph.
     * @return returns back buffer context used to finish drawing by the frame.
     */
    public Graphics drawAll(Graphics g)
    {
    	Graphics frameGraphics; // main graphics object

        frameGraphics = g;

    	if(backBuffer != null) // start drawing to backbuffer
        {
            g = backBuffer.getGraphics();
        }
        
        for(Drawable d : drawables) // draw all drawables
        {
            d.draw(g);
        }
        
        if(backBuffer != null) // present back buffer to front buffer (screen)
        {
            frameGraphics.drawImage(backBuffer, 0, 0, null);
        }
        
        return g;
    }
    
    /**
     * Sets this.draggedNode to null so it is no longer being dragged.
     */
    public void nullDraggedNode()
    {
    	draggedNode = null;
    }
    
    /**
     * Moves currently dragged node to where mouse is.
     */
    public void dragNode()
    {    	
    	if(draggedNode != null) // make sure it's not null
        {
            draggedNode.setPosition(AFrame.getMouse().x,AFrame.getMouse().y);            
        }
    }
    
    /**
     * Handles mouse pressed event.
     * Currently this event causes draw manager to look for
     * node that is under the cursor and marks is as draggedNode.
     */
    public void mousePressed()
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
}
