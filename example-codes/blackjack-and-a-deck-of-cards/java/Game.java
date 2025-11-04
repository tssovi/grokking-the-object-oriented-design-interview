package blackjack;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Player player;
    private Dealer dealer;
    private static final int MAX_NUM_OF_DECKS = 3;
    private Shoe shoe;

    public Game(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
        this.shoe = new Shoe(MAX_NUM_OF_DECKS);
    }

    public void playAction(String action, Hand hand) {
        switch (action.toLowerCase()) {
            case "hit":
                hit(hand);
                break;
            case "split":
                split(hand);
                break;
            case "stand pat":
                // do nothing
                break;
            case "stand":
                stand();
                break;
            default:
                System.out.println("Invalid move");
        }
    }

    public void hit(Hand hand) {
        hand.addCard(shoe.dealCard());
    }

    public void stand() {
        int dealerScore = dealer.getTotalScore();
        int playerScore = player.getTotalScore();
        List<Hand> hands = player.getHands();
        for (Hand hand : hands) {
            int bestScore = hand.resolveScore();
            if (bestScore == 21) {
                // blackjack, pay 3:2 of the bet
                System.out.println("Blackjack! Player wins 3:2");
            } else if (bestScore > dealerScore) {
                // pay player equal to the bet
                System.out.println("Player wins!");
            } else if (bestScore < dealerScore) {
                // collect the bet from the player
                System.out.println("Dealer wins!");
            } else { // tie
                // bet goes back to player
                System.out.println("Push! Tie game.");
            }
        }
    }

    public void split(Hand hand) {
        List<BlackjackCard> cards = hand.getCards();
        player.addHand(new Hand(cards.get(0), shoe.dealCard()));
        player.addHand(new Hand(cards.get(1), shoe.dealCard()));
        player.removeHand(hand);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your bet: ");
        double bet = scanner.nextDouble();
        player.placeBet(bet);

        Hand playerHand = new Hand(shoe.dealCard(), shoe.dealCard());
        player.addHand(playerHand);

        Hand dealerHand = new Hand(shoe.dealCard(), shoe.dealCard());
        dealer.addHand(dealerHand);

        while (true) {
            List<Hand> hands = player.getHands();
            for (Hand hand : hands) {
                String action = getUserAction(hand);
                playAction(action, hand);
                if (action.equals("stand")) {
                    break;
                }
            }
            break;
        }
        scanner.close();
    }

    private static double getBetFromUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your bet: ");
        double bet = scanner.nextDouble();
        return bet;
    }

    private static String getUserAction(Hand hand) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your hand score: " + hand.resolveScore());
        System.out.print("Enter action (hit/split/stand pat/stand): ");
        String action = scanner.nextLine();
        return action;
    }

    public static void main(String[] args) {
        Player player = new Player();
        Dealer dealer = new Dealer();
        Game game = new Game(player, dealer);
        game.start();
    }
}

