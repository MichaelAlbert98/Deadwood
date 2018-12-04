/*
   Deadwood GUI helper file
   Author: Moushumi Sharmin
   This file shows how to create a simple GUI using Java Swing and Awt Library
   Classes Used: JFrame, JLabel, JButton, JLayeredPane
*/

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class BoardLayersListener extends JFrame {

    // JLabels
    JLabel boardlabel;
    JLabel cardlabel;
    JLabel playerlabel;
    JLabel mLabel;
    ImageIcon icon;

    //JButtons
    JButton bAct;
    JButton bRehearse;
    JButton bMove;
    JButton bUpgrade;
    JButton bTakeRole;
    JButton bEndTurn;

    // JLayered Pane
    JLayeredPane bPane;

    // Constructor
    public BoardLayersListener(Board board) {

        // Set the title of the JFrame
        super("Deadwood");
        // Set the exit option for the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the JLayeredPane to hold the display, cards, dice and buttons
        bPane = getLayeredPane();

        // Create the deadwood board
        boardlabel = new JLabel();
        icon =  new ImageIcon("../figures/board.jpg");
        boardlabel.setIcon(icon);
        boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

        // Add the board to the lowest layer
        bPane.add(boardlabel, new Integer(0));

        // Set the size of the GUI
        setSize(icon.getIconWidth()+200,icon.getIconHeight()+200);

        // Add scene cards to the board
    /*   JLabel[] cardLabels = new JLabel[10];
       for (int i=0;i<10;i++) {
         cardLabels[i] = new JLabel();
         ImageIcon cIcon =  new ImageIcon("../figures/CardBack.jpg");
         cardLabels[i].setIcon(cIcon);
         int[] bounds = board.getRoomList().get(i).getxyhw();
         cardLabels[i].setBounds(bounds[0],bounds[1],bounds[3],bounds[2]);
         cardLabels[i].setOpaque(true);
         // Add the card to the lower layer
         bPane.add(cardLabels[i], new Integer(1));
       } */

        // Create the Menu for action buttons
        mLabel = new JLabel("MENU");
        mLabel.setBounds(icon.getIconWidth()+70,0,100,20);
        bPane.add(mLabel,new Integer(2));

        // Create Action buttons
        bAct = new JButton("ACT");
        bAct.setBackground(Color.white);
        bAct.setBounds(icon.getIconWidth()+30, 30,120, 20);
        bAct.addMouseListener(new boardMouseListener());

        bRehearse = new JButton("REHEARSE");
        bRehearse.setBackground(Color.white);
        bRehearse.setBounds(icon.getIconWidth()+30,60,120, 20);
        bRehearse.addMouseListener(new boardMouseListener());

        bMove = new JButton("MOVE");
        bMove.setBackground(Color.white);
        bMove.setBounds(icon.getIconWidth()+30,90,120, 20);
        bMove.addMouseListener(new boardMouseListener());

        bUpgrade = new JButton("UPGRADE");
        bUpgrade.setBackground(Color.white);
        bUpgrade.setBounds(icon.getIconWidth()+30,120,120, 20);
        bUpgrade.addMouseListener(new boardMouseListener());

        bTakeRole = new JButton("TAKE ROLE");
        bTakeRole.setBackground(Color.white);
        bTakeRole.setBounds(icon.getIconWidth()+30,150,120, 20);
        bTakeRole.addMouseListener(new boardMouseListener());

        bEndTurn = new JButton("END TURN");
        bEndTurn.setBackground(Color.white);
        bEndTurn.setBounds(icon.getIconWidth()+30,180,120, 20);
        bEndTurn.addMouseListener(new boardMouseListener());

        // Place the action buttons in the top layer
        bPane.add(bAct, new Integer(2));
        bPane.add(bRehearse, new Integer(2));
        bPane.add(bMove, new Integer(2));
        bPane.add(bUpgrade, new Integer(2));
        bPane.add(bTakeRole, new Integer(2));
        bPane.add(bEndTurn, new Integer(2));

    }
    // Create info trackers for players
    public void addPlayersInfo(ArrayList<Player> players) {
        JLabel name = new JLabel("Name");
        name.setBounds(10,icon.getIconHeight()+20,100,20);
        bPane.add(name,new Integer(2));
        JLabel cash = new JLabel("Cash");
        cash.setBounds(10,icon.getIconHeight()+40,100,20);
        bPane.add(cash,new Integer(2));
        JLabel credits = new JLabel("Credits");
        credits.setBounds(10,icon.getIconHeight()+60,100,20);
        bPane.add(credits,new Integer(2));
        JLabel rank = new JLabel("Rank");
        rank.setBounds(10,icon.getIconHeight()+80,100,20);
        bPane.add(rank,new Integer(2));
        JLabel rehearsals = new JLabel("Rehearsals");
        rehearsals.setBounds(10,icon.getIconHeight()+100,100,20);
        bPane.add(rehearsals,new Integer(2));
        JLabel location = new JLabel("Location");
        location.setBounds(10,icon.getIconHeight()+120,100,20);
        bPane.add(location,new Integer(2));
        JLabel role = new JLabel("Role");
        role.setBounds(10,icon.getIconHeight()+140,100,20);
        bPane.add(role,new Integer(2));
        JLabel[][] playersLabels = new JLabel[players.size()][7];
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            playersLabels[i][0] = new JLabel(p.getName());
            playersLabels[i][0].setBounds(i*400+100,icon.getIconHeight()+20,100,20);
            bPane.add(playersLabels[i][0],new Integer(2));
            playersLabels[i][1] = new JLabel(Integer.toString(p.getCash()));
            playersLabels[i][1].setBounds(i*400+100,icon.getIconHeight()+40,100,20);
            bPane.add(playersLabels[i][1],new Integer(2));
            playersLabels[i][2] = new JLabel(Integer.toString(p.getCredits()));
            playersLabels[i][2].setBounds(i*400+100,icon.getIconHeight()+60,100,20);
            bPane.add(playersLabels[i][2],new Integer(2));
            playersLabels[i][3] = new JLabel(Integer.toString(p.getRank()));
            playersLabels[i][3].setBounds(i*400+100,icon.getIconHeight()+80,100,20);
            bPane.add(playersLabels[i][3],new Integer(2));
            playersLabels[i][4] = new JLabel(Integer.toString(p.timesRehearsed()));
            playersLabels[i][4].setBounds(i*400+100,icon.getIconHeight()+100,100,20);
            bPane.add(playersLabels[i][4],new Integer(2));
            playersLabels[i][5] = new JLabel("nowhere");
            playersLabels[i][5].setBounds(i*400+100,icon.getIconHeight()+120,100,20);
            bPane.add(playersLabels[i][5],new Integer(2));
            playersLabels[i][6] = new JLabel("none");
            playersLabels[i][6].setBounds(i*400+100,icon.getIconHeight()+140,100,20);
            bPane.add(playersLabels[i][6],new Integer(2));
        }
    }

    // Create player tokens
    public void addPlayers(ArrayList<Player> players) {
        JLabel[] playerLabels = new JLabel[players.size()];
        for (int i=0;i<players.size();i++) {
            playerLabels[i] = new JLabel();
            ImageIcon pIcon = new ImageIcon(players.get(i).image);
            playerLabels[i].setIcon(pIcon);
            playerLabels[i].setBounds(1030+(i*50),270,pIcon.getIconWidth(),pIcon.getIconHeight());
            playerLabels[i].setVisible(true);
            bPane.add(playerLabels[i],new Integer(3));
        }
    }

    // This class implements Mouse Events
    class boardMouseListener implements MouseListener{

        // Code for the different button clicks
        public void mouseClicked(MouseEvent e) {

            if (e.getSource()== bAct){
                System.out.println("Acting is Selected\n");
            }
            else if (e.getSource()== bRehearse){
                System.out.println("Rehearse is Selected\n");
            }
            else if (e.getSource()== bMove){
                System.out.println("Move is Selected\n");
            }
            else if (e.getSource()== bUpgrade){
                System.out.println("Upgrade is Selected\n");
            }
            else if (e.getSource()== bTakeRole){
                System.out.println("Take Role is Selected\n");
            }
            else if (e.getSource()== bEndTurn){
                System.out.println("End Turn is Selected\n");
            }
        }
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
    }
}