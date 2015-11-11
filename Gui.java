import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class AFrame extends JFrame implements ActionListener, ItemListener
{
    public static Graph graph;              // graph object
    private TopPanel top;                   // top panel
    
    /**
     * Initializes the main frame.
     *
     */
    public AFrame()
    {
        super( "Dijkstra's Algorithm" );
        
        // initialize frame
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 800 , 600 );

        graph = Graph.graph1();
        
        // initialize top panel
        top = new TopPanel();
        top.graphs.addItemListener (this);
        top.start.addActionListener(this);
        top.prev.addActionListener (this);
        top.next.addActionListener (this);

        top.prev.setEnabled(false);
        top.next.setEnabled(false);
        
        add(top, BorderLayout.NORTH);
        add(new DrawManager(), BorderLayout.CENTER);
    }
    
    /**
     * Handles action listener event.
     *
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == top.start)
        {
            if (top.start.getText() == "Start") // handle start button
            {
                top.start.setText("Stop");
                top.graphs.setEnabled(false);
                top.prev.setEnabled(true);
                top.next.setEnabled(true);
                DrawableEdge.enableButtons(false);
            }
            else if (top.start.getText() == "Stop") // handle stop button
            {
                top.start.setText("Start");
                top.graphs.setEnabled(true);
                top.prev.setEnabled(false);
                top.next.setEnabled(false);
                DrawableEdge.enableButtons(true);
            }
        }
        else if (e.getSource() == top.prev) // handle prev button
        {
            if (graph.prevState())
            {
                graph.updateGraph();
            }
        }
        else if (e.getSource() == top.next) // handle next button
        {
            if (graph.nextState())
            {
                graph.updateGraph();
            }
        }
        
        repaint();
    }

    /**
     * Handles graph combo box.
     *
     * @param e item event
     */
    public void itemStateChanged(ItemEvent e) 
    {
        if (e.getSource() == top.graphs) // handle graph combo box
        {
            if (top.graphs.getSelectedIndex() == 0) // graph1
            {
                graph = Graph.graph1();                
            }
            else if (top.graphs.getSelectedIndex() == 1) // graph2
            {
            	return;
                //graph = Graph.graph2();
            }
            else if (top.graphs.getSelectedIndex() == 2) // graph3
            {
            	return;
                //graph = Graph.graph3(); 
            }
            
            graph.updateGraph();
            top.start.setText("Start");
            top.graphs.setEnabled(true);
            DrawableEdge.enableButtons(true);
            repaint();
        }
    }
}

public class Gui
{
    /**
     * Application's entry point.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args)
    {
        AFrame frame = new AFrame();
        frame.setVisible(true);
    }
}
