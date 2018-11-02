public class Deck {
   
   private Scene[] scenes;
   private int topIndex;
   
   public Deck() {
      this.scenes = null;
      this.topIndex = 0;
   }
   
   public Deck(Scene[] scenes) {
      this.scenes = scenes;
      this.topIndex = 0;
   }
   
   private void shuffleDeck() {
      return;
   }
   
   public Scene[] getScenes() {
      return this.scenes;
   }
   
   public void setScenes(Scene[] scenes) {
      this.scenes = scenes;
   }
   
   public int getTopIndex() {
      return this.topIndex;
   }
   
   public void setTopIndex(int value) {
      this.topIndex = value;
   }
   
}
