public class Scene{
   
   private Boolean isCardUp;
   private Boolean isSceneDone;
   private Card sceneCard;
   private Role[] sceneRoles;
   private int shotsLeft;
   private int budget;
   
   public Scene() {
      this.isCardUp = false;
      this.isSceneDone = false;
      this.sceneCard = null;
      this.sceneRoles = null;
      this.shotsLeft = 0;
      this.budget = 0;
   }
   
   public Scene(Card card, Role[] roles, int shots, int budget) {
      this.isCardUp = false;
      this.isSceneDone = false;
      this.sceneCard = card;
      this.sceneRoles = roles;
      this.shotsLeft = shots;
      this.budget = budget;
   }
   
   private void wrapScene() {
      return;
   }
   
   public Boolean getIsCardUp() {
      return this.isCardUp;
   }
   
   public void setIsCardUp(Boolean truth) {
      this.isCardUp = truth;
      return;
   }
   
   public Boolean getIsSceneDone() {
      return this.isSceneDone;
   }
   
   public void setIsSceneDone(Boolean truth) {
      this.isSceneDone = truth;
   }
   
   public Card getSceneCard() {
      return this.sceneCard;
   }
   
   public void setSceneCard(Card card) {
      this.sceneCard = card;
   }
   
   public Role[] getSceneRoles() {
      return this.sceneRoles;
   }
   
   public void setSceneRoles(Role[] roles) {
      this.sceneRoles = roles;
   }
   
   public int getShotsLeft() {
      return this.shotsLeft;
   }
   
   public void setShotsLeft(int value) {
      this.shotsLeft = value;
   }
   
   public int getBudget() {
      return this.budget;
   }
   
   public void setBudget(int budget) {
      this.budget = budget;
   }
  
}
