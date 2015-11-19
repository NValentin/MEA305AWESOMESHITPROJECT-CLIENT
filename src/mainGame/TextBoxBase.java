package mainGame;

import java.awt.Font;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.gui.TextField;

public class TextBoxBase{
    TrueTypeFont trueTypeFont;
    Image textBox;

    int chatLength = 10;
    static final int TEXTBOXWIDTH = 30;

    String textToRender[];
    String oldText[];

    public TextBoxBase(){
        try{
            trueTypeFont = new TrueTypeFont(new Font("Verdana", Font.BOLD, 12), true);
            textBox = new Image("resources/butt.png");
        }catch(SlickException e){}


        textToRender = new String[3];
        oldText = new String[chatLength];
    }

    public void render(Graphics g, GameContainer gc){

        g.setColor(new Color(50,50,50,200));
        g.fillRect(5, Main.ScreenHeight/2, Main.ScreenWidth/5, Main.ScreenHeight);
        g.setColor(Color.white);

        try{
            trueTypeFont.drawString(10, Main.ScreenHeight-100, PlayerStats.textToRender[0], Color.white);
            trueTypeFont.drawString(10, Main.ScreenHeight-80, PlayerStats.textToRender[1] , Color.white);
            trueTypeFont.drawString(10, Main.ScreenHeight-60, PlayerStats.textToRender[2] , Color.white);

            for (int i = 0; i < chatLength; i++){

                trueTypeFont.drawString(10, Main.ScreenHeight - 120 - 20 * i, oldText[i], Color.white);
            }
            }catch(Exception e){}
        }

    public void newMessage(String chatText, String name){
        updateOldMessages();
        int mark = 0;
        int mark2 = 0;
        String text = name+": "+chatText;

        if(text.length()<=TEXTBOXWIDTH) { // If there is only one line of text

            textToRender[0] = text;
            textToRender[1] = "";
            textToRender[2] = "";


        } else if (text.length()>TEXTBOXWIDTH && text.length()<=TEXTBOXWIDTH*2) { // If there are two lines of text
            //if (text.charAt(TEXTBOXWIDTH) != ' ') {
                for (int i = TEXTBOXWIDTH; i<= text.length(); i++) {
                    if (i==text.length()){
                        textToRender[0] = text.substring(0,text.length());
                        textToRender[1] = "";
                        textToRender[2] = "";
                    }
                    else if (text.charAt(i)==' ') {
                        mark = i;
                        textToRender[0] = text.substring(0,mark);
                        textToRender[1] = text.substring(mark+1,text.length());
                        textToRender[2] = "";
                        break;
                    }
                }
            //}

        } else if (text.length()>TEXTBOXWIDTH*2) { // If there are three or more lines of text
            //if (text.charAt(TEXTBOXWIDTH) != ' ') {
                for (int i = TEXTBOXWIDTH; i < TEXTBOXWIDTH * 2; i++) {
                    if (text.charAt(i) == ' ') {
                        mark = i;
                        textToRender[0] = text.substring(0,mark);
                        break;
                    }
                }
            //}
            //if (text.charAt(mark + TEXTBOXWIDTH) != ' ') {
                for (int i = mark + TEXTBOXWIDTH; i <= text.length(); i++) {
                    if (i==text.length()){
                        textToRender[1] = text.substring(mark+1,text.length());
                        textToRender[2] = "";
                        break;
                    }
                    else if (text.charAt(i) == ' ') {
                        mark2 = i;
                        textToRender[1] = text.substring(mark+1,mark2);
                        if(text.length()<mark2+1+TEXTBOXWIDTH){
                            textToRender[2] = text.substring(mark2+1, text.length());
                        } else if(text.length()>mark2+1+TEXTBOXWIDTH){
                            textToRender[2] = text.substring(mark2+1, mark2+1+TEXTBOXWIDTH);
                        }
                        break;
                    }
                }
            //}
        }
        PlayerStats.textPackage = textToRender;
        PlayerStats.textReady = true;
    }

    public void updateOldMessages(){
         if(textToRender[1] == ""){ //If there was one line of text..
            for (int i = chatLength-1; i>0; i--){
                oldText[i] = oldText[i-1];
            }

            oldText[0] = textToRender[0];

        } else if(textToRender[1] != "" && textToRender[2] == ""){ // If there was two lines of text..
             for (int i = chatLength-1; i>1; i--){
                 oldText[i] = oldText[i-2];
             }

            oldText[1] = textToRender[0];
            oldText[0] = textToRender[1];

        } else if(textToRender[2] != ""){ // If there was three lines of text..

             for (int i = chatLength-1; i>2; i--){
                 oldText[i] = oldText[i-3];
             }

            oldText[2] = textToRender[0];
            oldText[1] = textToRender[1];
            oldText[0] = textToRender[2];
        }
    }
}