public class Scene {

   private String name;
   private Room location;
   private Boolean isCardUp;
   private Boolean isSceneDone;
   private Role[] sceneRoles;
   private int shotsLeft;
   private int budget;

   public Scene() {
      this.name = "";
      this.location = null;
      this.isCardUp = false;
      this.isSceneDone = false;
      this.sceneRoles = null;
      this.shotsLeft = 0;
      this.budget = 0;
   }

   public Scene(Role[] roles, int shots, int budget, Room loc) {
      this.isCardUp = false;
      this.isSceneDone = false;
      this.sceneRoles = roles;
      this.shotsLeft = shots;
      this.budget = budget;
      this.location = loc;
   }

   private void wrapUpScene() {
      return;
   }

   private Boolean isCardFaceUp() {
      return this.isCardUp;
   }

   private void flipCardFaceUp(Boolean truth) {
      this.isCardUp = truth;
      return;
   }

   public Boolean GetisSceneDone() {
      return this.isSceneDone;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
     this.name = name;
     return;
   }

   public Room getLocation() {
      return this.location;
   }

    public void setLoccation(Room loc) {
      this.location = loc;
   }

   public Role[] getSceneRoles() {
      return this.sceneRoles;
   }

   private void setSceneRoles(Role[] roles) {
      this.sceneRoles = roles;
   }

   public int getShotsLeft() {
      return this.shotsLeft;
   }

   private void setShotsLeft(int value) {
      this.shotsLeft = value;
   }

   public int getBudget() {
      return this.budget;
   }

   private void setBudget(int budget) {
      this.budget = budget;
   }

}
