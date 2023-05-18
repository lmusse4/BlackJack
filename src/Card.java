import java.util.Random;

public class Card {

    private Suit suit;
    private Value value;

    // create arrays for values and suits - need these to generate random cards
    private Value[] values = Value.values();
    private Random randomValues = new Random();
    private Suit[] suits = Suit.values();
    private Random randomSuits = new Random();


    //Constructor
    public Card(Suit suit, Value value) {
        this.value = value;
        this.suit = suit;
    }

    // random card generated
    public Card() {
        this.suit = getRandomSuit();
        this.value = getRandomValue();
    }

    //prints out the value and suit
    public String toString() {
        return this.suit.toString() + "-" + this.value.toString();
    }

    public Value getValue() {
        return this.value;
    }

    public Value getRandomValue() {
        return values[randomValues.nextInt(values.length)];
    }
//
    public Suit getRandomSuit() {
        return suits[randomSuits.nextInt(values.length)];
    }
}