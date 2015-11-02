package mainGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created by Bjørn on 01-11-2015.
 */
public class CreateLobbyState extends BasicGameState {

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {

        if(gameContainer.getInput().isKeyPressed(Input.KEY_J)){
            stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Joined Lobby");
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {
        graphics.drawString("Create a Lobby!",100,100);

    }

    @Override
    public int getID() {
        return 1;
    }
}
