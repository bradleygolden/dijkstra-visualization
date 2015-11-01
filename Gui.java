import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class AFrame extends JFrame implements MouseListener
{
	private ScaledPoint draggedPt; // point that is being dragged
    private String mouseState;     // state of paint
    private int radius;            // node radius
	
	Graph graph;
	
    public AFrame()
    {
        super( "Dijkstra's Algorithm" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 800 , 600 );
        setVisible( true );
        addMouseListener(this);
        
        mouseState = "up";
        radius = 15;
        draggedPt = null;
        
        graph = Graph.testGraph();
    }
    
    @Override
    public void paint(Graphics g)
    {
    	ScaledPoint.updateWindow(getHeight(), getWidth());
    	
    	if(draggedPt != null) // drag point if there is one to drag
        {
            dragPoint();
        }
    	
    	super.paint(g);
    	for(Node n : graph.nodes)
    		g.drawOval(n.getScaledPoint().getX()-radius, n.getScaledPoint().getY()-radius, 2*radius, 2*radius);
    	for(Edge e : graph.getEdges())
    		g.drawLine(e.getStart().getScaledPoint().getX(),e.getStart().getScaledPoint().getY(),
    				e.getEnd().getScaledPoint().getX(),e.getEnd().getScaledPoint().getY());
    }
    
    void dragPoint()
    {
        if(mouseState.equals("up"))
        {
            draggedPt = null;
            return;
        }
        
        draggedPt.setX((double)getMousePosition().x/getWidth());
        draggedPt.setY((double)getMousePosition().y/getHeight());
        repaint();
    }
    
    int getDistanceFromMouse(ScaledPoint p, int mouseX, int mouseY)
    {        
        return (int)(Math.sqrt((p.getX()-mouseX)*(p.getX()-mouseX) + (p.getY()-mouseY)*(p.getY()-mouseY)));
    }
    
    public void mousePressed(MouseEvent e)
    {
        mouseState = "down";
        
        // find first point that collides with mouse
        for(Node n : graph.getNodes())
        {
            if(getDistanceFromMouse(n.getScaledPoint(), e.getX(), e.getY()) < radius)
            {
                draggedPt = n.getScaledPoint();
                dragPoint();
                break;
            }
        }
    }

    public void mouseReleased(MouseEvent e)
    {
        mouseState = "up";
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
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
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
