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
        butt.setFilter(Image.FILTER_LINEAR);
        doge = new Image("resources/doge.png");
        doge.setFilter(Image.FILTER_LINEAR);

    }

    void initMainMenuStateTextures()
    {
        try
        {
            menuBackground = new Image("resources/menuBackground.jpg");
            menuBackground.setFilter(Image.FILTER_LINEAR);
            buttonTemplate = new Image("resources/TemplateButton.jpg");
            buttonTemplate.setFilter(Image.FILTER_LINEAR);

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
            menuBackground.setFilter(Image.FILTER_LINEAR);
            templateButton = new Image("resources/TemplateButton.jpg");
            templateButton.setFilter(Image.FILTER_LINEAR);


        } catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    void initCreatePlayerTextures()
    {
        try
        {
            menuBackground = new Image("resources/menuBackground.jpg");
            menuBackground.setFilter(Image.FILTER_LINEAR);
            templateButton = new Image("resources/TemplateButton.jpg");
            templateButton.setFilter(Image.FILTER_LINEAR);

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
            tileSprites.setFilter(Image.FILTER_LINEAR);

            cardSprites = new SpriteSheet("resources/tileTexture_sheet.png", 256, 432, 0);
            cardSprites.setFilter(Image.FILTER_LINEAR);

            diceSprites = new SpriteSheet("resources/dice_sheet.png", 135, 135, 0);
            diceSprites.setFilter(Image.FILTER_LINEAR);

            buildingCost = new Image("resources/Buildingcost.jpg");
            buildingCost.setFilter(Image.FILTER_LINEAR);

            makeNewTrade = new Image("resources/trade_button.png");
            makeNewTrade.setFilter(Image.FILTER_LINEAR);

            accept = new Image("resources/accept.png");
            accept.setFilter(Image.FILTER_LINEAR);

            decline = new Image("resources/decline.png");
            decline.setFilter(Image.FILTER_LINEAR);

            acceptBig = new Image("resources/acceptOffer.png");
            acceptBig.setFilter(Image.FILTER_LINEAR);

            declineBig =  new Image("resources/declineOffer.png");
            decline.setFilter(Image.FILTER_LINEAR);

            counterBig = new Image("resources/counterOffer.png");
            counterBig.setFilter(Image.FILTER_LINEAR);

            increase = new Image("resources/increase.png");
            increase.setFilter(Image.FILTER_LINEAR);

            decrease = new Image("resources/decrease.png");
            decrease.setFilter(Image.FILTER_LINEAR);

            silverButton = new Image("resources/trade.png");
            silverButton.setFilter(Image.FILTER_LINEAR);

            templateButton = new Image("resources/TemplateButton.jpg");
            templateButton.setFilter(Image.FILTER_LINEAR);

            mapBackground = new Image("resources/map_background.png");
            mapBackground.setFilter(Image.FILTER_LINEAR);

            thief = new Image("resources/thief.png");
            thief.setFilter(Image.FILTER_LINEAR);

        } catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    void initEndGameTextures()
    {
        try
        {
            menuBackground = new Image("resources/menuBackground.jpg");
            menuBackground.setFilter(Image.FILTER_LINEAR);

            templateButton = new Image("resources/TemplateButton.jpg");
            templateButton.setFilter(Image.FILTER_LINEAR);

        } catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}
