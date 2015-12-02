package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created by Bjørn on 02-12-2015.
 */
public class State_EndGame  extends BasicGameState{

    public static Image menuBackground;
    public static Image button;
    int sizeX = Main.ScreenWidth/4;
    int sizeY = Main.ScreenHeight/10;


    Button backToMenu;

    ChatBox chatBox;
    TextField endChat;
    String chatText = "";
    Font font;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        menuBackground = new Image("resources/menuBackground.jpg");
        button = new Image("resources/TemplateButton.jpg");

        backToMenu = new Button(Main.ScreenWidth/2+10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, button);

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 12), true);
        chatBox = new ChatBox();
        endChat = new TextField(gameContainer, font, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        if(gameContainer.getInput().isKeyPressed(Input.KEY_ENTER) && endChat.getText()!="") {
            chatText = endChat.getText();
            chatBox.newMessage(chatText,PlayerStats.name);
            endChat.setText("");
        }

        if(backToMenu.isWithin()) {
            stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

        menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        backToMenu.draw();
        backToMenu.AddText("Main Menu", Color.white);

        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+140,sizeX,40);
        //Draw names
        g.setColor(Color.white);
        for (int i=0; i<4;i++){
            g.drawString(PlayerStats.names[i], Main.ScreenWidth/2-sizeX/2+10, (int)(Main.ScreenHeight*0.45f)+30+40*i);
        }
        g.setColor(Color.white);
        chatBox.render(g, gc);
        endChat.render(gc, g);


    }

    @Override
    public int getID() {
        return 4;
    }
}
