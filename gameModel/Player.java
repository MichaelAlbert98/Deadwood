/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Player.java - This class is used to store and modify attributes relevent to
 * a player.
 * Created: 11/03/2018
 * Revised: 12/06/2018
 */

import java.lang.*;
import java.util.*;

public class Player extends Subject {

  public String image;
  private Boolean isActive;
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
  public static class PlayerMessages {
    public static final String EndTurn = "ENDTURN";
    public static final String StartTurn = "STARTTURN";
    public static final String StatsUpdated = "STATSUPDATED";
    public static final String LocationUpdated = "LOCATIONUPDATED";

  }

  // Player constructor
  public Player(String name) {
    this.name = name;
    this.isActive = false;
    this.rank = 1;
    this.cash = 0;
    this.credits = 0;
    this.currentRole = null;
    this.rehearseTokens = 0;
    this.location = null;
  }

  /* Methods */

  public void startPlayerTurn() {
    this.notifyAllObservers(Player.PlayerMessages.StartTurn);
  }

  public void endPlayerTurn() {
    this.notifyAllObservers(Player.PlayerMessages.EndTurn);
  }

  public void movePlayer(Room destination) {
    if (this.location != null) {
      this.location.removePlayerFromRoom(this);
    }
    Room src = this.location;
    this.location = destination;
    destination.addPlayerToRoom(this);
    notifyAllObservers(Player.PlayerMessages.LocationUpdated);
    notifyAllObservers(Player.PlayerMessages.StatsUpdated);
  }

  public void quietMovePlayer(Room destination) {
    if (this.location != null) {
      this.location.removePlayerFromRoom(this);
    }
    destination.addPlayerToRoom(this);
    this.location = destination;
    notifyAllObservers(Player.PlayerMessages.LocationUpdated);
    notifyAllObservers(Player.PlayerMessages.StatsUpdated);
  }

  public void resetPlayer() {
    this.currentRole = null;
    this.rehearseTokens = 0;
  }

  public String getImage() {
    return (this.image + this.rank + ".png");
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

  /* isActive */
  public void setActive() {
    this.isActive = true;
    this.notifyAllObservers(PlayerMessages.StatsUpdated);
  }

  public void setInactive() {
    this.isActive = false;
    this.notifyAllObservers(PlayerMessages.StatsUpdated);
  }

  public Boolean getIsActive() {
    return this.isActive;
  }

  /* Acting */

  public Role getCurrentRole() {
    return this.currentRole;
  }

  public void setCurrentRole(Role role) {
    this.currentRole = role;
    notifyAllObservers(Player.PlayerMessages.LocationUpdated);
    return;
  }

  public void removeRole() {
    this.currentRole = null;
    notifyAllObservers(Player.PlayerMessages.LocationUpdated);
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
    notifyAllObservers(Player.PlayerMessages.StatsUpdated);
  }

  public void removeCurrencies(int removeCash, int removeCredits) {
    this.cash = this.cash - removeCash;
    this.credits = this.credits - removeCredits;
    notifyAllObservers(Player.PlayerMessages.StatsUpdated);
    notifyAllObservers(Player.PlayerMessages.LocationUpdated);
  }

}

/* Compares two players and returns the value of the difference between the two players' ranks.
   Used to determine who is paid first when wrapping a scene. */
class PlayerRoleComparator implements Comparator<Player> {
  public int compare(Player player1, Player player2) {
    return player1.getCurrentRole().getRank() - player2.getCurrentRole().getRank();
  }
}
