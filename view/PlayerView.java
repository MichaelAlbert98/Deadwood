
/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * PlayerView.java
 * 11/13/11
 */

import java.lang.*;
//import gameModel.*;

public class PlayerView extends myObserver {

  // Local Variables
  Player playerRef;

  // Constructor
  PlayerView(Player p) {
    this.playerRef = p;
    p.attach(this);
  }

  // Local PlayerView Update Override
  @Override
  void update(String message) {
    switch (message) {

    // Turn Start Message:
    case (Player.playerMessages.turnStart):
      System.out.printf("\n%s turn start!\n", this.playerRef.getName());
      break;

    // Turn End Message:
    case (Player.playerMessages.turnEnd):
      System.out.printf("%s turn complete!\n", this.playerRef.getName());
      break;

    // Locaition Updated Message:
    case (Player.playerMessages.locationUpdated):
      System.out.printf("Moved to %s!\n", this.playerRef.getLocation().getName());
      break;
    }
  }
}
