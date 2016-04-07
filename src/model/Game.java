package model;

import ai.AI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import static model.CribbageConstants.FINISH;
import static model.CribbageConstants.PEGGING_MAX;

/**
 * Model for a game of cribbage between two players.
 *
 * Added the AIs to make playing decisions.
 *
 * Enhance to automatically play a series of games, then provide a breakdown of
 * how well the two AIs did.
 *
 * @author jim
 */
public class Game implements CribbageConstants {

    private Player player1;         // Our first player
    private Player player2;         // Our second player
    private boolean player1Deals;   // who is dealing?
    private AI ai1 = null;          // First player's AI
    private AI ai2 = null;          // Second player's AI
    private int handCount = 0;      // # of hands played
    private ArrayList<Card> deck;   // our deck of cards
    private Random generator;       // we'll need this for lots
    private Hand crib;              // the current crib
    private Card starter;           // the starter card turned over
    public static final int GORY_DETAILS = 0;   // show everything imaginable
    public static final int SCORING = 0;        // show scoring & highlights
    public static final int HIGHLIGHTS = 2;     // only show the highlights
    public static final int SUMMARY = 3;     // only show the game summaries
    public static final int LOG_LEVEL = SUMMARY; // vary this for more or less output
    private static final int GAMES_TO_PLAY = 200;    // # of games in a match
    private int gamesPlayed = 0;    // how many of these have been played
    private int player1Wins = 0;    // how many times did AI #1 win?

    //--------------- Constructors --------------------
    //
    /**
     * No argument constructor. Assume default players, and that the first one
     * deals.
     */
    public Game() {
        player1 = new Player();
        player2 = new Player();
        player1Deals = true;
        generator = new Random();
        deck = null;
    }

    /**
     * Convenience constructor. Specifiy the opponents, and assume that the
     * first player deals.
     *
     * @param p1
     * @param p2
     */
    public Game(Player p1, Player p2) {
        this();
        player1 = p1;
        player2 = p2;
        player1Deals = true;
    }

    /**
     * Let's play a game of cribbage!
     */
    public void play() {
        // opening presentation
        logln(HIGHLIGHTS, "\n*****************************");
        logln(HIGHLIGHTS, "CRIBBAGE");
        logln(HIGHLIGHTS, "*****************************\n");
        logln(HIGHLIGHTS, "First player: " + player1.getName());
        logln(HIGHLIGHTS, "Second player: " + player2.getName() + "\n");

        while (gamesPlayed < GAMES_TO_PLAY) {
            // make sure the players start at the beginning, after an earlier game
            player1.setPosition(0);
            player2.setPosition(0);
            handCount = 0;

            // play hands until we have a winner
            while (winner() == null && handCount < FINISH) {

                // play a hand
                handCount++;
                logln(HIGHLIGHTS, "---------------- Hand " + handCount + " ----------------");
                shuffleDeck();                  // shuffle the deck
                dealHands();                    // deal two hands of six cards
                discards();                     // ask for discards for the crib

                turnover();                     // turn over a card
                if (winner() != null) {
                    break;
                }

                doThePegging();                 // do the pegging
                if (winner() != null) {
                    break;
                }

                countTheHands();                // scorePoints the hands            
                if (winner() != null) {
                    break;
                }
                player1Deals = !player1Deals;   // turn over the deal
            }

            // we are done
            logln(HIGHLIGHTS, "");
            if (winner() != null) {
                logln(HIGHLIGHTS, "Congratulations, " + winner().getName() + "!");
            } else {
                logln(HIGHLIGHTS, "Enough already!!!");
            }
            logln(HIGHLIGHTS, "\n" + player1.getName() + " scored " + player1.getPosition());
            logln(HIGHLIGHTS, player2.getName() + " scored " + player2.getPosition());
            logln(SUMMARY, player1.getName() + " @ " + player1.getPosition() + " vs " + player2.getName() + " @ " + player2.getPosition());
            
            if (player1 == winner()) {
                player1Wins++;
            }
            gamesPlayed++;
        }

        if (GAMES_TO_PLAY > 1) {
            System.out.println("\n--------------\n" + player1.getName() + " won " + player1Wins + " of " + GAMES_TO_PLAY
                    + " games.");
        }
    }

    /**
     * Shuffle the deck
     */
    private void shuffleDeck() {
        // start with a fresh deck
        deck = new ArrayList<Card>(DECK_SIZE);
        // generate the 52 cards
        for (String rank : allRanks) {
            for (String suit : allSuits) {
                deck.add(new Card(rank + suit));
            }
        }
        // and finally, suffle the deck
        Collections.shuffle(deck);
    }

