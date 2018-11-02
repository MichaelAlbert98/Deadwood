public class Player extends Subject {

   private String name;
   private Boolean hasRole;
   private Role currentRole;
   private int rehearseTokens;
   private Banker banker;
   
    public enum Messages{
        PlayerMoved,
        PlayerIsDead,
        SetRole,
    }

   public Player() {
      this.name = "";
      this.hasRole = false;
      this.currentRole = null;
      this.rehearseTokens = 0;
   }
   
   public Player(String name) {
      this.name = name;
      this.hasRole = false;
      this.currentRole = null;
      this.rehearseTokens = 0;
   }
   
   public String promptPlayer(String prompt) {
      return prompt;
   }
   
   public String getName() {
      return this.name;
   }
   
   public void setName(String name) {
      this.name = name;
      return;
   }
   
   public Boolean hasRole() {
      return this.hasRole;
   }
   
   public Role getCurrentRole() {
      return this.currentRole;
   }
   
   public void setCurrentRole(Role role) {
      this.currentRole = role;
      this.notifyAllObservers((int)Player.Messages.SetRole);
      return;
   }
   
   public void removeRole() {
      this.currentRole = null;
   }
      
   public int getTokens() {
      return this.rehearseTokens;
   }
   
   public void setTokens(int value) {
      this.rehearseTokens = value;
      return;
   }
   
}
