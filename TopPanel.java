import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JPanel holding user options
 */
public class TopPanel extends JPanel implements ActionListener, ItemListener
{
	private static Graph graph;                            // Graph to be drawn
    private static final String[] GRAPHLIST = {"",
                                               "Graph 1", 
                                               "Graph 2", 
                                               "Graph 3",
                                               "Generated Graph"
                                                        }; // Predefined graphs
    private JPanel headerPanel;                            // Panel containing title
    private JPanel chooseGraphs;                           // Panel containing graph options
    private JPanel steps;                                  // Panel containing stepping buttons
    private JPanel aboutPanel;                             // Panel for about button 
    private JPanel nodeSelectionPanel;                     // Panel to hold starting 
                                                           // and ending node selections
    private DialogPanel dialogPanel;                       // Panel containing
    private JLabel header;                                 // The title of the program
    private JLabel choose;                                 // Graphs label
    private JButton start;                                 // Starts algorithm
    private JButton prev;                                  // Go to previous position in algorithm
    private JButton next;                                  // Go to next position in algorithm
    private JButton about;                                 // About dijkstras
    private JButton legend;                                // Show key/legend 
    private JComboBox startingNode;                        // List of nodes for starting node
    private JComboBox endingNode;                          // List of nodes for ending node
    private JComboBox graphs;                              // List of predefined graphs
    private boolean startSet;                              // Start selected flag
    private boolean endSet;                                // End selected flag

    /**
    * Creates a JPanel containing header, graph selection, interaction buttons,
    * and starting and ending nodes.
    */
    public TopPanel() 
    {
        super();
        this.setLayout(new GridLayout(6,0));

        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        header = new JLabel("Dijkstra's Algorithm");
        header.setFont(new Font("Serif", Font.BOLD, 20));

        headerPanel.add(header);

        aboutPanel = new JPanel();
        about = new JButton("About");
        legend = new JButton("Show Legend");

        about.addActionListener(this);
        legend.addActionListener(this);

        aboutPanel.add(about);
        aboutPanel.add(legend);

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
        startSet = false;
        endSet = false;
        
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

                dialogPanel.setDialog("Algorithm started, use previous and next to step through.");
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
                graph.resetPath();

                dialogPanel.setDialog("Algorithm stopped. " +
                                      "Reselect starting and ending nodes to begin.");
            }

        }
        else if (e.getSource() == prev) // handle prev button
        {
            if (graph.prevState()) // if can go to prev state
            {
            	graph.updateGraph();
                next.setEnabled(true);
            } 
            else // can't continue
            {
                prev.setEnabled(false);
            }
        }
        else if (e.getSource() == next) // handle next button
        {
            if (graph.nextState()) // if can continue to next state
            {
            	graph.updateGraph();
                prev.setEnabled(true);
            }
            else // can't continue
            {
                next.setEnabled(false);
            }
        }
        else if (e.getSource() == about) // handle about button
        { 
            JOptionPane.showMessageDialog(this, "This is a visualization " + 
                                                "tool for Dijkstra's Algorithm." +
                                                "\n\nLearn about Dijkstra's Algorithm: " +
                                                "https://en.wikipedia.org/wiki/Dijkstra's_algorithm" +
                                                "\n\nDeveloped by Maciej, Bradley, Amanda, Cody.");
        }
        else if (e.getSource() == legend) // handle showing/hiding legend
        {
            if (legend.getText() == "Show Legend") // if legend is hidden
            {
                legend.setText("Hide Legend");
                DrawManager.showMap = true;
            }
            else // if legend is hidden
            {
                legend.setText("Show Legend");
                DrawManager.showMap = false;
            }
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
        String generateInput;
        int numVertices;
        int graphsIndex;
        String newDialog;
        int maxVertices;
        int minVertices;
        String helper;
        
        numVertices = 0;
        maxVertices = 25;
        minVertices = 2;

        if (e.getSource() == graphs) // handle graph combo box
        {
            graphsIndex = graphs.getSelectedIndex();

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
            	setGraph(Graph.graph2());
            }
            else if (graphsIndex == 3) // If graph3 has been selected
            {
            	setGraph(Graph.graph3());
            }
            else if (graphsIndex == 4 && 
                     graphs.isPopupVisible()) // If genereated graph has been selected
            {
                generateInput = JOptionPane.showInputDialog("Please enter number of " +
                                                            "vertices between 1 and 25.");

                try
                {
                    numVertices = new Integer(generateInput);
                }
                catch(NumberFormatException ie)
                {
                    return;
                }

                if (numVertices >= minVertices && 
                    numVertices < maxVertices) // generate if input is valid
                {
                    setGraph(Graph.generateGraph(numVertices));
                }

                graphs.hidePopup();
            }
            
            // Respond to user selecting graph
            newDialog = String.format("Graph %s selected. Select starting and ending nodes to begin.",
                                      graphsIndex);
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
        	helper = startingNode.getSelectedItem() + "";
        	
        	if(helper.equals("")) // invalidate if user selected empty
        	{
        		startSet = false;        		
        	}
        	else
        	{
	            graph.setStart(helper);
	            DrawableNode.setStart(helper);
	            startSet = true;
        	}
        }
        else if (e.getSource() == endingNode) // Ending node is selected
        {
        	helper = endingNode.getSelectedItem() + "";

        	if(helper.equals("")) // invalidate if user selected empty
        	{
        		endSet = false;        		
        	}
        	else
        	{
	            graph.setEnd(helper);
	            DrawableNode.setEnd(helper);
	            endSet = true;
        	}
        }

        if (startSet && endSet) // If start and end has been selected, allow user to start
        {
            start.setEnabled(true);
        }
        else
        {
        	start.setEnabled(false);
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

        for (String s : nodeList) // add each node to JComboBox
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
