package src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawableEdge extends Drawable
{
	private Edge edge;
	private int thickness;
	
	public DrawableEdge(Edge edge)
	{
		thickness = 3;
		this.edge = edge;
	}
	
	@Override
	public void draw(Graphics g)
	{
    	ScaledPoint start;  // starting point of an edge
    	ScaledPoint end;    // ending point of an edge
    	
		start = edge.getStart().getScaledPoint();
		end = edge.getEnd().getScaledPoint();
		
		((Graphics2D)g).setStroke(new BasicStroke(thickness));
    	g.setColor(edge.getColor());
    	g.drawLine(start.getX(),start.getY(), end.getX(),end.getY());
	}
}
