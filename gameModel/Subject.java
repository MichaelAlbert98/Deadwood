import java.util.ArrayList;
import java.util.List;

public class Subject {
    
    

   private List<myObserver> observers = new ArrayList<myObserver>();

   public void attach(myObserver observer){
      observers.add(observer);		
   }

   public void notifyAllObservers(int message){
      for (myObserver observer : observers) {
         observer.update(message);
      }
   } 	
}