/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Game.java
 * 11/2/11
 */

import java.util.ArrayList;

public class Game extends Subject {

    
    public static class gameMessages {
        public static final String newDay = "NEWDAY";
    }

    public int testOneDay;

    //Game Attributes
    public Board board;
    private Deck deck;
    public ArrayList<Player> playerList;
    private Player activePlayer;
    private int currentDay;


    public Game(int numPlayers, int numDays){
        this.board = new Board();
        this.deck = new Deck();
        this.playerList = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            this.playerList.add(new Player(String.format("Player %d", (i+1))));
        }
        this.activePlayer = playerList.get(0);
        this.currentDay = 0;

        //REMOVE THIS LATER
        this.testOneDay = 0;
    }

    public void nextTurn(){
        this.activePlayer.playerTurn();
    }

    public void newDay() {
        this.currentDay++;
        this.notifyAllObservers(Game.gameMessages.newDay);
    }

    public boolean isDayOver(){
      return false;
    }

    public boolean isGameOver() {
        if (testOneDay == 0){
            testOneDay++;
            return false;
        } else {
            return true;
        }
    }

    public int getCurrentDay(){
        return this.currentDay;
    }

}
