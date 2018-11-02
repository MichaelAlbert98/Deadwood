import gameModel.*;
import java.util.ArrayList;

public class TextView{

    Game gameRef;
    TextView(Game gameref){
        this.gameRef = gameref;
        GameView g = new GameView(this.gameRef);
        //Will loop this for all players
        PlayerView p = new PlayerView(this.gameRef.playerList.get(0));
        //Will loop this for all scenes
        SceneView s = new SceneView(this.gameRef.board.roomList[0].roomScene);
    }

    @Override
    void update(int message){
        //Will switch on messages to display correct message
    }
}