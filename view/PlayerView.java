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
    void update(int message){
      /*
      if (this.playerRef.observerMessages.SetRole.ordinal() == message) {
        System.out.printf("%s was cast in the role!\n", this.playerRef.getName());
      }
      */
    }
}
