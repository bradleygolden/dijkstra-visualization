package src;

import java.awt.Color;
import java.awt.Graphics;

public class DrawableNode extends Drawable
{
	private int radius;
	private Node node;
	private ScaledPoint point;

	public DrawableNode(Node node)
	{
		this.radius = 20;
		this.node = node;
		this.point = node.getScaledPoint();
	}

	@Override
	public void draw(Graphics g)
	{
    	String dist;        // distance being drawn
		
		g.setColor(node.getColor());
        g.fillOval(point.getX()-radius, point.getY()-radius,      // draw node circle
        		2*radius, 2*radius);
        
        g.setColor(Color.BLACK);
		g.drawString(node.getName(), point.getX(), point.getY()); // draw node name
		g.setColor(Color.WHITE);
		g.fillRect(point.getX() + 20, point.getY() - 15, 80, 20); // draw back for node distance
		g.setColor(Color.BLACK);
		dist = "Distance:" + (node.getValue() == Integer.MAX_VALUE ? "\u221e" : node.getValue());
		g.drawString(dist, point.getX() + 20, point.getY());      // draw distance
	}
	
	private int getDistanceFromMouse(int mouseX, int mouseY)
    {		
        return (int)(Math.sqrt((point.getX()-mouseX)*(point.getX()-mouseX) + 
        		(point.getY()-mouseY)*(point.getY()-mouseY)));
    }
	
	public boolean isMouseOver()
	{
		return getDistanceFromMouse(mouseX, mouseY) < radius;
	}
	
	public void setPosition(int x, int y)
	{
		point.setWinXY(x, y);
	}
}
