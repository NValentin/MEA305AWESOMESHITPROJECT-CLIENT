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
    int ScreenHeight = Main.ScreenHeight;
    Image texture;
    boolean pressed;
    Font font;
    int fontSize = 50;

    Rectangle butShape;

    public Button(int x, int y, int sizeX, int sizeY, Image texture) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.texture = texture;
        this.pressed = false;
        font = new TrueTypeFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, fontSize), true);

        butShape = new Rectangle(x, y, sizeX, sizeY);
    }

    public Button(int x, int y, int sizeX, int sizeY, Image texture, int fontSize) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.texture = texture;
        this.pressed = false;
        this.font = new TrueTypeFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, fontSize), true);
    }

    public void draw(Graphics g) {
        g.texture(butShape, texture, true);
    }

    public Button(int x, int y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        pressed = false;
    }

    public void SetPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void AddText(String text, Color color) {
        font.drawString(x + sizeX/2f - font.getWidth(text) / 2f, y + sizeY / 2f - font.getHeight(text) / 2f, text, color);
    }

    public boolean isWithin(GameContainer gc) {
        return gc.getInput().isMousePressed(0) && butShape.contains(Mouse.getX(), Main.ScreenHeight - Mouse.getY());
    }
}

