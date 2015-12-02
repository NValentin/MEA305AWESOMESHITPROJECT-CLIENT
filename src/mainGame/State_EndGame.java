package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.*;

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
    UnicodeFont font;

    String gameEnded;
    String winner;
    String winnerName;
    boolean gameWon;


    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        menuBackground = new Image("resources/menuBackground.jpg");
        button = new Image("resources/TemplateButton.jpg");

        backToMenu = new Button(Main.ScreenWidth / 2 - sizeX / 2, (int) (Main.ScreenHeight * 0.85f),
                sizeX, sizeY, Texture.buttonTemplate);

        font = new UnicodeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 46));
        chatBox = new ChatBox();
        endChat = new TextField(gameContainer, font, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2);

        gameWon = true;
        gameEnded = "Game Ended!";
        winner = "Winner:";
        winnerName = "";
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

        if(gc.getInput().isKeyPressed(Input.KEY_ENTER) && endChat.getText()!="") {
            chatText = endChat.getText();
            chatBox.newMessage(chatText,PlayerStats.name);
            endChat.setText("");
        }

        if(backToMenu.isWithin()) {
            sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
        if (gameWon) {
            for (int j = 0; j < PlayerStats.points.length; j++) {
                if (PlayerStats.points[j] == 10) {
                    winnerName = PlayerStats.names[j];
                } else {
                    winnerName = "No one!";
                }
            }
            gameWon = false;
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

        menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        backToMenu.draw();
        backToMenu.AddText("Main Menu", Color.white);


        g.setColor(new Color(50,50,50,200));
        //g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+20,sizeX,160);

        g.setColor(Color.black);
        g.setFont(new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.BOLD, 34), true));
        g.drawString(gameEnded, Main.ScreenWidth/2-g.getFont().getWidth(gameEnded)/2, (int)(Main.ScreenHeight*0.32));

        g.setFont(new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 26), true));
        g.setColor(Color.white);
        g.drawString(winner, Main.ScreenWidth/2-g.getFont().getWidth(winner)/2, (int)(Main.ScreenHeight*0.48));

        g.setFont(new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.BOLD, 46), true));
        g.setColor(new Color(0, 100, 0));
        g.drawString(winnerName, Main.ScreenWidth/2-g.getFont().getWidth(winnerName)/2, (int)(Main.ScreenHeight*0.55));


        chatBox.render(g, gc);
        endChat.render(gc, g);


    }

    @Override
    public int getID() {
        return 4;
    }
}
