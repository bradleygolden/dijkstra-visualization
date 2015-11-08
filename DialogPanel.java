import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

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
        dialog = new JLabel("Please select a starting node");
    }

    /**
    * Sets the dialog label to a new string
    * @param dialog the string to change the label to 
    */
    public void setDialog(String dialog)
    // PRE: dialog is initialized
    // POST: JLabel dialog is set to new dialog
    {
        this.dialog = new JLabel(dialog);
    }
}
