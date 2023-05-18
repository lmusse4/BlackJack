import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    //instance vars
    private ArrayList<Card> deck;

    //constructor
    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void createFullDeck() {
        // generate cards
        //new
        for(Suit cardSuit : Suit.values()){
            for(Value cardValue : Value.values()){
                //Add new a card
                this.deck.add(new Card(cardSuit, cardValue));
            }
        }

    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public String toString(){
        String cardListOutput = "";
        for(Card aCard : this.deck){
            cardListOutput += "\n-" + aCard.toString();
        }
        return cardListOutput;
    }

    public Card getCard(int i){
        return this.deck.get(i);
    }

    public void removeCard(int i){
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
        this.deck.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.deck.size();

        //put cards into moveto deck
        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard((this.getCard(i)));
        }
        //removing cards from used cards pile
        for(int i =0; i< thisDeckSize; i++){
            this.removeCard(0);
        }

    }

    //Return total value of cards in the deck
    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;

        for(Card aCard : this.deck){
            switch(aCard.getValue()){
                case TWO:  totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE:  totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }

        for (int i = 0; i < aces; i++) {

            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }

        return totalValue;
    }
}