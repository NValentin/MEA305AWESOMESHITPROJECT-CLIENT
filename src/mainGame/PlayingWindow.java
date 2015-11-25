package mainGame;

import mapClasses.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * Created by Kingo on 30-10-2015.
 */
public class PlayingWindow extends BasicGameState
{
    Texture textures;
    int theWidth = Main.ScreenWidth;
    int theHeight = Main.ScreenHeight;
    String[] names = new String [] {"Fred", "George", "Ron", "Ginny"};
    GUI_Overlay gui_overlay;
    int[] currentResources = new int[5];
    int turn = 0;
    boolean yourTurn = false;

    GameMap map;
    boolean buildMap = true;

    int sizeX = Main.ScreenWidth/4;
    int sizeY = Main.ScreenHeight/10;

    TextBoxBase chatBox;
    TextField chat;
    String chatText = "";
    Font font;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException
    {
        gui_overlay = new GUI_Overlay();
        textures = new Texture();
        textures.initPlayingWindowTextures();
        map = new GameMap();

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 12), true);
        chatBox = new TextBoxBase();
        chat = new TextField(gameContainer, font, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException
    {
        map.update();
        if(gameContainer.getInput().isKeyPressed(Input.KEY_3)) {
            if (yourTurn) {
                yourTurn = false;
                System.out.println(yourTurn);
            } else {
                yourTurn = true;
                System.out.println(yourTurn);
            }
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_ENTER) && chat.getText()!="") {
            chatText = chat.getText();
            chatBox.newMessage(chatText, PlayerStats.name);
            chat.setText("");
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBG, Graphics g) throws SlickException {
        if (buildMap)
        {
            map.GameMapGameMap();
            buildMap = false;
        }
        map.render(g);
        gui_overlay.PlayerList(35, 35, g, PlayerStats.names, turn);
        gui_overlay.ResourceBar(200, 10, g, currentResources[0], currentResources[1], currentResources[2], currentResources[3], currentResources[4]);
        gui_overlay.BuildingWindow(theWidth - 205, theHeight - 255, 200, 250);
        gui_overlay.TradePopupWindow(theWidth - 215, theHeight - 55, gui_overlay.trade, PlayerStats.names[turn], g);
        gui_overlay.IncomingTradeWindow(theWidth / 2 - 200, theHeight / 2 - 150, gui_overlay.tradeWindow, g);
        gui_overlay.OfferWindow(theWidth / 2 - 200, theHeight / 2 - 150, gui_overlay.offerWindow, g);
        if (yourTurn)
            gui_overlay.TradeWithWindow(Main.ScreenWidth - 200, 200, g, names);
        gui_overlay.DisplayDice(g, theWidth - 150, 50);
        g.setColor(Color.white);
        chatBox.render(g, gc);
        chat.render(gc, g);
    }

    @Override
    public int getID() {
        return 3;
    }

}
