package mainGame;

import java.awt.*;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

/**
 * Created by Kingo on 02-12-2015.
 */
public class DevelopmentCard {
    ArrayList<Integer> cardsDrawn = new ArrayList<>();
    ArrayList<Rectangle> cardContainer = new ArrayList<>();

    public DevelopmentCard() { }

    //when drawing a new development card
    public void DrawNewCard() {
        System.out.println("New Card");
        PlayerStats.updateCard = true;
    }


    //Added the cards to your "hand" and displays them, if you have more than 0.
    public void DisplayCards(int x, int y) {
        if (PlayerStats.canGetNewCard) {
            cardsDrawn.add(PlayerStats.cardID);
            System.out.println("Added card to array: " + PlayerStats.cardID);
            cardContainer.add(new Rectangle(x + 50 * (cardContainer.size()), y, 50, 100));
            PlayerStats.canGetNewCard = false;
        }
        if (cardsDrawn.size() > 0) {
            for (int i = 0; i < cardsDrawn.size(); i++) {
                getCardTexture(cardsDrawn.get(i)).draw(x + 50 * i, y, 50, 100);
            }
        }
    }

    //Displays a big version of the card, if you hover over it.
    public void Hover(GameContainer gc) {
        for (int i = 0; i < cardsDrawn.size(); i++) {
            if (cardContainer.get(i).contains(Mouse.getX(), Main.ScreenHeight - Mouse.getY()))
            {
                getCardTexture(i).draw(Main.ScreenWidth/2-75, Main.ScreenHeight/2-150, 150, 300);
            }
        }
    }

    //If you press the card
    public void isPressed(GameContainer gc) {
        for (int i = 0; i < cardContainer.size(); i++) {
            if (cardContainer.get(i).contains(Mouse.getX(), Main.ScreenHeight - Mouse.getY()) && gc.getInput().isMousePressed(0)) {
                System.out.println("Dev card pressed");
                System.out.println("Card ID:" + (cardsDrawn.get(i)));
                useCard((cardsDrawn.get(i)));
                cardsDrawn.remove(i);
            }
        }
    }

    //Swtich case when you press the card.
    public void useCard(int cardType) {
        switch (cardType) {
            case 1:
                System.out.println("Knight Played");
                GameMap.moveThief = true;
                break;
            case 2:
                System.out.println("University Played");
                PlayerStats.point +=1;
                break;
            case 3:
                System.out.println("Library Played");
                PlayerStats.point +=1;
                break;
            case 4:
                System.out.println("Marketplace Played");
                PlayerStats.point +=1;
                break;
            case 5:
                System.out.println("Parliament Played");
                PlayerStats.point +=1;
                break;
            case 6:
                System.out.println("Cathedral Played");
                PlayerStats.point +=1;
                break;
            case 7:
                System.out.println("Year of Plenty Played");
                GUI_Overlay.isYearOfPlenty = true;
                break;
            case 8:
                System.out.println("Road Building Played");
                //Add build road function
                break;
            case 9:
                System.out.println("Monopoly Played");
                //Add take all of one resource
                break;
            default:
                System.out.println("Something went wrong with using the development cards.");
                break;
        }
    }

    //Switch case that returns the correct texture for the development card
    public Image getCardTexture(int index) {
        Image tmp_tex;
        switch (index) {
            case 1:
                //Knight texture
                tmp_tex = Texture.cardSprites.getSprite(0, 0);
                break;
            case 2:
                //University texture
                tmp_tex = Texture.cardSprites.getSprite(5, 0);
                break;
            case 3:
                //library Texture
                tmp_tex = Texture.cardSprites.getSprite(4, 0);
                break;
            case 4:
                //Marketplace Texture
                tmp_tex = Texture.cardSprites.getSprite(0, 1);
                break;
            case 5:
                //Parlament Texture
                tmp_tex = Texture.cardSprites.getSprite(6, 0);
                break;
            case 6:
                //Cathedral Texture
                tmp_tex = Texture.cardSprites.getSprite(5, 0);
                break;
            case 7:
                //Year of Plenty texture
                tmp_tex = Texture.cardSprites.getSprite(3, 0);
                break;
            case 8:
                //Road building Texture
                tmp_tex = Texture.cardSprites.getSprite(2, 0);
                break;
            case 9:
                //Monopoly Texture
                tmp_tex = Texture.cardSprites.getSprite(1, 0);
                break;
            default:
                //If no other texture was found
                tmp_tex = Texture.doge;
                break;
        }
        return tmp_tex;
    }



}
