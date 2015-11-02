package mainGame;

import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Bjørn on 02-11-2015.
 */
public class Button {

    int x;
    int y;
    int sizeX;
    int sizeY;
    int ScreenHeight = Main.ScreenHeight;
    Image texture;
    boolean pressed;

    public Button(int x, int y, int sizeX, int sizeY, Image texture) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.texture = texture;
        this.pressed = false;
    }

    public void draw() {
        texture.draw(x, y, sizeX, sizeY);
    }

    public Button(int x, int y, int sizeX, int sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        pressed = false;
    }

    public boolean isWithin() {
        int posX = Mouse.getX();
        int posY = Mouse.getY();

        if (!Mouse.isButtonDown(0))
            pressed = true;
        if (pressed) {
            if (Mouse.isButtonDown(0)) {
                pressed = false;
                if (posX > x && posX < x + sizeX && posY > ScreenHeight - y - sizeY && posY < ScreenHeight - y) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

