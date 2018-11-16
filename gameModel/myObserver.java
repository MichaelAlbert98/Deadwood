/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * myObserver.java - This class is a parent class for classes that need to
 * observe the game.
 * Created 11/02/2018
 * Revised 11/16/2018
 */

//Abstract parent class for the specific observers
public abstract class myObserver {
    abstract void update(String message);
}
