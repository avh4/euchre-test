import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EuchreGame extends JComponent implements MouseMotionListener, MouseListener, ActionListener {

    public static final Color TABLE_COLOR = new Color(204, 185, 159);
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final Color RED_SUIT = new Color(214, 120, 102);
    public static final Color BLUE_SUIT = new Color(109, 172, 214);
    public static final Color PURPLE_SUIT = new Color(151, 102, 214);
    public static final Color ORANGE_SUIT = new Color(214, 157, 20);
    private int highlightedCard = -1;

    ArrayList<Card> deck;

    public GameModel gameModel = new GameModel();

    public EuchreGame() {
        deck = new ArrayList<Card>();
        deck.add(makeCard("A", RED_SUIT));
        deck.add(makeCard("B", RED_SUIT));
        deck.add(makeCard("G", RED_SUIT));
        deck.add(makeCard("S", RED_SUIT));
        deck.add(makeCard("X", RED_SUIT));
        deck.add(makeCard("Z", RED_SUIT));
        deck.add(makeCard("A", BLUE_SUIT));
        deck.add(makeCard("B", BLUE_SUIT));
        deck.add(makeCard("G", BLUE_SUIT));
        deck.add(makeCard("S", BLUE_SUIT));
        deck.add(makeCard("X", BLUE_SUIT));
        deck.add(makeCard("Z", BLUE_SUIT));
        deck.add(makeCard("A", PURPLE_SUIT));
        deck.add(makeCard("B", PURPLE_SUIT));
        deck.add(makeCard("G", PURPLE_SUIT));
        deck.add(makeCard("S", PURPLE_SUIT));
        deck.add(makeCard("X", PURPLE_SUIT));
        deck.add(makeCard("Z", PURPLE_SUIT));
        deck.add(makeCard("A", ORANGE_SUIT));
        deck.add(makeCard("B", ORANGE_SUIT));
        deck.add(makeCard("G", ORANGE_SUIT));
        deck.add(makeCard("S", ORANGE_SUIT));
        deck.add(makeCard("X", ORANGE_SUIT));
        deck.add(makeCard("Z", ORANGE_SUIT));

        for (int i = 0; i < 5; i++) {
            gameModel.myCards.add(deck.remove(0));
            gameModel.player2Cards.add(deck.remove(0));
            gameModel.player3Cards.add(deck.remove(0));
            gameModel.player4Cards.add(deck.remove(0));
        }
    }

    private Card makeCard(String letter, Color suit) {
        Card c = new Card();
        c.letter = letter;
        c.color = suit;
        return c;
    }

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
            gameModel.myCards.get(i).drawFront(g, xForPlayersCard(i), yForPlayersCard(), highlighted);
        }

        // Opponent 1's cards
        for (int i = 0; i < gameModel.player2Cards.size(); i++) {
            gameModel.player2Cards.get(i).drawBack(g, 30 + (Card.CARD_WIDTH / 2) * i, (GAME_HEIGHT - Card.CARD_HEIGHT) / 2);
        }

        // Opponent 2's card
        for (int i = 0; i < gameModel.player3Cards.size(); i++) {
            gameModel.player3Cards.get(i).drawBack(g, 300 + (Card.CARD_WIDTH / 2) * i, 30);
        }

        // Opponent 3's cards
        for (int i = 0; i < gameModel.player4Cards.size(); i++) {
            gameModel.player4Cards.get(i).drawBack(g, GAME_WIDTH - 30 - Card.CARD_WIDTH * 3 + (Card.CARD_WIDTH / 2) * i, (GAME_HEIGHT - Card.CARD_HEIGHT) / 2);
        }

        int centerX = (GAME_WIDTH - Card.CARD_WIDTH) / 2;
        int centerY = (GAME_HEIGHT - Card.CARD_HEIGHT) / 2;
        if (gameModel.playedCards[0] != null) {
            gameModel.playedCards[0].drawFront(g, centerX, centerY + Card.CARD_HEIGHT * 3 / 4, false);
        }
        if (gameModel.playedCards[1] != null) {
            gameModel.playedCards[1].drawFront(g, centerX - Card.CARD_WIDTH - 10, centerY, false);
        }
        if (gameModel.playedCards[2] != null) {
            gameModel.playedCards[2].drawFront(g, centerX, centerY - Card.CARD_HEIGHT * 3 / 4, false);
        }
        if (gameModel.playedCards[3] != null) {
            gameModel.playedCards[3].drawFront(g, centerX + Card.CARD_WIDTH + 10, centerY, false);
        }
    }

    private int yForPlayersCard() {
        return GAME_HEIGHT - Card.CARD_HEIGHT - 30;
    }

    private int xForPlayersCard(int i) {
        return 200 + (Card.CARD_WIDTH + 10) * i;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        highlightedCard = -1;
        for (int i = 0; i < gameModel.myCards.size(); i++) {
            if (e.getX() >= xForPlayersCard(i)
                    && e.getX() <= xForPlayersCard(i) + Card.CARD_WIDTH
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
