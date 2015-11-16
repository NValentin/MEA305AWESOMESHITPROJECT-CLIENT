package mainGame;

import org.newdawn.slick.Color;

public class AssignColor {
    int playerID;
    Color playerColor;

    public AssignColor(int i){
        i = playerID;
        colouring();

    }


    Color colouring(){
        if (playerID==0){
            playerColor = Color.red;

        }
        else if(playerID==1){
            playerColor = Color.blue;
        }
        else if (playerID==2){
            playerColor = Color.orange;
        }
        else if (playerID==3){
            playerColor = Color.white;
        }

        return playerColor;
    }
}
