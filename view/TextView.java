/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * TextView.java
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import gameModel.*;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

//import com.sun.corba.se.spi.orbutil.fsm.Input;

public class TextView {

    Game gameRef;
    GameView gameView;
    ArrayList<PlayerView> PlayerViews = new ArrayList<PlayerView>();
    ArrayList<RoomView> RoomViews = new ArrayList<RoomView>();
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
        // Players:
        for (Player player : this.gameRef.playerList) {
            PlayerView p = new PlayerView(player);
            PlayerViews.add(p);
        }
        // Scenes:
        ArrayList<Room> roomList = this.gameRef.board.roomList;
        for (int i = 0; i < roomList.size(); i++) {
            RoomViews.add(new RoomView(roomList.get(i)));
            if (roomList.get(i).getScene() != null) {
                SceneView s = new SceneView(roomList.get(i).getScene());
                SceneViews.add(s);
            }
        }
    }


    /* Text Command List
     *
     * List of the recognised text commands that can be entered by the player using TextView
     */
    public static class textCommandList {
        public static final String ACTIVEPLAYER = "active player?";
        public static final String STATS = "stats";
        public static final String WHO = "who";
        public static final String WHERE = "where";
        public static final String ROOMSTATS = "room stats";
        public static final String MOVE = "move";
        public static final String UPGRADE = "upgrade";
        public static final String TAKEROLE = "take role";
        public static final String ACT = "act";
        public static final String REHEARSE = "rehearse";
        public static final String ENDTURN = "end turn";
        public static final String BLANK = "";
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
        ArrayList<String> activePlayerActionSet = new ArrayList<String>();

        //startGameMessage();
        this.gameRef.newDay();
        this.gameRef.activePlayer.startPlayerTurn();
        activePlayerActionSet = determinePlayerActionSet();
        // Switch on player movement
        while (!this.gameRef.isGameOver()) {

            String input = "";
            switch(input) {
            // Player Move
            case TextView.textCommandList.MOVE:
                if (activePlayerActionSet.contains(TextView.textCommandList.MOVE)) {
                    if (movementPhase(scanner)) {
                        activePlayerActionSet.remove(TextView.textCommandList.MOVE);
                        activePlayerActionSet = determinePlayerActionSet();
                        activePlayerActionSet.remove("move");
                    }
                } else {
                    System.out.println("Movement not allowed.");
                }
                break;

            // Player Take Role:
                case TextView.textCommandList.TAKEROLE:
                    if (activePlayerActionSet.contains(TextView.textCommandList.TAKEROLE)) {
                        if (takeRolePhase(scanner)) {
                            activePlayerActionSet.remove(TextView.textCommandList.TAKEROLE);
                        }
                    } else {
                        System.out.println("Cannot take a role right now.");
                    }
                    break;

            // Player Act:
               case TextView.textCommandList.ACT:
               if (activePlayerActionSet.contains(TextView.textCommandList.ACT)) {
                  actingPhase();
                  activePlayerActionSet.remove(TextView.textCommandList.ACT);
                  activePlayerActionSet.remove(TextView.textCommandList.REHEARSE);
               }
               else {
                  System.out.println("Cannot currently act.");
               }
               break;

            // Player Rehearse:
               case TextView.textCommandList.REHEARSE:
               if (activePlayerActionSet.contains(TextView.textCommandList.REHEARSE)) {
                  this.gameRef.activePlayer.addRehearse();
                  System.out.printf("You now have %s rehearse tokens. \n" , this.gameRef.activePlayer.timesRehearsed());
                  activePlayerActionSet.remove(TextView.textCommandList.ACT);
                  activePlayerActionSet.remove(TextView.textCommandList.REHEARSE);
               }
               else {
                  System.out.println("Cannot currently rehearse.");
               }
               break;

            // Player Upgrade:
            case TextView.textCommandList.UPGRADE:
                if (activePlayerActionSet.contains(TextView.textCommandList.UPGRADE)) {
                    if (upgradePhase(scanner)) {
                        activePlayerActionSet.remove(TextView.textCommandList.UPGRADE);
                    }
                } else {
                    System.out.println("Upgrade not allowed.");
                }
                break;

            // Player End Turn
            case "end turn":
                activePlayerActionSet = endPlayerTurn();
                break;


            /**** Cheats ****/
/*            case "sudo move":
                System.out.printf("Enter desired location: ");
                String input = toNoun(scanner.nextLine());
                Room dest = null;
                if ((dest = Board.nameToRoom.get(input)) != null) {
                  this.gameRef.activePlayer.setCurrentRole(null);
                  this.gameRef.activePlayer.setLocation(dest);
                } else {
                  System.out.printf("Room not found.\n");
                }
                break;


            case "sudo give cash":
                System.out.printf("Enter desired cash: ");
                int cash = Integer.parseInt(scanner.nextLine());
                this.gameRef.activePlayer.addCurrencies(cash, 0);
                break;

            case "sudo give credits":
                System.out.printf("Enter desired credits: ");
                int credits = Integer.parseInt(scanner.nextLine());
                this.gameRef.activePlayer.addCurrencies(0, credits);
                break;

            case "s":
                this.gameRef.board.decrementScenesRemaining();
                System.out.printf("Scences remaining is %s.\n", this.gameRef.board.getNumScenesRemaining());
                break; */

            case "":
                break;

            default:
                System.out.println("Command not recognized.");
                break;
            }

            if (activePlayerActionSet.size() == 0) {
                activePlayerActionSet = endPlayerTurn();
            }

        }


        System.out.println("Game over!");
        String winner = this.gameRef.calculateWinner();
        System.out.println(winner + " is the winner!");
        scanner.close();
    }


