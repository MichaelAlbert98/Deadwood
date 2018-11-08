import java.util.ArrayList;
import java.util.Collections;

public class Deck {

   public ArrayList<Scene> sceneList;
   private int topIndex;

   public Deck() {
      //TEMPORARY DECK CONSTRUCTOR:
      this.sceneList = new ArrayList<Scene>();
      this.topIndex = 0;
   }

   public Deck(ArrayList<Scene> scenes) {
      this.sceneList = scenes;
      this.topIndex = scenes.size()-1;
      shuffleDeck();
   }

   private void shuffleDeck() {
      Collections.shuffle(this.sceneList);
   }

   public Scene getTopScene() {
      return this.sceneList.get(this.topIndex);
   }
}
