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

public class ParseXML{
   
     // building a document from the XML file
     // returns a Document object after loading the book.xml file.
   public Document getDocFromFile(String filename) throws ParserConfigurationException {
   
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = null;
   
      try{
         doc = db.parse(filename);
      } catch (Exception ex){
         System.out.println("XML parse failure");
         ex.printStackTrace();
      }
      return doc;
      // exception handling
   
   }

     // reads data from XML file and prints data
   public void readCardData(Document d){
   
      Element root = d.getDocumentElement();
   
      NodeList cards = root.getElementsByTagName("card");
   
      for (int i=0; i<cards.getLength();i++){
      
         System.out.println("Printing information for card "+(i+1));
      
             //reads data from the nodes
         Node card = cards.item(i);
         String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
         System.out.println("Name = "+cardName);
         String cardImg = card.getAttributes().getNamedItem("img").getNodeValue();
         System.out.println("img = "+cardImg);
         String cardBudget = card.getAttributes().getNamedItem("budget").getNodeValue();
         System.out.println("budget = "+cardBudget);
      
             //reads data
      
         NodeList children = card.getChildNodes();
      
         for (int j=0; j< children.getLength(); j++) {
         
            Node sub = children.item(j);
         
            if("scene".equals(sub.getNodeName())) {
               System.out.println("Printing information for scene.");
               String sceneNumber = sub.getAttributes().getNamedItem("number").getNodeValue();
               System.out.println("Number = "+sceneNumber);
               String desc = sub.getTextContent();
               System.out.println("Description = "+desc);
            }
            
            else if ("part".equals(sub.getNodeName())) {
               System.out.println("Part");
               String name = sub.getAttributes().getNamedItem("name").getNodeValue();
               System.out.println("Name = "+name);
               String level = sub.getAttributes().getNamedItem("level").getNodeValue();
               System.out.println("Level = "+level);
               
               NodeList infos = sub.getChildNodes();
                 
               for (int k=0; k< infos.getLength(); k++) {
                     
                  Node info = infos.item(k);
                     
                  if ("area".equals(info.getNodeName())) {
                     String x = info.getAttributes().getNamedItem("x").getNodeValue();
                     System.out.println("x = "+x);
                     String y = info.getAttributes().getNamedItem("y").getNodeValue();
                     System.out.println("y = "+y);
                     String h = info.getAttributes().getNamedItem("h").getNodeValue();
                     System.out.println("h = "+h);
                     String w = info.getAttributes().getNamedItem("w").getNodeValue();
                     System.out.println("w = "+w);
                  }
                  else if ("line".equals(info.getNodeName())) {  
                     String line = info.getTextContent();
                     System.out.println("Line = "+line);
                  }
               }
            } 
         
         } //for. childnodes
      
         System.out.println("\n");
      
      }//for card nodes
   
   }// method


