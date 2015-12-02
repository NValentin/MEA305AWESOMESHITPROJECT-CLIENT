package Network;

import java.util.ArrayList;

/**
 * Created by Kingo on 02-12-2015.
 */
public class DevelopmentCard {
    public boolean getNewCard = false;
    public boolean newCardReceived =  false;
    ArrayList<Integer> cardID = new ArrayList<>();

    public DevelopmentCard() { }

    public void DrawNewCard() {
        getNewCard = true;
        if (newCardReceived) {

        }
    }

    public void DisplayCards() {
        //Display cards here
    }



}
