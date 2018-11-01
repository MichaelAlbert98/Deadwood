public class Game {
   
   private Board board;
   private Deck deck;
   private Player[] playerList;
   private Player currentPlayer;
   private int playerCount;
   private int currentDay;
   
   public Game() {
      this.board = null;
      this.deck = null;
      this.playerList = null;
      this.currentPlayer = null;
      this.playerCount = 0;
      this.currentDay = 1;
   }
   
   public Game(Board board, Deck deck, Player[] playerlist, int playerCount) {
      this.board = board;
      this.deck = deck;
      this.playerList = playerList;
      this.playerCount = playerCount;
      this.currentDay = 1;
   }
   
   private void startGame() {
      return;
   }
   
   private void newDay() {
      return;
   }
   
   private void upgradePhase() {
      return;
   }
   
   private void movementPhase() {
      return;
   }
   
   private void actingPhase() {
      return;
   }
   
   private void calculateScores() {
      return;
   }
   
   private void displayScores() {
      return;
   }
   
   private void declareWinner() {
      return;
   }
   
   private void endGame() {
      return;
   }
   
}
