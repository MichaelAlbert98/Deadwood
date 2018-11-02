import gameModel.*;
import java.lang.*;


public class GameView extends myObserver {
    Game gameRef;
    GameView(game g) {
        this.gameRef = g;
        g.attach(this);
    }

    @Override
    void update(int message){
        //Will switch on messages to display correct message
    }
}