/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * SceneView.java
 * 11/2/11
 */

import java.lang.*;
//import gameModel.*;

public class SceneView extends myObserver {

    //Local Variables
    Scene sceneRef;

    //Constructor
    SceneView(Scene s){
        this.sceneRef = s;
        s.attach(this);
    }

    //Local SceneView Update Override
    @Override
    void update(String message){

    }

}
