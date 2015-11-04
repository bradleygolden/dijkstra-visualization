import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class TopPanel extends JPanel
{
    private JPanel headerPanel;                            // Panel containing title
    private JLabel header;                                 // The title of the program
    private JPanel chooseGraphs;                           // Panel containing graph options
    private JLabel choose;                                 // graphs label
    protected JComboBox graphs;                            // List of predefined graphs
    private static final String[] GRAPHLIST = {"Graph 1", 
                                               "Graph 2", 
                                               "Graph 3"}; // Predefined graphs
    protected JButton start;                                 // Starts algorithm
    private JPanel steps;                                  // Panel containing stepping buttons
    protected JButton prev;                                  // Go to previous position in algorithm
    protected JButton next;                                  // Go to next position in algorithm
    private DialogPanel dialogPanel;                       // Panel containing

    /**
    * Creates a JPanel containing title, options, and a dialogPanel 
    */
    public TopPanel() 
    {
        super();
        this.setLayout(new GridLayout(4,0));

        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        header = new JLabel("Dijkstra's Algorithm");
        header.setFont(new Font("Serif", Font.BOLD, 20));

        headerPanel.add(header);

        chooseGraphs = new JPanel();
        choose = new JLabel("Choose a graph:");
        graphs = new JComboBox(GRAPHLIST);
        start = new JButton("Start");

        chooseGraphs.add(choose);
        chooseGraphs.add(graphs);
        chooseGraphs.add(start);

        steps = new JPanel();
        prev = new JButton("Previous Step");
        next = new JButton("Next Step");

        steps.add(prev);
        steps.add(next);

        dialogPanel = new DialogPanel();

        this.add(headerPanel);
        this.add(chooseGraphs);
        this.add(steps);
        this.add(dialogPanel);
    }

    /**
    *  Test driver for TopPanel
    */
    public static void main(String[] args) 
    {
        // Test Code
        JFrame frame = new JFrame("Dijkstra's Algorithm");
        frame.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
        frame.getContentPane().add(new JPanel(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
