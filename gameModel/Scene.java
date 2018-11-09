/* CSCI 345 - Object Oriented Design - Deadwood
 * Michael Albert and Ryan Lingg
 * Scene.java
 * Created 11/02/2018
 * Revised 11/08/2018
 */

import java.util.ArrayList;

public class Scene extends Subject {

   private String name;
   private String img;
   private int sceneNum;
   private String desc;
   private Room location;
   private Boolean isCardUp;
   private Boolean isSceneDone;
   private ArrayList<Role> sceneRoles;
   private int shotsLeft;
   private int budget;

   public Scene() {
      this.name = "";
      this.img = "";
      this.sceneNum = 0;
      this.desc = "";
      this.location = null;
      this.isCardUp = false;
      this.isSceneDone = false;
      this.sceneRoles = new ArrayList<Role>(0);
      this.shotsLeft = 0;
      this.budget = 0;
   }

   private void wrapUpScene() {
      return;
   }

   private Boolean isCardFaceUp() {
      return this.isCardUp;
   }

   private void flipCardFaceUp() {
      this.isCardUp = true;
      return;
   }

   public Boolean getIsSceneDone() {
      return this.isSceneDone;
   }

   public void setIsSceneDone(Boolean truth) {
     this.isSceneDone = truth;
     return;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
     this.name = name;
     return;
   }

   public String getImg() {
      return this.img;
   }

   public void setImg(String img) {
     this.img = img;
     return;
   }

   public int getSceneNum() {
      return this.sceneNum;
   }

   public void setSceneNum(int sceneNum) {
     this.sceneNum = sceneNum;
     return;
   }

   public String getDesc() {
     return this.desc;
   }

   public void setDesc(String desc) {
     this.desc = desc;
     return;
   }

   public Room getLocation() {
      return this.location;
   }

    public void setLoccation(Room loc) {
      this.location = loc;
   }

   public ArrayList<Role> getSceneRoles() {
      return this.sceneRoles;
   }

   public void setSceneRoles(Role role) {
      this.sceneRoles.add(role);
      return;
   }

   public int getShotsLeft() {
      return this.shotsLeft;
   }

   private void setShotsLeft(int value) {
      this.shotsLeft = value;
   }

   public int getBudget() {
      return this.budget;
   }

   public void setBudget(int budget) {
      this.budget = budget;
   }

}
