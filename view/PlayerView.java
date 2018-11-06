/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * PlayerView.java
 * 11/2/11
 */

import java.lang.*;
import gameModel.*;


public class PlayerView extends myObserver {

    //Local Variables
    Player playerRef;

    //Constructor
    PlayerView(Player p){
        this.playerRef = p;
        p.attach(this);
    }

    //Local PlayerView Update Override
    @Override
    void update(String message){

      //Turn Start Message:
      if (Player.playerMessages.turnStart.equals(message)) {
        System.out.printf("%s turn start!\n", this.playerRef.getName());
      }
    }
}