   // reads data from XML file and prints data
   public static void readBoardData(Document d){
   
      Element root = d.getDocumentElement();
      NodeList sets = root.getElementsByTagName("set");
      NodeList trailers = root.getElementsByTagName("trailer");
      NodeList offices = root.getElementsByTagName("office");
      
      // reads data from set nodes
      for (int i=0; i<sets.getLength();i++){
      
         System.out.println("Printing information for set "+(i+1));
      
           //reads data from the nodes
         Node set = sets.item(i);
         String setName = set.getAttributes().getNamedItem("name").getNodeValue();
         System.out.println("Name = "+setName);
      
           //reads data
      
         NodeList children = set.getChildNodes();
      
         for (int j=0; j< children.getLength(); j++) {
         
            Node sub = children.item(j);
         
            if("neighbors".equals(sub.getNodeName())) {
               System.out.println("Printing information for neighbors.");
               
               NodeList neighbors = sub.getChildNodes();
               
               for (int k=0; k< neighbors.getLength(); k++) {
                  
                  Node neighbor = neighbors.item(k);
                  
                  if ("neighbor".equals(neighbor.getNodeName())) {
                     String name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                     System.out.println("name = "+name);
                  }
                  
               } //for neighbors nodes
                       
            }
             
            else if ("area".equals(sub.getNodeName())) {
               System.out.println("Printing information for area.");
               String x = sub.getAttributes().getNamedItem("x").getNodeValue();
               System.out.println("x = "+x);
               String y = sub.getAttributes().getNamedItem("y").getNodeValue();
               System.out.println("y = "+y);
               String h = sub.getAttributes().getNamedItem("h").getNodeValue();
               System.out.println("h = "+h);
               String w = sub.getAttributes().getNamedItem("w").getNodeValue();
               System.out.println("w = "+w);
            }
            
            else if ("takes".equals(sub.getNodeName())) {
               System.out.println("Printing information for takes.");
               NodeList infos = sub.getChildNodes();
               
               for (int k=0;k< infos.getLength(); k++) {
                  
                  Node info = infos.item(k);
                  
                  if ("take".equals(info.getNodeName())) {
                     String number = info.getAttributes().getNamedItem("number").getNodeValue();
                     System.out.println("Number = "+number);
                     
                     NodeList areas = info.getChildNodes();
                     
                     for (int l=0;l< areas.getLength(); l++) {
                        Node area = areas.item(l);
                        if ("area".equals(area.getNodeName())) {
                           String x = area.getAttributes().getNamedItem("x").getNodeValue();
                           System.out.println("x = "+x);
                           String y = area.getAttributes().getNamedItem("y").getNodeValue();
                           System.out.println("y = "+y);
                           String h = area.getAttributes().getNamedItem("h").getNodeValue();
                           System.out.println("h = "+h);
                           String w = area.getAttributes().getNamedItem("w").getNodeValue();
                           System.out.println("w = "+w);
                        } 
                        
                     }
                     
                  }
                  
               } //for infos
               
            }
            
            else if ("parts".equals(sub.getNodeName())) {
               System.out.println("Printing information for parts");
               
               NodeList parts = sub.getChildNodes();
               
               for (int k=0;k< parts.getLength(); k++) {
               
                  Node part = parts.item(k);
                  
                  if ("part".equals(part.getNodeName())) {
                     String name = part.getAttributes().getNamedItem("name").getNodeValue();
                     System.out.println("Name = "+name);
                     String level = part.getAttributes().getNamedItem("level").getNodeValue();
                     System.out.println("Level = "+level);
                     
                     NodeList infos = part.getChildNodes();
                     
                     for (int l=0;l< infos.getLength(); l++) {
                     
                        Node info = infos.item(l);
                        
                        if ("area".equals(info.getNodeName())) {
                           String x = info.getAttributes().getNamedItem("x").getNodeValue();
                           System.out.println("x = "+x);
                           String y = info.getAttributes().getNamedItem("y").getNodeValue();
                           System.out.println("y = "+y);
                           String h = info.getAttributes().getNamedItem("h").getNodeValue();
                           System.out.println("h = "+h);
                           String w = info.getAttributes().getNamedItem("w").getNodeValue();
                           System.out.println("w = "+w);
                        } 
                        else if ("line".equals(info.getNodeName())) {
                           String line = info.getTextContent();
                           System.out.println("Line = "+line);
                        }
                        
                     } //for area & line nodes
                     
                  } 
                  
               } //for part nodes
               
            } 
         
         } //for childnodes
      
         System.out.println("\n");
      
      }//for set nodes
      
      // reads data from trailer node
      for (int i=0; i<trailers.getLength();i++){
      
         System.out.println("Printing information for trailer "+(i+1));
      
         //reads data from the nodes
         Node trailer = trailers.item(i);
      
         NodeList children = trailer.getChildNodes();
      
         for (int j=0; j< children.getLength(); j++) {
         
            Node sub = children.item(j);
         
            if("neighbors".equals(sub.getNodeName())) {
               System.out.println("Printing information for neighbors.");
               
               NodeList neighbors = sub.getChildNodes();
               
               for (int k=0; k< neighbors.getLength(); k++) {
                  
                  Node neighbor = neighbors.item(k);
                  
                  if ("neighbor".equals(neighbor.getNodeName())) {
                     String name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                     System.out.println("name = "+name);
                  }
                  
               } //for neighbors nodes
                       
            }
             
            else if ("area".equals(sub.getNodeName())) {
               System.out.println("Printing information for area.");
               String x = sub.getAttributes().getNamedItem("x").getNodeValue();
               System.out.println("x = "+x);
               String y = sub.getAttributes().getNamedItem("y").getNodeValue();
               System.out.println("y = "+y);
               String h = sub.getAttributes().getNamedItem("h").getNodeValue();
               System.out.println("h = "+h);
               String w = sub.getAttributes().getNamedItem("w").getNodeValue();
               System.out.println("w = "+w);
            }
         
         }
         
      }
      
      // reads data from office node
      for (int i=0; i<offices.getLength();i++){
      
         System.out.println("Printing information for office "+(i+1));
      
         //reads data from the nodes
         Node office = offices.item(i);
      
         NodeList children = office.getChildNodes();
      
         for (int j=0; j< children.getLength(); j++) {
         
            Node sub = children.item(j);
         
            if("neighbors".equals(sub.getNodeName())) {
               System.out.println("Printing information for neighbors.");
               
               NodeList neighbors = sub.getChildNodes();
               
               for (int k=0; k< neighbors.getLength(); k++) {
                  
                  Node neighbor = neighbors.item(k);
                  
                  if ("neighbor".equals(neighbor.getNodeName())) {
                     String name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                     System.out.println("name = "+name);
                  }
                  
               } //for neighbors nodes
                       
            }
             
            else if ("area".equals(sub.getNodeName())) {
               System.out.println("Printing information for area.");
               String x = sub.getAttributes().getNamedItem("x").getNodeValue();
               System.out.println("x = "+x);
               String y = sub.getAttributes().getNamedItem("y").getNodeValue();
               System.out.println("y = "+y);
               String h = sub.getAttributes().getNamedItem("h").getNodeValue();
               System.out.println("h = "+h);
               String w = sub.getAttributes().getNamedItem("w").getNodeValue();
               System.out.println("w = "+w);
            }
            
            else if ("upgrades".equals(sub.getNodeName())) {
               System.out.println("Printing information for upgrades.");
               
               NodeList upgdInfos = sub.getChildNodes();
               
               for (int k=0; k< upgdInfos.getLength(); k++) {
                  
                  Node upgdInfo = upgdInfos.item(k);
                  
                  if ("upgrade".equals(upgdInfo.getNodeName())) {
                     String level = upgdInfo.getAttributes().getNamedItem("level").getNodeValue();
                     System.out.println("level = "+level);
                     String currency = upgdInfo.getAttributes().getNamedItem("currency").getNodeValue();
                     System.out.println("currency = "+currency);
                     String amt = upgdInfo.getAttributes().getNamedItem("amt").getNodeValue();
                     System.out.println("amt = "+amt);
                     
                     NodeList areas = upgdInfo.getChildNodes();
                     
                     for (int l=0;l< areas.getLength(); l++) {
                     
                        Node area = areas.item(l);
                        
                        if ("area".equals(area.getNodeName())) {
                           String x = area.getAttributes().getNamedItem("x").getNodeValue();
                           System.out.println("x = "+x);
                           String y = area.getAttributes().getNamedItem("y").getNodeValue();
                           System.out.println("y = "+y);
                           String h = area.getAttributes().getNamedItem("h").getNodeValue();
                           System.out.println("h = "+h);
                           String w = area.getAttributes().getNamedItem("w").getNodeValue();
                           System.out.println("w = "+w);
                        } 
                        
                     }
                     
                  }
                  
               }
               
            }
         
         }
         
      }

   }// method
   
}//class
