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
    GameView gameView;
    ArrayList<PlayerView> PlayerViews = new ArrayList<PlayerView>();
    ArrayList<SceneView> SceneViews = new ArrayList<SceneView>();


    /* TextView Constructor
     *
     * This method builds a new TextView model from the game.
     * It keeps track of all of the observers that are attached
     * to the subjects with ArrayLists.
     */
    TextView(Game gameLocation){
        // Game:
        this.gameRef = gameLocation;
        gameView = new GameView(this.gameRef);
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


    /* Start Listener
     *
     * This method is called when the user needs to input data.
     * It demands input until the subject has reached a valid outcome.
     */
    public static void startListener() {
        Boolean enteredValidInput = false;
        Scanner scanner = new Scanner(System.in);
        String input;

        //Switch on player movement
        while (!enteredValidInput) {
            input = scanner.nextLine();
            System.out.printf("Input Recieved: '%s'.\n", input);
            enteredValidInput = true;
        }

        scanner.close();
    }

}
