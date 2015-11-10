import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class GButton extends Drawable
{
	private Point location;
	private String text;
	private Dimension size;
	private GradientPaint gradient;
	
	public GButton(String text, int x, int y, int width, int height)
	{
		this.text = text;
		this.location = new Point(x,y);
		this.size = new Dimension(width, height);
	}
	
	boolean isMouseOver()
	{
		Point mouse;
		
		mouse = AFrame.getMouse();
		
		return mouse.x > location.x && mouse.x < location.x + size.width && 
				mouse.y > location.y && mouse.y < location.y + size.height;
	}

	@Override
    public void draw(Graphics g)
	{		
		location.x -= size.width/2;
		location.y -= size.height/2;

		gradient = new GradientPaint(location.x,location.y,
				Color.WHITE,location.x, location.y+size.height,Color.GRAY);
		((Graphics2D) g).setPaint(gradient);
		g.fillRect(location.x, location.y, size.width, size.height);
		g.setColor(Color.BLACK);
		((Graphics2D)g).setStroke(new BasicStroke(1));
		g.drawRect(location.x, location.y, size.width, size.height);
		g.drawString(text, location.x + 10, location.y + 15);
    }
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void setLocation(int x, int y)
	{
		location.x = x;
		location.y = y;
	}
}
