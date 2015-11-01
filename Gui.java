import javax.swing.*;
import java.awt.*;

class AFrame extends JFrame
{
    public AFrame()
    {
        super( "Dijkstra's Algorithm" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 800 , 600 );
        setVisible( true );
    }
}

public class Gui
{
    public static void main(String[] args)
    {
        AFrame frame = new AFrame();
        frame.getContentPane().add(new TopPanel(), BorderLayout.NORTH);
        frame.getContentPane().add(new JPanel(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
