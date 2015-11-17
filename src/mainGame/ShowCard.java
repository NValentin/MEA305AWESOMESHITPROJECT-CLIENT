package mainGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
/**
 * Created by Kingo on 02-Nov-15.
 */
public class ShowCard {
    int x;
    int y;
    int sizeX;
    int sizeY;
    int ScreenHeight = Main.ScreenHeight;
    int ScreenWidth = Main.ScreenWidth;
    Graphics g;

    ShowCard(int id, int _x, int _y, int _sizeX, int _sizeY, Graphics g) {
        this.x = _x;
        this.y = _y;
        this.sizeX = _sizeX;
        this.sizeY = _sizeY;
        this.g = g;
    }

    public void Draw() {

    }

    public void Hover() {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if (posX > x && posX < x + sizeX && posY > ScreenHeight - y - sizeY && posY < ScreenHeight - y) {
            //tex.draw(ScreenWidth/2-100, ScreenHeight/2-200, 200, 400);
        }
    }
}
