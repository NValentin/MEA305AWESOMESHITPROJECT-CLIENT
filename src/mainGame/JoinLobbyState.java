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
    boolean checkIfReady = false;
    float countdown;

    Button back;
    Button forward;

    TextBoxBase chatBox;
    TextField lobbyChat;
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
                java.awt.Font.PLAIN, 12), true);
        chatBox = new TextBoxBase();
        lobbyChat = new TextField(gameContainer, font, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2);

        countdown = 6f;

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i)
            throws SlickException {

        if(gameContainer.getInput().isKeyPressed(Input.KEY_3)) {
            PlayerStats.StartGame=true;
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_ESCAPE) || back.isWithin()){
            stateBasedGame.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_ENTER) && lobbyChat.getText()!="") {
            chatText = lobbyChat.getText();
            chatBox.newMessage(chatText,PlayerStats.name);
            lobbyChat.setText("");
        }
        if(forward.isWithin() && !checkIfReady) {
            PlayerStats.lobbyReady=true;
            checkIfReady = true;
        }
        if(PlayerStats.StartGame) {
            //countdown -= 0.0012f;
            //if (countdown < 0.5f) {
            lobbyChat.setLocation(Main.ScreenWidth+200,Main.ScreenHeight+200);
                stateBasedGame.enterState(3, new FadeOutTransition(), new FadeInTransition());
            //}
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g)
            throws SlickException {
        menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        back.draw();
        back.AddText("Back", Color.white);
        forward.draw();
        forward.AddText("Ready", Color.white);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString("PLAYERS:", Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f));

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
        lobbyChat.render(gc, g);

        if(PlayerStats.StartGame){
                g.drawString("Game Starts in "+(int)countdown, Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+200);

        }

    }

    @Override
    public int getID() {
        return 2;
    }
}
