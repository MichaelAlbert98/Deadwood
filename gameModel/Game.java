/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Game.java - This class detects when to end a day and when the game is over.
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import java.util.ArrayList;

public class Game extends Subject {

    public static class gameMessages {
        public static final String newDay = "NEWDAY";
        public static final String dayEnd = "ENDDAY";

    }

    // Game Attributes
    private int currentDay;
    private int daysInGame;
    public Board board;
    public ArrayList<Player> playerList;
    public Player activePlayer;

    /*
     * Game New Day
     *
     * Game Object Constructor
     */
    public Game(int numPlayers, int numDays, Board board) {
        this.currentDay = 0;
        this.daysInGame = numDays;
        this.board = board;

        this.playerList = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            this.playerList.add(new Player(String.format("Player %d", (i + 1))));

        }
        this.activePlayer = playerList.get(0);
    }

    /*
     * Next Player
     *
     * Set the active player to the next player in the cycle.
     */
    public void nextPlayer() {
        int activePlayerIndex = this.playerList.indexOf(this.activePlayer);
        if ((playerList.size() - 1) == activePlayerIndex) {
            this.activePlayer = this.playerList.get(0);
        } else {
            this.activePlayer = this.playerList.get(activePlayerIndex + 1);
        }
    }

    /*
     * Game New Day
     *
     * This method iterates the day counter, resets the board for the next day, and
     * then tells the view that the day has been updated.
     */
    public void newDay() {
        this.currentDay++;
        this.board.resetBoard();
        if (this.currentDay <= this.daysInGame) {
            this.notifyAllObservers(Game.gameMessages.newDay);
        }
        // Move all players back to starting room
        for (int i = 0; i < this.playerList.size(); i++) {
            this.playerList.get(i).quietMovePlayer(Board.nameToRoom.get("Trailer"));
        }
    }

    /*
     * Is Day Over
     *
     * This method checkes the board to see if enough scenes have been completed to
     * warrent the start of the next day.
     */
    public boolean isDayOver() {
        if (this.board.getNumScenesRemaining() == 0) {
            return true;
        } else if (this.currentDay == 0) {
            return true;
        }
        return false;
    }

    /*
     * Is Game Over
     *
     * This method is called after each game loop iteration and makes sure the last
     * day has not ended.
     */
    public boolean isGameOver() {
        if (currentDay < (daysInGame + 1)) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * calculateWinner
     *
     * This method is called when isGameOver returns true. It calculates a
     * score for each player, then returns the name of the player with the
     * largest score.
     */
    public String calculateWinner() {
        int topScore = 0;
        String name = "";
        for (int i=0;i<this.playerList.size();i++) {
          int newScore = this.playerList.get(i).getRank()*5 +
                      this.playerList.get(i).getCash() +
                      this.playerList.get(i).getCredits();
          if (newScore > topScore) {
            topScore = newScore;
            name = this.playerList.get(i).getName();
          }
        }
        return name;
    }

    /* Getter and Setter Methods */

    /* Get Current Day */
    public int getCurrentDay() {
        return this.currentDay;
    }

}
