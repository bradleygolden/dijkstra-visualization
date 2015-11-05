package src;

import java.awt.Color;
import java.awt.Graphics;

public class DrawableNode extends Drawable
{
	private int radius;
	private Node node;

	public DrawableNode(Node node)
	{
		radius = 20;
		this.node = node;
	}

	@Override
	public void draw(Graphics g)
	{
    	ScaledPoint nodePt; // scaled point of a node
    	String dist;        // distance being drawn
    	
    	nodePt = node.getScaledPoint();
		
		g.setColor(node.getColor());
        g.fillOval(nodePt.getX()-radius, nodePt.getY()-radius,      // draw node circle
        		2*radius, 2*radius);
        
        g.setColor(Color.BLACK);
		g.drawString(node.getName(), nodePt.getX(), nodePt.getY()); // draw node name
		g.setColor(Color.WHITE);
		g.fillRect(nodePt.getX() + 20, nodePt.getY() - 15, 80, 20); // draw back for node distance
		g.setColor(Color.BLACK);
		dist = "Distance:" + (node.getValue() == Integer.MAX_VALUE ? "\u221e" : node.getValue());
		g.drawString(dist, nodePt.getX() + 20, nodePt.getY());      // draw distance
	}
}
