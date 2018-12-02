/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * myObserver.java - This interface is for classes that need to
 * observe the game.
 * Created 11/02/2018
 * Revised 11/16/2018
 */

//Abstract parent class for the specific observers
public interface myObserver {
    void update(String message);
}
