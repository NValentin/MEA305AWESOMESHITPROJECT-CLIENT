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

    int sizeX = Main.ScreenWidth / 4;
    int sizeY = Main.ScreenHeight / 10;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        Texture textures = new Texture();
        textures.initMainMenuStateTextures();

        join = new Button(Main.ScreenWidth / 2 - sizeX / 2, (int) (Main.ScreenHeight * 0.50f),
                sizeX, sizeY, Texture.buttonTemplate);
        exit = new Button(Main.ScreenWidth / 2 - sizeX / 2, (int) (Main.ScreenHeight * 0.85f),
                sizeX, sizeY, Texture.buttonTemplate);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {

        /*if (gc.getInput().isKeyPressed(Input.KEY_C))
        {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Created Lobby");
        }*/

        if (gc.getInput().isKeyPressed(Input.KEY_J))
        {
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Entered Lobby");
        }

        if (join.isWithin())
        {
            (new Thread(new GameClient())).start();
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }

        if (exit.isWithin())
        {
            System.exit(0);

        }
    }

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
    public int getID()
    {
        return 0;
    }
}