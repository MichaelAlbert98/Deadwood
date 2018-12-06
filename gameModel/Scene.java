/* CSCI 345 - Object Oriented Design - Deadwood
 * Michael Albert and Ryan Lingg
 * Scene.java - This class stores and modifies the attributes related to a
 * scene.
 * Created 11/02/2018
 * Revised 11/16/2018
 */

import java.util.ArrayList;

public class Scene extends Subject {

  private String name;
  private String img;
  private int sceneNum;
  private String desc;
  private Room location;
  private Boolean isCardUp;
  public Boolean isSceneDone;
  private ArrayList<Role> sceneRoles;
  private int shotsLeft;
  private int budget;

  public Scene() {
    this.name = "";
    this.img = "";
    this.sceneNum = 0;
    this.desc = "";
    this.location = null;
    this.isCardUp = false;
    this.isSceneDone = false;
    this.sceneRoles = new ArrayList<Role>(0);
    this.shotsLeft = 0;
    this.budget = 0;
  }

  public boolean areRolesAvailable(int rank) {
    for (Role role : this.sceneRoles) {
      if (role.isRoleAvailable(rank)) {
        return true;
      }
    }
    return false;
  }

  public void printAllRolesInScene() {
    for (Role r : this.sceneRoles) {
      r.displayRoleStats();
    }
  }

  public Boolean getIsSceneDone() {
    return this.isSceneDone;
  }

  public void setIsSceneDone(Boolean truth) {
    this.isSceneDone = truth;
    return;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
    return;
  }

  public String getImg() {
    if (this.isCardUp == false) {
      return "../figures/CardBack.jpg";
    }
    else if ((this.isCardUp == true) && (this.isSceneDone == false)) {
      return "../figures/cards/" + this.img;
    }
    return "";
  }

  public void setImg(String img) {
    this.img = img;
    return;
  }

  public int getSceneNum() {
    return this.sceneNum;
  }

  public void setSceneNum(int sceneNum) {
    this.sceneNum = sceneNum;
    return;
  }

  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
    return;
  }

  public Room getLocation() {
    return this.location;
  }

  public void setLocation(Room loc) {
    this.location = loc;
  }

  public ArrayList<Role> getSceneRoles() {
    return this.sceneRoles;
  }

  public String getAllRolesNames() {
    String nameList = "";
    int iter = 0;
    for (Role role : this.sceneRoles) {
      if (iter != 0) {
        nameList = nameList + ", ";
      }
      nameList = nameList + role.getName();
      iter++;
    }
    return nameList;
  }

  public void addRoleToScene(Role role) {
    this.sceneRoles.add(role);
    return;
  }

  public int getShotsLeft() {
    return this.shotsLeft;
  }

  public void setShotsLeft(int value) {
    this.shotsLeft = this.shotsLeft + value;
  }

  public int getBudget() {
    return this.budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  /* Methods Not Used in Text Version */

  public Boolean isCardFaceUp() {
    return this.isCardUp;
  }

  public void flipCardFaceUp() {
    this.isCardUp = true;
    return;
  }

}
