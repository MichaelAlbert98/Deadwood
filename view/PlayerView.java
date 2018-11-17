/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * PlayerView.java
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import java.lang.*;
import gameModel.*;

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

    // Updated Money
    case (Player.playerMessages.updatedMoney) :
      System.out.printf("You now have $%s and %scr.\n", this.playerRef.getCash(), this.playerRef.getCredits());
      break;

    //Player Took Role:
    case (Player.playerMessages.tookRole):
      if (this.playerRef.getCurrentRole() == null) {
        System.out.printf("%s removed from role.\n", this.playerRef.getName());
      } else {
        System.out.printf("You have now been cast as %s in %s!\n",
                          this.playerRef.getCurrentRole().getName(),
                          this.playerRef.getLocation().getName());
      }
      break;

    // Locaition Updated Message:
    case (Player.playerMessages.locationUpdated):
      System.out.printf("Moved to %s!\n", this.playerRef.getLocation().getName());
      break;
    }
  }
}
