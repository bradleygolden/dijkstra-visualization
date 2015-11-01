import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

public class DialogPanel extends JPanel
{
    private JLabel dialog;

    public DialogPanel()
    {
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialog = new JLabel("Please select a starting node");
    }

    public void setDialog(String dialog)
    // PRE: dialog is initialized
    // POST: JLabel dialog is set to new dialog
    {
        this.dialog = new JLabel(dialog);
    }
}
