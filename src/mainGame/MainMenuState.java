package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created by Bjørn on 01-11-2015.
 */
public class MainMenuState extends BasicGameState {


    public static Image menuBackground;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {

        menuBackground = new Image("resources/menuBackground.jpg");

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {

        if(gameContainer.getInput().isKeyPressed(Input.KEY_C)) {
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Created Lobby");
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_J)){
            stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Joined Lobby");
        }

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {

        //menuBackground.draw(Main.GameWindowOffset,Main.GameWindowOffset,Main.GameWindowWidth,Main.GameWindowHeight);
        graphics.drawString("Main Menu",100,100);


    }

    @Override
    public int getID() {
        return 0;
    }
}
