/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * TextView.java
 * Created: 11/03/2018
 * Revised: 12/03/2018
 */


import gameModel.*;
import view.*;

import java.util.ArrayList;
import java.util.Random;

public class PlayerActionController {

    Game gameRef;

    PlayerActionController(Game game) {
        this.gameRef = game;
    }

    /* Determine Player Action Set
     *
     * Based on the players location and whether or not they are in a role,
     * this method returns an arraylist of the possible valid actions they can
     * at the start of their turn.
     */
    public ArrayList<String> determinePlayerActionSet() {
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


    /* Movement Phase
    *
    * Displays the valid moves for the player then retireves player input about desired movement.
    * Checks if the movement is valid then updates the players location.
    */
    private Boolean movementPhase() {
        // Prompt section:
        //displayPlayerPrompt("Which room do you want to move to?", this.gameRef.activePlayer.getLocation().getAdjRooms());

        // Response section:
        String input = "";

        //FILL IN LATER USING BUTTONS

        //Try to move player to the new room.
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


    /* Take Role
     *
     * Returns true if successfully took role, else false
     */
    private boolean takeRolePhase(){
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

            //DISPLAY ROLES AND GET INPUT: (USE BUTTONS)
            //System.out.printf("Select a role to take: (options: %s)\n", availStr);


            String input = ""; //scanner.nextLine();
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


    /* Upgrade Phase
     *
     * Retireves player input about desired upgrade.
     * Then calls upgrade with data to check if upgrade is valid and performs upgrade.
     */
    private Boolean upgradePhase() {
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
                input = ""; //scanner.nextLine().toLowerCase();
                // Check Input
                try {
                    desiredRank = Integer.parseInt(input);
                    if ((desiredRank <= this.gameRef.activePlayer.getRank())) {
                        System.out.println("Invlid rank. You must choose a rank higher than your current rank.");
                    } else if (desiredRank > 6) {
                        System.out.println("Invlaid rank. You cannot choose a acting rank higher than the max of 6.");
                    } else {
                        System.out.print("Select the desired payment type ('Cash' or 'Credit'): ");
                        input = ""; //scanner.nextLine();
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
    private void endPlayerTurn() {
        this.gameRef.activePlayer.endPlayerTurn();
        this.gameRef.nextPlayer();
        if (this.gameRef.isDayOver()) {
            this.gameRef.newDay();
        }
        if (!this.gameRef.isGameOver()) {
            this.gameRef.activePlayer.startPlayerTurn();
        }
        return;
    }
}
