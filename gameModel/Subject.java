/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Subject.java
 * 11/2/11
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
   public void notifyAllObservers(int message) {
      for (myObserver observer : observers) {
        observer.update(message);
      }
   }
}
