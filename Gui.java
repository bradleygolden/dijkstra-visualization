import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JPanel {

    private Point lastPoint;

    public Gui() 
    {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastPoint = new Point(e.getX(), e.getY());
                System.out.println(lastPoint.toString());
            }
        });
    }

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Title");
        frame.getContentPane().add(new Gui(), BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
