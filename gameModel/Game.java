/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Game.java
 * 11/2/11
 */

import java.util.ArrayList;

public class Game extends Subject {

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
            this.playerList.add(new Player());
        }
        this.activePlayer = playerList.get(0);
        this.currentDay = 0;
    }

    public void newDay() {

    }

    public boolean isDayOver(){
      return false;
    }



}
