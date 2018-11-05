/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * TextView.java
 * 11/2/11
 */

import gameModel.*;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Scanner;

public class TextView{

    Game gameRef;
    ArrayList<PlayerView> PlayerViews = new ArrayList<PlayerView>();
    ArrayList<SceneView> SceneViews = new ArrayList<SceneView>();


    // Constructor creates views for objects with loud actions,
    // they will all subscribe to the display observer
    TextView(Game gameLocation){
        // Game:
        this.gameRef = gameLocation;
        GameView g = new GameView(this.gameRef);
        // Players:
        for (Player player : this.gameRef.playerList) {
          PlayerView p = new PlayerView(player);
          PlayerViews.add(p);
        }
        // Scenes:
        ArrayList<Room> roomList = this.gameRef.board.roomList;
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getScene() != null) {
                SceneView s = new SceneView(roomList.get(i).getScene());
                SceneViews.add(s);
            }
          }
    }


    //Used for player input
    public void startListener() {
        Scanner scanner = new Scanner(System.in);

        //Switch on player movement
    }

}
