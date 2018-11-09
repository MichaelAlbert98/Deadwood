import java.util.*;

public class Board {

   public ArrayList<Room> roomList;
   private Map<String,Room> nameToRoom;
   private Room[][] adjacentRooms;
   private Deck deck;
   public int scenesRemaining;

   public Board() {
     this.roomList = new ArrayList<Room>(0);
     // this.nameToRoom = null;
     this.adjacentRooms = new Room[12][12];
     this.deck = new Deck();
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

   public int getNumScenesRemaining() {
      return this.scenesRemaining;
   }

   public void setScenesRemaining(int value) {
      return;
   }

   public void resetBoard() {
      int roomIter = 0;
      while (roomIter < this.roomList.size()) {
        Room room = this.roomList.get(roomIter);
        room.resetRoom();
      }
      this.scenesRemaining = 10;
   }

}
