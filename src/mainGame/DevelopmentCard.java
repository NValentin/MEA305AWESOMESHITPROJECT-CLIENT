package mainGame;

import java.awt.*;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import javax.xml.soap.Text;

/**
 * Created by Kingo on 02-12-2015.
 */
public class DevelopmentCard {
    ArrayList<Integer> cardsDrawn = new ArrayList<>();
    ArrayList<Rectangle> cardContainer = new ArrayList<>();
    boolean canGetNewCard = false;

    public DevelopmentCard() { }

    public void DrawNewCard() {
        System.out.println("New Card");
        PlayerStats.updateCard = true;
        canGetNewCard = true;
    }

    public void DisplayCards(int x, int y) {
        if (PlayerStats.updateCard && canGetNewCard) {
            cardsDrawn.add(PlayerStats.cardID);
            System.out.println("Added card to array");
            PlayerStats.updateCard = false;
            cardContainer.add(new Rectangle(x + 50 * (cardContainer.size()-1), y, 50, 100));
            canGetNewCard = false;
        }
        if (cardsDrawn.size() > 0) {
            for (int i = 0; i < cardsDrawn.size(); i++) {
                getCardTexture(i).draw(x + 50 * i, y, 50, 100);
            }
        }
    }

    public void Hover(GameContainer gc) {
        for (int i = 0; i < cardsDrawn.size(); i++) {
            if (cardContainer.get(i).contains(Mouse.getX(), Main.ScreenHeight - Mouse.getY()))
            {
                getCardTexture(i).draw(Main.ScreenWidth/2-75, Main.ScreenHeight/2-150, 150, 300);
            }
        }
    }

    public Image getCardTexture(int index) {
        int x = 0;
        int y = 0;
        switch (index) {
            case 1:
                x = 0;
                y = 0;
                break;
            case 2:
                x = 5;
                y = 0;
                break;
            case 3:
                x = 4;
                y = 0;
                break;
            case 4:
                x = 0;
                y = 1;
                break;
            case 5:
                x = 6;
                y = 0;
                break;
            case 6:
                x = 5;
                y = 0;
                break;
            case 7:
                x = 3;
                y = 0;
                break;
            case 8:
                x = 2;
                y = 0;
                break;
            case 9:
                x = 1;
                y = 0;
                break;
        }
        return Texture.cardSprites.getSprite(x, y);
    }



}
