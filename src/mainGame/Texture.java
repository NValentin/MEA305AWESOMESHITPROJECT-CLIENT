package mainGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Texture
{
    //Actually Used Textures
    public static SpriteSheet tileSprites;
    public static Image butt;
    public static Image doge;

    public static Image menuBackground;
    public static Image buttonTemplate;

    Texture() throws SlickException
    {
        butt = new Image("resources/butt.png");
        doge = new Image("resources/doge.png");
    }
    void initMainMenuStateTextures()
    {
        try
        {
            menuBackground = new Image("resources/menuBackground.jpg");
            buttonTemplate = new Image("resources/TemplateButton.jpg");

        } catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    void initPlayingWindowTextures()
    {
        try
        {
            tileSprites = new SpriteSheet("resources/tileTexture_Sheet.png", 120, 140, 3);
        } catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
