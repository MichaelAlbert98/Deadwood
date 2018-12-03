/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Player.java - This class is used to store and modify attributes relevent to
 * a player.
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import java.lang.*;
import java.util.*;

public class Player extends Subject {

  public String image;
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
    public static final String leavingRoom = "LEAVINGROOM";
    public static final String locationUpdated = "LOCATIONUPDATED";
    public static final String updatedMoney = "UPDATEDMOENY";
    public static final String tookRole = "TOOKROLE";
  }

  public Player(String name) {
    this.name = name;
    this.rank = 1;
    this.cash = 0;
    this.credits = 0;
    this.currentRole = null;
    this.rehearseTokens = 0;
    this.location = null;

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
    Room src = this.location;
    notifyAllObservers(Player.playerMessages.leavingRoom);
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

  public void resetPlayer() {
    this.currentRole = null;
    this.rehearseTokens = 0;
  }


  /**** Getters and Setters for Player **** /

  /* Name */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
    return;
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

  public void addRehearse() {
     this.rehearseTokens = this.rehearseTokens + 1;
  }

  public void resetRehearse() {
     this.rehearseTokens = 0;
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

  /* Currency Value Modifiers */

  public int getCash() {
    return this.cash;
  }

  public int getCredits() {
    return this.credits;
  }

  public void addCurrencies(int addCash, int addCredits) {
    this.cash = this.cash + addCash;
    this.credits = this.credits + addCredits;
    notifyAllObservers(Player.playerMessages.updatedMoney);
  }

  public void removeCurrencies(int removeCash, int removeCredits) {
    this.cash = this.cash - removeCash;
    this.credits = this.credits - removeCredits;
    notifyAllObservers(Player.playerMessages.updatedMoney);
  }

}

class PlayerRoleComparator implements Comparator<Player> {
  public int compare(Player player1, Player player2) {
    return player1.getCurrentRole().getRank() - player2.getCurrentRole().getRank();
  }
}