/**** Movement Functions ****/

    /* Movement Phase
     *
     * Displays the valid moves for the player then retireves player input about desired movement.
     * Checks if the movement is valid then updates the players location.
     */
    private Boolean movementPhase(Scanner scanner) {
        // Prompt section:
        displayPlayerPrompt("Which room do you want to move to?", this.gameRef.activePlayer.getLocation().getAdjRooms());
        // Response section:
        String input = scanner.nextLine();
        input = toNoun(input);
        Room dest = null;
        if ((dest = Board.nameToRoom.get(input)) != null) {
            if (this.gameRef.activePlayer.getLocation().getAdjRooms().contains(input)) {
                this.gameRef.activePlayer.movePlayer(dest);
                return true;
            } else {
                System.out.println("You cannot move to that room.");
            }
        } else {
            System.out.println("Room does not exist.");
        }
        return false;
    }

/**** Role Functions ****/

    /* Take Role
     *
     * Returns true if successfully took role, else false
     */
    private boolean takeRolePhase(Scanner scanner){
        Boolean tookRole = false;
        Room location = this.gameRef.activePlayer.getLocation();
        ArrayList<Role> availableRoles = new ArrayList<Role>();

        for (Role role : location.getScene().getSceneRoles()) {
            //If role isnt taken and isnt too high of level, add to list
            if (role.isRoleAvailable(this.gameRef.activePlayer.getRank())) {
                availableRoles.add(role);
            }
        }

        String availStr = "";
        if (availableRoles.size() != 0) {
            int iter = 0;
            for (Role role : availableRoles) {
                if (iter != 0) {
                    availStr = availStr + ", ";
                }
                availStr = availStr + "'" + role.getName() + "'";
                iter++;
            }


            System.out.printf("Select a role to take: (options: %s)\n", availStr);
            String input = scanner.nextLine();

            Role roleChosen = null;
            for (Role role : availableRoles) {
                if (role.getName().toLowerCase().equals(input.toLowerCase())) {
                    roleChosen = role;
                }
            }

            if (roleChosen !=  null) {
                System.out.println("Took role.");
                this.gameRef.activePlayer.setCurrentRole(roleChosen);
                roleChosen.setIsTaken(true);
                tookRole = true;
            } else {
                System.out.println("Couldnt find role.");
            }
        } else {
            System.out.println("No roles available for your rank. Come back with more experiance.\n");
            tookRole = true;
        }


        return tookRole;
    }

    /* Acting Phase
     *
     * Randomly determines if work was success, then pays out to player based on outcome.
     */
    private void actingPhase() {
       Random rand = new Random();
       int roll = rand.nextInt(6)+1 + this.gameRef.activePlayer.timesRehearsed();
       if (roll >= this.gameRef.activePlayer.getLocation().getScene().getBudget()) {
         if (this.gameRef.activePlayer.getCurrentRole().getOnCard()) {
            System.out.println("Success! You earned two credits.");
            this.gameRef.activePlayer.addCurrencies(0, 2);
            this.gameRef.activePlayer.getLocation().getScene().setShotsLeft(-1);
            if (this.gameRef.activePlayer.getLocation().getScene().getShotsLeft() == 0) {
              this.gameRef.activePlayer.getLocation().wrapScene();
              this.gameRef.board.decrementScenesRemaining();
            }
         }
         else {
            System.out.println("Success! You earned one dollar and one credit.");
            this.gameRef.activePlayer.addCurrencies(1, 1);
            this.gameRef.activePlayer.getLocation().getScene().setShotsLeft(-1);
            if (this.gameRef.activePlayer.getLocation().getScene().getShotsLeft() == 0) {
              this.gameRef.activePlayer.getLocation().wrapScene();
              this.gameRef.board.decrementScenesRemaining();
            }
         }
       }
       else {
         if (this.gameRef.activePlayer.getCurrentRole().getOnCard()) {
            System.out.println("Failure. You earned nothing.");
         }
         else {
            System.out.println("Failure. You earned one dollar.");
            this.gameRef.activePlayer.addCurrencies(1, 0);
         }
       }
    }


