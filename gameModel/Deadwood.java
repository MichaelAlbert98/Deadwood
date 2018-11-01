/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Deadwood.java
 * 11/31/11
 */


public class Deadwood {

    //Deadwood Attributes
    private static int numPlayers;
    private static final int minPlayers = 2;
    private static final int maxPlayers = 8;
    private static int numDays;
    private static Game game;
    

    /* Main Method
    * 
    * Controller for the Deadwood game.
    */
    public static void main(String[] args) {

        try {

            //1) Set the number of players for the game
            if (args.length == 0){
                setNumPlayers = 2;
            } else {
                setNumPlayers(args[0]);
            }

            //2) Set the number of days designated for this player count
            setNumDays();

            //3) Initalize and start the game instance
            Game g = new Game(numPlayers,numDays);

        } catch (numPlayersException e) {
            System.out.println("There must be between 2 and 8 players.");
        } catch (NumberFormatException e){
            System.out.println("Number of players desired must be a integer between 2 and 8.");
        } catch (exception e){
            System.out.println("Unidentified Error encountered. Exiting Deadwood.");
        }

        System.out.println("Game Over.");
    }


    /* setNumPlayers
    *
    * Setter for the number of players in a Deadwood game.
    */
    private setNumPlayers(String countStr) throws numPlayersException, NumberFormatException {
        int playerCount;
        if ((playerCount = Integer.parseInt(countStr))) {

        }
        if ((playerCount < minPlayers) && (playerCount > maxPlayers)) {
            throw new numPlayersException();
        } else {
            numPlayers = playerCount;
        }
    }

    /* setNumPlayers
    *
    * Setter for the number of players in a Deadwood game.
    */
    private setNumDays() throws numPlayersException, NumberFormatException {
        if (numPlayers < 4) {
            numDays = 3;
        } else {
            numDays = 4;
        }
    }
}