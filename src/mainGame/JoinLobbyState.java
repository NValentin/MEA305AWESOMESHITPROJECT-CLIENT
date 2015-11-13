package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
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
    TextBoxBase chatBox;

    Button back;
    Button ok;

    TextField chat;
    String chatText = "";

    Font font;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {

        menuBackground = new Image("resources/menuBackground.jpg");
        button = new Image("resources/TemplateButton.jpg");

        back = new Button(Main.ScreenWidth/2-sizeX/2,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, button);

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 18), true);

        chatBox = new TextBoxBase();
        chat = new TextField(gameContainer, font, 0, Main.ScreenHeight-sizeY/2-5, sizeX, sizeY/2);

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

        if(gameContainer.getInput().isKeyPressed(Input.KEY_ENTER) && chat.getText()!="") {
            chatText = chat.getText();
            chatBox.newMessage(chatText,TextBoxBase.personTalking);
            chat.setText("");
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g)
            throws SlickException {
        g.drawString("Lobby", 100, 100);
        menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        back.draw();
        g.drawRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f), 100, 50);
        g.drawString("PLAYERS \n \n  Fred \n  George \n  Ginny \n  Ron", Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f));


        //ok.draw();
        chatBox.render(g, gc);
        chat.render(gc, g);

    }

    @Override
    public int getID() {
        return 2;
    }
}
