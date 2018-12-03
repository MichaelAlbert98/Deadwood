/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * SceneView.java
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */

import java.lang.*;
import gameModel.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class SceneView extends JFrame implements myObserver {

    //Local Variables
    Scene sceneRef;

    //Constructor
    SceneView(Scene s){
        this.sceneRef = s;
        s.attach(this);
    }

    //Local SceneView Update Override
    @Override
    public void update(String message){

    }

}
