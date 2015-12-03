package mainGame;

import java.util.ArrayList;

/**
 * Created by Kingo on 02-12-2015.
 */
public class DevelopmentCard {
    public boolean getNewCard = false;
    public boolean newCardReceived =  false;
    ArrayList<Integer> cardsDrawn = new ArrayList<>();

    public DevelopmentCard() { }

    public void DrawNewCard() {
        PlayerStats.updateCard = true;
        if (PlayerStats.updateCard) {
            cardsDrawn.add(PlayerStats.cardID);
            PlayerStats.updateCard = false;
        }
    }

    public void DisplayCards() {
        System.out.println("---Updating Cards-----------");
        if (cardsDrawn.size() > 0) {
            System.out.println("Current cards on hand");
            for (int i = 0; i < cardsDrawn.size(); i++) {
                System.out.println("Card ID: " + cardsDrawn.get(i));
            }
        } else {
            System.out.println("No cards yet...");
        }
    }



}
