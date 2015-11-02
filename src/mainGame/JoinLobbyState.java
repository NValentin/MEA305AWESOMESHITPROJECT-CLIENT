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
public class JoinLobbyState extends BasicGameState {

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {

        if(gameContainer.getInput().isKeyPressed(Input.KEY_C)) {
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Created Lobby");
        }

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
            throws SlickException {
        graphics.drawString("Join the Lobby!", 100, 100);

    }

    @Override
    public int getID() {
        return 2;
    }
}
