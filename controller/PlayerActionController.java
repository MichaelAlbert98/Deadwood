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
            activePlayerActionSet.add("ACT");
            if (this.gameRef.activePlayer.timesRehearsed()+1 <
                    this.gameRef.activePlayer.getLocation().getScene().getBudget()) {
                activePlayerActionSet.add("REHEARSE");
            }
        } else {
            activePlayerActionSet.add("MOVE");
            if (this.gameRef.activePlayer.getLocation().getScene() != null) {
                if (this.gameRef.activePlayer.getLocation().getScene().areRolesAvailable(this.gameRef.activePlayer.getRank())){
                    activePlayerActionSet.add("TAKE ROLE");
                }
            }
            if (this.gameRef.activePlayer.getLocation().getName().equals("Office")) {
                activePlayerActionSet.add("UPGRADE");
            }
        }
        activePlayerActionSet.add("END TURN");
        return activePlayerActionSet;
    }


    /* Movement Phase
    *
    * Displays the valid moves for the player then retireves player input about desired movement.
    * Checks if the movement is valid then updates the players location.
    */
    public void movementPhase(String input) {
                this.gameRef.activePlayer.movePlayer(gameRef.board.nameToRoom.get(input));
                return;
    }

    /* Rehearse
     *
     *  Incremends players rehearse counter.
     */
    public void rehearsePhase(){
        this.gameRef.activePlayer.addRehearse();
        return;
    }

    /* Find Role
     *
     * Returns ArrayList of role names that player can take.
     */
    public ArrayList<String> findRolePhase() {
        Room location = this.gameRef.activePlayer.getLocation();
        ArrayList<String> availableRoles = new ArrayList<String>();

        for (Role role : location.getScene().getSceneRoles()) {
            //If role isnt taken and isnt too high of level, add to list
            if (role.isRoleAvailable(this.gameRef.activePlayer.getRank())) {
                availableRoles.add(role.getName());
            }
        }
        return availableRoles;
    }

    /* Take Role
     *
     * Returns true if successfully took role, else false
     */
    public void takeRolePhase(String input){
        ArrayList<Role> availableRoles = this.gameRef.activePlayer.getLocation().getScene().getSceneRoles();
        Role roleChosen = null;
        for (Role role : availableRoles) {
            if (role.getName().toLowerCase().equals(input.toLowerCase())) {
                roleChosen = role;
            }
        }
        this.gameRef.activePlayer.setCurrentRole(roleChosen);
        roleChosen.setIsTaken(true);
    }

    /* Acting Phase
     *
     * Randomly determines if work was success, then pays out to player based on outcome.
     */
    public boolean actingPhase() {
        Random rand = new Random();
        int roll = rand.nextInt(6)+1 + this.gameRef.activePlayer.timesRehearsed();
        if (roll >= this.gameRef.activePlayer.getLocation().getScene().getBudget()) {
            if (this.gameRef.activePlayer.getCurrentRole().getOnCard()) {
                this.gameRef.activePlayer.addCurrencies(0, 2);
                this.gameRef.activePlayer.getLocation().getScene().setShotsLeft(-1);
                if (this.gameRef.activePlayer.getLocation().getScene().getShotsLeft() == 0) {
                    this.gameRef.activePlayer.getLocation().wrapScene();
                    this.gameRef.board.decrementScenesRemaining();
                }
            }
            else {
                this.gameRef.activePlayer.addCurrencies(1, 1);
                this.gameRef.activePlayer.getLocation().getScene().setShotsLeft(-1);
                if (this.gameRef.activePlayer.getLocation().getScene().getShotsLeft() == 0) {
                    this.gameRef.activePlayer.getLocation().wrapScene();
                    this.gameRef.board.decrementScenesRemaining();
                }
            }
            return true;
        }
        else {
            if (this.gameRef.activePlayer.getCurrentRole().getOnCard()) {
            }
            else {
                this.gameRef.activePlayer.addCurrencies(1, 0);
            }
        }
        return false;
    }


    /* Upgrade Phase
     *
     * Retireves player input about desired upgrade.
     * Then calls upgrade with data to check if upgrade is valid and performs upgrade.
     */
    public ArrayList<Integer> getLevels(String type) {
        ArrayList<Integer> levels = new ArrayList<Integer>();
        if (type.equals("Cash")) {
            int cash = gameRef.activePlayer.getCash();
            int rank = gameRef.activePlayer.getRank();
            if (cash >= 4 && rank == 1) {
                levels.add(2);
            }
            if (cash >= 10 && rank <= 2) {
                levels.add(3);
            }
            if (cash >= 18 && rank <= 3) {
                levels.add(4);
            }
            if (cash >= 28 && rank <= 4) {
                levels.add(5);
            }
            if (cash >= 40 && rank <= 5) {
                levels.add(6);
            }
        }
        if (type.equals("Credits")) {
            int credits = gameRef.activePlayer.getCredits();
            int rank = gameRef.activePlayer.getRank();
            if (credits >= 5 && rank == 1) {
                levels.add(2);
            }
            if (credits >= 10 && rank <= 2) {
                levels.add(3);
            }
            if (credits >= 15 && rank <= 3) {
                levels.add(4);
            }
            if (credits >= 20 && rank <= 4) {
                levels.add(5);
            }
            if (credits >= 25 && rank <= 5) {
                levels.add(6);
            }
        }
        return levels;
    }

    /* Upgrade Phase
     *
     * Retireves player input about desired upgrade.
     * Then calls upgrade with data to check if upgrade is valid and performs upgrade.
     */
    public ArrayList<String> getMoneyTypes() {
        ArrayList<String> types = new ArrayList<String>();
        if (gameRef.activePlayer.getCash() >= 4)
            types.add("Cash");
        if (gameRef.activePlayer.getCredits() >= 5) {
            types.add("Credits");
        }
        return types;
    }

    /* Upgrade Phase
     *
     * Retireves player input about desired upgrade.
     * Then calls upgrade with data to check if upgrade is valid and performs upgrade.
     */
    public void upgradePhase(String moneyType,int level) {
        int[] cashLevels = {4,10,18,28,40};
        int[] creditLevels = {5,10,15,20,25};
        gameRef.activePlayer.setRank(level);
        if (moneyType.equals("Cash")) {
            gameRef.activePlayer.removeCurrencies(cashLevels[level-2],0);
        }
        if (moneyType.equals("Credits")) {
            gameRef.activePlayer.removeCurrencies(0,creditLevels[level-2]);
        }
        return;
    }


    /* Upgrade
     *
     * Once a valid rank and money type have been input is entered this method checks if upgrade is
     * valid, and  upgrades the player if so, else notitifies the player of invalid choice
     */
  /*  public boolean upgrade(String moneyType, int rank) {
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
    } */


    /* Valid Upgrade Checker
     *
     * Makes sure input is valid and that player can afford upgrade, return false if not
     */
  /*  private Boolean validUpgrade(String moneyType, int rank) {
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
    } */

    /* End Player Turn
     *
     * This function tells the active player object their turn has ended,
     * updates the day if needed, and starts the next day if the game has not ended.
     */
    public void endPlayerTurn() {
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
