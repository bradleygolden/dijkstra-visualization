import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class AFrame extends JFrame implements MouseListener, ActionListener
{
    private Image backBuffer;
    public static final int TIMER_CYCLE = 25; // period between redrawTimer triggers in ms
    private Timer redrawTimer;     // timer that will repeatedly redraw the context if necessary
	private ScaledPoint draggedPt; // point that is being dragged
    private int radius;            // node radius
	
	Graph graph;
	
    public AFrame()
    {
        super( "Dijkstra's Algorithm" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 800 , 600 );
        setVisible( true );
        addMouseListener(this);
        redrawTimer = new Timer(TIMER_CYCLE,null);
        redrawTimer.addActionListener(this);
        backBuffer = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        
        radius = 15;
        draggedPt = null;
        
        graph = Graph.graph1();
    }
    
    @Override
    public void paint(Graphics g)
    {    	
        ScaledPoint.updateWindow(getHeight(), getWidth());

        //if(backBuffer.getHeight(null) != getHeight() || backBuffer.getWidth(null) != getWidth())
        //	backBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        
        Graphics frameg = g;
        if(backBuffer == null) // weird java
        	return;
        g = backBuffer.getGraphics();
        g.setColor(Color.BLACK);
        
        for(Node n : graph.nodes)
            g.drawOval(n.getScaledPoint().getX()-radius, n.getScaledPoint().getY()-radius, 2*radius, 2*radius);
        for(Edge e : graph.getEdges())
            g.drawLine(e.getStart().getScaledPoint().getX(),e.getStart().getScaledPoint().getY(),
                       e.getEnd().getScaledPoint().getX(),e.getEnd().getScaledPoint().getY());
        frameg.drawImage(backBuffer, 0, 0, null);
        super.paint(g);
    }
    
    void dragPoint()
    {        
        draggedPt.setX((double)getMousePosition().x/getWidth());
        draggedPt.setY((double)getMousePosition().y/getHeight());
    }
    
    int getDistanceFromMouse(ScaledPoint p, int mouseX, int mouseY)
    {        
        return (int)(Math.sqrt((p.getX()-mouseX)*(p.getX()-mouseX) + (p.getY()-mouseY)*(p.getY()-mouseY)));
    }
    
    public void mousePressed(MouseEvent e)
    {
        // find first point that collides with mouse
        for(Node n : graph.getNodes())
        {
            if(getDistanceFromMouse(n.getScaledPoint(), e.getX(), e.getY()) < radius)
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
            if(draggedPt != null) // drag point if there is one to drag
            {
                dragPoint();
                repaint();
            }
            else
            {
                redrawTimer.stop();
            }
        }
    }
}

public class Gui
{
    public static void main(String[] args)
    {
        AFrame frame = new AFrame();
        frame.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
        frame.getContentPane().add(new JPanel(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
