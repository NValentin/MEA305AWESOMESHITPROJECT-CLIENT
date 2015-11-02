package mainGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Niels on 01-11-2015.
 */
public class Texture
{
    private Image butt;
    private Image doge;

    //Actually Used Textures
    SpriteSheet tileSprites;

    Texture(int ID) throws SlickException
    {
        if (ID == 3)
        {
            tileSprites = new SpriteSheet("resources/hexagonTerrain_sheet.png", 120, 140, 2);
            butt = new Image("resources/butt.png");
            doge = new Image("resources/doge.png");
        }
    }

    public Image getButt()
    {
        return butt;
    }

    public Image getDoge()
    {
        return doge;
    }

    public Image getGrassTex()
    {
        return tileSprites.getSprite(5, 1);
    }
}
