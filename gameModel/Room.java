/* CSCI 345 - Object Oriented Design - Deadwood
 * Michael Albert and Ryan Lingg
 * Room.java
 * Created: 11/01/2018
 * Revised: 11/04/2018
 */

import java.util.ArrayList;

public class Room {

   private String name;
   private int[] xyhw;
   private ArrayList<String> adjacentRooms;
   private int roomIndex;
   private int shots;
   private ArrayList<int[]> shotsxyhw;
   private Player[] playersInRoom;
   private ArrayList<Role> roomRoles;
   public Scene roomScene;

   public Room() {
      this.name = "";
      this.xyhw = new int[4];
      this.adjacentRooms = new ArrayList<String>(0);
      this.roomIndex = 0;
      this.shots = 0;
      this.shotsxyhw = new ArrayList<int[]>(0);
      this.playersInRoom = null;
      this.roomRoles = new ArrayList<Role>(0);
      this.roomScene = null;
   }

   public void resetRoom(){
     //remove and reset room components
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
     this.name = name;
     return;
   }

   public int[] getxyhw() {
     return this.xyhw;
   }

   public void setxyhw(int index, int value) {
     this.xyhw[index] = value;
   }

   public ArrayList<String> getAdjRooms() {
      return this.adjacentRooms;
   }

   public void setAdjRooms(String room) {
     this.adjacentRooms.add(room);
     return;
   }

   public int getRoomIndex() {
      return this.roomIndex;
   }

   public void setRoomIndex(int index) {
     this.roomIndex = index;
     return;
   }

   public int getShots() {
      return this.shots;
   }

   public void setShots(int value) {
      this.shots = value;
      return;
   }

   public ArrayList<int[]> getShotsxyhw() {
     return this.shotsxyhw;
   }

   public void setShotsxyhw(int[] xyhw) {
     this.shotsxyhw.add(xyhw);
     return;
   }

   public Player[] getPlayersInRoom() {
      return this.playersInRoom;
   }

   public void setPlayersInRoom(Player player) {
      this.playersInRoom[0] = player;
      return;
   }

   public ArrayList<Role> getRoles() {
      return this.roomRoles;
   }

   public void setRoles(Role role) {
      this.roomRoles.add(role);
      return;
   }

   public Scene getScene() {
       return this.roomScene;
   }

   public void setScene(Scene scene) {
      this.roomScene = scene;
      return;
   }

}
