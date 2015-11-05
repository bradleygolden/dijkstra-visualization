package src;

import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class AFrame extends JFrame implements MouseListener, ActionListener, ItemListener, MouseMotionListener
{
    private Image backBuffer;      // back buffer to draw graph
	private ScaledPoint draggedPt; // point that is being dragged
	private int nodeRadius;        // radius of the circles drawn as nodes
	private Graph graph;
    private TopPanel top;
    private Drawable[] drawables;
	
    public AFrame()
    {
        super( "Dijkstra's Algorithm" );
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 800 , 600 );
        setVisible( true );
        addMouseListener(this);
        addMouseMotionListener(this);
        
        backBuffer = new BufferedImage(3000, 2000, BufferedImage.TYPE_INT_RGB);
        draggedPt = null;
        nodeRadius = 20;        
        graph = Graph.graph1();
        initDrawables();

        top = new TopPanel();
        top.graphs.addItemListener (this);
        top.start.addActionListener(this);
        top.prev.addActionListener (this);
        top.next.addActionListener (this);
        
        add(top, BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.CENTER);
    }
    
    public void initDrawables()
    {
    	int index;
    	
    	index = 0;
    	drawables = new Drawable[graph.getNodes().length + graph.getEdges().length];    	
    	
    	for(Edge e : graph.getEdges())
    		drawables[index++] = new DrawableEdge(e);
    	
    	for(Node n : graph.getNodes())
    		drawables[index++] = new DrawableNode(n);
    }

    @Override
    public void paint(Graphics g)
    {    	
    	Graphics frameGraphics; // main graphics object
    	
    	frameGraphics = g;
        ScaledPoint.updateWindow(getHeight(), getWidth());
        
        if(backBuffer != null) // start drawing to backbuffer
        	g = backBuffer.getGraphics();
        
        for(Drawable d : drawables)
        	d.draw(g);
        
        if(backBuffer != null) // present back buffer to front buffer
        	frameGraphics.drawImage(backBuffer, 0, 0, null);
        
        super.paint(g);
    }
    
    void dragPoint()
    {        
        draggedPt.setX((double)getMousePosition().x/getWidth());
        draggedPt.setY((double)getMousePosition().y/getHeight());
    }
    
    int getDistanceFromMouse(ScaledPoint p, int mouseX, int mouseY)
    {        
        return (int)(Math.sqrt((p.getX()-mouseX)*(p.getX()-mouseX) + 
        		(p.getY()-mouseY)*(p.getY()-mouseY)));
    }
    
    public void mousePressed(MouseEvent e)
    {
        // find first point that collides with mouse
        for(Node n : graph.getNodes())
        {
            if(getDistanceFromMouse(n.getScaledPoint(), e.getX(), e.getY()) < nodeRadius)
            {
                draggedPt = n.getScaledPoint();                
                break;
            }
        }
    }

    public void mouseReleased(MouseEvent e)
    {
    	draggedPt = null;
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {        
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {        
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
        draggedPt = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == top.start)
        {
            if (top.start.getText() == "Start")
            {
                top.start.setText("Stop");
                top.graphs.setEnabled(false);
            }
            else if (top.start.getText() == "Stop")
            {
                top.start.setText("Start");
                top.graphs.setEnabled(true);
            }
        }
        else if (e.getSource() == top.prev)
        {
            // Boilerplate
        }
        else if (e.getSource() == top.next)
        {
            // Boilerplate
        }
    }

    public void itemStateChanged(ItemEvent e) 
    {
        if (e.getSource() == top.graphs) 
        {
            if (top.graphs.getSelectedIndex() == 0)
            {
                graph = Graph.graph1();
            }
            else if (top.graphs.getSelectedIndex() == 1)
            {
                graph = Graph.graph2();
            }
            else if (top.graphs.getSelectedIndex() == 2)
            {
                graph = Graph.graph3();                
            }

            top.start.setText("Start");
            initDrawables();
            repaint();
        }
    }

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(draggedPt != null)
		{
			dragPoint();
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}
}

public class Gui
{
    public static void main(String[] args)
    {
        AFrame frame = new AFrame();        
    }
}
