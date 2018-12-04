/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * guiView.java
 * Created: 12/01/2018
 * Revised: 12/04/2018
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
    static JLayeredPane boardWindow;
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

        // Start the game.
        this.gameRef.newDay();
        this.gameRef.activePlayer.startPlayerTurn();

        this.mLabel = new JLabel("");
        this.mLabel.setBounds(icon.getIconWidth() + 70, 0, 100, 20);
        this.mLabel.setBounds(icon.getIconWidth() + 70, 0, 100, 20);
        this.boardWindow.add(mLabel,new Integer(2));
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
                renderActionMenu(actionSet);
            }
            else if (e.getSource() == bAct) {
                //actingPhase();
                JOptionPane.showMessageDialog(boardWindow, "You have acted.");
            } else if (e.getSource() == bRehearse) {
                //rehearsePhase();
                JOptionPane.showMessageDialog(boardWindow, "You have rehearsed.");
            } else if (e.getSource() == bMove) {
                //movementPhase();
                String[] MOVEMENT = {"trailer","main street","hotel","saloon","train station","jail","general store",
                        "church","ranch","bank","secret hideout","office"};
                String movement = (String) JOptionPane.showInputDialog(boardWindow, "Where do you want to move?"
                        ,"Movement Options", JOptionPane.QUESTION_MESSAGE, null, MOVEMENT, MOVEMENT[0]);
            } else if (e.getSource() == bUpgrade) {
                //upgradePhase();
                String[] MONEYTYPE = {"cash","credit"};
                Integer[] LEVELS = {2,3,4,5,6};
                String moneyType = (String) JOptionPane.showInputDialog(boardWindow, "What do you want to pay with?"
                        ,"Payment Options", JOptionPane.QUESTION_MESSAGE, null, MONEYTYPE, MONEYTYPE[0]);
                int level = (Integer)JOptionPane.showInputDialog(boardWindow, "What level do you want to upgrade to?"
                        ,"Level Number", JOptionPane.QUESTION_MESSAGE, null, LEVELS, LEVELS[0]);
            } else if (e.getSource() == bTakeRole) {
                //takeRolePhase();
                String[] ROLES = {"temp"};
                String moneyType = (String) JOptionPane.showInputDialog(boardWindow, "Which role do you want to take?"
                        ,"Role Options", JOptionPane.QUESTION_MESSAGE, null, ROLES, ROLES[0]);
            } else if (e.getSource() == bEndTurn) {
                //endPlayerTurn();
                JOptionPane.showMessageDialog(boardWindow, "You have ended your turn.");
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

    private void renderActionMenu(ArrayList<String> actionSet) {
        // Create the Menu for action buttons
        mLabel = new JLabel("MENU");
        mLabel.setBounds(icon.getIconWidth() + 70, 0, 100, 20);
        boardWindow.add(mLabel, new Integer(2));

        // Create Action buttons
        if (actionSet.contains("ACT")) {
            bAct = new JButton("ACT");
            bAct.setBackground(Color.white);
            bAct.setBounds(icon.getIconWidth() + 30, 30, 120, 20);
            bAct.addMouseListener(new boardMouseListener());
            boardWindow.add(bAct, new Integer(2));
        }

        if (actionSet.contains("REHEARSE")) {
            bRehearse = new JButton("REHEARSE");
            bRehearse.setBackground(Color.white);
            bRehearse.setBounds(icon.getIconWidth() + 30, 60, 120, 20);
            bRehearse.addMouseListener(new boardMouseListener());
            boardWindow.add(bRehearse, new Integer(2));
        }

        if (actionSet.contains("MOVE")) {
            bMove = new JButton("MOVE");
            bMove.setBackground(Color.white);
            bMove.setBounds(icon.getIconWidth() + 30, 90, 120, 20);
            bMove.addMouseListener(new boardMouseListener());
            boardWindow.add(bMove, new Integer(2));
        }

        if (actionSet.contains("UPGRADE")) {
            bUpgrade = new JButton("UPGRADE");
            bUpgrade.setBackground(Color.white);
            bUpgrade.setBounds(icon.getIconWidth() + 30, 120, 120, 20);
            bUpgrade.addMouseListener(new boardMouseListener());
            boardWindow.add(bUpgrade, new Integer(2));
        }

        if (actionSet.contains("TAKE ROLE")) {
            bTakeRole = new JButton("TAKE ROLE");
            bTakeRole.setBackground(Color.white);
            bTakeRole.setBounds(icon.getIconWidth() + 30, 150, 120, 20);
            bTakeRole.addMouseListener(new boardMouseListener());
            boardWindow.add(bTakeRole, new Integer(2));
        }

        if (actionSet.contains("END TURN")) {
            bEndTurn = new JButton("END TURN");
            bEndTurn.setBackground(Color.white);
            bEndTurn.setBounds(icon.getIconWidth() + 30, 180, 120, 20);
            bEndTurn.addMouseListener(new boardMouseListener());
            boardWindow.add(bEndTurn, new Integer(2));
        }
    }
}