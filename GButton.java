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
	private static Color color = Color.WHITE;
	
	public GButton(String text, int x, int y, int width, int height)
	{
		this.text = text;
		this.location = new Point(x,y);
		this.size = new Dimension(width, height);
	}
	
	boolean isMouseOver()
	{
		return mouseX > location.x && mouseX < location.x + size.width && 
				mouseY > location.y && mouseY < location.y + size.height;
	}

	@Override
    public void draw(Graphics g)
	{		
		location.x -= size.width/2;
		location.y -= size.height/2;
		
		
		
		//g.setColor(color);
		GradientPaint redtowhite = new GradientPaint(location.x,location.y,
				color.WHITE,location.x, location.y+size.height,color.GRAY);
		((Graphics2D) g).setPaint(redtowhite);
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
