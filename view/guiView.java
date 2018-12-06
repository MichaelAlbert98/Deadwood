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

public class guiView extends JFrame {

    // References:
    guiView instance;
    Game gameRef;
    PlayerActionController actionController;

    // Views:
    GameView gameView;
    ArrayList<PlayerView> PlayerViews = new ArrayList<PlayerView>();
    ArrayList<RoomView> RoomViews = new ArrayList<RoomView>();


    // Board Attributes:
    static JLayeredPane boardWindow;
    JPanel mPanel;
    JLabel boardlabel;
    JLabel cardlabel;
    JLabel playerlabel;
    JLabel mLabel;
    ImageIcon icon;

    // Player Attributes:
    JLabel player1Icon = new JLabel();
    JLabel player2Icon = new JLabel();
    JLabel player3Icon = new JLabel();

    // Action JButtons:
    JButton bAct = new JButton("ACT");
    JButton bRehearse = new JButton("REHEARSE");
    JButton bMove = new JButton("MOVE");
    JButton bUpgrade = new JButton("UPGRADE");
    JButton bTakeRole = new JButton("TAKE ROLE");
    JButton bEndTurn = new JButton("END TURN");


    public guiView() {

        super("Deadwood");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the JLayeredPane Window
        this.boardWindow = getLayeredPane();

        // Create the deadwood board and add the board to the lowest layer
        this.boardlabel = new JLabel();
        icon = new ImageIcon("../figures/board.jpg");
        this.boardlabel.setIcon(icon);
        this.boardlabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        this.boardWindow.add(boardlabel, new Integer(0));

        // Set the size of the GUI
        setSize(icon.getIconWidth() + 200, icon.getIconHeight()+200);
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
        for (int i = 0; i < this.gameRef.playerList.size(); i++) {
            Player player = this.gameRef.playerList.get(i);
            PlayerView p = new PlayerView(player, boardWindow, i);
            PlayerViews.add(p);
        }
        // Rooms & Scenes:
        ArrayList<Room> roomList = this.gameRef.board.roomList;
        for (int i = 0; i < roomList.size(); i++) {
            RoomViews.add(new RoomView(roomList.get(i), boardWindow));
        }

        // Start the game.
        this.gameRef.newDay();
        this.gameRef.activePlayer.startPlayerTurn();
        renderActionMenu(actionController.determinePlayerActionSet());


        // Create labels for player info
        JLabel name = new JLabel("Name");
        name.setBounds(10, icon.getIconHeight() + 20, 100, 20);
        boardWindow.add(name, new Integer(2));
        JLabel cash = new JLabel("ActivePlayer");
        cash.setBounds(10, icon.getIconHeight() + 40, 100, 20);
        boardWindow.add(cash, new Integer(2));
        JLabel credits = new JLabel("Cash");
        credits.setBounds(10, icon.getIconHeight() + 60, 100, 20);
        boardWindow.add(credits, new Integer(2));
        JLabel rank = new JLabel("Credits");
        rank.setBounds(10, icon.getIconHeight() + 80, 100, 20);
        boardWindow.add(rank, new Integer(2));
        JLabel rehearsals = new JLabel("Rank");
        rehearsals.setBounds(10, icon.getIconHeight() + 100, 100, 20);
        boardWindow.add(rehearsals, new Integer(2));
        JLabel location = new JLabel("Rehearsals");
        location.setBounds(10, icon.getIconHeight() + 120, 100, 20);
        boardWindow.add(location, new Integer(2));
        JLabel role = new JLabel("Die");
        role.setBounds(10, icon.getIconHeight() + 140, 100, 20);
        boardWindow.add(role, new Integer(2));

    }

    // This class implements Mouse Events
    class boardMouseListener implements MouseListener {

