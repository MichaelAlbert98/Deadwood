public class Deck {
   
   private Card[] cards;
   private int topIndex;
   
   public Deck() {
      this.cards = null;
      this.topIndex = 0;
   }
   
   public Deck(Card[] cards) {
      this.cards = cards;
      this.topIndex = 0;
   }
   
   private void shuffleDeck() {
      return;
   }
   
   public Card[] getCards() {
      return this.cards;
   }
   
   public void setCards(Card[] cards) {
      this.cards = cards;
   }
   
   public int getTopIndex() {
      return this.topIndex;
   }
   
   public void setTopIndex(int value) {
      this.topIndex = value;
   }
   
}
