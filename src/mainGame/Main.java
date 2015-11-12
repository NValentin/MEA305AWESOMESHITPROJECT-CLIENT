package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame
{
	public static final int ScreenWidth = 1280;
	public static final int ScreenHeight = 720;
	
	public Main(String gameName)
	{
		super(gameName);
	}

	public static void main(String[] args) throws SlickException
	{
        AppGameContainer appgc = new AppGameContainer(new Main("Dick Butt: The Game"));

        appgc.setDisplayMode(ScreenWidth, ScreenHeight, false);
        appgc.setAlwaysRender(true);

		appgc.start();
	}

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new MainMenuState());
        this.addState(new CreatePlayerState());
        this.addState(new JoinLobbyState());
		this.addState(new PlayingWindow());
    }
}