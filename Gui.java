import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class AFrame extends JFrame implements MouseListener, ActionListener, ItemListener, MouseMotionListener, ComponentListener
{
	private static Point mouse = new Point();
    private Graph graph;              // graph object
    private TopPanel top;             // top panel
    private DrawManager drawManager;
    
    /**
     * Initializes the main frame.
     *
     */
    public AFrame()
    {
        super( "Dijkstra's Algorithm" );
        
        // initialize frame
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 800 , 600 );
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);         
        
        drawManager = new DrawManager();
        graph = Graph.graph1();
        drawManager.initDrawables(graph);
        
        // initialize top panel
        top = new TopPanel();
        top.graphs.addItemListener (this);
        top.start.addActionListener(this);
        top.prev.addActionListener (this);
        top.next.addActionListener (this);
        
        add(top, BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.CENTER);
    }    

    /**
     * Main paint method.
     *
     */
    @Override
    public void paint(Graphics g)
    {    	
    	ScaledPoint.updateWindow(getHeight() - 0, getWidth());
    	
    	g = drawManager.drawAll(g);
    	
        super.paint(g);
        
    }
    
    public static Point getMouse()
    {
    	return mouse;
    }
    
    /**
     * Handles mouse pressed event.
     *
     * @param e mouse event
     */
    public void mousePressed(MouseEvent e)
    {               
        drawManager.mousePressed();
    }

    /**
     * Handles mouse released event.
     *
     * @param e mouse event
     */
    public void mouseReleased(MouseEvent e)
    {
        drawManager.nullDraggedNode();
    }
    
    /**
     * Handles mouse clicked event.
     *
     * @param e mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
    	drawManager.mouseClicked();
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
        drawManager.nullDraggedNode();
    }
    
    /**
     * Handles action listener event.
     *
     * @param e action event
     */
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

    /**
     * Handles graph combo box.
     *
     * @param e item event
     */
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
            drawManager.initDrawables(graph);
            repaint();
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
    	mouse.x = e.getX();
    	mouse.y = e.getY();
        drawManager.dragNode();
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
    	mouse.x = e.getX();
    	mouse.y = e.getY();
    }

	@Override
	public void componentHidden(ComponentEvent arg0)
	{		
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{		
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
		repaint();
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
		repaint();		
	}
}

public class Gui
{
    /**
     * Application's entry point.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args)
    {        
        AFrame frame = new AFrame();
        frame.setVisible(true);
    }
}
