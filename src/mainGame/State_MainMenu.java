package mainGame;

import Network.GameClient;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class State_MainMenu extends BasicGameState
{
    private Button join;
    private Button exit;

    int sizeX = Main.ScreenWidth / 4; // Standard width of buttons
    int sizeY = Main.ScreenHeight / 10; // Standard height of buttons
    boolean firstGame; // Conditional meant to be used for games following the first one, to not connect to the server twice

    /**
     * Initiation method. creates/assigns variables and objects
     * @param gc GameContainer component
     *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        Texture textures = new Texture();
        textures.initMainMenuStateTextures(); //Takes textures from the texture class

        join = new Button(Main.ScreenWidth / 2 - sizeX / 2, (int) (Main.ScreenHeight * 0.50f),
                sizeX, sizeY, Texture.buttonTemplate);
        exit = new Button(Main.ScreenWidth / 2 - sizeX / 2, (int) (Main.ScreenHeight * 0.85f),
                sizeX, sizeY, Texture.buttonTemplate);
        firstGame = true; // See above
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
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
        if (gc.getInput().isKeyPressed(Input.KEY_J)) // Shortcut for getting to the lobby. Used for testing.
        {
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Entered Lobby");
        }

        if (join.isWithin()) // Starts the run method in GameClient and enters new state when button is clicked
        {
            if(firstGame) {
                (new Thread(new GameClient())).start();
                firstGame = false;
            }
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }

        if (exit.isWithin())
        {
            System.exit(0);

        }

        if(gc.getInput().isKeyPressed(Input.KEY_4)) {
            sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
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
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        Texture.menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        join.draw();
        exit.draw();
        join.AddText("Join Game", Color.white);
        exit.AddText("Exit", Color.white);
    }

    @Override
    public int getID() //Returns the ID of the state. Useful for switching between states, although we haven't used it yet
    {
        return 0;
    }
}