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
    int playerIdx;
    JLabel guiDice;
    JLabel[] guiStats;
    JLayeredPane guiWindow;

    // Constructor
    PlayerView(Player p, JLayeredPane window, int idx) {
        this.playerRef = p;
        this.playerIdx = idx;
        this.guiWindow = window;

        this.guiDice = new JLabel();
        this.guiStats = new JLabel[7];
        for (int i = 0; i < 7; i++) {
            this.guiStats[i] = new JLabel();
        }
        p.attach(this);
    }

    // Local PlayerView Update Override
    @Override
    public void update(String message) {
        switch (message) {

            case (Player.PlayerMessages.StartTurn):
                JOptionPane.showMessageDialog(this.guiWindow, this.playerRef.getName() + "'s turn start!");
                break;

            case (Player.PlayerMessages.EndTurn):
                JOptionPane.showMessageDialog(this.guiWindow, this.playerRef.getName() + "'s turn over!");
                break;

            case (Player.PlayerMessages.LocationUpdated):

                this.guiWindow.remove(guiDice);
                guiDice = new JLabel();
                ImageIcon diceImg = new ImageIcon(this.playerRef.getImage());
                int[] roomXYHW = this.playerRef.getLocation().getxyhw();

                if (this.playerRef.getCurrentRole() != null) {
                    int[] roleXYHW = this.playerRef.getCurrentRole().getxyhw();
                    guiDice.setIcon(diceImg);
                    if (this.playerRef.getCurrentRole().getOnCard()) {
                        guiDice.setBounds(roomXYHW[0] + roleXYHW[0] + 1, roomXYHW[1] + roleXYHW[1], diceImg.getIconWidth(), diceImg.getIconHeight());
                    }
                    else {
                        guiDice.setBounds(roleXYHW[0] + 3,roleXYHW[1] + 3, diceImg.getIconWidth(), diceImg.getIconHeight());
                    }
                    this.guiWindow.add(guiDice, new Integer(2));
                    guiDice.revalidate();
                } else {
                    roomXYHW = this.playerRef.getLocation().getxyhw();
                    guiDice.setIcon(diceImg);
                    guiDice.setBounds(roomXYHW[0] + (playerIdx * diceImg.getIconWidth()), roomXYHW[1], diceImg.getIconWidth(), diceImg.getIconHeight());
                    this.guiWindow.add(guiDice, new Integer(2));
                    guiDice.revalidate();
                }
                guiWindow.repaint();
                break;

            case (Player.PlayerMessages.StatsUpdated):
                for(int i = 0; i < 7; i++) {
                    this.guiWindow.remove(this.guiStats[i]);
                }

                this.guiStats[0] = new JLabel(this.playerRef.getName());
                if (this.playerRef.getIsActive()) {
                    this.guiStats[1] = new JLabel("Active!");
                } else {
                    this.guiStats[1] = new JLabel("Inactive");
                }
                this.guiStats[2] = new JLabel(String.valueOf(this.playerRef.getCash()));
                this.guiStats[3] = new JLabel(String.valueOf(this.playerRef.getCredits()));
                this.guiStats[4] = new JLabel(String.valueOf(this.playerRef.getRank()));
                this.guiStats[5] = new JLabel(String.valueOf(this.playerRef.timesRehearsed()));
                this.guiStats[6] = new JLabel();
                this.guiStats[6].setIcon(new ImageIcon(this.playerRef.getImage()));

                for (int i = 0; i < 6; i++) {
                    this.guiStats[i].setBounds(10+(200*(playerIdx+1)),900+ (20*(i+1)),100,20);
                    this.guiWindow.add(guiStats[i],new Integer(2));
                    guiStats[i].revalidate();
                }
                this.guiStats[6].setBounds(10+(200*(playerIdx+1)),900+(20*(6+1)),100,60);
                this.guiWindow.add(guiStats[6],new Integer(2));
                guiWindow.repaint();
                break;
        }
    }
}
