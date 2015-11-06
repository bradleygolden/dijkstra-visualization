package src;

import java.awt.Graphics;
import javax.swing.JFrame;

public abstract class Drawable
{
	protected static int mouseX = 0;
	protected static int mouseY = 0;
	
	public abstract void draw(Graphics g);
	
	public static void setMouse(int x, int y)
	{
		mouseX = x;
		mouseY = y;
	}
}
