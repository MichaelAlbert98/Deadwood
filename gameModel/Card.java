public class Card {

   private String name;
   private int budget;
   private Room location;
   private Role[] roles;
   
   public Card() {
      this.name = "";
      this.budget = 0;
      this.location = null;
      this.roles = null;
   }
   
   public Card(String name, int budget, Room loc, Role[] roles) {
      this.name = name;
      this.budget = budget;
      this.location = loc;
      this.roles = roles;
   }
   
   public String getName() {
      return this.name;
   }
   
   public void setName(String name) {
      this.name = name;
      return;
   }
   
   public int getBudget() {
      return this.budget;
   }
   
   public void setBudget(int budget) {
      this.budget = budget;
   }
   
   public Room getLocation() {
      return this.location;
   }
   
   public void setLoccation(Room loc) {
   this.location = loc;
   }
   
   public Role[] getRoles() {
      return this.roles;
   }
   
   public void setRoles(Role[] roles) {
      this.roles = roles;
   }
   
}
