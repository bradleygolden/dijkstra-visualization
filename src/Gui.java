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
    private Image backBuffer;         // back buffer to draw graph
	private DrawableNode draggedNode; // point that is being dragged
	private Graph graph;              // graph object
    private TopPanel top;             // top panel
    private Drawable[] drawables;     // array of drawable objects
	
    public AFrame()
    {
        super( "Dijkstra's Algorithm" );
        
        // initialize frame
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 800 , 600 );
        addMouseListener(this);
        addMouseMotionListener(this);
        
        // initialize variables
        backBuffer = new BufferedImage(3000, 2000, BufferedImage.TYPE_INT_RGB);
        draggedNode = null;     
        graph = Graph.graph1();
        initDrawables();
        
        // initialize top panel
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
    	int index; // index of drawable object in drawable array
    	
    	index = 0;
    	drawables = new Drawable[graph.getNodes().length + graph.getEdges().length];    	
    	
    	for(Edge e : graph.getEdges()) // init all edges
    		drawables[index++] = new DrawableEdge(e);
    	
    	for(Node n : graph.getNodes()) // init all nodes
    		drawables[index++] = new DrawableNode(n);
    }

    @Override
    public void paint(Graphics g)
    {    	
    	Graphics frameGraphics; // main graphics object
    	
    	//super.paint(g);
    	frameGraphics = g;
        ScaledPoint.updateWindow(getHeight() - 0, getWidth());        
        
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
        
        super.paint(g);
    }
    
    public void mousePressed(MouseEvent e)
    {        
    	Drawable.setMouse(e.getX(), e.getY());
    	
    	for(Drawable d : drawables) // find first node that collides with mouse
        {
            if(d instanceof DrawableNode && ((DrawableNode) d).isMouseOver())
            {
            	draggedNode = (DrawableNode)d;
            	break;
            }            	
        }
    }

    public void mouseReleased(MouseEvent e)
    {
    	draggedNode = null;
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
        draggedNode = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == top.start)
        {
            if (top.start.getText() == "Start") // handle start button
            {
                top.start.setText("Stop");
                top.graphs.setEnabled(false);
            }
            else if (top.start.getText() == "Stop") // handle stop button
            {
                top.start.setText("Start");
                top.graphs.setEnabled(true);
            }
        }
        else if (e.getSource() == top.prev) // handle prev button
        {
            // Boilerplate
        }
        else if (e.getSource() == top.next) // handle next button
        {
            // Boilerplate
        }
    }

    public void itemStateChanged(ItemEvent e) 
    {
        if (e.getSource() == top.graphs) // handle graph combo box
        {
            if (top.graphs.getSelectedIndex() == 0) // graph1
            {
                graph = Graph.graph1();
            }
            else if (top.graphs.getSelectedIndex() == 1) // graph2
            {
                graph = Graph.graph2();
            }
            else if (top.graphs.getSelectedIndex() == 2) // graph3
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
		if(draggedNode != null) // drag node
		{
			draggedNode.setPosition(e.getX(),e.getY());
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
        frame.setVisible(true);
    }
}
