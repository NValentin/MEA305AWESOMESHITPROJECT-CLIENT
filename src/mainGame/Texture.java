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
    public static SpriteSheet cardSprites;
    public static SpriteSheet diceSprites;

    public static Image menuBackground;
    public static Image mapBackground;

    public static Image buildingCost;
    public static Image makeNewTrade;
    public static Image increase;
    public static Image decrease;
    public static Image acceptBig;
    public static Image declineBig;
    public static Image counterBig;
    public static Image accept;
    public static Image decline;
    public static Image silverButton;
    public static Image templateButton;
    public static Image thief;


    public static Image buttonTemplate;

    public static Image butt;
    public static Image doge;

    Texture() throws SlickException
    {
        butt = new Image("resources/butt.png");
        doge = new Image("resources/doge.png");

        buildingCost = new Image("resources/Buildingcost.jpg");
        makeNewTrade = new Image("resources/trade_button.png");
        accept = new Image("resources/accept.png");
        decline = new Image("resources/decline.png");
        acceptBig = new Image("resources/acceptOffer.png");
        declineBig =  new Image("resources/declineOffer.png");
        counterBig = new Image("resources/counterOffer.png");
        increase = new Image("resources/increase.png");
        decrease = new Image("resources/decrease.png");
        silverButton = new Image("resources/trade.png");
        templateButton = new Image("resources/TemplateButton.jpg");
        thief = new Image("resources/thief.png");
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

    void initJoinLobbyTextures()
    {
        try
        {
            menuBackground = new Image("resources/menuBackground.jpg");
            templateButton = new Image("resources/TemplateButton.jpg");


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
            cardSprites = new SpriteSheet("resources/tileTexture_sheet.png", 256, 432, 0);
            diceSprites = new SpriteSheet("resources/dice_sheet.png", 135, 135, 0);

            mapBackground = new Image("resources/map_background.png");
        } catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
