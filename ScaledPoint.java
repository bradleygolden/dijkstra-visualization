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

    public void updateWindow(int h, int w) {
        height = h;
        width = w;
    }

    public int getX() {
        return (int)x * width;
    }

    public int getY() {
        return (int)y * height;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }
}
