import java.awt.Graphics;

public abstract class Drawable
{
	protected static String path = "";
    /**
     * Draws the object using g
     *
     * @param g Initialized Graphics object used to draw.
     */
    public abstract void draw(Graphics g);
    
    public static void setPath(String path)
    {
    	Drawable.path = path;
    }
}
