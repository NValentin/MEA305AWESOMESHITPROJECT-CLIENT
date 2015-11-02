package mainGame;

import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

/**
 * Created by Bjørn on 02-11-2015.
 */
public class Button {

    int x;
    int y;
    int sizeX;
    int sizeY;
    int ScreenHeight = Main.ScreenHeight;
    boolean pressed;

    public Button(int x, int y, int sizeX, int sizeY, Image texture) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        texture.draw(x, y, sizeX, sizeY);
        pressed = false;
    }

    public boolean isWithin() {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        //System.out.println("Height: " + (ScreenWidth - x - sizeX) + " Width: " + (ScreenHeight-y-sizeY));
        if (Mouse.isButtonDown(0)) {
            if (posX > x && posX < x + sizeX && posY > ScreenHeight - y - sizeY && posY < ScreenHeight - y) {
                return true;
            } else {return false; }
        } else {return false; }
    }

}
