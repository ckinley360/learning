
import java.util.Comparator;
 
public class BySuitInValueOrder implements Comparator<Card> {
    
    @Override
    public int compare(Card c1, Card c2) {
        // If the suits are equal, then compare the values.
        if (c1.getSuit() == c2.getSuit()) {
            return c1.getValue() - c2.getValue();
        }
        
        // Otherwise, compare the suits.
        return c1.getSuit().ordinal() - c2.getSuit().ordinal();
    }
}
