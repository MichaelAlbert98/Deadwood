/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * PlayerView.java
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import java.lang.*;
import gameModel.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class PlayerView implements myObserver {

  // Local Variables
  Player playerRef;
  Room previousRoom;

  // Constructor
  PlayerView(Player p) {
    this.playerRef = p;
    p.attach(this);
  }

  // Local PlayerView Update Override
  @Override
  public void update(String message) {
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
      System.out.printf("%s now have $%s and %scr.\n",this.playerRef.getName(), this.playerRef.getCash(), this.playerRef.getCredits());
      break;

    //Player Took Role:
    case (Player.playerMessages.tookRole):

      break;


    // Locaition Updated Message:
    case (Player.playerMessages.locationUpdated):

      break;
    }
  }
}
