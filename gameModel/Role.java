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
   
   public Boolean getOnCard() {
      return this.isCardRole;
   }
   
   public int getRank() {
      return this.requiredRank;
   }
   
   public Boolean getIsTaken() {
      return this.isTaken;
   }
   
   public void setIsTaken(Boolean truth) {
      this.isTaken = truth;
   }
}
