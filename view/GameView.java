/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * GameView.java
 * 11/2/11
 */


import gameModel.*;
import java.lang.*;


public class GameView extends myObserver {
    //Local Variables
    Game gameRef;

    //Constructor
    GameView(Game g) {
        this.gameRef = g;
        g.attach(this);
    }

    //Local SceneView Update Override
    @Override
    void update(String message){
        
        //New Day Message:
        if (Game.gameMessages.newDay.equals(message)) {
            System.out.printf("Day %d start!\n", gameRef.getCurrentDay());
        }
    }
}
