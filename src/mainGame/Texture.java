package mainGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Niels on 01-11-2015.
 */
public class Texture
{
    //Actually Used Textures
    public static SpriteSheet tileSprites;
    public static Image butt;
    public static Image doge;

    Texture(int ID) throws SlickException
    {
        tileSprites = new SpriteSheet("resources/hexagonTerrain_sheet.png", 120, 140, 2);
        butt = new Image("resources/butt.png");
        doge = new Image("resources/doge.png");
    }
}
