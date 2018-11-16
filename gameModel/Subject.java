/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Subject.java - This class is used to give classes that extend it methods to
 * use observers.
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import java.util.ArrayList;
import java.util.List;

public class Subject {

   //List of observers that subjects report too
   private List<myObserver> observers = new ArrayList<myObserver>();

   //Attach method for subjects to connect themselves with observers
   public void attach(myObserver observer) {
      observers.add(observer);
   }

   public void detach(myObserver observer) {
      observers.remove(observer);
   }

   //Public brodcast to let all observers know a update has occured
   public void notifyAllObservers(String message) {
      for (myObserver observer : observers) {
        observer.update(message);
      }
    }
}
