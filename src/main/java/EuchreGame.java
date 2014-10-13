import javax.swing.*;
import java.awt.*;

public class EuchreGame extends JComponent {

    public static final Color TABLE_COLOR = new Color(204, 185, 159);
    public static final int CARD_CORNER = 7;
    public static final int CARD_HEIGHT = 100;
    public static final int CARD_WIDTH = 70;
    public static final Color CARD_FILL = new Color(241, 239, 228);
    public static final Color CARD_BORDER = new Color(40, 38, 35);
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

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
        return new Dimension(GAME_WIDTH, GAME_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(TABLE_COLOR);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // Main player's cards
        for (int i = 0; i < 5; i++) {
            drawCardFront(g, 200 + (CARD_WIDTH + 10) * i, GAME_HEIGHT - CARD_HEIGHT - 30, Character.toString((char) ('A' + i)));
        }

        // Opponent 1's cards
        for (int i = 0; i < 5; i++) {
            drawCardBack(g, 30 + (CARD_WIDTH / 2) * i, (GAME_HEIGHT - CARD_HEIGHT) / 2);
        }

        // Opponent 2's card
        for (int i = 0; i < 5; i++) {
            drawCardBack(g, 300 + (CARD_WIDTH / 2) * i, 30);
        }

        // Opponent 3's cards
        for (int i = 0; i < 5; i++) {
            drawCardBack(g, GAME_WIDTH - 30 - CARD_WIDTH * 3 + (CARD_WIDTH / 2) * i, (GAME_HEIGHT - CARD_HEIGHT) / 2);
        }
    }

    private void drawCardBack(Graphics g, int x, int y) {
        drawCardFrame(g, x, y);
    }

    private void drawCardFront(Graphics g, int x, int y, String letter) {
        drawCardFrame(g, x, y);
        g.setFont(new Font(Font.SERIF, Font.PLAIN, 42));
        g.drawString(letter, x + CARD_WIDTH / 2 - 15, y + CARD_HEIGHT / 2 + 15);
    }

    private void drawCardFrame(Graphics g, int x, int y) {
        g.setColor(CARD_FILL);
        g.fillRoundRect(x, y, CARD_WIDTH, CARD_HEIGHT, CARD_CORNER, CARD_CORNER);
        g.setColor(CARD_BORDER);
        g.drawRoundRect(x, y, CARD_WIDTH, CARD_HEIGHT, CARD_CORNER, CARD_CORNER);
    }
}
