import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JPanel holding user options
 */
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
    private boolean startSet = false;                      // Start selected flag
    private boolean endSet = false;                        // End selected flag
    private JButton about;                                 // About dijkstras
    private JPanel aboutPanel;                             // Panel for about button 

    /**
    * Creates a JPanel containing header, graph selection, interaction buttons,
    * and starting and ending nodes.
    */
    public TopPanel() 
    {
        super();
        this.setLayout(new GridLayout(6,3));

        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        header = new JLabel("Dijkstra's Algorithm");
        header.setFont(new Font("Serif", Font.BOLD, 20));

        headerPanel.add(header);

        aboutPanel = new JPanel();
        about = new JButton("About");
        about.addActionListener(this);

        aboutPanel.add(about);

        chooseGraphs = new JPanel();
        choose = new JLabel("Choose a graph:");
        graphs = new JComboBox(GRAPHLIST);
        start = new JButton("Start");

        chooseGraphs.add(choose);
        chooseGraphs.add(graphs);
        chooseGraphs.add(start);

        start.setEnabled(false);

        steps = new JPanel();
        prev = new JButton("Previous Step");
        next = new JButton("Next Step");

        steps.add(prev);
        steps.add(next);
        
        graph = null;
        
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

        startingNode.addItemListener(this);
        endingNode.addItemListener(this);

        nodeSelectionPanel.add(new JLabel("Starting Node:"));
        nodeSelectionPanel.add(startingNode);
        nodeSelectionPanel.add(new JLabel("Ending Node:"));
        nodeSelectionPanel.add(endingNode);
        nodeSelectionPanel.setVisible(false);

        this.add(headerPanel);
        this.add(aboutPanel);
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
        if (e.getSource() == start) // if start button is pressed
        {
            if (start.getText() == "Start") // handle starting conditions
            {
                start.setText("Stop");
                graphs.setEnabled(false);
                prev.setEnabled(true);
                next.setEnabled(true);
                DrawableEdge.enableButtons(false);
                nodeSelectionPanel.setVisible(false);
                graph = Graph.regenerateGraph(graph);
                graph.updateGraph();

                dialogPanel.setDialog("Algorithm started, use previous and next to step through");
            }
            else if (start.getText() == "Stop") // handle stopping conditions
            {
                start.setText("Start");
                graphs.setEnabled(true);
                prev.setEnabled(false);
                next.setEnabled(false);
                DrawableEdge.enableButtons(true);
                nodeSelectionPanel.setVisible(true);
                graph = Graph.regenerateGraph(graph);
                graph.updateGraph();

                dialogPanel.setDialog("Algorithm stopped.");
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
        else if (e.getSource() == about)
        { 
            JOptionPane.showMessageDialog(this, "This is a visualization tool for Dijkstra's Algorithm." +
                                                "Learn about Dijkstra's Algorithm: " +
                                                "https://en.wikipedia.org/wiki/Dijkstra's_algorithm" +
                                                "\n\nVisualization by Maciej, Bradley, Amanda, Cody.");
        }
        
        getParent().repaint();
    }

    /**
     * Handles item state events 
     *
     * @param e item event
     */
    public void itemStateChanged(ItemEvent e) 
    {

        if (e.getSource() == graphs) // handle graph combo box
        {
            String newDialog;

            int graphsIndex = graphs.getSelectedIndex();

            if (graphsIndex == 0) // If no graph has been selected
            {
                dialogPanel.resetDialog();
                nodeSelectionPanel.setVisible(false);
                return;
            }
            if (graphsIndex == 1) // If graph1 has been selected
            {
            	setGraph(Graph.graph1());
            }
            else if (graphsIndex == 2) // If graph2 has been selected
            {
            	graph = Graph.graph2();
            }
            else if (graphsIndex == 3) // If graph3 has been selected
            {
            	graph = Graph.graph3();
            }
            
            // Respond to user selecting graph
            graph.updateGraph();
            newDialog = String.format("Graph %s selected. Select starting and ending nodes to begin.", graphsIndex);
            updateSelectionPanel(newDialog); 

            // New graph selected, can now start a new run of Dijkstra's
            start.setText("Start");
            start.setEnabled(false);
            startSet = false;
            endSet = false;

            DrawableEdge.enableButtons(true);
            getParent().repaint();
        }
        else if (e.getSource() == startingNode) // Starting node is selected
        {
            String start = startingNode.getSelectedItem() + "";

            graph.setStart(start);
            DrawableNode.setStart(start);
            startSet = true;
        }
        else if (e.getSource() == endingNode) // Ending node is selected
        {
            String end = endingNode.getSelectedItem() + "";

            graph.setEnd(end);
            DrawableNode.setEnd(end);
            endSet = true;
        }

        if (startSet && endSet) // If start and end has been selected, allow user to start
        {
            start.setEnabled(true);
        }

        getParent().repaint();
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
}
