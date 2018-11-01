public class Player {

   private String name;
   private Boolean hasRole;
   private Role currentRole;
   private int rehearseTokens;
   private Banker banker;
   
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
   
   public void shotSuccess(Role role) {
      return;
   }
   
   public void shotFailure(Role role) {
      return;
   }
   
   public String getName() {
      return this.name;
   }
   
   public void setName(String name) {
      this.name = name;
      return;
   }
   
   public Boolean getHasRole() {
      return this.hasRole;
   }
   
   public void setHasRole(Boolean truth) {
      this.hasRole = truth;
      return;
   }
   
   public Role getCurrentRole() {
      return this.currentRole;
   }
   
   public void setCurrentRole(Role role) {
      this.currentRole = role;
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
