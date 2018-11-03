/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * TextView.java
 * 11/2/11
 */

import gameModel.*;
import java.util.ArrayList;

public class TextView{

    Game gameRef;
    TextView(Game gameLocation){
        this.gameRef = gameLocation;
        //Create views for objects with loud actions,
        // they will all subscribe to the display observer
        GameView g = new GameView(this.gameRef);
        for (Player player : this.gameRef.playerList) {
          PlayerView p = new PlayerView(player);
        }
    }

    /* not sure if this is needed
    @Override
    void update(int message){
    }
    */
}
