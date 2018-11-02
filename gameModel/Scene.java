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
   
   public Boolean isSceneFinished() {
      return this.isSceneDone;
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
   
   private void addSceneRoles(Role[] roles) {
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
