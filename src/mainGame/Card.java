package mainGame;

import org.newdawn.slick.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Kingo on 03-Nov-15.
 */
public class Card {
    int id;
    SQLiteJDBC sqLiteJDBC;
    int ScreenHeight = Main.ScreenHeight;
    int ScreenWidth = Main.ScreenWidth;
    int cardID;
    int type;
    String headline;
    String text;
    int imageID;
    Image[] cards = new Image[5];
    Font F_headline;
    Font F_text;
    String text1 = "Hallo";
    String text2 = "World!";

    public  Card(int id) throws SlickException{
        this.id = id;
        sqLiteJDBC = new SQLiteJDBC();
        sqLiteJDBC.GetInfo(id);
        cardID = sqLiteJDBC.cardID;
        type = sqLiteJDBC.type;
        headline = sqLiteJDBC.headline;
        text = sqLiteJDBC.text;
        imageID = sqLiteJDBC.imageID;
        cards[0] = new Image("resources/cards/Brown.png");
        cards[1] = new Image("resources/cards/green.png");
        cards[2] = new Image("resources/cards/Lightblue.png");
        cards[3] = new Image("resources/cards/red.png");
        cards[4] = new Image("resources/cards/yellow.png");
        F_headline = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 20), true);
        F_text = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 12), true);
    }

    public void draw(int x, int y, int sizeX, int sizeY, Graphics g) {
        cards[type-1].draw(x, y, sizeX, sizeY);
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if (posX > x && posX < x + sizeX && posY > ScreenHeight - y - sizeY && posY < ScreenHeight - y) {
            g.setColor(Color.white);
            g.fill(new Rectangle(ScreenWidth / 2 - 100, ScreenHeight / 2 - 200, 200, 400));
            g.setColor(Color.black);
            g.setFont(F_headline);
            g.drawString(headline, ScreenWidth / 2 - headline.length()*5, ScreenHeight/2 - 185);
            cards[type-1].draw(ScreenWidth / 2 - 80, ScreenHeight / 2 - 150, 160, 200);
            g.setFont(F_text);
            g.drawString(text1 + "\n" + text2, ScreenWidth / 2 - 80, ScreenHeight/2+50);
            g.setColor(Color.white);
        }
    }
}
