import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TopPanel extends JPanel implements ActionListener, ItemListener
{
	private static Graph graph;                            // graph object
    private static final String[] GRAPHLIST = {"",
                                               "Graph 1", 
                                               "Graph 2", 
                                               "Graph 3"}; // Predefined graphs
    private JPanel headerPanel;                            // Panel containing title
    private JLabel header;                                 // The title of the program
    private JPanel chooseGraphs;                           // Panel containing graph options
    private JLabel choose;                                 // graphs label
    private JComboBox graphs;                              // List of predefined graphs
    private JButton start;                                 // Starts algorithm
    private JPanel steps;                                  // Panel containing stepping buttons
    private JButton prev;                                  // Go to previous position in algorithm
    private JButton next;                                  // Go to next position in algorithm
    private DialogPanel dialogPanel;                       // Panel containing
    private JPanel nodeSelectionPanel;                     // Panel to hold starting and ending node selections
    private JComboBox startingNode;                        // List of nodes for starting node
    private JComboBox endingNode;                          // List of nodes for ending node

    /**
    * Creates a JPanel containing title, options, and a dialogPanel 
    */
    public TopPanel() 
    {
        super();
        this.setLayout(new GridLayout(5,0));

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
        
        graph = null;
        
        // initialize top panel
        graphs.addItemListener (this);
        start.addActionListener(this);
        prev.addActionListener (this);
        next.addActionListener (this);

        prev.setEnabled(false);
        next.setEnabled(false);

        dialogPanel = new DialogPanel();
        nodeSelectionPanel = new JPanel();
        startingNode = new JComboBox();
        endingNode = new JComboBox();

        nodeSelectionPanel.add(new JLabel("Starting Node:"));
        nodeSelectionPanel.add(startingNode);
        nodeSelectionPanel.add(new JLabel("Ending Node:"));
        nodeSelectionPanel.add(endingNode);
        nodeSelectionPanel.setVisible(false);

        this.add(headerPanel);
        this.add(chooseGraphs);
        this.add(steps);
        this.add(dialogPanel);
        this.add(nodeSelectionPanel);
    }
    
    /**
     * Handles action listener event.
     *
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == start)
        {
            if (start.getText() == "Start") // handle start button
            {
                start.setText("Stop");
                graphs.setEnabled(false);
                prev.setEnabled(true);
                next.setEnabled(true);
                DrawableEdge.enableButtons(false);
                nodeSelectionPanel.setVisible(false);
            }
            else if (start.getText() == "Stop") // handle stop button
            {
                start.setText("Start");
                graphs.setEnabled(true);
                prev.setEnabled(false);
                next.setEnabled(false);
                DrawableEdge.enableButtons(true);
                nodeSelectionPanel.setVisible(true);
            }

        }
        else if (e.getSource() == prev) // handle prev button
        {
            if (graph.prevState())
            {
            	graph.updateGraph();
            }
        }
        else if (e.getSource() == next) // handle next button
        {
            if (graph.nextState())
            {
            	graph.updateGraph();
            }
        }
        
        getParent().repaint();
    }

    /**
     * Handles graph combo box.
     *
     * @param e item event
     */
    public void itemStateChanged(ItemEvent e) 
    {

        if (e.getSource() == graphs) // handle graph combo box
        {
            String newDialog;

            int graphsIndex = graphs.getSelectedIndex();

            if (graphsIndex == 0)
            {
                dialogPanel.resetDialog();
                nodeSelectionPanel.setVisible(false);
                return;
            }
            if (graphsIndex == 1) // graph1
            {
            	graph = Graph.graph1();
            }
            else if (graphsIndex == 2) // graph2
            {
            	graph = Graph.graph2();
            }
            else if (graphsIndex == 3) // graph3
            {
            	graph = Graph.graph3();
            }
            
            graph.updateGraph();
            newDialog = String.format("Graph %s selected. Select starting and ending nodes to begin.", graphsIndex);

            // Respond to user selecting graph

            updateSelectionPanel(newDialog); 

            // New graph, can now start a new run of Dijkstra's
            start.setText("Start");

            graphs.setEnabled(true);
            DrawableEdge.enableButtons(true);
            getParent().repaint();
        }
    }

    /**
     * Updates node list and shows selection boxes
     *
     * @param String newDialog new message to display
     */
    private void updateSelectionPanel(String newDialog)
    {
        dialogPanel.setDialog(newDialog);

        String[] nodeList = graph.getNodeNames();
        startingNode.removeAllItems();
        endingNode.removeAllItems();

        for (String s : nodeList)
        {
            startingNode.addItem(s);
            endingNode.addItem(s);
        }

        nodeSelectionPanel.setVisible(true);
    }

    /**
     * Sets private static graph to new graph
     *
     * @param g the new graph to be set
     */
    public static void setGraph(Graph g)
    {
        graph = g;
    }

    /**
     * Returns instance of current graph
     *
     * @return the current graph instance 
     */
    public static Graph getGraph()
    {
        return graph;
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
