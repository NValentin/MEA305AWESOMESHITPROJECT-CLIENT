package mainGame;

import mapClasses.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import javax.xml.soap.Text;


/**
 * Created by Kingo on 30-10-2015.
 */
public class State_PlayingWindow extends BasicGameState
{
    Texture textures;
    int theWidth = Main.ScreenWidth;
    int theHeight = Main.ScreenHeight;
    GUI_Overlay gui_overlay;
    public static int[] currentResources = new int[5];
    boolean yourTurn = false;
    public static int tradeId = 0;

    GameMap map;
    boolean buildMap = true;

    int sizeX = Main.ScreenWidth/4;
    int sizeY = Main.ScreenHeight/10;

    ChatBox chatBox;
    TextField gameChat;
    String chatText = "";
    Font font;

    static String gameInfo = "";

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException
    {
        gui_overlay = new GUI_Overlay();
        textures = new Texture();
        textures.initPlayingWindowTextures();
        map = new GameMap();


        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 12), true);
        chatBox = new ChatBox();
        gameChat = new TextField(gameContainer, font, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException
    {
        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        map.update(gameContainer);
        if(gameContainer.getInput().isKeyPressed(Input.KEY_3)) {
            if (yourTurn) {
                yourTurn = false;
                System.out.println(yourTurn);
            } else {
                yourTurn = true;
                System.out.println(yourTurn);
            }
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_ENTER) && gameChat.getText()!="") {
            chatText = gameChat.getText();
            chatBox.newMessage(chatText, PlayerStats.name);
            gameChat.setText("");
        }
        if (PlayerStats.die1+PlayerStats.die2==7){

        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBG, Graphics g) throws SlickException {
        Texture.mapBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);

        if (buildMap) {
            map.GameMapGameMap();
            buildMap = false;
        }
        map.render(g);
        gui_overlay.PlayerList(15, 40, g, PlayerStats.names, PlayerStats.points, PlayerStats.turn);
        gui_overlay.ResourceBar(Main.ScreenWidth/2-250, 10, g, currentResources[0], currentResources[1], currentResources[2], currentResources[3], currentResources[4]);
        gui_overlay.BuildingWindow(g, theWidth - 205, theHeight - 275, 200, 270);
        gui_overlay.TradePopupWindow(theWidth - 215, theHeight - 55, PlayerStats.tradingWithyou[PlayerStats.ID-1], PlayerStats.names[PlayerStats.turn], g);
        gui_overlay.IncomingTradeWindow(theWidth / 2 - 200, theHeight / 2 - 150, gui_overlay.tradeWindow, g);
        gui_overlay.OfferWindow(theWidth / 2 - 200, theHeight / 2 - 150, gui_overlay.offerWindow, g, tradeId);
        gui_overlay.ShowStats(g, theWidth - 145, 10);
        gui_overlay.HandleTradeRespons(PlayerStats.tradeAccpeted);
        if (PlayerStats.playerturn[PlayerStats.ID - 1]) {
            gui_overlay.TradeWithWindow(Main.ScreenWidth - 215, 205, g, PlayerStats.names);
            gui_overlay.DisplayDice(g, theWidth - 172, 75, map, true);
            gui_overlay.EndTurn(theWidth - 190, 370);
        } else {
            gui_overlay.DisplayDice(g, theWidth - 172, 75, map, false);
        }

        g.setColor(Color.white);
        chatBox.render(g, gc);
        gameChat.render(gc, g);

        g.getFont().getWidth(gameInfo);
        g.drawString(gameInfo,theWidth/2-g.getFont().getWidth(gameInfo)/2,theHeight-40);

    }

    @Override
    public int getID() {
        return 3;
    }

}
