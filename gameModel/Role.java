/* CSCI 345 - Object Oriented Design - Deadwood
 * Michael Albert and Ryan Lingg
 * Role.java
 * Created: 11/01/2018
 * Revised: 11/04/2018
 */ 

public class Role {

   private String name;
   private Boolean isCardRole;
   private Boolean isTaken;
   private int requiredRank;

   public Role() {
      this.name = "";
      this.isCardRole = false;
      this.isTaken = false;
      this.requiredRank = 0;
   }

   public Role(String name, Boolean onCard, int rank) {
      this.name = name;
      this.isCardRole = onCard;
      this.isTaken = false;
      this.requiredRank = rank;
   }

   public String getName() {
     return this.name;
   }

   public void setName(String name) {
     this.name = name;
     return;
   }

   public Boolean getOnCard() {
      return this.isCardRole;
   }

   public void setOnCard(Boolean truth) {
     this.isCardRole = truth;
     return;
   }

   public int getRank() {
      return this.requiredRank;
   }

   public void setRank(int rank) {
     this.requiredRank = rank;
     return;
   }

   public Boolean getIsTaken() {
      return this.isTaken;
   }

   public void setIsTaken(Boolean truth) {
      this.isTaken = truth;
   }
}