    /**
     * Deal two hands of six cards
     */
    private void dealHands() {
        // show who is dealing
        logln(GORY_DETAILS, "Dealing... " + (player1Deals ? player1.getName() : player2.getName()));

        // deal to player 1
        player1.setHand(deal1Hand());
        ai1.setHand(player1.getHand(), player1Deals);
        logln(HIGHLIGHTS, player1.getName() + " hand: " + player1.getHand() + " @ " + player1.getPosition());

        // deal to player 2
        player2.setHand(deal1Hand());
        ai2.setHand(player2.getHand(), !player1Deals);
        logln(HIGHLIGHTS, player2.getName() + " hand: " + player2.getHand() + " @ " + player2.getPosition());
    }

    /**
     * Deal a hand.
     *
     * Yes, this is not per "human" rules, where cards are dealt to players
     * alternately. We trust that the random number generator will do its thing
     * fairly enough.
     *
     * @return
     */
    private Hand deal1Hand() {
        // Start with an empty new hand
        Hand hand = new Hand();
        for (int i = 0; i < HAND_SIZE; i++) {
            // pick one of the cards left in the deck
            int which = generator.nextInt(deck.size());
            Card c = deck.get(which);
            // add it to the hand
            hand.addCard(c);
            // and remove it from the deck
            deck.remove(c);
        }
        return hand;
    }

    /**
     * Ask the AIs for the cards to go into the crib
     */
    private void discards() {
        logln(GORY_DETAILS, "Discarding to crib...");
        crib = new Hand();

        // do the hard work
        crib.addCard(ai1.discard());
        crib.addCard(ai1.discard());
        crib.addCard(ai2.discard());
        crib.addCard(ai2.discard());

        // update the player hands
        player1.setHand(ai1.getHand());
        player2.setHand(ai2.getHand());

        // and make sure the AIs have their own hand!
        // this apparent code redundancy is because the original Player class was
        // not required to make a copy of a hand it was given.
        // If we did not do this, then the starter card coulw be added to the AI's
        // hand, giving it an incorrect hand with 5 cards to choose from.
        ai1.setHand(player1.getHand(), player1Deals);
        ai2.setHand(player2.getHand(), player1Deals);

        // what do we have now?
        logln(GORY_DETAILS, player1.getName() + " hand: " + player1.getHand());
        logln(GORY_DETAILS, player2.getName() + " hand: " + player2.getHand());
        logln(GORY_DETAILS, "Crib: " + crib);
    }

    /**
     * Turn over a card to use as starter
     */
    private void turnover() {
        int which = generator.nextInt(deck.size());

        // determine the starter
        starter = deck.get(which);
        deck.remove(starter);

        // add it to both player's hands
        player1.getHand().addCard(starter);
        player2.getHand().addCard(starter);
        crib.addCard(starter);

        // status report
        logln(GORY_DETAILS, "Turned over... " + starter);
        if (starter.getRank().equals(JACK)) {
            scorePoints(HIS_NOBS);
            logln(SCORING, "DEaler gets " + HIS_NOBS + " for turning over a Jack");
        }
        logln(GORY_DETAILS, player1.getName() + " hand: " + player1.getHand());
        logln(GORY_DETAILS, player2.getName() + " hand: " + player2.getHand());
        logln(GORY_DETAILS, player1.getName() + " AI hand: " + ai1.getHand());
        logln(GORY_DETAILS, player2.getName() + " AI hand: " + ai2.getHand());
    }

