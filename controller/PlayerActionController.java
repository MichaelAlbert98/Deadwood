/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * TextView.java
 * Created: 11/03/2018
 * Revised: 12/06/2018
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


    /* movementPhase
    *
    * Displays the valid moves for the player. Updates player's location to whatever they choose.
    * 
    */
    public void movementPhase(String input) {
       this.gameRef.activePlayer.movePlayer(gameRef.board.nameToRoom.get(input));
       return;
    }

    /* rehearsePhase
     *
     *  Incremends players rehearse counter.
     */
    public void rehearsePhase(){
        this.gameRef.activePlayer.addRehearse();
        return;
    }

    /* findRolePhase
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

    /* takeRolePhase
     *
     * Moves player to the role that they chose.
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

    /* actingPhase
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
                this.gameRef.activePlayer.getLocation().notifyAllObservers(Room.RoomMessages.ShotUpdate);
                if (this.gameRef.activePlayer.getLocation().getScene().getShotsLeft() == 0) {
                    this.gameRef.activePlayer.getLocation().wrapScene();
                    this.gameRef.board.decrementScenesRemaining();
                }
            }
            else {
                this.gameRef.activePlayer.addCurrencies(1, 1);
                this.gameRef.activePlayer.getLocation().getScene().setShotsLeft(-1);
                this.gameRef.activePlayer.getLocation().notifyAllObservers(Room.RoomMessages.ShotUpdate);
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

    /* getLevels
     *
     * Returns the ranks that a player is able to upgrade to with their chosen moneyType.
     * 
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

    /* getMoneyTypes
     *
     * Returns types of money that player can use to upgrade.
     *
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

    /* upgradePhase
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


    /* End Player Turn
     *
     * This function tells the active player object their turn has ended,
     * updates the day if needed, and starts the next day if the game has not ended.
     */
    public String endPlayerTurn() {
        String winner = "";
        this.gameRef.activePlayer.endPlayerTurn();
        this.gameRef.nextPlayer();
        if (this.gameRef.isDayOver()) {
            this.gameRef.newDay();
        }
        if (!this.gameRef.isGameOver()) {
            this.gameRef.activePlayer.startPlayerTurn();
        }
        else if (this.gameRef.isGameOver()) {
            winner = this.gameRef.calculateWinner();
        }
        return winner;
    }
}
