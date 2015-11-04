import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class AFrame extends JFrame implements MouseListener, ActionListener, ItemListener
{
    public static final int TIMER_CYCLE = 25; // period between redrawTimer triggers in ms
    private Image backBuffer;      // back buffer to draw graph
    private Timer redrawTimer;     // timer that will repeatedly redraw the context if necessary
	private ScaledPoint draggedPt; // point that is being dragged
	private int nodeRadius;        // radius of the circles drawn as nodes
	private JButton[] edgeButtons; // buttons for edges	

	Graph graph;
    TopPanel top;
	
    public AFrame()
    {
        super( "Dijkstra's Algorithm" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 800 , 600 );
        setVisible( true );
        addMouseListener(this);
        redrawTimer = new Timer(TIMER_CYCLE, null);
        redrawTimer.addActionListener(this);
        redrawTimer.start();
        backBuffer = new BufferedImage(3000, 2000, BufferedImage.TYPE_INT_RGB);
        draggedPt = null;
        nodeRadius = 20;
        
        graph = Graph.graph1();

        top = new TopPanel();
        top.graphs.addItemListener(this);
        top.start.addActionListener(this);
        top.prev.addActionListener(this);
        top.next.addActionListener(this);
        initEdgeButtons();
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
    	Graphics frameGraphics; // main graphics object
    	
    	frameGraphics = g;    	
        ScaledPoint.updateWindow(getHeight(), getWidth());
        
        if(backBuffer != null) // handle weird java weird java
        	g = backBuffer.getGraphics();
        
        drawGraph(g);
        
        if(backBuffer != null)
        	frameGraphics.drawImage(backBuffer, 0, 0, null); // present back buffer to front buffer
        
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
                redrawTimer.start();
                dragPoint();
                break;
            }
        }
    }

    public void mouseReleased(MouseEvent e)
    {
    	draggedPt = null;
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
        draggedPt = null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == redrawTimer)
        {
        	repaint();
            if(draggedPt != null) // drag point if there is one to drag
            {
                dragPoint();                
            }
        }
        else if (e.getSource() == top.start)
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
                graph = Graph.graph1();
            else if (top.graphs.getSelectedIndex() == 1)
                graph = Graph.graph2();
            else if (top.graphs.getSelectedIndex() == 2)
                graph = Graph.graph3();

            top.start.setText("Start");
            repaint();
        }
    }
    
    public void initEdgeButtons()
    {
        edgeButtons = new JButton[graph.getEdges().length];
    	
    	for(int i=0;i<edgeButtons.length;i++)
    	{    		
    		edgeButtons[i] = new JButton(Integer.toString(graph.getEdges()[i].getVal()));
    		edgeButtons[i].addActionListener(this);
    		edgeButtons[i].setSize(60,30);
    		add(edgeButtons[i]);
    	}
    }
    
    public void drawGraph(Graphics g)
    {
    	Color originalColor;// color carried by g
    	ScaledPoint start;  // starting point of an edge
    	ScaledPoint end;    // ending point of an edge
    	ScaledPoint nodePt; // scaled point of a node
    	Color fontColor;    // color of the font
    	int index;          // variable used for indexing
    	Point difVec;       // difference vector between start and end
    	
    	if(graph == null)
    		return;
    	
    	originalColor = g.getColor();
    	nodeRadius = 20;
    	fontColor = Color.BLACK;
    	index = 0;
    	
    	for(Edge e : graph.getEdges())
        {
    		start = e.getStart().getScaledPoint();
    		end = e.getEnd().getScaledPoint();
    		
    		difVec = new Point(start.getX() - end.getX(),start.getY() - end.getY());
        	difVec.x = -difVec.x/2;
        	difVec.y = -difVec.y/2;
        	if(edgeButtons != null)
        	{
        		edgeButtons[index].setLocation(start.getX()+difVec.x, start.getY()+difVec.y);
        	}
    		
        	g.setColor(e.getColor());
        	g.drawLine(start.getX(),start.getY(), end.getX(),end.getY());
        	
        	
        	index++;
        }
    	
    	for(Node n : graph.nodes)
    	{
    		nodePt = n.getScaledPoint();
    		
    		g.setColor(n.getColor());
            g.fillOval(nodePt.getX()-nodeRadius, nodePt.getY()-nodeRadius, 
            		2*nodeRadius, 2*nodeRadius);
            
            g.setColor(fontColor);
    		g.drawString(Integer.toString(n.getValue()), nodePt.getX(), nodePt.getY());
    	}       
        
        g.setColor(originalColor);
    }
}

public class Gui
{
    public static void main(String[] args)
    {
        AFrame frame = new AFrame();

        frame.getContentPane().add(frame.top, BorderLayout.NORTH);
        frame.getContentPane().add(new JPanel(), BorderLayout.CENTER);
    }
}
