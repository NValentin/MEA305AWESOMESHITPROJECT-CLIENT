package mainGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Niels on 01-11-2015.
 */
public class Texture
{
    private Image butt;
    private Image doge;

    //Actually Used Textures
    private Image grassTex;

    Texture() throws SlickException
    {
        butt = new Image("resources/butt.png");
        doge = new Image("resources/doge.png");

        grassTex = new Image("resources/grass.png");
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
        return grassTex;
    }
}
