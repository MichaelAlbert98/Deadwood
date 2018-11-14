
/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Board.java
 * 11/13/2018
 */

import java.util.*;

public class Board {

  public static Map<String, Room> nameToRoom = new HashMap<String, Room>();
  public ArrayList<Room> roomList;
  private Deck deck;
  public int scenesRemaining;

  public Board(Deck deck) {
    this.roomList = new ArrayList<Room>();
    this.deck = deck;
  }

  public Boolean isValidMove(Room start, Room end) {
    return true;
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

  public void setScenesRemaining(int value) {
    return;
  }

  public void resetBoard() {
    int roomIter = 0;
    while (roomIter < this.roomList.size()) {
      Room room = this.roomList.get(roomIter);
      room.resetRoom();
      roomIter++;
    }
    this.scenesRemaining = 10;
  }

}
