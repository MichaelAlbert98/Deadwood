import gameModel.*;
import java.util.ArrayList;
public abstract class TextView{

    Game gameRef;
    TextView(Game gameref){
        this.gameRef = gameref;
        
        PlayerView p = new PlayerView(this.gameRef.playerList.get(0));
        
    }
    
}