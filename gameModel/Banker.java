public class Banker {
   
   private int rank;
   private int cash;
   private int credits;
   
   public Banker() {
      this.rank = 0;
      this.cash = 0;
      this.credits = 0;
   }
   
   public Banker(int rank, int cash, int credits) {
      this.rank = rank;
      this.cash = cash;
      this.credits = credits;
   }
   
   public void upgrade(String moneyType, int rank) {
      return;
   }
   
   public int getRank() {
      return this.rank;
   }
   
   public void setRank(int rank) {
      this.rank = rank;
   }
   
   public int getCash() {
      return this.cash;
   }
   
   public void setCash(int amount) {
      this.cash = amount;
   }
   
   public void addCash(int amount) {
      this.cash = this.cash + amount;
   }
   
   public int getCredits() {
      return this.credits;
   }
   
   public void setCredits(int amount) {
      this.credits = amount;
   }
   
   public void addCredits(int amount) {
      this.credits = this.credits + amount;
   }
   
}
