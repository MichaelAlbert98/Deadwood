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

    //Game Attributes
    public Board board;
    private Deck deck;
    public ArrayList<Player> playerList;
    private Player activePlayer;
    private int currentDay;
    private int daysInGame;

    //TEST VARIABLE: (REMOVE LATER)
    public int eoDay = 1;

    
    public Game(int numPlayers, int numDays){
        this.board = new Board();
        this.deck = new Deck();
        this.playerList = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            this.playerList.add(new Player(String.format("Player %d", (i+1))));
        }
        this.activePlayer = playerList.get(0);
        this.currentDay = 0;
        this.daysInGame = numDays;
    }

    public void nextTurn(){
        this.activePlayer.playerTurn();

        //Make the next player in roatation the active player
        int activePlayerIndex = this.playerList.indexOf(this.activePlayer);
        if ((playerList.size()-1) == activePlayerIndex) {
            this.activePlayer = this.playerList.get(0);
        } else {
            this.activePlayer = this.playerList.get(activePlayerIndex+1);
        }

    }

    public void newDay() {
        this.currentDay++;
        this.notifyAllObservers(Game.gameMessages.newDay);
    }

    public boolean isDayOver(){
        if (eoDay < playerList.size()) {
            eoDay++;
            return false;
        } else {
            eoDay = 1;
            return true;
        }
    }

    public boolean isGameOver() {
        if (currentDay < (daysInGame+1)){
            return false;
        } else {
            return true;
        }
    }

    public int getCurrentDay(){
        return this.currentDay;
    }

}
