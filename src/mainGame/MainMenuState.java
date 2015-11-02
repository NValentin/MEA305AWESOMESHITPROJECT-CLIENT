package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuState extends BasicGameState {


    public static Image menuBackground;
    public static Image button;

    Button create;
    Button join;
    Button exit;

    int sizeX = Main.ScreenWidth/4;
    int sizeY = Main.ScreenHeight/10;
    int createX = Main.ScreenWidth/2-sizeX/2;
    int createY = (int)(Main.ScreenHeight*0.4f);
    int joinX = Main.ScreenWidth/2-sizeX/2;
    int joinY = (int)(Main.ScreenHeight*0.55f);
    int exitX = Main.ScreenWidth/2-sizeX/2;
    int exitY = (int)(Main.ScreenHeight*0.85f);

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {

        menuBackground = new Image("resources/menuBackground.jpg");
        button = new Image("resources/TemplateButton.jpg");

        create = new Button(createX,createY,sizeX,sizeY, button);
        join = new Button(joinX,joinY,sizeX,sizeY, button);
        exit = new Button(exitX,exitY,sizeX,sizeY, button);

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
            System.out.println("Entered Lobby");
        }

        if(create.isWithin()){
            stateBasedGame.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }

        if(join.isWithin()){
            stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }

        if(exit.isWithin()){
            System.exit(0);

        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)
            throws SlickException {

        menuBackground.draw(0,0,Main.ScreenWidth,Main.ScreenHeight);
        create.draw();
        join.draw();
        exit.draw();
    }

    @Override
    public int getID() {
        return 0;
    }
}