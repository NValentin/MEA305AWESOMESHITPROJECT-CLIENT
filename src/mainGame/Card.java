package mainGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
/**
 * Created by Kingo on 02-Nov-15.
 */
public class Card {
    Image tex;
    int x;
    int y;
    int sizeX;
    int sizeY;
    int ScreenHeight = Main.ScreenHeight;
    int ScreenWidth = Main.ScreenWidth;

    Card(Image _tex, int _x, int _y, int _sizeX, int _sizeY) {
        this.tex = _tex;
        this.x = _x;
        this.y = _y;
        this.sizeX = _sizeX;
        this.sizeY = _sizeY;
    }

    public void Draw() {
        tex.draw(x, y, sizeX, sizeY);
    }

    public void Hover() {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if (posX > x && posX < x + sizeX && posY > ScreenHeight - y - sizeY && posY < ScreenHeight - y) {
            tex.draw(ScreenWidth/2-100, ScreenHeight/2-200, 200, 400);
        }
    }
}
