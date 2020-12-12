
import java.util.ArrayList;
import java.util.Collections;
 
public class Hand implements Comparable<Hand> {
    
    private ArrayList<Card> cards;
    
    public Hand() {
        this.cards = new ArrayList<>();
    }
    
    public void add(Card card) {
        this.cards.add(card);
    }
    
    public void print() {
        for (Card card:this.cards) {
            System.out.println(card);
        }
    }
    
    public void sort() {
        Collections.sort(this.cards);
    }
    
    public ArrayList<Card> getCards() {
        return this.cards;
    }
    
    public void sortBySuit() {
        BySuitInValueOrder bySuitInValueOrderSorter = new BySuitInValueOrder();
        Collections.sort(this.cards, bySuitInValueOrderSorter);
    }
    
    @Override
    public int compareTo(Hand hand) {
        // Calculate the value of this hand.
        int thisHandValue = 0;
        for (Card card:this.cards) {
            thisHandValue += card.getValue();
        }
        
        // Calculate the value of the compared hand.
        int comparedHandValue = 0;
        for (Card card:hand.getCards()) {
            comparedHandValue += card.getValue();
        }
        
        // Compare the values of the hands.
        if (thisHandValue < comparedHandValue) {
            return -1;
        } else if (thisHandValue > comparedHandValue) {
            return 1;
        } else {
            return 0;
        }
    }
}
