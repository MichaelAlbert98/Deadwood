
/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * TextView.java
 * 11/13/11
 */

import gameModel.*;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Scanner;

public class TextView {

    Game gameRef;
    GameView gameView;
    ArrayList<PlayerView> PlayerViews = new ArrayList<PlayerView>();
    ArrayList<SceneView> SceneViews = new ArrayList<SceneView>();

    /*
     * TextView Constructor
     *
     * This method builds a new TextView model from the game. It keeps track of all
     * of the observers that are attached to the subjects with ArrayLists.
     */
    TextView(Game gameLocation) {
        // Game:
        this.gameRef = gameLocation;
        gameView = new GameView(this.gameRef);
        // Players:
        for (Player player : this.gameRef.playerList) {
            PlayerView p = new PlayerView(player);
            PlayerViews.add(p);
        }
        // Scenes:
        ArrayList<Room> roomList = this.gameRef.board.roomList;
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getScene() != null) {
                SceneView s = new SceneView(roomList.get(i).getScene());
                SceneViews.add(s);
            }
        }
    }

    /*
     * Start Listener
     *
     * This method is called when the user needs to input data. It demands input
     * until the subject has reached a valid outcome.
     */
    public void startListener() {
        Scanner scanner = new Scanner(System.in);
        String inputAction;

        startGameMessage();
        this.gameRef.newDay();
        this.gameRef.activePlayer.startPlayerTurn();
        // Switch on player movement
        while (!this.gameRef.isGameOver()) {

            inputAction = scanner.nextLine().toLowerCase();
            switch (inputAction) {

            // User wants to know current player:
            case "active player?":
            case "stats":
                activePlayerInfo();
                break;

            case "where":
                activePlayerLocation();
                break;

            case "who":
                activePlayerName();
                break;

            case "move":
                movementPhase(scanner);
                break;

            case "upgrade":
                upgradePhase(scanner);
                break;

            case "end turn":
                endPlayerTurn();
                break;
            
            case "":
                break;

            default:
                System.out.println("Command not recognized.");
                break;
            }

        }

        scanner.close();
    }

    /* Listener Sub-Methods */

    // Active Players Information: (commands: 'active player?', 'stats')
    private void activePlayerInfo() {
        System.out.printf("The active player is %s located in %s. Their rank is %d, and have $%d and %dcr. ",
                this.gameRef.activePlayer.getName(), this.gameRef.activePlayer.getLocation().getName(),
                this.gameRef.activePlayer.getRank(), this.gameRef.activePlayer.getCash(),
                this.gameRef.activePlayer.getCredits());
        if (gameRef.activePlayer.getCurrentRole() == null) {
            System.out.printf("They are currently not in a role.\n");
        } else {
            System.out.printf("They are currently working in the scene %s.\n",
                    this.gameRef.activePlayer.getCurrentRole().getName());
        }
    }

    // Active Player Name: (commands: 'who') 
    private void activePlayerName() {
        System.out.printf("The active player is %s($%d, %dcr).\n", this.gameRef.activePlayer.getName(),
                            this.gameRef.activePlayer.getCash(), this.gameRef.activePlayer.getCredits());
    }

    private void activePlayerLocation() {
        if (this.gameRef.activePlayer.getCurrentRole() == null) {
            System.out.printf("The active player is located in %s wrapped.\n", this.gameRef.activePlayer.getLocation().getName());
        } else {
            System.out.printf("The active player is located in %s shooting %s %s.\n",
                                this.gameRef.activePlayer.getLocation(), this.gameRef.activePlayer.getLocation().getScene().getName(),
                                this.gameRef.activePlayer.getLocation().getScene().getSceneNum());
        }
    }


    // Movement Helpers: (command: 'move')
    private void movementPhase(Scanner scanner) {
        System.out.println("Which room do you want to move to?");
        String input = scanner.nextLine().toLowerCase();
        // If room exists, check its a valid move, then move the player
        if (this.gameRef.board.nameToRoom.get(input) != null) {
            Room current = this.gameRef.activePlayer.getLocation();
            Room dest = this.gameRef.board.nameToRoom.get(input);
            if (this.gameRef.board.areRoomsAdjacent(current, dest)) {
                this.gameRef.activePlayer.movePlayer(dest);
            } else {
                System.out.println("You cannot move to that room.");
            }
        } else {
            System.out.println("Room does not exist.");
        }
    }

    // Upgrade Helpers: (command 'upgrade')
    private void upgradePhase(Scanner scanner) {
        if (this.gameRef.activePlayer.getLocation().getName().equals("Casting Office")) {
            System.out.print("Select the desired rank you wish to upgrade too: ");
        }   else {
            System.out.println("You must be in the casting office to upgrade your actor rank.");
        }
    }

    // End Players Turn: (command: 'end turn')
    private void endPlayerTurn() {
        this.gameRef.activePlayer.endPlayerTurn();
        this.gameRef.nextPlayer();
        if (this.gameRef.isDayOver()) {
            this.gameRef.newDay();
        }
        if (!this.gameRef.isGameOver()) {
            this.gameRef.activePlayer.startPlayerTurn();
        }
    }

    // Starting Game Message:
    private void startGameMessage() {
        System.out.printf("%s%s%s%s%s%s%s", "   _____                 _                         _ \n",
                "  |  __ \\               | |                       | |\n",
                "  | |  | | ___  __ _  __| |_      _____   ___   __| |\n",
                "  | |  | |/ _ \\/ _` |/ _` \\ \\ /\\ / / _ \\ / _ \\ / _` |\n",
                "  | |__| |  __/ (_| | (_| |\\ V  V / (_) | (_) | (_| |\n",
                "  |_____/ \\___|\\__,_|\\__,_| \\_/\\_/ \\___/ \\___/ \\__,_|\n\n",
                "              By Ryan Lingg and Michael Albert               \n");
    }
}
