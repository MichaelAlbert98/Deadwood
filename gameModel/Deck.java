import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    public ArrayList<Scene> sceneList;
    private int topIndex;

    public Deck() {
        this.sceneList = new ArrayList<Scene>(0);
        this.topIndex = -1;
    }

    public Deck(ArrayList<Scene> scenes) {
        this.sceneList = scenes;
        this.topIndex = scenes.size() - 1;
        shuffleDeck();
    }

    private void shuffleDeck() {
        Collections.shuffle(this.sceneList);
    }

    public Scene getTopScene() {
        if (this.topIndex == -1) {
            this.topIndex = this.sceneList.size()-1;
        }
        Scene topScene = this.sceneList.get(this.topIndex);
        this.topIndex--;
        return topScene;
    }
}
