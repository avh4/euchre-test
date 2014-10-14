import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EuchreGame extends JComponent implements MouseMotionListener, MouseListener, ActionListener {

    public static final Color TABLE_COLOR = new Color(204, 185, 159);
    public static final int CARD_CORNER = 7;
    public static final int CARD_HEIGHT = 100;
    public static final int CARD_WIDTH = 70;
    public static final Color CARD_FILL = new Color(241, 239, 228);
    public static final Color CARD_BORDER = new Color(40, 38, 35);
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    private int highlightedCard = -1;

    public GameModel gameModel = new GameModel();

    public static void main(String[] args) {
        JFrame window = new JFrame("Euchre");
        EuchreGame game = new EuchreGame();
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        window.addMouseMotionListener(game);
        window.addMouseListener(game);

        new Timer(1000, game).start();
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
        for (int i = 0; i < gameModel.myCards.size(); i++) {
            boolean highlighted = (highlightedCard == i);
            drawCardFront(g, xForPlayersCard(i), yForPlayersCard(), gameModel.myCards.get(i), highlighted);
        }

        // Opponent 1's cards
        for (int i = 0; i < gameModel.player2Cards.size(); i++) {
            drawCardBack(g, 30 + (CARD_WIDTH / 2) * i, (GAME_HEIGHT - CARD_HEIGHT) / 2);
        }

        // Opponent 2's card
        for (int i = 0; i < gameModel.player3Cards.size(); i++) {
            drawCardBack(g, 300 + (CARD_WIDTH / 2) * i, 30);
        }

        // Opponent 3's cards
        for (int i = 0; i < gameModel.player4Cards.size(); i++) {
            drawCardBack(g, GAME_WIDTH - 30 - CARD_WIDTH * 3 + (CARD_WIDTH / 2) * i, (GAME_HEIGHT - CARD_HEIGHT) / 2);
        }

        int centerX = (GAME_WIDTH - CARD_WIDTH) / 2;
        int centerY = (GAME_HEIGHT - CARD_HEIGHT) / 2;
        if (gameModel.playedCards[0] != null) {
            drawCardFront(g, centerX, centerY + CARD_HEIGHT * 3 / 4, gameModel.playedCards[0], false);
        }
        if (gameModel.playedCards[1] != null) {
            drawCardFront(g, centerX - CARD_WIDTH - 10, centerY, gameModel.playedCards[1], false);
        }
        if (gameModel.playedCards[2] != null) {
            drawCardFront(g, centerX, centerY - CARD_HEIGHT * 3 / 4, gameModel.playedCards[2], false);
        }
        if (gameModel.playedCards[3] != null) {
            drawCardFront(g, centerX + CARD_WIDTH + 10, centerY, gameModel.playedCards[3], false);
        }
    }

    private int yForPlayersCard() {
        return GAME_HEIGHT - CARD_HEIGHT - 30;
    }

    private int xForPlayersCard(int i) {
        return 200 + (CARD_WIDTH + 10) * i;
    }

    private void drawCardBack(Graphics g, int x, int y) {
        drawCardFrame(g, x, y, false);
    }

    private void drawCardFront(Graphics g, int x, int y, String letter, boolean highlighted) {
        drawCardFrame(g, x, y, highlighted);
        g.setFont(new Font(Font.SERIF, Font.PLAIN, 42));
        g.drawString(letter, x + CARD_WIDTH / 2 - 15, y + CARD_HEIGHT / 2 + 15);
    }

    private void drawCardFrame(Graphics g, int x, int y, boolean highlighted) {
        if (highlighted) {
            g.setColor(new Color(240, 200, 0));
        } else {
            g.setColor(CARD_FILL);
        }
        g.fillRoundRect(x, y, CARD_WIDTH, CARD_HEIGHT, CARD_CORNER, CARD_CORNER);
        g.setColor(CARD_BORDER);
        g.drawRoundRect(x, y, CARD_WIDTH, CARD_HEIGHT, CARD_CORNER, CARD_CORNER);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        highlightedCard = -1;
        for (int i = 0; i < gameModel.myCards.size(); i++) {
            if (e.getX() >= xForPlayersCard(i)
                    && e.getX() <= xForPlayersCard(i) + CARD_WIDTH
                    && e.getY() >= yForPlayersCard()) {
                highlightedCard = i;
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (highlightedCard != -1) {
            gameModel.playedCards[0] = gameModel.myCards.get(highlightedCard);
            gameModel.myCards.remove(highlightedCard);
            highlightedCard = -1;
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameModel.playedCards[0] == null) {
            // waiting for player
            return;
        } else if (gameModel.playedCards[1] == null) {
            gameModel.playedCards[1] = gameModel.player2Cards.get(0);
            gameModel.player2Cards.remove(0);
        } else if (gameModel.playedCards[2] == null) {
            gameModel.playedCards[2] = gameModel.player3Cards.get(0);
            gameModel.player3Cards.remove(0);
        } else if (gameModel.playedCards[3] == null) {
            gameModel.playedCards[3] = gameModel.player4Cards.get(0);
            gameModel.player4Cards.remove(0);
        } else {
            // the round is over
            gameModel.playedCards[0] = null;
            gameModel.playedCards[1] = null;
            gameModel.playedCards[2] = null;
            gameModel.playedCards[3] = null;
        }
        repaint();
    }
}
