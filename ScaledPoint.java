public class ScaledPoint
{
    private float x;
    private float y;
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
        return x * width;
    }

    public int getY() {
        return y * height;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }
}