/**** Upgrade Functions ****/

    /* Upgrade Phase
     *
     * Retireves player input about desired upgrade.
     * Then calls upgrade with data to check if upgrade is valid and performs upgrade.
     */
    private Boolean upgradePhase(Scanner scanner) {
        String input;
        int desiredRank;
        String desiredPaymentType;

        // Check Location
        if (this.gameRef.activePlayer.getLocation().getName().equals("Office")) {
            // Check Rank
            if (this.gameRef.activePlayer.getRank() == 6) {
                System.out.println("You are already the max actor rank.");
            } else {
                System.out.print("Select the desired rank you wish to upgrade too. The upgrades and costs are the following: \n" +
                                "Rank: \t Cash \t Credit \n 2 \t 4 \t 5 \n 3 \t 10 \t 10 \n 4 \t 18 \t 15 \n 5 \t 28 \t 20" +
                                "\n 6 \t 40 \t 25 \n Select the desired rank: ");
                input = scanner.nextLine().toLowerCase();
                // Check Input
                try {
                    desiredRank = Integer.parseInt(input);
                    if ((desiredRank <= this.gameRef.activePlayer.getRank())) {
                        System.out.println("Invlid rank. You must choose a rank higher than your current rank.");
                    } else if (desiredRank > 6) {
                        System.out.println("Invlaid rank. You cannot choose a acting rank higher than the max of 6.");
                    } else {
                        System.out.print("Select the desired payment type ('Cash' or 'Credit'): ");
                        input = scanner.nextLine();
                        desiredPaymentType = input.toLowerCase();
                        if (desiredPaymentType.equals("cash") || desiredPaymentType.equals("credit")) {
                            return upgrade(desiredPaymentType, desiredRank);
                        } else {
                            System.out.println("Invalid payment type selected.");
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Desired rank must only contain a integer.");
                }
            }
        } else {
            System.out.println("You must be in the casting office to upgrade your actor rank.");
        }
        return false;
    }

    /* Upgrade
     *
     * Once a valid rank and money type have been input is entered this method checks if upgrade is
     * valid, and  upgrades the player if so, else notitifies the player of invalid choice
     */
    public boolean upgrade(String moneyType, int rank) {
        Boolean canUpgrade = validUpgrade(moneyType, rank);
        if (canUpgrade == false) {
            return false;
        }

        if (moneyType.equals("cash")) {
            if (rank == 2) {
                this.gameRef.activePlayer.setRank(2);
                System.out.println("Upgraded to rank 2 and lost 4 cash.");
                this.gameRef.activePlayer.removeCurrencies(4, 0);
            } else if (rank == 3) {
                this.gameRef.activePlayer.setRank(3);
                System.out.println("Upgraded to rank 3 and lost 10 cash.");
                this.gameRef.activePlayer.removeCurrencies(10, 0);
            } else if (rank == 4) {
                this.gameRef.activePlayer.setRank(4);
                System.out.println("Upgraded to rank 4 and lost 18 cash.");
                this.gameRef.activePlayer.removeCurrencies(18, 0);
            } else if (rank == 5) {
                this.gameRef.activePlayer.setRank(5);
                System.out.println("Upgraded to rank 5 and lost 28 cash.");
                this.gameRef.activePlayer.removeCurrencies(28, 0);
            } else {
                this.gameRef.activePlayer.setRank(6);
                System.out.println("Upgraded to rank 6 and lost 40 cash.");
                this.gameRef.activePlayer.removeCurrencies(40, 0);
            }
        }

        else {
            if (rank == 2) {
                this.gameRef.activePlayer.setRank(2);
                System.out.println("Upgraded to rank 2 and lost 5 credits.");
                this.gameRef.activePlayer.removeCurrencies(0, 5);
            } else if (rank == 3) {
                this.gameRef.activePlayer.setRank(3);
                System.out.println("Upgraded to rank 3 and lost 10 credits.");
                this.gameRef.activePlayer.removeCurrencies(0, 10);
            } else if (rank == 4) {
                this.gameRef.activePlayer.setRank(4);
                System.out.println("Upgraded to rank 4 and lost 15 credits.");
                this.gameRef.activePlayer.removeCurrencies(0, 15);
            } else if (rank == 5) {
                this.gameRef.activePlayer.setRank(5);
                System.out.println("Upgraded to rank 5 and lost 20 credits.");
                this.gameRef.activePlayer.removeCurrencies(0, 20);
            } else {
                this.gameRef.activePlayer.setRank(6);
                System.out.println("Upgraded to rank 6 and lost 25 credits.");
                this.gameRef.activePlayer.removeCurrencies(0, 25);
            }
        }
        return true;
    }


    /* Valid Upgrade Checker
     *
     * Makes sure input is valid and that player can afford upgrade, return false if not
     */
    private Boolean validUpgrade(String moneyType, int rank) {
       if (moneyType.equals("cash")) {
            if ((rank == 2 && this.gameRef.activePlayer.getCash() < 4) || (rank == 3 && this.gameRef.activePlayer.getCash() < 10) || (rank == 4 && this.gameRef.activePlayer.getCash() < 18)
                    || (rank == 5 && this.gameRef.activePlayer.getCash() < 28) || (rank == 6 && this.gameRef.activePlayer.getCash() < 40)) {
                System.out.println("You do not have enough cash to upgrade to this rank.");
                return false;
            }
        } else if (moneyType.equals("credit")) {
            if ((rank == 2 && this.gameRef.activePlayer.getCredits() < 5) || (rank == 3 && this.gameRef.activePlayer.getCredits() < 10) || (rank == 4 && this.gameRef.activePlayer.getCredits() < 15)
                    || (rank == 5 && this.gameRef.activePlayer.getCredits() < 20) || (rank == 6 && this.gameRef.activePlayer.getCredits() < 25)) {
                System.out.println("You do not have enough credits to upgrade to this rank.");
                return false;
            }
        }
        return true;
    }


    /* End Player Turn
     *
     * This function tells the active player object their turn has ended,
     * updates the day if needed, and starts the next day if the game has not ended.
     */
    private ArrayList<String> endPlayerTurn() {
        this.gameRef.activePlayer.endPlayerTurn();
        this.gameRef.nextPlayer();
        if (this.gameRef.isDayOver()) {
            this.gameRef.newDay();
        }
        if (!this.gameRef.isGameOver()) {
            this.gameRef.activePlayer.startPlayerTurn();
        }
        return determinePlayerActionSet();
    }


/**** General Helpers ****/

    /* Determine Player Action Set
     *
     * Based on the players location and whether or not they are in a role,
     * this method returns an arraylist of the possible valid actions they can
     * at the start of their turn.
     */
    private ArrayList<String> determinePlayerActionSet() {
        ArrayList<String> activePlayerActionSet = new ArrayList<String>();
        if (this.gameRef.activePlayer.getCurrentRole() != null) {
            activePlayerActionSet.add("act");
            if (this.gameRef.activePlayer.timesRehearsed()+1 <
            this.gameRef.activePlayer.getLocation().getScene().getBudget()) {
               activePlayerActionSet.add("rehearse");
            }
        } else {
            activePlayerActionSet.add("move");
            if (this.gameRef.activePlayer.getLocation().getScene() != null) {
                if (this.gameRef.activePlayer.getLocation().getScene().areRolesAvailable(this.gameRef.activePlayer.getRank())){
                    activePlayerActionSet.add("take role");
                }
            }
            if (this.gameRef.activePlayer.getLocation().getName().equals("Office")) {
                activePlayerActionSet.add("upgrade");
            }
        }
        activePlayerActionSet.add("end turn");
        return activePlayerActionSet;
    }


     /* Display Player Prompt
     *
     * Given a prompt and a list of options, this method displays them in
     * text format to the command line so the user knows what to choose from.
     */
    private void displayPlayerPrompt(String prompt, ArrayList<String> options) {
        System.out.printf("%s (Options: ", prompt);
        int i = 0;
        for (Object o : options.toArray()) {
            if (i != 0) {
                System.out.print(", ");
            }
            String s = String.valueOf(o);
            System.out.printf("'%s'", s);
            i++;
        }
        System.out.println(")");
    }

    /* Word To Noun
     *
     * Capitalizes the first character in each word of a string.
     */
    private String toNoun(String input){
        for (int i = 0; i < input.length(); i++){
            if (i == 0) {
                input = input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
            } else if (input.charAt(i-1) == ' '){
                input = input.substring(0,i) + input.substring(i,i+1).toUpperCase() + input.substring(i+1).toLowerCase();
            }
        }
        return input;
    }

    /* Starting Game Message
     *
     * Prints Deadwood Ascii Text Art Logo
     */
    private void startGameMessage() {
        System.out.printf("%s%s%s%s%s%s%s", "   _____                 _                         _ \n",
                "  |  __ \\               | |                       | |\n",
                "  | |  | | ___  __ _  __| |_      _____   ___   __| |\n",
                "  | |  | |/ _ \\/ _` |/ _` \\ \\ /\\ / / _ \\ / _ \\ / _` |\n",
                "  | |__| |  __/ (_| | (_| |\\ V  V / (_) | (_) | (_| |\n",
                "  |_____/ \\___|\\__,_|\\__,_| \\_/\\_/ \\___/ \\___/ \\__,_|\n\n",
                "            By Ryan Lingg and Michael Albert               \n");
    }
}
