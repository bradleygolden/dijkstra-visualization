import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class DrawManager
{
	private Image backBuffer;         // back buffer to draw graph
    private DrawableNode draggedNode; // point that is being dragged
	
	private Drawable[] drawables;     // array of drawable objects
	
	public DrawManager()
	{
		// initialize variables
        backBuffer = new BufferedImage(3000, 2000, BufferedImage.TYPE_INT_RGB);
        draggedNode = null;
	}
	
	/**
     * Initializes all drawables based on the current graph.
     *
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
        
        if(backBuffer != null) // present back buffer to front buffer
        {
            frameGraphics.drawImage(backBuffer, 0, 0, null);
        }
        
        return g;
    }
    
    public void nullDraggedNode()
    {
    	draggedNode = null;
    }
    
    public void dragNode()
    {    	
    	if(draggedNode != null) // drag node
        {
            draggedNode.setPosition(AFrame.getMouse().x,AFrame.getMouse().y);            
        }
    }
    
    public void mousePressed()
    {
    	for(Drawable d : drawables) // find first node that collides with mouse
        {
            if(d instanceof DrawableNode && ((DrawableNode) d).isMouseOver())
            {
                draggedNode = (DrawableNode)d;
                break;
            }               
        }
    }
    
    public void mouseClicked()
    {        
        for(Drawable d : drawables) // find first gbutton that collides with mouse
        {
            if(d instanceof DrawableEdge && ((DrawableEdge) d).clickButton())
            {            	
                break;
            }               
        }
    }
}
