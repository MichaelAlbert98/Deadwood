
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
  }

  public Player(String name) {
    this.name = name;
    this.rank = 1;
    this.cash = 0;
    this.credits = 0;
    this.currentRole = null;
    this.rehearseTokens = 0;

  }

  public void startPlayerTurn() {
    this.notifyAllObservers(Player.playerMessages.turnStart);
  }

  public void endPlayerTurn() {
    this.notifyAllObservers(Player.playerMessages.turnEnd);
  }

  public void movePlayer(Room destination) {
    this.location = destination;
  }

  public String promptPlayer(String prompt) {
    return prompt;
  }

  // check if upgrade is valid, if so increase rank and decrease relevent
  // attribute.
  public void upgrade(String moneyType, int rank) {

    if (!this.location.getName().equals("Office")) {
      System.out.println(this.name + " is not at Office. Cannot upgrade.");
      return;
    }

    Boolean canUpgrade = validUpgrade(moneyType, rank);

    if (canUpgrade == false) {
      return;
    }
    if (moneyType.equals("cash")) {
      if (rank == 2) {
        this.rank = 2;
        this.cash = this.cash - 4;
        System.out.println(this.name + " upgraded to rank 2 and lost 4 cash.");
      } else if (rank == 3) {
        this.rank = 3;
        this.cash = this.cash - 10;
        System.out.println(this.name + " upgraded to rank 3 and lost 10 cash.");
      } else if (rank == 4) {
        this.rank = 4;
        this.cash = this.cash - 18;
        System.out.println(this.name + " upgraded to rank 4 and lost 18 cash.");
      } else if (rank == 5) {
        this.rank = 5;
        this.cash = this.cash - 28;
        System.out.println(this.name + " upgraded to rank 5 and lost 28 cash.");
      } else {
        this.rank = 6;
        this.cash = this.cash - 40;
        System.out.println(this.name + " upgraded to rank 6 and lost 40 cash.");
      }
    }

    else {
      if (rank == 2) {
        this.rank = 2;
        this.credits = this.credits - 5;
        System.out.println(this.name + " upgraded to rank 2 and lost 5 credits.");
      } else if (rank == 3) {
        this.rank = 3;
        this.credits = this.credits - 10;
        System.out.println(this.name + " upgraded to rank 3 and lost 10 credits.");
      } else if (rank == 4) {
        this.rank = 4;
        this.credits = this.credits - 15;
        System.out.println(this.name + " upgraded to rank 4 and lost 15 credits.");
      } else if (rank == 5) {
        this.rank = 5;
        this.credits = this.credits - 20;
        System.out.println(this.name + " upgraded to rank 5 and lost 20 credits.");
      } else {
        this.rank = 6;
        this.credits = this.credits - 25;
        System.out.println(this.name + " upgraded to rank 6 and lost 25 credits.");
      }
      return;
    }
  }

  // makes sure input is valid and that player can afford upgrade, return false if
  // not.
  private Boolean validUpgrade(String moneyType, int rank) {
    if (!moneyType.equals("cash") || !moneyType.equals("credits")) {
      System.out.println("Input must be cash or credits.");
      return false;
    } else if (rank != 2 || rank != 3 || rank != 4 || rank != 5 || rank != 6) {
      System.out.println("Please input a valid argument for rank.");
      return false;
    } else if (moneyType.equals("cash")) {
      if ((rank == 2 && this.cash < 4) || (rank == 3 && this.cash < 10) || (rank == 4 && this.cash < 18)
          || (rank == 5 && this.cash < 28) || (rank == 6 && this.cash < 40)) {
        System.out.println(this.name + " does not have enough cash to upgrade to this rank.");
        return false;
      }
    } else if (moneyType.equals("credits")) {
      if ((rank == 2 && this.credits < 5) || (rank == 3 && this.credits < 10) || (rank == 4 && this.credits < 15)
          || (rank == 5 && this.credits < 20) || (rank == 6 && this.credits < 25)) {
        System.out.println(this.name + " does not have enough credits to upgrade to this rank.");
        return false;
      }
    }
    return true;
  }

  /* Getters and Setter for Player */

  public String getName() {
    return this.name;
  }

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

  public Room getLocation() {
    return this.location;
  }

  public int getRank() {
    return this.rank;
  }

  public int getCash() {
    return this.cash;
  }

  public int getCredits() {
    return this.credits;
  }

  public void addCash(int value) {
    this.cash = this.cash + value;
    return;
  }

  public void removeCash(int value) {
    this.cash = this.cash - value;
    return;
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
