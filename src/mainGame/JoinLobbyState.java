package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created by Bjørn on 01-11-2015.
 */
public class JoinLobbyState extends BasicGameState {

    public static Image menuBackground;
    public static Image button;
    int sizeX = Main.ScreenWidth/4;
    int sizeY = Main.ScreenHeight/10;

    Button back;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {

        menuBackground = new Image("resources/menuBackground.jpg");
        button = new Image("resources/TemplateButton.jpg");

        back = new Button(Main.ScreenWidth/2-sizeX/2,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, button);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {

        if(gameContainer.getInput().isKeyPressed(Input.KEY_C)) {
            stateBasedGame.enterState(3, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Created Lobby");
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE) || back.isWithin()){
            stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {
        graphics.drawString("Join the Lobby!", 100, 100);
        menuBackground.draw(0,0,Main.ScreenWidth,Main.ScreenHeight);
        back.draw();

    }

    @Override
    public int getID() {
        return 2;
    }
}
