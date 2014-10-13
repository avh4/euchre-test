import javax.swing.*;
import java.awt.*;

public class EuchreGame extends JComponent {
    public static void main(String[] args) {
        JFrame window = new JFrame("Euchre");
        window.add(new EuchreGame());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 800, 600);
    }
}