    /**
     * Handle the pegging round.
     */
    private void doThePegging() {
        logln(GORY_DETAILS, "Pegging...");
        boolean whoNext = player1Deals;
        Hand sequence = new Hand();
        boolean keepGoing = true;   // more sequences to come
        int counting = 0;

        // repeat until neither has cards left
        while (keepGoing) {
            boolean sequencing = true;
            int passesInARow = 0;

            // handle a sequence, i.e. cards add up to 31 or both players pass
            while (sequencing) {
                AI nextMover = whoNext ? ai1 : ai2; // whose turn is it to play?
                Player nextPlayer = whoNext ? player1 : player2;
                Card card = nextMover.peggingPlay(sequence); // and they play ... ?
                log(GORY_DETAILS, nextPlayer.getName() + " plays " + card + " // ");

                // handle a card played
                if (card != null) {
                    // do we have an illegal AI?
                    if (countThem(sequence) + card.value() > 31) {
                        System.out.println(nextPlayer.getName() + " has played a card pegging which puts the total over 31.");
                        System.out.println("PLAYER IS DISQUALIFIED AND FORFEITS THE GAME!!");
                        return;
                    }

                    // looks legit so far
                    passesInARow = 0;
                    sequence.addCard(card);
                    showTheSequence(sequence);

                    // do we have 15?
                    if (countThem(sequence) == 15) {
                        scorePoints(2, nextPlayer);
                        logln(SCORING, " 15 for two // ");
                    }

                    // pegging pairs?
                    counting = scorePoints(pairThem(sequence), nextPlayer);
                    if (counting > 0) {
                        logln(SCORING, " " + counting + " in pairs // ");
                    }

                    // pegging run?                    
                    counting = scorePoints(runThem(sequence), nextPlayer);
                    if (counting > 0) {
                        logln(SCORING, " " + counting + " in a run // ");
                    }
                } else {
                    // Did the AI renege?
                    int sofar = countThem(sequence);
                    int needed = PEGGING_MAX - sofar;
                    for (Card hmmm : nextMover.getHand().getCards()) {
                        if (hmmm.value() <= needed) {
                            System.out.println(nextPlayer.getName() + " has reneged, passing when they could have played.");
                            System.out.println("PLAYER IS DISQUALIFIED AND FORFEITS THE GAME!!");
                            return;
                        }
                    }

                    // looks legit
                    passesInARow++;
                    logln(SCORING, nextPlayer.getName() + " passed.");
                }
                // if the cards add up to 31, we are done this sequence and last player gets 2 points
                if (countThem(sequence) == 31) {
                    scorePoints(2, nextPlayer);
                    logln(SCORING, " 31 for two // ");
                    break;
                }
                // if we got two passes in a row, we are done this sequence and last player to peg gets 1 point
                if (passesInARow == 2) {
                    scorePoints(1, nextPlayer);
                    logln(SCORING, " 1 for the go // ");
                    break;
                }
                // otherwise, pegging passes to the next player, and we continue the pegging sequence
                whoNext = !whoNext;

                // check for a winner
                if (winner() != null) {
                    break;
                }
            }
            // check for a winner
            if (winner() != null) {
                break;
            }
            // make sure there is still a sequence to come
            keepGoing = (ai1.getHand().getCards().size() + ai2.getHand().getCards().size()) > 0;
            sequence = new Hand();
        }
    }

    /**
     * Show the sequence of cards played pegging
     */
    private void showTheSequence(Hand sequence) {
        String result = "";
        for (Card card : sequence.getCards()) {
            result += card.toString() + " ";
        }
        logln(GORY_DETAILS, "... " + result + " = " + countThem(sequence));
    }

    /**
     * Work method to add the values of the cards in a sequence
     */
    private int countThem(Hand sequence) {
        int answer = 0;
        for (Card card : sequence.getCards()) {
            answer += card.value();
        }
        return answer;
    }

    /**
     * Work method to determine if the last card(s) played make any pairs
     */
    public int pairThem(Hand sequence) {
        int result = 0;
        ArrayList<Card> cards = sequence.getCards();
        int limit = cards.size();

        // we could have 4 of a kind            
        if (limit >= 4) {
            String matcher = cards.get(limit - 1).getRank();
            if (matcher.equals(cards.get(limit - 2).getRank())
                    && matcher.equals(cards.get(limit - 3).getRank())
                    && matcher.equals(cards.get(limit - 4).getRank())) {
                result = 12;
            }
        }

        // we could have 3 of a kind
        if (result == 0 && limit >= 3) {
            String matcher = cards.get(limit - 1).getRank();
            if (matcher.equals(cards.get(limit - 2).getRank())
                    && matcher.equals(cards.get(limit - 3).getRank())) {
                result = 6;
            }
        }

        // we could have a pair
        if (result == 0 && limit >= 2) {
            String matcher = cards.get(limit - 1).getRank();
            if (matcher.equals(cards.get(limit - 2).getRank())) {
                result = 2;
            }
        }
        return result;
    }

