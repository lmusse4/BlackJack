import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

//        System.out.println(playingDeck);

        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        double playerMoney = 1000.00;
        Scanner userInput = new Scanner(System.in);

        // Game loops
        while (playerMoney > 0) {
            //Continue
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You can't bet more than you have. Bye!");
                break;
            }
            //Start Dealing
            //Player gets two cards
            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            //Dealer gets two cards
            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);
            boolean endRound = false;

            while (true) {
                //printing players hand
                System.out.println("Your hand:");
                System.out.println(playerHand.toString());
                System.out.println("Your hand's value at: " + playerHand.cardsValue());

                //Display dealer hand
                System.out.println("Dealer Hand: " + dealerHand.getCard(0).toString() + " and [Hidden from player]");

                //Players choice
                System.out.println("Please make a choice. Enter 1 to Hit or 2 to Stand.");
                int input = userInput.nextInt();

                //Hit
                if (input == 1) {
                    playerHand.draw(playingDeck);
                    System.out.println("You drew : " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                    //bust if over 21
                    if (playerHand.cardsValue() > 21) {
                        System.out.println("Your hand's value is now: " + playerHand.cardsValue() + ". Busted.");
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (input == 2) {
                    break;
                }
            }
            //Reveal Dealer Cards
            System.out.println("Dealer Cards: " + dealerHand.toString());
            //Dealer win
            if((dealerHand.cardsValue() > playerHand.cardsValue()) && endRound == false) {
                System.out.println("Dealer won.");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Dealer draws at 16, stands at 17
            while ((dealerHand.cardsValue() < 17) && endRound == false){
                dealerHand.draw(playingDeck);
                System.out.println("Dealer Drew: " + dealerHand.getCard(dealerHand.deckSize()-1).toString());
            }

            //Print dealer's total
            System.out.println("The value of the Dealer's hand is now: " + dealerHand.cardsValue());

            //dealer bust
            if((dealerHand.cardsValue() > 21) && endRound == false){
                System.out.println("Dealer busts! You win.");
                playerMoney += playerBet;
                endRound = true;
            }

            //Tie
            if((playerHand.cardsValue() == dealerHand.cardsValue()) && endRound == false){
                System.out.println("Push or Tie");
                endRound = true;
            }

            //player win
            if((playerHand.cardsValue() > dealerHand.cardsValue()) && endRound == false){
                System.out.println("You won! :) ");
                playerMoney += playerBet;
                endRound = true;
            }
            else if (endRound == false){
                System.out.println("You lose the hand");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerHand.moveAllToDeck(playingDeck);
            dealerHand.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");

        }
        System.out.println("Game over! You don't have enough money");
    }
}