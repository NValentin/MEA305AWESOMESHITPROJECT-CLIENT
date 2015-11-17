package mainGame;

import Network.GameClient;
import Network.PlayerStats;
import org.newdawn.slick.*;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class CreatePlayerState extends BasicGameState {

    public static Image menuBackground;
    public static Image button;
    int sizeX = Main.ScreenWidth/4;
    int sizeY = Main.ScreenHeight/10;

    Button back;
    Button forward;
    Button ok;

    TextField nameField;

    String playerName = "";

    Font font;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {

        menuBackground = new Image("resources/menuBackground.jpg");
        button = new Image("resources/TemplateButton.jpg");

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 24), true);

        nameField = new TextField(gameContainer, font, Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.55f), sizeX, (int)(sizeY*0.7));

        back = new Button(Main.ScreenWidth/2-sizeX-10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, button);
        forward = new Button(Main.ScreenWidth/2+10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, button);
        ok =  new Button(Main.ScreenWidth/2+sizeX/2-sizeX/3, (int)(Main.ScreenHeight*0.55f)+sizeY+10, sizeX/3, sizeY/3, button);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {

        if(gameContainer.getInput().isKeyPressed(Input.KEY_J)){
            stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Joined Lobby");
        }
        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE) || back.isWithin()){
            stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }

        if(forward.isWithin() && playerName != ""){
            stateBasedGame.enterState(2, new FadeOutTransition(), new FadeInTransition());
            PlayerStats.name = playerName;
            TextBoxBase.personTalking = playerName;
            //SharedData.names[1] = playerName;
        }

        if((gameContainer.getInput().isKeyPressed(Input.KEY_ENTER)||ok.isWithin()) && nameField.getText()!=""){
            playerName = nameField.getText();
            nameField.setText("");
            //System.out.println(playerName);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g)
            throws SlickException {
        g.drawString("Create a name!",100,100);
        menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        back.draw();
        forward.draw();
        ok.draw();
        nameField.render(gc, g);
        g.drawString("Your name: "+playerName,Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.5f));

    }

    @Override
    public int getID() {
        return 1;
    }
}