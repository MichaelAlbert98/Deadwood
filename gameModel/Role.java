/* CSCI 345 - Object Oriented Design - Deadwood
 * Michael Albert and Ryan Lingg
 * Role.java - This class is designed to hold  and change the attributes of a
 * role that players can take.
 * Created: 11/01/2018
 * Revised: 11/08/2018
 */

public class Role {

   private String name;
   private Boolean isCardRole;
   private Boolean isTaken;
   private int requiredRank;
   private int[] xyhw;
   private String line;

   public Role() {
      this.name = "";
      this.isCardRole = false;
      this.isTaken = false;
      this.requiredRank = 0;
      this.xyhw = new int[4];
      this.line = "";
   }

  public boolean isRoleAvailable(int rank) {
      if (!this.isTaken) {
        if (this.requiredRank <= rank) {
          return true;
        }
      }
    return false;
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

   public Boolean isTaken() {
      return this.isTaken;
   }

   public void setIsTaken(Boolean truth) {
      this.isTaken = truth;
   }

   public int[] getxyhw() {
      return this.xyhw;
   }

   public void setxyhw(int index, int value) {
      this.xyhw[index] = value;
   }

   public String getLine() {
      return this.line;
   }

   public void setLine(String line) {
      this.line = line;
   }

}
