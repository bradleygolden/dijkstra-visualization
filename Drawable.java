import java.awt.Graphics;

/**
 * Abstract base class for drawable graph objects.
 * @date 11/11/2015
 * @project Data structure visualization - Dijkstra's Algorithm
 * @author Maciej Szpakowski
 */
public abstract class Drawable
{
    protected static String path = ""; // current shortest path as a string of nodes

    /**
     * Draws the object using g
     *
     * @param g Initialized Graphics object used to draw.
     */
    public abstract void draw(Graphics g);

    /**
     * Sets the Drawable.path to path.
     * @param path new string path
     */
    public static void setPath(String path)
    {
        Drawable.path = path;
    }
}
