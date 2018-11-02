import java.lang.*;
import gameModel.*;

public class SceneView extends myObserver {

    Scene sceneRef;
    SceneView(Scene s){
        this.scenerRef = s;
        s.attach(this);
    }

    @Override
    void update(int message){
        
    }

}