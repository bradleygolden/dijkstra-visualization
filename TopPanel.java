import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

public class TopPanel extends JPanel 
{
    private JPanel headerPanel;
    private JLabel header;
    private JPanel chooseGraphs;
    private JLabel choose;
    private JComboBox graphs;
    private JButton start;
    private JButton startOver;
    private JPanel steps;
    private JButton prev;
    private JButton next;
    private JPanel dialogPanel;
    private JLabel dialog;

    public TopPanel() 
    {
        super();
        this.setLayout(new GridLayout(4,0));

        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        header = new JLabel("Dijkstra's Algorithm");

        headerPanel.add(header);

        chooseGraphs = new JPanel();
        choose = new JLabel("Choose a graph:");
        graphs = new JComboBox();
        start = new JButton("Start");
        startOver = new JButton("Start Over");

        chooseGraphs.add(choose);
        chooseGraphs.add(graphs);
        chooseGraphs.add(start);
        chooseGraphs.add(startOver);

        steps = new JPanel();
        prev = new JButton("Previous");
        next = new JButton("Next");

        steps.add(prev);
        steps.add(next);

        dialogPanel= new JPanel();
        dialogPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialog = new JLabel("Please select a starting node");

        dialogPanel.add(dialog);

        this.add(headerPanel);
        this.add(chooseGraphs);
        this.add(steps);
        this.add(dialogPanel);
    }

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Title");
        frame.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
        frame.getContentPane().add(new JPanel(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
