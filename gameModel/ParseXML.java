
/* CSCI 345 - Object Oriented Design - Deadwood
 * Michael Albert and Ryan Lingg
 * ParseXML.java
 * Created 11/05/2018
 * Revised 11/06/2018
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.util.ArrayList;

public class ParseXML {

  public ParseXML() {

  }

  /*
   * // Temporary main method for testing. public static void main(String[] args)
   * throws ParserConfigurationException { Document d =
   * getDocFromFile("../figures/board.xml"); Board b = new Board();
   * readBoardData(d,b); System.out.println("finish"); }
   */

  // building a document from the XML file
  // returns a Document object after loading the book.xml file.
  public Document getDocFromFile(String filename) throws ParserConfigurationException {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = null;

    try {
      doc = db.parse(filename);
    } catch (Exception ex) {
      System.out.println("XML parse failure");
      ex.printStackTrace();
    }
    return doc;
    // exception handling

  }

  // reads data from XML file and sets Scene data in Deck
  public void readCardData(Document d, Deck deck) {

    Element root = d.getDocumentElement();

    NodeList cards = root.getElementsByTagName("card");

    for (int i = 0; i < cards.getLength(); i++) {

      // reads data from the nodes
      Node card = cards.item(i);
      Scene scene = new Scene();
      String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
      scene.setName(cardName);
      String cardImg = card.getAttributes().getNamedItem("img").getNodeValue();
      scene.setImg(cardImg);
      String cardBudget = card.getAttributes().getNamedItem("budget").getNodeValue();
      scene.setBudget(Integer.parseInt(cardBudget));

      // reads data
      NodeList children = card.getChildNodes();
      for (int j = 0; j < children.getLength(); j++) {
        Node sub = children.item(j);

        if ("scene".equals(sub.getNodeName())) {
          String sceneNumber = sub.getAttributes().getNamedItem("number").getNodeValue();
          scene.setSceneNum(Integer.parseInt(sceneNumber));
          String desc = sub.getTextContent();
          scene.setDesc(desc);
        }

        else if ("part".equals(sub.getNodeName())) {
          Role role = new Role();
          String name = sub.getAttributes().getNamedItem("name").getNodeValue();
          role.setName(name);
          String level = sub.getAttributes().getNamedItem("level").getNodeValue();
          role.setRank(Integer.parseInt(level));

          NodeList infos = sub.getChildNodes();
          for (int k = 0; k < infos.getLength(); k++) {
            Node info = infos.item(k);

            if ("area".equals(info.getNodeName())) {
              String x = info.getAttributes().getNamedItem("x").getNodeValue();
              role.setxyhw(0, (Integer.parseInt(x)));
              String y = info.getAttributes().getNamedItem("y").getNodeValue();
              role.setxyhw(1, (Integer.parseInt(y)));
              String h = info.getAttributes().getNamedItem("h").getNodeValue();
              role.setxyhw(2, (Integer.parseInt(h)));
              String w = info.getAttributes().getNamedItem("w").getNodeValue();
              role.setxyhw(3, (Integer.parseInt(w)));
            } else if ("line".equals(info.getNodeName())) {
              String line = info.getTextContent();
              role.setLine(line);
            }
          }
          scene.setSceneRoles(role);
        }

      } // for. childnodes
      deck.sceneList.add(scene);
    } // for card nodes

  }// method

  // reads data from XML file and prints data
  public void readBoardData(Document d, Board b) {

    Element root = d.getDocumentElement();
    NodeList sets = root.getElementsByTagName("set");
    NodeList trailers = root.getElementsByTagName("trailer");
    NodeList offices = root.getElementsByTagName("office");

    // reads data from set nodes
    for (int i = 0; i < sets.getLength(); i++) {

      // create Room and set data fields from xml file
      Room room = new Room();
      Node set = sets.item(i);
      String setName = set.getAttributes().getNamedItem("name").getNodeValue();
      room.setName(setName);
      Board.nameToRoom.put(setName, room);

      // read data
      NodeList children = set.getChildNodes();
      for (int j = 0; j < children.getLength(); j++) {
        Node sub = children.item(j);

        // fill out adjRooms
        if ("neighbors".equals(sub.getNodeName())) {
          NodeList neighbors = sub.getChildNodes();

          for (int k = 0; k < neighbors.getLength(); k++) {
            Node neighbor = neighbors.item(k);

            if ("neighbor".equals(neighbor.getNodeName())) {
              String name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
              name = name.substring(0,1).toUpperCase() + name.substring(1);
              room.setAdjRooms(name);
            }

          } // for neighbors nodes

        }

        // fill out xyhw
        else if ("area".equals(sub.getNodeName())) {
          String x = sub.getAttributes().getNamedItem("x").getNodeValue();
          room.setxyhw(0, Integer.parseInt(x));
          String y = sub.getAttributes().getNamedItem("y").getNodeValue();
          room.setxyhw(1, (Integer.parseInt(y)));
          String h = sub.getAttributes().getNamedItem("h").getNodeValue();
          room.setxyhw(2, (Integer.parseInt(h)));
          String w = sub.getAttributes().getNamedItem("w").getNodeValue();
          room.setxyhw(3, (Integer.parseInt(w)));
        }

        // fill out shots and shotsxyhw fields
        else if ("takes".equals(sub.getNodeName())) {
          NodeList infos = sub.getChildNodes();

          for (int k = 0; k < infos.getLength(); k++) {
            Node info = infos.item(k);

            if ("take".equals(info.getNodeName())) {
              String number = info.getAttributes().getNamedItem("number").getNodeValue();
              int[] xyhw = new int[4];
              NodeList areas = info.getChildNodes();

              for (int l = 0; l < areas.getLength(); l++) {
                Node area = areas.item(l);
                if ("area".equals(area.getNodeName())) {
                  String x = area.getAttributes().getNamedItem("x").getNodeValue();
                  xyhw[0] = Integer.parseInt(x);
                  String y = area.getAttributes().getNamedItem("y").getNodeValue();
                  xyhw[1] = Integer.parseInt(y);
                  String h = area.getAttributes().getNamedItem("h").getNodeValue();
                  xyhw[2] = Integer.parseInt(h);
                  String w = area.getAttributes().getNamedItem("w").getNodeValue();
                  xyhw[3] = Integer.parseInt(w);
                  room.setShotsxyhw(xyhw);
                }

              }

            }

          } // for infos

        }

        // fill out roomRoles field
        else if ("parts".equals(sub.getNodeName())) {
          NodeList parts = sub.getChildNodes();

          for (int k = 0; k < parts.getLength(); k++) {
            Node part = parts.item(k);

            // create Role and fill out fields
            if ("part".equals(part.getNodeName())) {
              Role role = new Role();
              String name = part.getAttributes().getNamedItem("name").getNodeValue();
              role.setName(name);
              String level = part.getAttributes().getNamedItem("level").getNodeValue();
              role.setRank(Integer.parseInt(level));

              NodeList infos = part.getChildNodes();
              for (int l = 0; l < infos.getLength(); l++) {
                Node info = infos.item(l);

                if ("area".equals(info.getNodeName())) {
                  String x = info.getAttributes().getNamedItem("x").getNodeValue();
                  role.setxyhw(0, Integer.parseInt(x));
                  String y = info.getAttributes().getNamedItem("y").getNodeValue();
                  role.setxyhw(1, Integer.parseInt(y));
                  String h = info.getAttributes().getNamedItem("h").getNodeValue();
                  role.setxyhw(2, Integer.parseInt(h));
                  String w = info.getAttributes().getNamedItem("w").getNodeValue();
                  role.setxyhw(3, Integer.parseInt(w));
                } else if ("line".equals(info.getNodeName())) {
                  String line = info.getTextContent();
                  role.setLine(line);
                }

              } // for area & line nodes
              room.setRoles(role);
            }

          } // for part nodes

        }

      } // for childnodes
      b.roomList.add(room);
    } // for set nodes

    // reads data from trailer node
    for (int i = 0; i < trailers.getLength(); i++) {

      // create Room and fill out fields
      Room room = new Room();
      room.setName("Trailer");
      Board.nameToRoom.put("Trailer", room);

      Node trailer = trailers.item(i);
      NodeList children = trailer.getChildNodes();

      for (int j = 0; j < children.getLength(); j++) {
        Node sub = children.item(j);

        if ("neighbors".equals(sub.getNodeName())) {
          NodeList neighbors = sub.getChildNodes();

          for (int k = 0; k < neighbors.getLength(); k++) {
            Node neighbor = neighbors.item(k);

            if ("neighbor".equals(neighbor.getNodeName())) {
              String name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
              room.setAdjRooms(name);
            }

          } // for neighbors nodes

        }

        else if ("area".equals(sub.getNodeName())) {
          String x = sub.getAttributes().getNamedItem("x").getNodeValue();
          room.setxyhw(0, Integer.parseInt(x));
          String y = sub.getAttributes().getNamedItem("y").getNodeValue();
          room.setxyhw(1, Integer.parseInt(y));
          String h = sub.getAttributes().getNamedItem("h").getNodeValue();
          room.setxyhw(2, Integer.parseInt(h));
          String w = sub.getAttributes().getNamedItem("w").getNodeValue();
          room.setxyhw(3, Integer.parseInt(w));
        }

      }
      b.roomList.add(room);
    }

    // reads data from office node
    for (int i = 0; i < offices.getLength(); i++) {

      // create Room and fill out fields
      Room room = new Room();
      room.setName("Office");
      Board.nameToRoom.put("Office", room);
      Node office = offices.item(i);

      NodeList children = office.getChildNodes();

      for (int j = 0; j < children.getLength(); j++) {
        Node sub = children.item(j);

        if ("neighbors".equals(sub.getNodeName())) {
          NodeList neighbors = sub.getChildNodes();

          for (int k = 0; k < neighbors.getLength(); k++) {
            Node neighbor = neighbors.item(k);

            if ("neighbor".equals(neighbor.getNodeName())) {
              String name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
              room.setAdjRooms(name);
            }

          } // for neighbors nodes

        }

        else if ("area".equals(sub.getNodeName())) {
          String x = sub.getAttributes().getNamedItem("x").getNodeValue();
          room.setxyhw(0, Integer.parseInt(x));
          String y = sub.getAttributes().getNamedItem("y").getNodeValue();
          room.setxyhw(1, Integer.parseInt(y));
          String h = sub.getAttributes().getNamedItem("h").getNodeValue();
          room.setxyhw(2, Integer.parseInt(h));
          String w = sub.getAttributes().getNamedItem("w").getNodeValue();
          room.setxyhw(3, Integer.parseInt(w));
        }

        else if ("upgrades".equals(sub.getNodeName())) {

          NodeList upgdInfos = sub.getChildNodes();

          for (int k = 0; k < upgdInfos.getLength(); k++) {
            Node upgdInfo = upgdInfos.item(k);

            if ("upgrade".equals(upgdInfo.getNodeName())) {
              String level = upgdInfo.getAttributes().getNamedItem("level").getNodeValue();
              String currency = upgdInfo.getAttributes().getNamedItem("currency").getNodeValue();
              String amt = upgdInfo.getAttributes().getNamedItem("amt").getNodeValue();

              NodeList areas = upgdInfo.getChildNodes();
              int[] xyhw = new int[4];

              for (int l = 0; l < areas.getLength(); l++) {
                Node area = areas.item(l);

                if ("area".equals(area.getNodeName())) {
                  String x = area.getAttributes().getNamedItem("x").getNodeValue();
                  xyhw[0] = Integer.parseInt(x);
                  String y = area.getAttributes().getNamedItem("y").getNodeValue();
                  xyhw[1] = Integer.parseInt(y);
                  String h = area.getAttributes().getNamedItem("h").getNodeValue();
                  xyhw[2] = Integer.parseInt(h);
                  String w = area.getAttributes().getNamedItem("w").getNodeValue();
                  xyhw[3] = Integer.parseInt(w);
                  room.setShotsxyhw(xyhw);
                }

              }

            }

          }

        }

      }
      b.roomList.add(room);
    }

  }// method

}// class
