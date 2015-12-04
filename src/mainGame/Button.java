package mainGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

/**
 * Created by Kingo on 02-11-2015.
 */
public class Button {

    int x;
    int y;
    int sizeX;
    int sizeY;
    String texture;
    Font font;
    int fontSize = 50;

    Rectangle butShape;

    //Constructor for the buttons class. The x and y position of the button, and the size and texture of it.
    public Button(int x, int y, int sizeX, int sizeY, String texture) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.texture = texture;
        font = new TrueTypeFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, fontSize), true);

        butShape = new Rectangle(this.x, this.y, this.sizeX, this.sizeY);
    }

    //An overloaded version of the constructor for the button with font size, if the font size shouldn't be 50.
    public Button(int x, int y, int sizeX, int sizeY, String texture, int fontSize) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.texture = texture;
        this.font = new TrueTypeFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, fontSize), true);

        butShape = new Rectangle(this.x, this.y, this.sizeX, this.sizeY);
    }

    //Method for displaying the buttons
    public void draw(Graphics g) {
        g.texture(butShape, getButtonTexture(), true);
    }

    //For moving the buttons to another place
    public void SetPos(int x, int y) {
        this.x = x;
        this.y = y;
        updateShape();
    }

    //Updading the shape, if some of the stats have altered.
    private void updateShape()
    {
        butShape.setX(x);
        butShape.setY(y);
    }

    //Adding text to the button
    public void AddText(String text, Color color) {
        font.drawString(x + sizeX/2f - font.getWidth(text) / 2f, y + sizeY / 2f - font.getHeight(text) / 2f, text, color);
    }

    //Method for handling if the buttons is pressed.
    public boolean isPressed(GameContainer gc) {
        boolean wasPressed = false;

        if (butShape.contains(Mouse.getX(), Main.ScreenHeight - Mouse.getY()) && gc.getInput().isMousePressed(0))
        {
            wasPressed = true;
        }
        return wasPressed;
    }

    //Method for getting the corrent texture for the class.
    private Image getButtonTexture()
    {
        Image tmpTexture;

        switch (texture)
        {
            case ("templateButton"):
                tmpTexture = Texture.templateButton;
                break;
            case ("makeNewTrade"):
                tmpTexture = Texture.makeNewTrade;
                break;
            case("increase"):
                tmpTexture = Texture.increase;
                break;
            case ("decrease"):
                tmpTexture = Texture.decrease;
                break;
            case ("acceptBig"):
                tmpTexture = Texture.acceptBig;
                break;
            case ("declineBig"):
                tmpTexture = Texture.declineBig;
                break;
            case ("counterBig"):
                tmpTexture = Texture.counterBig;
                break;
            case ("accept"):
                tmpTexture = Texture.accept;
                break;
            case ("decline"):
                tmpTexture = Texture.decline;
                break;
            case ("silverButton"):
                tmpTexture = Texture.silverButton;
                break;
            default:
                tmpTexture = Texture.doge;
                break;
        }
        return tmpTexture;
    }
}

