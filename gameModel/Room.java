/* CSCI 345 - Object Oriented Design - Deadwood
 * Michael Albert and Ryan Lingg
 * Room.java - This class is designed to hold the attributes of
 * one of twelve locations in Deadwood.
 * Created: 11/01/2018
 * Revised: 12/06/2018
 */

import java.util.*;

public class Room extends Subject {

  private String name;
  private int[] xyhw;
  public ArrayList<String> adjacentRooms; // used to check if a player move is valid
  private int shots;
  private ArrayList<int[]> shotsxyhw;
  private ArrayList<Player> playersInRoom;
  private ArrayList<Role> roomRoles;
  public Scene roomScene;


  /*
   * Room Messages
   *
   * The list of message identifiers that will correspond to board
   * updates for the view model to interpret and perform.
   */
  public static class RoomMessages {
    public static final String ResetRoom = "RESETROOM";
    public static final String WrapScene = "WRAPSCENE";
    public static final String SceneUpdate = "SCENEUPDATE";
    public static final String ShotUpdate = "SHOTUPDATE";
  }
  

  /**** CONSTRUCTOR ****/
  public Room() {
    this.name = "";
    this.xyhw = new int[4];
    this.adjacentRooms = new ArrayList<String>(0);
    this.shots = 0;
    this.shotsxyhw = new ArrayList<int[]>(0);
    this.playersInRoom = new ArrayList<Player>(0);
    this.roomRoles = new ArrayList<Role>(0);
    this.roomScene = null;
  }

  /**** METHODS ****/

  /* Roles and Scenes */

  public ArrayList<Role> getRoomRoles() {
    return this.roomRoles;
  }

  public void addRoleToRoom(Role role) {
    this.roomRoles.add(role);
    return;
  }

  public void addRoomRolesToScene() {
     for (int i = 0; i < this.roomRoles.size(); i++) {
        this.roomScene.getSceneRoles().add(this.roomRoles.get(i));
     }
  }

  // pays out to players on/off scene, sets scene to done, removes players from
  // scene.
  public void wrapScene() {
    ArrayList<Player> onCardPlayers = new ArrayList<Player>();
    ArrayList<Player> offCardPlayers = new ArrayList<Player>();

    for (int i = 0; i < this.playersInRoom.size(); i++) {
      if (this.playersInRoom.get(i).getCurrentRole() != null) {
        if (this.playersInRoom.get(i).getCurrentRole().getOnCard() == true) {
          onCardPlayers.add(this.playersInRoom.get(i));
        } else {
          offCardPlayers.add(this.playersInRoom.get(i));
        }
      }
    }

    // Pay off card players if there is an on card player, remove from scene.
    for (int i = 0; i < offCardPlayers.size(); i++) {
      if (onCardPlayers.size() > 0) {
        offCardPlayers.get(i).addCurrencies(offCardPlayers.get(i).getCurrentRole().getRank(), 0);
      }
      offCardPlayers.get(i).setCurrentRole(null);
      offCardPlayers.get(i).resetRehearse();
    }

    if (onCardPlayers.size() > 0) {
       // Pay on card players starting from highest role.
       Collections.sort(onCardPlayers, new PlayerRoleComparator());
       Random rand = new Random();
       for (int i = 0; i < this.roomScene.getBudget(); i++) {
         int k = i % onCardPlayers.size();
         int roll = rand.nextInt(6) + 1;
         onCardPlayers.get(k).addCurrencies(roll, 0);
       }

       // Remove on card players from scene.
       for (int i = 0; i < onCardPlayers.size(); i++) {
         onCardPlayers.get(i).setCurrentRole(null);
         onCardPlayers.get(i).resetRehearse();
       }
    }
    this.roomScene.isSceneDone = true;
    this.notifyAllObservers(RoomMessages.SceneUpdate);
    //Set scene in room to null
    this.roomScene = null;

    //this.notifyAllObservers("REMOVECARD")

    return;
  }

  // Resets the room at the end of the day.
  public void resetRoom(Deck deck) {
    this.playersInRoom = new ArrayList<Player>();
    if (!this.name.equals("Trailer") && !this.name.equals("Office")) {
      this.roomScene = deck.getTopScene();
      this.roomScene.setLocation(this);
      this.roomScene.setShotsLeft(this.shots);
      this.notifyAllObservers(RoomMessages.ShotUpdate);
      for (int i=0;i<this.roomRoles.size();i++) {
        this.roomRoles.get(i).setIsTaken(false);
      }
      addRoomRolesToScene();
      this.notifyAllObservers(RoomMessages.SceneUpdate);
    }
  }


  public void printAllRolesInRoom() {
    for (Role r : this.roomRoles) {
      r.displayRoleStats();
    }
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

  /* Room Scene Shots */

  public Scene getScene() {
    return this.roomScene;
  }

  public int getShots() {
    return this.shots;
  }

  public void setShots(int value) {
    this.shots = this.shots + value;
    return;
  }

  public ArrayList<int[]> getShotsxyhw() {
    return this.shotsxyhw;
  }

  public void setShotsxyhw(int[] xyhw) {
    this.shotsxyhw.add(xyhw);
    return;
  }

  /* Players In Room */
  public ArrayList<Player> getPlayersInRoom() {
    return this.playersInRoom;
  }

  public void removePlayerFromRoom(Player player) {
    this.playersInRoom.remove(player);
  }

  public void addPlayerToRoom(Player player) {
    this.playersInRoom.add(player);
    if (this.roomScene != null) {
      if (!this.roomScene.isCardFaceUp()) {
        this.roomScene.flipCardFaceUp();
        this.notifyAllObservers(RoomMessages.SceneUpdate);
      }
    }
    return;
  }

}
