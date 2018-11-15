
/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Player.java
 * 11/13/2018
 */

import java.lang.*;
import java.util.*;

public class Player extends Subject {

  private String name;
  private int rank;
  private int cash;
  private int credits;
  private Role currentRole;
  private int rehearseTokens;
  private Room location;

  /*
   * Player Messages
   *
   * The list of message identifiers that will correspond to message types for the
   * view model to interpret.
   */
  public static class playerMessages {
    public static final String turnStart = "TURNSTART";
    public static final String turnEnd = "TURNEND";
    public static final String locationUpdated = "LOCATIONUPDATED";
  }

  public Player(String name) {
    this.name = name;
    this.rank = 1;
    this.cash = 0;
    this.credits = 0;
    this.currentRole = null;
    this.rehearseTokens = 0;

  }

  /* Methods */

  public void startPlayerTurn() {
    this.notifyAllObservers(Player.playerMessages.turnStart);
  }

  public void endPlayerTurn() {
    this.notifyAllObservers(Player.playerMessages.turnEnd);
  }

  public void movePlayer(Room destination) {
    if (this.location != null) {
      this.location.removePlayerFromRoom(this);
    }
    this.location = destination;
    destination.addPlayerToRoom(this);
    notifyAllObservers(Player.playerMessages.locationUpdated);
  }

  public void quietMovePlayer(Room destination) {
    if (this.location != null) {
      this.location.removePlayerFromRoom(this);
    }
    destination.addPlayerToRoom(this);
    this.location = destination;
  }
  

  /**** Getters and Setters for Player **** /

  /* Name */
  public String getName() {
    return this.name;
  }

  /* Acting */

  public Role getCurrentRole() {
    return this.currentRole;
  }

  public void setCurrentRole(Role role) {
    this.currentRole = role;
    return;
  }

  public void removeRole() {
    this.currentRole = null;
  }

  public int timesRehearsed() {
    return this.rehearseTokens;
  }

  /* Location */

  public Room getLocation() {
    return this.location;
  }
  
  public void setLocation(Room room) {
   this.location = room;
   return;
  }

  /* Rank */

  public int getRank() {
    return this.rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  /* Cash */

  public int getCash() {
    return this.cash;
  }

  public void addCash(int value) {
    this.cash = this.cash + value;
    return;
  }

  public void removeCash(int value) {
    this.cash = this.cash - value;
    return;
  }

  /* Credits */

  public int getCredits() {
    return this.credits;
  }

  public void addCredits(int value) {
    this.credits = this.credits + value;
    return;
  }

  public void removeCredits(int value) {
    this.credits = this.credits - value;
    return;
  }

}

class PlayerRoleComparator implements Comparator<Player> {
  public int compare(Player player1, Player player2) {
    return player1.getCurrentRole().getRank() - player2.getCurrentRole().getRank();
  }
}
