import java.util.ArrayList;
import java.util.List;

public class Subject {

    //List of observers that subjects report too
   private List<myObserver> observers = new ArrayList<myObserver>();

   //Attach method for subjects to connect themselves with observers
   public void attach(myObserver observer){
      observers.add(observer);		
   }

   //Public class to let all observers know a update has occured
   public void notifyAllObservers(int message){
      for (myObserver observer : observers) {
         observer.update(message);
      }
   } 	
}