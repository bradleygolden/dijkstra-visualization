import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Our own graphics button because JButton won't cooperate with custom drawings.
 * @date 11/11/2015
 * @project Data structure visualization - Dijkstra's Algorithm
 * @author Maciej Szpakowski
 */
public class GButton extends Drawable
{
	public static final Color GRADIENT_START_COLOR = Color.WHITE; // color of the gradient top
	public static final Color GRADIENT_END_COLOR = Color.GRAY;    // color of the gradient bottom
	public static final int TEXT_OFFSET_X = 10;         // x offset of the button's text
	public static final int TEXT_OFFSET_Y = 15;         // y offset of the button's text
	public static final Color FONT_COLOR = Color.BLACK; // color of the font and frame
	public static final int HILIGHT_THICKNESS = 3;      // thickness of the button's frame when 
	                                                    // it's highlighted
	
	private Point location;          // location of the button
	private String text;             // text of the button
	private Dimension size;          // size of the button
	private GradientPaint gradient;  // gradient used to fill the button
	
	/**
	 * Initializes GButton.
	 * @param text text of the button
	 * @param width width of the button
	 * @param height height of the button
	 */
	public GButton(String text, int width, int height)
	{
		this.text = text;
		this.location = new Point();
		this.size = new Dimension(width, height);
	}
	
	/**
	 * Check if cursor is over the button.
	 * @return Result of the collision check.
	 */
	public boolean isMouseOver()
	{
		Point mouse;  // mouse coordinates
		
		mouse = DrawManager.getMouse();
		
		// this is a simple rectangle collision detection
		// check if coordinates of the cursor are within rectangle bound by the button
		return mouse.x > location.x && mouse.x < location.x + size.width && 
				mouse.y > location.y && mouse.y < location.y + size.height;
	}

	@Override
	/**
	 * Draws the button to graphics object g.
	 * @param g Graphics object used to draw the button.
	 */
    public void draw(Graphics g)
	{
		Color gradientEnd;  // color for gradient end, depends on buttons being enabled
		
		gradientEnd = DrawableEdge.getEnabledFlag() ? GRADIENT_END_COLOR : GRADIENT_START_COLOR;
		
		// offset the button so its center is in the middle not in the corner
		location.x -= size.width/2;
		location.y -= size.height/2;

		// fill the button interior with gradient
		gradient = new GradientPaint(location.x, location.y, GRADIENT_START_COLOR,
				                     location.x, location.y + size.height, gradientEnd);
		((Graphics2D)g).setPaint(gradient);
		g.fillRect(location.x, location.y, size.width, size.height);		
		
		if(isMouseOver() && DrawableEdge.getEnabledFlag()) // make frame thicker if highlighted
		{
			((Graphics2D)g).setStroke(new BasicStroke(HILIGHT_THICKNESS));
		}
		else
		{
			((Graphics2D)g).setStroke(new BasicStroke(1));
		}
		
		// draw frame around button and text inside
		g.setColor(FONT_COLOR);
		g.drawRect(location.x, location.y, size.width, size.height);
		g.drawString(text, location.x + TEXT_OFFSET_X, location.y + TEXT_OFFSET_Y);
    }
	
	/**
	 * Sets text of the button.
	 * @param text String used to set the text.
	 */
	public void setText(String text)
	{
		this.text = text;
	}
	
	/**
	 * Sets the location of the button.
	 * @param x new x coordinate in pixels from the upper
	 *          left corner of the frame
	 * @param y new y coordinate in pixels from the upper
	 *          left corner of the frame
	 */
	public void setLocation(int x, int y)
	{
		location.x = x;
		location.y = y;
	}
}
