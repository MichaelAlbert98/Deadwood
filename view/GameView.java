/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * GameView.java
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */


import gameModel.*;
import java.lang.*;


public class GameView implements myObserver {
    //Local Variables
    Game gameRef;

    /* GameView Constructor
     *
     * The constructor makes a reference to the game it will view,
     * then attaches itself to the observer list the game will report to.
     */
    GameView(Game g) {
        this.gameRef = g;
        g.attach(this);
    }

    /* Update
     *
     * This method is the link that reports changes in the model to the view.
     * Update recieves the message string which corresponds a update in the view.
     * In the text implementation all this does is print out a corresponding
     * message to the terminal.
     */
    @Override
    public void update(String message){
        //New Day Message:
        if (Game.gameMessages.newDay.equals(message)) {
            System.out.printf("\nDay %d starting!\n", gameRef.getCurrentDay()); }

        //End of Day Message
        else if (Game.gameMessages.newDay.equals(message)) {
            System.out.printf("\nDay %d has ended!\n", gameRef.getCurrentDay());
        }
    }
}