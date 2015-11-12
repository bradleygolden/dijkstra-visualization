/**
 * @author Bradley Golden, Amanda Olson, Cody Roberts, Maciej Szpakowski
 * <p>
 * @project  Project 3 - Data Structure Visualization
 * <p>
 * @date November 12, 2015
 * <p>
 *This class simulates an edge.
 * <p>
 * Used to convert relative x and y coordinates into actual x and y coordinates given
 * a dynamic display.
 */
public class ScaledPoint
{
    private double x; // x coordinate
    private double y; // y coordinate
    private static int height = 0; // height of the window
    private static int width = 0; // width of the window

    /**
     * Creates a ScaledPoint object with x and y coordinates set to 0.0
     */
    public ScaledPoint()
    {
        x = 0.0;
        y = 0.0;
    }

    /**
     * Updates the static values of height and width in the ScaledPoint class to h and w
     * respectively.
     *
     * @param h The height of the current window. Must be initialized.
     * @param w The width of the current window. Must be initialized.
     */
    public static void updateWindow(int h, int w)
    {
        height = h;
        width = w;
    }

    /**
     * Getter for the x coordinate.
     *
     * @return Scaled x value.
     */
    public int getX()
    {
        return (int)(x * width);
    }

    /**
     * Getter for the y coordinate.
     *
     * @return Scaled y value.
     */
    public int getY()
    {
        return (int)(y * height);
    }

    /**
     * Setter for the x coordinate.
     *
     * @param x Relative x coordinate. Must be initialized. x is stored as an instance
     * variable x.
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Setter for the y coordiante.
     *
     * @param y Relative y coordinate. Must be initialized. y is stored as an instance
     * variable y.
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Setter for the x and y coordinates.
     *
     * @param x Relative x coordinate. Must be initialized. x is stored as an instance
     * variable x.
     * @param y Relative y coordinate. Must be initialized. y is stored as an instance
     * variable y.
     */
    public void setXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets scaled x and y coordinates based on panel's width and height
     * @param x new x coordinate in the panel.
     * @param y new y coordinate in the panel.
     */
    public void setWinXY(int x, int y)
    {
        this.x = (double)x/width;
        this.y = (double)y/height;
    }
}