    /**
     * Work method to determine if the last card(s) played make a run.
     *
     * When pegging, we could have a run of up to 7! (A-2-3-4-5-6-7, adding up
     * to 28).
     */
    public int runThem(Hand sequence) {
        int result = 0;
        ArrayList<Card> cards = sequence.getCards();
        int limit = cards.size();

        // as long as at least 3 cards have been played...
        if (limit >= 3) {
            // if the last "n" cards form a run of "n", we have our answer
            for (int n = limit; n > 2; n--) {
                Hand checkit = new Hand();
                // get the last "n" cards
                for (int index = 0; index < n; index++) {
                    checkit.addCard(cards.get(limit - 1 - index));
                }
                // see if they form a run
                if (Scoring.runs(checkit) == n) {
                    result = n;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Score the hands.
     *
     * If we get a winner at any point, we are done. The pone (non-dealer) gets
     * counted first, then the dealer.
     */
    private void countTheHands() {
        logln(GORY_DETAILS, "Counting the hands...");
        Player dealer = player1Deals ? player1 : player2;
        Player pone = player1Deals ? player2 : player1;

        // pone scores first
        logln(SCORING, pone.getName() + " (pone) scores "
                + scorePoints(Scoring.score(pone.getHand(), false), pone));
        if (winner() != null) {
            return;
        }

        // then the dealer
        logln(SCORING, dealer.getName() + " (dealer) scores "
                + scorePoints(Scoring.score(dealer.getHand(), true), dealer));
        if (winner() != null) {
            return;
        }

        // and the dealer's crib
        logln(SCORING, dealer.getName() + " (crib) scores "
                + scorePoints(Scoring.score(crib, true), dealer));
    }

    /**
     * Update the dealer's scorePoints.
     *
     * @param howMuch How many points to scorePoints for the dealer
     */
    private int scorePoints(int howMuch) {
        if (player1Deals) {
            scorePoints(howMuch, player1);
        } else {
            scorePoints(howMuch, player2);
        }
        return howMuch;
    }

    /**
     * Update a player's scorePoints.
     *
     * @param howMuch How many points to scorePoints for the designated player
     * @param player Which player gets the points
     */
    private int scorePoints(int howMuch, Player player) {
        player.setPosition(player.getPosition() + howMuch);
        // we can only go to the finish peg
        if (player.getPosition() > FINISH) {
            player.setPosition(FINISH);
        }
        return howMuch;
    }

    /**
     * Determine who, if either, won
     */
    public Player winner() {
        if (player1.getPosition() >= FINISH) {
            return player1;
        }
        if (player2.getPosition() >= FINISH) {
            return player2;
        }
        return null;
    }
    //--------------- Field accessors & mutators --------------------
    //

    /**
     * Player1 accessor.
     *
     * @return the first player
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Player1 mutator.
     *
     * @param player1 the player to set
     */
    public void setPlayer1(Player player1) {
        if (player1 != null) {
            this.player1 = player1;
        }
    }

    /**
     * Player2 accessor.
     *
     * @return the second player
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Player2 mutator.
     *
     * @param player2 the player to set
     */
    public void setPlayer2(Player player2) {
        if (player2 != null) {
            this.player2 = player2;
        }
    }

    /**
     * player2Deals accessor.
     *
     * @return true if player 1 is dealing, false otherwise.
     */
    public boolean isPlayer1Deals() {
        return player1Deals;
    }

    /**
     * player1Deals mutator.
     *
     * @param player1Deals the player1Deals to set
     */
    public void setPlayer1Deals(boolean player1Deals) {
        this.player1Deals = player1Deals;
    }

    /**
     * Get the first player's AI.
     *
     * @return the ai1
     */
    public AI getAi1() {
        return ai1;
    }

    /**
     * Set the first player's AI.
     *
     * @param ai1 the ai1 to set
     */
    public void setAi1(AI ai1) {
        this.ai1 = ai1;
        ai1.setPlayer(player1);
    }

    /**
     * Get the second player's AI.
     *
     * @return the ai2
     */
    public AI getAi2() {
        return ai2;
    }

    /**
     * Set the second player's AI.
     *
     * @param ai2 the ai2 to set
     */
    public void setAi2(AI ai2) {
        this.ai2 = ai2;
        ai2.setPlayer(player2);
    }

    //--------------- Utility methods --------------------
    //
    /**
     * Provide a text representation of a player.
     *
     * @return
     */
    public String toString() {
        return "Game between " + player1.getName() + " (" + player1.getPosition() + ") and " + player2.getName() + " (" + player2.getPosition() + ")";
    }

    /**
     * Level-controlled logging output
     */
    public void log(int level, String message) {
        if (level >= LOG_LEVEL) {
            System.out.print(message);
        }
    }

    /**
     * Level-controlled logging output
     */
    public void logln(int level, String message) {
        if (level >= LOG_LEVEL) {
            System.out.println(message);
        }
    }
}
