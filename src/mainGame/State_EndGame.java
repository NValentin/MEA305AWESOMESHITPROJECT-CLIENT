package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class State_EndGame  extends BasicGameState{

    Texture texture;
    int sizeX = Main.ScreenWidth/4;
    int sizeY = Main.ScreenHeight/10;


    Button backToMenu;

    ChatBox chatBox;
    TextField endChat;
    TrueTypeFont font;

    String gameEnded;
    String winner;
    String winnerName;
    boolean gameWon;

    Font chatFont;
    Font endedFont;
    Font winnerFont;
    Font nameFont;


    /**
     * Initiation method. creates/assigns variables and objects
     * @param gc GameContainer component
     *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @throws SlickException
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        texture = new Texture();
        texture.initEndGameTextures(); //Takes textures from the texture class

        // Create the 4 fonts used in this game state
        chatFont = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 12), true);
        endedFont = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.BOLD, 34), true);
        winnerFont = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 26), true);
        nameFont = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.BOLD, 46), true);

        backToMenu = new Button(Main.ScreenWidth / 2 - sizeX / 2, (int) (Main.ScreenHeight * 0.85f),
                sizeX, sizeY, Texture.buttonTemplate);

        chatBox = new ChatBox();
        endChat = new TextField(gc, chatFont, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2);

        gameWon = true;
        gameEnded = "Game Ended!";
        winner = "Winner:";
        winnerName = "";
    }

    /**
     * Update method. Listens for conditionals
     * @param gc GameContainer component
     *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @param i Update parameter
     *          @see Update and Slick2D
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        if(gc.getInput().isKeyPressed(Input.KEY_ENTER) && endChat.getText()!="") { //sends messages when enter is pressed
            chatBox.newMessage(endChat.getText(),PlayerStats.name); // Takes the text from the textfield and creates a message from it
            endChat.setText(""); // resets the text in the textfield
        }

        if(backToMenu.isWithin()) { //This was an attempt to reset the game. Currently, it DOES NOT work. The only way out of this screen is closing the game.
            /*State_PlayingWindow.buildMap = true;
            sbg.getState(2).init(gc, sbg);
            sbg.getState(3).init(gc, sbg);
            sbg.getState(4).init(gc, sbg);
            sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());*/
            System.exit(0); // Terminates the program
        }
        if (gameWon) { //If someone won the game, checks which player has 10 points. Updates winnerName accordingly.
            for (int j = 0; j < PlayerStats.points.length; j++) {
                if (PlayerStats.points[j] == 10) {
                    winnerName = PlayerStats.names[j];
                } else {
                    winnerName = "No one!";
                }
            }
            gameWon = false;
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

        Texture.menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        backToMenu.draw();
        backToMenu.AddText("End Game", Color.white);


        g.setColor(new Color(50,50,50,200));

        //Draw string with particular color and size
        g.setColor(Color.black);
        g.setFont(endedFont);
        g.drawString(gameEnded, Main.ScreenWidth / 2 - g.getFont().getWidth(gameEnded) / 2, (int) (Main.ScreenHeight * 0.32));

        //Draw string with particular color and size
        g.setFont(winnerFont);
        g.setColor(Color.white);
        g.drawString(winner, Main.ScreenWidth/2-g.getFont().getWidth(winner)/2, (int)(Main.ScreenHeight*0.48));

        //Draw string with particular color and size
        g.setFont(nameFont);
        g.setColor(new Color(0, 100, 0));
        g.drawString(winnerName, Main.ScreenWidth/2-g.getFont().getWidth(winnerName)/2, (int)(Main.ScreenHeight*0.55));

        chatBox.render(g, gc);
        endChat.render(gc, g);


    }

    @Override
    public int getID() { //Returns the ID of the state. Useful for switching between states, although we haven't used it yet
        return 4;
    }
}
