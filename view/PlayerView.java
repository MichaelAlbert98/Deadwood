import java.lang.*;
import gameModel.*;


public class PlayerView extends myObserver {
    

    Player playerRef;
    PlayerView(Player p){
        this.playerRef = p;
        p.attach(this);
    }

    @Override
    void update(int message){
        if(message == Player.Messages.SetRole){
            System.out("Player has set role.");
        }
        
    }
}