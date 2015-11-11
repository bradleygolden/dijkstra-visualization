import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Gui
{
	public static Graph graph;              // graph object
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
