
import ai.AI;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import model.Card;
import model.CribbageConstants;
import model.Game;
import model.Hand;
import model.Player;
import model.Scoring;

/**
 * Driver for ACIT1515 assignment 2, fall 2013.
 *
 * Make a few of the domain objects and report them.
 *
 * @author jim
 */
public class Cribbage implements CribbageConstants {

    Game game = null;   // THE game
    public final static String AI_FOLDER = "./build/classes/ai";

    /**
     * Application entry point.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cribbage cribbage = new Cribbage();

        // uncomment only one of the following
        cribbage.play();
//        game.dummy();                
    }

    /**
     * Default constructor. Just setup an empty game
     */
    public Cribbage() {
        game = new Game();
    }

    /**
     * Play a game of cribbage
     */
    public void play() {
        System.out.println("ACIT1515 Fall 2013, Assignment 3");
        System.out.println("");
        System.out.println("How about a nice game of crib?");

        // Setup the game
        setupGame();

        // and away we go
        game.play();

    }

    /**
     * Setup our game.
     *
     * Choose our two players. Determine which AIs are present, and present a
     * menu of them to select from. Create appropriate player/agent combos.
     */
    public void setupGame() {

        // what do we have for AIs?
        File[] candidates = null;
        try {
            File folder = new File(AI_FOLDER);
            candidates = folder.listFiles();
        } catch (Exception ioe) {
            System.out.println("Unable to choose players ... " + ioe.getMessage());
        }

        // how many of these are real?
        ArrayList<String> playerNames = new ArrayList<String>();
        for (File who : candidates) {
            String name = who.getName();
            int pos = name.indexOf(".class");
            if (pos > 0) {
                // we are only interested in AI classes
                name = name.substring(0, pos);
                try {
                    AI x = (AI) Class.forName("ai." + name).newInstance();
                    playerNames.add(name);
                } catch (Exception e) {
                    // that didn't work; ignore the candidate
                }

            }
        }

        // bail if no opponents
        if (playerNames.size() < 2) {
            System.out.println("Not enough players :(");
            System.exit(0);
        }

        // order them
        Collections.sort(playerNames);

        // and present them as a menu
        System.out.println("Available players:");
        
        
        for (int index = 0; index < playerNames.size(); index++) {
            System.out.println("" + index + ". " + playerNames.get(index));
        }

        // who is playing? Make assumptions for bogus input
        Scanner gimme = new Scanner(System.in);
        System.out.println("");
        System.out.print("Index of player 1? ");
        int p1 = gimme.nextInt();
        if (p1 < 0 || p1 >= playerNames.size()) {
            p1 = 0;
        }
        System.out.print("Index of player 2? ");
        int p2 = gimme.nextInt();
        if (p2 < 0 || p2 >= playerNames.size() || p1 == p2) {
            p2 = (p1 + 1) % playerNames.size();
        }

        try {
            // For each contestant, make a Player and then an AI
            String name = playerNames.get(p1);
            Player player = new Player(name);
            AI ai = (AI) Class.forName("ai." + name).newInstance();
            game.setPlayer1(player);
            game.setAi1(ai);

            // and the second player
            name = playerNames.get(p2);
            player = new Player(name);
            ai = (AI) Class.forName("ai." + name).newInstance();
            game.setPlayer2(player);
            game.setAi2(ai);
        } catch (Exception e) {
            System.out.println("Something went terribly wrong trying to set up our game :(\n");
            e.printStackTrace();
        }
    }

    /**
     * Dummy player & hand, for testing
     */
    public void dummy() {

        // Make some cards
        Card c1 = new Card("4H");
        Card c2 = new Card("2C");
        Card c3 = new Card("JS");
        Card c4 = new Card("3H");
        Card c5 = new Card("3D");
        Card c6 = new Card("5S");
        System.out.println("Our first six cards: " + c1 + ", " + c2 + ", " + c3 + ", " + c4 + ", " + c5 + ", " + c6);

        // Make some more cards
        Card c11 = new Card("7H");
        Card c12 = new Card("8C");
        Card c13 = new Card("QS");
        Card c14 = new Card("AH");
        Card c15 = new Card("4D");
        Card c16 = new Card("4S");
        System.out.println("Our second six cards: " + c11 + ", " + c12 + ", " + c13 + ", " + c14 + ", " + c15 + ", " + c16);


        // Let's place the cards into two hands, with turned card
        Hand hand1 = new Hand(c1, c2, c3, c4, c5);
        Hand hand2 = new Hand(c11, c12, c13, c14, c5);
        System.out.println("First hand is " + hand1);
        System.out.println("Second hand is " + hand2);

        // Make our players
        Player player1 = new Player("Jim");
        Player player2 = new Player("George");
        player1.setHand(hand1);
        player2.setHand(hand2);
        System.out.println("First player is " + player1);
        System.out.println("Second player is " + player2);

        // finally, combine these two in a game
        Game game = new Game(player1, player2);

        int move1 = Scoring.score(hand1, true);
        int move2 = Scoring.score(hand2, false);

        // some scoring...
        System.out.println("First player's hand is worth " + move1);
        System.out.println("Second player's hand is worth " + move2);

        player1.setPosition(move1);
        player2.setPosition(move2);
        System.out.println("Summary: " + game);
    }
}
