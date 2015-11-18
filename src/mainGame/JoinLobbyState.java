package mainGame;

import Network.PlayerStats;
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
    Button forward;

    TextField chat;
    String chatText = "";

    Font font;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
            throws SlickException {

        menuBackground = new Image("resources/menuBackground.jpg");
        button = new Image("resources/TemplateButton.jpg");

        back = new Button(Main.ScreenWidth/2-sizeX-10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, button);
        forward = new Button(Main.ScreenWidth/2+10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, button);

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 18), true);

        chatBox = new TextBoxBase();
        chat = new TextField(gameContainer, font, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2);

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
        if(forward.isWithin()) {
            PlayerStats.lobbyReady = true;
            //SharedData.lobbyReady[1] = true;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g)
            throws SlickException {
        g.drawString("Lobby", 100, 100);
        menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        back.draw();
        forward.draw();
        g.setColor(Color.white);
        g.drawString("PLAYERS:", Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f));
//+SharedData.names[0] + "\n  "+SharedData.names[1]+" \n  "+SharedData.names[2]+" \n  "+SharedData.names[3]

        //Draw player box 1
        g.setColor(new Color(50,50,50,200));
        if(PlayerStats.lobbyReadyAll[0]){
            g.setColor(new Color(0,100,0,200));
        }
        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+20,sizeX,40);
        //Draw player box 2
        g.setColor(new Color(50,50,50,200));
        if(PlayerStats.lobbyReadyAll[1]){
            g.setColor(new Color(0,100,0,200));
        }
        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+60,sizeX,40);
        //Draw player box 3
        g.setColor(new Color(50,50,50,200));
        if(PlayerStats.lobbyReadyAll[2]){
            g.setColor(new Color(0,100,0,200));
        }
        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+100,sizeX,40);
        //Draw player box 4
        g.setColor(new Color(50,50,50,200));
        if(PlayerStats.lobbyReadyAll[3]){
            g.setColor(new Color(0,100,0,200));
        }
        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+140,sizeX,40);
        //Draw names
        g.setColor(Color.white);
        for (int i=0; i<4;i++){
            g.drawString(PlayerStats.names[i], Main.ScreenWidth/2-sizeX/2+10, (int)(Main.ScreenHeight*0.45f)+30+40*i);
        }
        g.setColor(Color.white);
        chatBox.render(g, gc);
        chat.render(gc, g);

    }

    @Override
    public int getID() {
        return 2;
    }
}
