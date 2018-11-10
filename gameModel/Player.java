/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Player.java
 * Created: 11/02/2018
 * Revised: 11/07/2018
 */

import java.lang.*;

public class Player extends Subject {

   private String name;
   private int rank;
   private int cash;
   private int credits;
   private Role currentRole;
   private int rehearseTokens;
   private Room location;


   /* Player Messages
    *
    * The list of message identifiers that will correspond to
    * message types for the view model to interpret.
    */
   public static class playerMessages {
      public static final String turnStart = "TURNSTART";
      public static final String turnEnd = "TURNEND";
   }

   public Player() {
      this.name = "";
      this.currentRole = null;
      this.rehearseTokens = 0;
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


   /* Getters and Setter for Player */

  public int getRank(){
    return this.rank;
  }
  
  public int getCash(){
    return this.cash;
  }

  public int getCredits(){
    return this.credits;
  }  

   public String getName() {
      return this.name;
   }

   public Role getCurrentRole() {
      return this.currentRole;
   }

   private void setCurrentRole(Role role) {
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

}
