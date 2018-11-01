import java.util.*;

public class Board {

   private Room[] roomList;
   private Map<String,Room> nameToRoom;
   private Room[][] adjacentRooms;
   private int scenesRemaining;
   
   public Board(Room[] roomList, Map<String,Room> nameToRoom, Room[][] adjacentRooms) {
      this.roomList = roomList;
      this.nameToRoom = nameToRoom;
      this.adjacentRooms = adjacentRooms;
      this.scenesRemaining = 10;
   }
   
   public Room nameToRoom(String name) {
      Room room = new Room();
      return room;
   }
   
   public Boolean areRoomsAdjacent(Room start, Room end) {
      return true;
   }
   
   public Boolean isValidMove(Room start, Room end) {
      return true; 
   }
   
   public Scene getScenesRemaining() {
      Scene scene = new Scene();
      return scene;
   }
   
   public void setScenesRemaining(int value) {
      return;
   }
   
}
