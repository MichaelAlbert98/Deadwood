/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Board.java - This class stores attributes pertaining to the game board
 * and also resets the board when a day ends.
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import java.util.*;

public class Board {

  private static Board boardInstance;
  public static Map<String, Room> nameToRoom = new HashMap<String, Room>();
  public ArrayList<Room> roomList;
  private Deck deck;
  private int scenesRemaining;

  private Board(Deck deck) {
    this.roomList = new ArrayList<Room>();
    this.deck = deck;
  }

  public static Board getBoard(Deck deck) {
    if (boardInstance == null) {
      boardInstance = new Board(deck);
      return boardInstance;
    }
    return boardInstance;
  }

  public int getNumScenesRemaining() {
    return this.scenesRemaining;
  }

  public void printNameToRoomKeys() {
    System.out.print("Keys: ");
    for (Object o : nameToRoom.keySet().toArray()) {
      String s = String.valueOf(o);
      System.out.printf("'%s' ", s);
    }
    System.out.println();
  }

  public void decrementScenesRemaining() {
    this.scenesRemaining = scenesRemaining - 1;
    return;
  }

  public void resetBoard() {
    int roomIter = 0;
    while (roomIter < this.roomList.size()) {
      Room room = this.roomList.get(roomIter);
      room.resetRoom(this.deck);
      roomIter++;
    }
    this.scenesRemaining = 10;
  }

  public ArrayList<Room> getRoomList() {
    return this.roomList;
  }

  public Deck getDeck() {
    return this.deck;
  }

}