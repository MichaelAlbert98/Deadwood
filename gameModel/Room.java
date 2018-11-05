public class Room {

   private String name;
   private String[] adjacentRooms;
   private int roomIndex;
   private int counters;
   private Player[] playersInRoom;
   private Role[] roomRoles;
   public Scene roomScene;

   public Room() {
      this.name = "";
      this.adjacentRooms = null;
      this.roomIndex = 0;
      this.counters = 0;
      this.roomRoles = null;
      this.roomScene = null;
   }

   public Room(String name, String[] adjRooms, int index, int counters, Role[] roomRoles, Scene roomScene) {
      this.name = name;
      this.adjacentRooms = adjRooms;
      this.roomIndex = index;
      this.counters = counters;
      this.roomRoles = roomRoles;
      this.roomScene = roomScene;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
     this.name = name;
     return;
   }

   public String[] getAdjRooms() {
      return this.adjacentRooms;
   }

   public void setAdjRooms(String[] rooms) {
     this.adjacentRooms = rooms;
     return;
   }

   public int getRoomIndex() {
      return this.roomIndex;
   }

   public void setRoomIndex(int index) {
     this.roomIndex = index;
     return;
   }

   public int getCounters() {
      return this.counters;
   }

   public void setCounters(int value) {
      this.counters = value;
      return;
   }

   public Player[] getPlayersInRoom() {
      return this.playersInRoom;
   }

   public void setPlayersInRoom(Player player) {
      this.playersInRoom[0] = player;
      return;
   }

   public Role[] getRoles() {
      return this.roomRoles;
   }

   public void setRoles(Role[] roles) {
      this.roomRoles = roles;
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
