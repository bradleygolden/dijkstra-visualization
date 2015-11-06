package src;

import java.awt.Graphics;

public abstract class Drawable
{
    protected static int mouseX = 0; // x coordiante of the cursor
    protected static int mouseY = 0; // y coordinate of the cursor
    
    /**
     * Draws the object using g
     *
     * @param g Initialized Graphics object used to draw.
     */
    public abstract void draw(Graphics g);
    
    /**
     * Sets mouseX, mouseY.
     *
     * @param x x coordinate of the cursor.
     * @param y y coordinate of the cursor.
     */
    public static void setMouse(int x, int y)
    {
        mouseX = x;
        mouseY = y;
    }
}