        // Code for the different button clicks
        public void mouseClicked(MouseEvent e) {
            Game gameRef = getGameRef();
            PlayerActionController actionController = getPAC();

            if (e.getSource() == bAct) {
                //actingPhase();
                Boolean success = actionController.actingPhase();
                if (success) {
                    JOptionPane.showMessageDialog(boardWindow, "You succeeded!");
                } else {
                    JOptionPane.showMessageDialog(boardWindow, "You failed!");
                }
                ArrayList<String> actions = new ArrayList<String>();
                actions.add("END TURN");
                renderActionMenu(actions);
            } else if (e.getSource() == bRehearse) {
                //rehearsePhase();
                actionController.rehearsePhase();
                JOptionPane.showMessageDialog(boardWindow, "You have rehearsed.");
                ArrayList<String> actions = new ArrayList<String>();
                actions.add("END TURN");
                renderActionMenu(actions);
            } else if (e.getSource() == bMove) {
                //movementPhase();
                String[] MOVEMENT = gameRef.activePlayer.getLocation().getAdjRooms().toArray(new String[0]);
                String movement = (String) JOptionPane.showInputDialog(boardWindow, "Where do you want to move?"
                        , "Movement Options", JOptionPane.QUESTION_MESSAGE, null, MOVEMENT, MOVEMENT[0]);
                if (movement != null) {
                    actionController.movementPhase(movement);
                }
                ArrayList<String> actions = actionController.determinePlayerActionSet();
                actions.remove("MOVE");
                renderActionMenu(actions);
            } else if (e.getSource() == bUpgrade) {
                //upgradePhase();
                String[] MONEYTYPE = actionController.getMoneyTypes().toArray(new String[0]);
                if (MONEYTYPE.length != 0) {
                    String moneyType = (String) JOptionPane.showInputDialog(boardWindow, "What do you want to pay with?"
                            , "Payment Options", JOptionPane.QUESTION_MESSAGE, null, MONEYTYPE, MONEYTYPE[0]);
                    Integer[] LEVELS = actionController.getLevels(moneyType).toArray(new Integer[0]);
                    int level = (Integer) JOptionPane.showInputDialog(boardWindow, "What level do you want to upgrade to?"
                            , "Level Number", JOptionPane.QUESTION_MESSAGE, null, LEVELS, LEVELS[0]);
                    actionController.upgradePhase(moneyType, level);
                } else {
                    JOptionPane.showMessageDialog(boardWindow, "You do not have enough credits or cash.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
                ArrayList<String> actions = new ArrayList<String>();
                actions.add("END TURN");
                renderActionMenu(actions);
            } else if (e.getSource() == bTakeRole) {
                //takeRolePhase();
                String[] ROLES = actionController.findRolePhase().toArray(new String[0]);
                String role = (String) JOptionPane.showInputDialog(boardWindow, "Which role do you want to take?"
                        , "Role Options", JOptionPane.QUESTION_MESSAGE, null, ROLES, ROLES[0]);
                if (role != null) {
                    actionController.takeRolePhase(role);
                }
                ArrayList<String> actions = new ArrayList<String>();
                actions.add("END TURN");
                renderActionMenu(actions);
            } else if (e.getSource() == bEndTurn) {
                //endPlayerTurn();
                //endPlayerTurn();
                String winner = actionController.endPlayerTurn();
                if (!winner.equals("")) {
                   JOptionPane.showMessageDialog(boardWindow, winner 
                              + " is the winner! Congratulations on playing through this extremely boring game. If you found this message through other means, shame on you. -Michael");              
                }
                renderActionMenu(actionController.determinePlayerActionSet());
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

        // Method to allow access to outer class reference.
        private Game getGameRef() {
            return gameRef;
        }

        private JLayeredPane getBoardWindow() {
            return boardWindow;
        }

        private PlayerActionController getPAC() {
            return actionController;
        }
    }

    private void renderActionMenu(ArrayList<String> actionSet) {
        // Create the Menu for action buttons
        reset();
        mLabel = new JLabel("MENU");
        mLabel.setBounds(icon.getIconWidth() + 70, 0, 100, 20);
        boardWindow.add(mLabel, new Integer(2));

        // Create Action buttons
        bAct = new JButton("ACT");
        bAct.setBounds(icon.getIconWidth() + 30, 30, 120, 20);
        bAct.setBackground(Color.gray);
        if (actionSet.contains("ACT")) {
            bAct.setBackground(Color.white);
            bAct.addMouseListener(new boardMouseListener());
        }
        boardWindow.add(bAct, new Integer(2));

        bRehearse = new JButton("REHEARSE");
        bRehearse.setBounds(icon.getIconWidth() + 30, 60, 120, 20);
        bRehearse.setBackground(Color.gray);
        if (actionSet.contains("REHEARSE")) {
            bRehearse.setBackground(Color.white);
            bRehearse.addMouseListener(new boardMouseListener());
        }
        boardWindow.add(bRehearse, new Integer(2));

        bMove = new JButton("MOVE");
        bMove.setBounds(icon.getIconWidth() + 30, 90, 120, 20);
        bMove.setBackground(Color.gray);
        if (actionSet.contains("MOVE")) {
            bMove.setBackground(Color.white);
            bMove.addMouseListener(new boardMouseListener());
        }
        boardWindow.add(bMove, new Integer(2));

        bUpgrade = new JButton("UPGRADE");
        bUpgrade.setBounds(icon.getIconWidth() + 30, 120, 120, 20);
        bUpgrade.setBackground(Color.gray);
        if (actionSet.contains("UPGRADE")) {
            bUpgrade.setBackground(Color.white);
            bUpgrade.addMouseListener(new boardMouseListener());
        }
        boardWindow.add(bUpgrade, new Integer(2));

        bTakeRole = new JButton("TAKE ROLE");
        bTakeRole.setBounds(icon.getIconWidth() + 30, 150, 120, 20);
        bTakeRole.setBackground(Color.gray);
        if (actionSet.contains("TAKE ROLE")) {
            bTakeRole.setBackground(Color.white);
            bTakeRole.addMouseListener(new boardMouseListener());
        }
        boardWindow.add(bTakeRole, new Integer(2));

        bEndTurn = new JButton("END TURN");
        bEndTurn.setBounds(icon.getIconWidth() + 30, 180, 120, 20);
        bEndTurn.setBackground(Color.gray);
        if (actionSet.contains("END TURN")) {
            bEndTurn.setBackground(Color.white);
            bEndTurn.addMouseListener(new boardMouseListener());
        }
        boardWindow.add(bEndTurn, new Integer(2));
    }

    private void reset() {
        boardWindow.remove(bAct);
        boardWindow.remove(bRehearse);
        boardWindow.remove(bMove);
        boardWindow.remove(bUpgrade);
        boardWindow.remove(bTakeRole);
        boardWindow.remove(bEndTurn);
    }
}