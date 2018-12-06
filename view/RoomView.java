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
import javax.imageio.ImageIO;



public class RoomView extends JFrame implements myObserver {

    //Local Variables
    Room roomRef;
    JLayeredPane guiWindow;
    JLabel guiScene;
    JLabel[] guiShot;

    //Constructor
    RoomView(Room ref, JLayeredPane window){
        this.guiScene = new JLabel();
        this.guiShot = new JLabel[5];
        for(int i=0;i<this.guiShot.length;i++) {
            this.guiShot[i] = new JLabel();
        }
        this.guiWindow = window;
        this.roomRef = ref;
        this.roomRef.attach(this);
    }

    //Local SceneView Update Override
    @Override
    public void update(String message){

        switch (message) {

            // Scene Updated
            case (Room.RoomMessages.SceneUpdate):
                guiWindow.remove(guiScene);
                ImageIcon sceneIcon = new ImageIcon(this.roomRef.roomScene.getImg());
                Image scaledIcon = sceneIcon.getImage();
                scaledIcon = scaledIcon.getScaledInstance(roomRef.getxyhw()[3],roomRef.getxyhw()[2], java.awt.Image.SCALE_SMOOTH);
                sceneIcon = new ImageIcon(scaledIcon);
                guiScene = new JLabel();
                guiScene.setIcon(sceneIcon);
                guiScene.setBounds(roomRef.getxyhw()[0], roomRef.getxyhw()[1], roomRef.getxyhw()[3],roomRef.getxyhw()[2]);
                guiWindow.add(guiScene, new Integer(1));
                guiScene.revalidate();
                guiWindow.repaint();
                break;


            case (Room.RoomMessages.ShotUpdate):
                ImageIcon shotIcon = new ImageIcon("../figures/shot.png");
                for (int i = 0; i < roomRef.getShots(); i++) {
                    guiWindow.remove(guiShot[i]);
                    guiShot[i].revalidate();
                }
                for (int i = 0; i < roomRef.roomScene.getShotsLeft(); i++) {
                    guiShot[i] = new JLabel();
                    guiShot[i].setIcon(shotIcon);
                    int[] shotXYWH = this.roomRef.getShotsxyhw().get(i);
                    guiShot[i].setBounds(shotXYWH[0], shotXYWH[1], shotXYWH[3], shotXYWH[2]);
                    guiWindow.add(guiShot[i], new Integer(1));
                    guiShot[i].revalidate();
                    guiWindow.repaint();
                }
                guiWindow.repaint();
                break;
        }
    }

}
