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

    float sizeX = Main.ScreenWidth/4;
    float sizeY = Main.ScreenHeight/10;
    float createX = Main.ScreenWidth/2-sizeX/2;
    float createY = Main.ScreenHeight*0.4f;
    float joinX = Main.ScreenWidth/2-sizeX/2;
    float joinY = Main.ScreenHeight*0.55f;
    float exitX = Main.ScreenWidth/2-sizeX/2;
    float exitY = Main.ScreenHeight*0.7f;
    Color button = new Color(50,50,50);

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
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
            throws SlickException {

        menuBackground.draw(0,0,Main.ScreenWidth,Main.ScreenHeight);


        g.drawRect(createX,createY,sizeX,sizeY);
        g.drawString("Create Game",createX+Main.ScreenWidth*0.02f,createY+Main.ScreenHeight*0.04f);

        g.drawRect(joinX,joinY,sizeX,sizeY);
        g.drawString("Join Game",joinX+Main.ScreenWidth*0.02f,joinY+Main.ScreenHeight*0.04f);

        g.drawRect(exitX,exitY,sizeX,sizeY);
        g.drawString("Close Application",exitX+Main.ScreenWidth*0.02f,exitY+Main.ScreenHeight*0.04f);


    }

    @Override
    public int getID() {
        return 0;
    }
}
