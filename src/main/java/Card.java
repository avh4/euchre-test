import java.awt.*;

public class Card {
    public static final int CARD_CORNER = 7;
    public static final int CARD_HEIGHT = 100;
    public static final int CARD_WIDTH = 70;
    public static final Color CARD_FILL = new Color(241, 239, 228);
    public static final Color CARD_BORDER = new Color(40, 38, 35);

    String letter;
    Color color;

    public void drawFront(Graphics g, int x, int y, boolean highlighted) {
        drawCardFrame(g, x, y, highlighted, color);
        g.setFont(new Font(Font.SERIF, Font.PLAIN, 42));
        g.drawString(letter, x + CARD_WIDTH / 2 - 15, y + CARD_HEIGHT / 2 + 15);

    }

    public void drawBack(Graphics g, int x, int y) {
        drawCardFrame(g, x, y, false, CARD_FILL);
    }

    private void drawCardFrame(Graphics g, int x, int y, boolean highlighted, Color color) {
        if (highlighted) {
            g.setColor(color.brighter());
        } else {
            g.setColor(color);
        }
        g.fillRoundRect(x, y, CARD_WIDTH, CARD_HEIGHT, CARD_CORNER, CARD_CORNER);
        g.setColor(CARD_BORDER);
        g.drawRoundRect(x, y, CARD_WIDTH, CARD_HEIGHT, CARD_CORNER, CARD_CORNER);
    }
}
