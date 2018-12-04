/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * GameView.java
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import controller.*;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class guiView extends JFrame  {

    // References:
    guiView instance;
    Game gameRef;
    PlayerActionController actionController;

    // Views:
    GameView gameView;
    ArrayList<PlayerView> PlayerViews = new ArrayList<PlayerView>();
    ArrayList<RoomView> RoomViews = new ArrayList<RoomView>();
    ArrayList<SceneView> SceneViews = new ArrayList<SceneView>();


    // Swing Attributes:
    JLayeredPane boardWindow;
    JLabel boardlabel;
    JLabel cardlabel;
    JLabel playerlabel;
    JLabel mLabel;
    ImageIcon icon;

    // Action JButtons:
    JButton bStartGame;
    JButton bAct;
    JButton bRehearse;
    JButton bMove;
    JButton bUpgrade;
    JButton bTakeRole;
    JButton bEndTurn;

    //Movement JButtons
    JButton bMoveUp;
    JButton bMoveDown;
    JButton bMoveRight;
    JButton bMoveLeft;

    //Role JButtons;
    JButton bRole0;
    JButton bRole1;
    JButton bRole2;
    JButton bRole3;
    JButton bRole4;
    JButton bRole5;
    JButton bRole6;


    public guiView() {

        super("Deadwood");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the JLayeredPane Window
        this.boardWindow = getLayeredPane();

        // Create the deadwood board and add the board to the lowest layer
        this.boardlabel = new JLabel();
        icon =  new ImageIcon("../figures/board.jpg");
        this.boardlabel.setIcon(icon);
        this.boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        this.boardWindow.add(boardlabel, new Integer(0));

        // Set the size of the GUI
        setSize(icon.getIconWidth()+200,icon.getIconHeight()+200);
    }

    public guiView(Game game) {
        super("Deadwood");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the deadwood board and add the board to the lowest layer
        icon = new ImageIcon("../figures/board.jpg");

        // Set References
        this.gameRef = game;
        this.actionController = new PlayerActionController(game);

        // Attach observers:
        //Game:
        this.gameView = new GameView(this.gameRef);
        //Players:
        for (Player player : this.gameRef.playerList) {
            PlayerView p = new PlayerView(player);
            PlayerViews.add(p);
        }
        // Rooms & Scenes:
        ArrayList<Room> roomList = this.gameRef.board.roomList;
        for (int i = 0; i < roomList.size(); i++) {
            RoomViews.add(new RoomView(roomList.get(i)));
            if (roomList.get(i).getScene() != null) {
                SceneView s = new SceneView(roomList.get(i).getScene());
                SceneViews.add(s);
            }
        }


        this.mLabel = new JLabel("");
        this.mLabel.setBounds(icon.getIconWidth() + 70, 0, 100, 20);
        this.mLabel.setBounds(icon.getIconWidth() + 70, 0, 100, 20);
        this.boardWindow.add(mLabel, new Integer(2));
        this.bStartGame = new JButton("START GAME");
        this.bStartGame.setBackground(Color.white);
        this.bStartGame.setBounds(icon.getIconWidth() + 30, 30, 120, 20);
        this.bStartGame.addMouseListener(new boardMouseListener());
        this.boardWindow.add(bStartGame, new Integer(2));

    }

    // This class implements Mouse Events
    class boardMouseListener implements MouseListener {

        // Code for the different button clicks
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == bStartGame) {
                ArrayList<String> actionSet = actionController.determinePlayerActionSet();
                renderActionMenu(new ArrayList<String>());
            }
            else if (e.getSource() == bAct) {
                //actingPhase();
                gameRef.newDay();
                System.out.println("Acting is Selected\n");
            } else if (e.getSource() == bRehearse) {
                //rehearsePhase();
                System.out.println("Rehearse is Selected\n");
            } else if (e.getSource() == bMove) {
                //movementPhase();
                System.out.println("Move is Selected\n");
            } else if (e.getSource() == bUpgrade) {
                //upgradePhase();
                System.out.println("Upgrade is Selected\n");
            } else if (e.getSource() == bTakeRole) {
                //takeRolePhase();
                System.out.println("Take Role is Selected\n");
            } else if (e.getSource() == bEndTurn) {
                //endPlayerTurn();
                System.out.println("End Turn is Selected\n");
            }
        }

        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse Pressed\n");
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    private void renderActionMenu(ArrayList<String> actionSet) {
        // Create the Menu for action buttons
        mLabel = new JLabel("MENU");
        mLabel.setBounds(icon.getIconWidth() + 70, 0, 100, 20);
        boardWindow.add(mLabel, new Integer(2));

        // Create Action buttons
        bAct = new JButton("ACT");
        bAct.setBackground(Color.white);
        bAct.setBounds(icon.getIconWidth() + 30, 30, 120, 20);
        bAct.addMouseListener(new boardMouseListener());

        bRehearse = new JButton("REHEARSE");
        bRehearse.setBackground(Color.white);
        bRehearse.setBounds(icon.getIconWidth() + 30, 60, 120, 20);
        bRehearse.addMouseListener(new boardMouseListener());

        bMove = new JButton("MOVE");
        bMove.setBackground(Color.white);
        bMove.setBounds(icon.getIconWidth() + 30, 90, 120, 20);
        bMove.addMouseListener(new boardMouseListener());

        bUpgrade = new JButton("UPGRADE");
        bUpgrade.setBackground(Color.white);
        bUpgrade.setBounds(icon.getIconWidth() + 30, 120, 120, 20);
        bUpgrade.addMouseListener(new boardMouseListener());

        bTakeRole = new JButton("TAKE ROLE");
        bTakeRole.setBackground(Color.white);
        bTakeRole.setBounds(icon.getIconWidth() + 30, 150, 120, 20);
        bTakeRole.addMouseListener(new boardMouseListener());

        bEndTurn = new JButton("END TURN");
        bEndTurn.setBackground(Color.white);
        bEndTurn.setBounds(icon.getIconWidth() + 30, 180, 120, 20);
        bEndTurn.addMouseListener(new boardMouseListener());

        // Place the action buttons in the top layer
        boardWindow.add(bAct, new Integer(2));
        boardWindow.add(bRehearse, new Integer(2));
        boardWindow.add(bMove, new Integer(2));
        boardWindow.add(bUpgrade, new Integer(2));
        boardWindow.add(bTakeRole, new Integer(2));
        boardWindow.add(bEndTurn, new Integer(2));
    }
}