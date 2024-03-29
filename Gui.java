import javax.swing.*;
import java.awt.*;
/**
 * @author Bradley Golden, Amanda Olson, Cody Roberts, Maciej Szpakowski
 * <p>
 * @project  Project 3 - Data Structure Visualization
 * <p>
 * @date November 12, 2015
 * <p>
 * Gui implemenation of Dijkstra's Algorithm.
 */
public class Gui
{
    /**
     * Application's entry point.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Dijkstra's Algorithm");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 800 , 600 );
        frame.add(new TopPanel(), BorderLayout.NORTH);
        frame.add(new DrawManager(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
