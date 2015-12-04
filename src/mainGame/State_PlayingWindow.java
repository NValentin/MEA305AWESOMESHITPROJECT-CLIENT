package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class State_PlayingWindow extends BasicGameState
{
    Texture textures;
    int theWidth = Main.ScreenWidth;
    int theHeight = Main.ScreenHeight;
    GUI_Overlay gui_overlay;
    public static int[] currentResources = new int[] {5, 5, 5, 5, 5};
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

    int tmpTurn;

    public static boolean isNormalGameRound = false;

    static String gameInfo = "";

    Boolean tradingOut = false;
    Boolean tradingIn = false;

    /**
     * Initiation method. creates/assigns variables and objects
     * @param gc GameContainer component
     *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException
    {
        gui_overlay = new GUI_Overlay();
        textures = new Texture();
        textures.initPlayingWindowTextures();
        map = new GameMap();


        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 12), true);
        chatBox = new ChatBox();
        gameChat = new TextField(gc, font, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2);
    }

    /**
     * Update method. Listens for conditionals
     * @param gc GameContainer component
     *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @param i Update parameter
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
        if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        map.update(gc);
        if(gc.getInput().isKeyPressed(Input.KEY_3)) { // Method used for quickly changing game states. Used in testing
            if (yourTurn) {
                yourTurn = false;
                System.out.println(yourTurn);
            } else {
                yourTurn = true;
                System.out.println(yourTurn);
            }
        }

        if(gc.getInput().isKeyPressed(Input.KEY_ENTER) && gameChat.getText()!="") { // Updates chat
            chatText = gameChat.getText();
            chatBox.newMessage(chatText, PlayerStats.name);
            gameChat.setText("");
        }

        if(gc.getInput().isKeyPressed(Input.KEY_4)) { // Method used for quickly changing game states. Used in testing
            gameChat.setLocation(Main.ScreenWidth+200,Main.ScreenHeight+200);
            sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
        }
        if(tmpTurn != PlayerStats.turn && PlayerStats.turn != PlayerStats.ID-1){ // Writes turn info in the game window
            gameInfo = PlayerStats.names[PlayerStats.turn]+"'s turn";
            tmpTurn = PlayerStats.turn;
        }
        else if(tmpTurn != PlayerStats.turn && PlayerStats.turn == PlayerStats.ID-1){ // Writes turn info in the game window
            System.out.println(isNormalGameRound);
            gameInfo = "Your turn!";
            tmpTurn = PlayerStats.turn;
        }
    }

    /**
     * Render method. Renders elements in the game window
     @param gc GameContainer component
      *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @param g Graphics component
     *          @see Graphics and slick2D
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Texture.mapBackground.draw(0,0, Main.ScreenWidth, Main.ScreenHeight);
        if (buildMap) {
            map.GameMapGameMap();
            buildMap = false;
        }
        map.render(g, gc);
        gui_overlay.DisplayCards(200, 200, gc);
        gui_overlay.YearOfPlenty(GUI_Overlay.isYearOfPlenty, g, gc);
        gui_overlay.PlayerList(15, 40, g, PlayerStats.names, PlayerStats.points, PlayerStats.turn, gc);
        gui_overlay.ResourceBar(Main.ScreenWidth/2-250, 10, g, currentResources[0], currentResources[1], currentResources[2], currentResources[3], currentResources[4], gc);
        gui_overlay.BuildingWindow(g, theWidth - 205, theHeight - 275, 200, 270, gc);
        gui_overlay.IncomingTradeWindow(theWidth / 2 - 200, theHeight / 2 - 150, gui_overlay.tradeWindow, g, gc);
        gui_overlay.OfferWindow(theWidth / 2 - 200, theHeight / 2 - 150, gui_overlay.offerWindow, g, gc);
        gui_overlay.ShowStats(g, theWidth - 145, 10, gc);

        if (PlayerStats.targetPlayerTrade == PlayerStats.ID || PlayerStats.adjustMyResources) {
            if (PlayerStats.tradeResourcesToHandle) {
                if (!PlayerStats.resetTradingResources) {
                    gui_overlay.TradePopupWindow(theWidth - 215, theHeight - 55, GUI_Overlay.popTrade, PlayerStats.names[PlayerStats.turn], g, gc);
                } else {
                    System.out.println("Trading");
                    Trading.ReAdjustResources();
                }
            }
        }
        if (PlayerStats.playerturn[PlayerStats.ID - 1]) {
            gui_overlay.TradeWithWindow(Main.ScreenWidth - 215, 205, g, PlayerStats.names, gc);
            gui_overlay.DisplayDice(g, theWidth - 172, 75, map, true, gc);
            gui_overlay.EndTurn(theWidth - 190, 370, gc, g);
        } else {
            gui_overlay.DisplayDice(g, theWidth - 172, 75, map, false, gc);
        }

        g.setColor(Color.white);
        chatBox.render(g, gc);
        gameChat.render(gc, g);

        g.setFont(new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.BOLD, 12), true));
        g.getFont().getWidth(gameInfo);
        g.drawString(gameInfo,theWidth/2-g.getFont().getWidth(gameInfo)/2,theHeight/7);
    }

    @Override
    public int getID() {
        return 3;
    }
}
