/**
 * @author Bradley Golden, Amanda Olson, Cody Roberts, Maciej Szpakowski
 * <p>
 * @project  Project 3 - Data Structure Visualization
 * <p>
 * @date November 12, 2015
 * <p>
 * Gives the user a useful dialog panel which provides
 * various instructions on how to use the GUI interface.
 */

import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 */
public class DialogPanel extends JPanel
{
    private JLabel dialog; // Label to hold messages

    /**
    * Creates a Dialog Panel that holds the dialog label
    */
    public DialogPanel()
    {
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialog = new JLabel("Welcome.  Please select a graph to begin");
        this.add(dialog);
    }

    /**
    * Sets the dialog label to a new string
    *
    * @param dialog the string to change the label to
    */
    public void setDialog(String dialog)
    {
        this.dialog.setText(dialog);
    }

    /**
     * Resets dialog to the welcome message
     */
    public void resetDialog()
    {
        this.dialog.setText("Welcome.  Please select a graph to begin");
    }
}
