/* CSCI 345 - Object Oriented Design - Deadwood
 * Ryan Lingg and Michael Albert
 * Deadwood.java - This class initializes the game and board with a given
 * number of players.
 * Created: 11/03/2018
 * Revised: 11/16/2018
 */


import org.w3c.dom.Document;

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Deadwood {

    //Deadwood Attributes
    public static int numPlayers = 0;
    private static final int minPlayers = 2;
    private static final int maxPlayers = 8;
    private static int numDays;

    /* Main Method
    *
    * Controller for the Deadwood game.
    */
    public static void main(String[] args) {

        try{

            //read in card data.
            ParseXML parser = new ParseXML();
            Document d = parser.getDocFromFile("../figures/cards.xml");
            Deck deck = new Deck();
            parser.readCardData(d,deck);
            deck.shuffleDeck();

            //read in board data.
            d = parser.getDocFromFile("../figures/board.xml");
            Board board = Board.getBoard(deck);
            parser.readBoardData(d,board);

            guiView gameBoard = new guiView();
            gameBoard.setVisible(true);

            // Take input from the user about number of players
            while (numPlayers == 0) {
                try{
                    int temp = Integer.parseInt(JOptionPane.showInputDialog(gameBoard, "How many players?"));
                    if ((temp == 2) || (temp == 3)) {
                        numPlayers = temp;
                    }
                    else {
                        JOptionPane.showMessageDialog(gameBoard, "Please input 2 or 3.", "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(gameBoard, "Please input a number.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
            }
            numDays = 3;
            Game game = Game.getGame(numPlayers,numDays,board);
            for (int i = 0; i < numPlayers; i++) {
                while (true) {
                    try {
                        String name = JOptionPane.showInputDialog(gameBoard, "What is your name Player " + (i + 1) + "?");
                        game.playerList.get(i).setName(name);
                        if (name.matches("")) {
                            JOptionPane.showMessageDialog(gameBoard, "Name must contain characters.", "Warning", JOptionPane.ERROR_MESSAGE);
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(gameBoard, "Input a name.", "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (i == 0) {
                    game.playerList.get(i).image = "../figures/dice/b1.png";
                }
                if (i == 1) {
                    game.playerList.get(i).image = "../figures/dice/c1.png";
                }
                if (i == 2) {
                    game.playerList.get(i).image = "../figures/dice/g1.png";
                }
            }
            gameBoard = new guiView(game);



        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error encountered. Exiting Deadwood.");
        }
    }
}