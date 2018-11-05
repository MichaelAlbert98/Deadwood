/* CSCI 345 - Object Oriented Design - Deadwood
 * Michael Albert and Ryan Lingg
 * Banker.java
 * Created 11/01/2018
 * Revised 11/04/2018
 */ 

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
   
   //check if upgrade is valid, if so increase rank and decrease relevent attribute.
   public void upgrade(String moneyType, int rank) {
      try {
         validUpgrade(moneyType,rank) ;  
         if (moneyType.equals("cash")) {
            if (rank == 2) {
               this.rank = 2 ;
               this.cash = this.cash - 4 ;
               System.out.println("You upgraded to rank 2 and lost 4 cash.") ; }
            else if (rank == 3) {
               this.rank = 3 ;
               this.cash = this.cash - 10 ;
               System.out.println("You upgraded to rank 3 and lost 10 cash.") ; }
            else if (rank == 4) {
               this.rank = 4 ;
               this.cash = this.cash - 18 ;
               System.out.println("You upgraded to rank 4 and lost 18 cash.") ; }
            else if (rank == 5) {
               this.rank = 5 ;
               this.cash = this.cash - 28 ;
               System.out.println("You upgraded to rank 5 and lost 28 cash.") ; }
            else {
               this.rank = 6 ;
               this.cash = this.cash - 40 ;
               System.out.println("You upgraded to rank 6 and lost 40 cash.") ; }
         }
         
         else {
            if (rank == 2) {
               this.rank = 2 ;
               this.cash = this.credits - 5 ;
               System.out.println("You upgraded to rank 2 and lost 5 credits.") ; }
            else if (rank == 3) {
               this.rank = 3 ;
               this.cash = this.credits - 10 ;
               System.out.println("You upgraded to rank 3 and lost 10 credits.") ; }
            else if (rank == 4) {
               this.rank = 4 ;
               this.cash = this.credits - 15 ;
               System.out.println("You upgraded to rank 4 and lost 15 credits.") ; }
            else if (rank == 5) {
               this.rank = 5 ;
               this.cash = this.credits - 20 ;
               System.out.println("You upgraded to rank 5 and lost 20 credits.") ; }
            else {
               this.rank = 6 ;
               this.cash = this.credits - 25 ;
               System.out.println("You upgraded to rank 6 and lost 25 credits.") ; }
         }
         return;
      }
      
      catch (IllegalArgumentException e) {
         System.out.println(e.getMessage()) ;
      }
      
   }
   
   //makes sure input is valid and that player can afford upgrade, throws exception if not.
   private void validUpgrade(String moneyType, int rank) {
      if (!moneyType.equals("cash") || !moneyType.equals("credits")) {
         throw new IllegalArgumentException("Input must be cash or credits.") ; }
      else if (rank != 2 || rank != 3 || rank != 4  || rank != 5 || rank != 6) {
         throw new IllegalArgumentException("Please input a valid argument for rank.") ; }
      else if (moneyType.equals("cash")) {
         if (rank == 2 && this.cash < 4) {
            throw new IllegalArgumentException("You do not have enough cash to upgrade to this rank.") ; }
         else if (rank == 3 && this.cash < 10) {
            throw new IllegalArgumentException("You do not have enough cash to upgrade to this rank.") ; }
         else if (rank == 4 && this.cash < 18) {
            throw new IllegalArgumentException("You do not have enough cash to upgrade to this rank.") ; }
         else if (rank == 5 && this.cash < 28) {
            throw new IllegalArgumentException("You do not have enough cash to upgrade to this rank.") ; }
         else if (rank == 6 && this.cash < 40) {
            throw new IllegalArgumentException("You do not have enough cash to upgrade to this rank.") ; }
      }
      else if (moneyType.equals("credits")) {
         if (rank == 2 && this.credits < 5) {
            throw new IllegalArgumentException("You do not have enough credits to upgrade to this rank.") ; }
         else if (rank == 3 && this.credits < 10) {
            throw new IllegalArgumentException("You do not have enough credits to upgrade to this rank.") ; }
         else if (rank == 4 && this.credits < 15) {
            throw new IllegalArgumentException("You do not have enough credits to upgrade to this rank.") ; }
         else if (rank == 5 && this.credits < 20) {
            throw new IllegalArgumentException("You do not have enough credits to upgrade to this rank.") ; }
         else if (rank == 6 && this.credits < 25) {
            throw new IllegalArgumentException("You do not have enough credits to upgrade to this rank.") ; }
      }
      return ;
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
