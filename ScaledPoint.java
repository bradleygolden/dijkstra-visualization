public class ScaledPoint
{
    private double x;
    private double y;
    private static int height = 0;
    private static int width = 0;

    public ScaledPoint()
    {
        x = 0.0;
        y = 0.0;
    }

    public static void updateWindow(int h, int w) 
    // PRE: h and w are initialized
    // POST: static values of height and width are set to h and w respectively
    {
        height = h;
        width = w;
    }

    public int getX()
    // POST: FCTVAL = scaled x value
    {
        return (int)(x * width);
    }

    public int getY()
    // POST: FCTVAL = scaled y value
    {
        return (int)(y * height);
    }

    public void setX(double x)
    // PRE: x is initialized
    // POST: x is stored as instance variable x
    {
        this.x = x;
    }

    public void setY(double y)
    // PRE: y is initialized
    // POST: y is stored as instance variable y
    {
        this.y = y;
    }
    
    public void setXY(double x, double y)
    {
    	this.x = x;
    	this.y = y;
    }
}
