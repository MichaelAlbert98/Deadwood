/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Player.java
 * 11/2/11
 */

import java.lang.*;

public class Player extends Subject {

   private String name;
   private Boolean hasRole;
   private Role currentRole;
   private int rehearseTokens;
   private Banker banker;
   private Room location;

   //The list of messages that will correspond
   // to message types for the view model to interpret.
   public static class playerMessages {
     public static final String turnStart = "TURNSTART";
   }

   public Player() {
      this.name = "";
      this.hasRole = false;
      this.currentRole = null;
      this.rehearseTokens = 0;
      this.banker = new Banker();
   }

   public Player(String name) {
      this.name = name;
      this.hasRole = false;
      this.currentRole = null;
      this.rehearseTokens = 0;
   }

   public void playerTurn() {
     this.notifyAllObservers(Player.playerMessages.turnStart);
   }


   public String promptPlayer(String prompt) {
      return prompt;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
      return;
   }

   public Boolean hasRole() {
      return this.hasRole;
   }

   public Role getCurrentRole() {
      return this.currentRole;
   }

   public void setCurrentRole(Role role) {
      this.currentRole = role;
      //This is how the textView will be told to display messages
      //this.notifyAllObservers(Player.observerMessages.SetRole.ordinal());
      return;
   }

   public void removeRole() {
      this.currentRole = null;
   }

   public int getTokens() {
      return this.rehearseTokens;
   }

   public void setTokens(int value) {
      this.rehearseTokens = value;
      return;
   }

}
