import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModel {
    List<String> myCards = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E"));
    List<String> player2Cards = new ArrayList<String>(Arrays.asList("J", "K", "L", "M", "V"));
    List<String> player3Cards = new ArrayList<String>(Arrays.asList("O", "3", "C", "D", "E"));
    List<String> player4Cards = new ArrayList<String>(Arrays.asList("A", "d", "C", "K", "M"));
    String[] playedCards = new String[4];
}
