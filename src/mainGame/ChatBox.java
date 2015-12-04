package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import java.awt.*;

public class ChatBox {
    Font font;

    int chatLength = 10;
    static final int TEXTBOXWIDTH = 30;

    String textToRender[];
    String oldText[];

    public ChatBox(){

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.BOLD, 12), true);

        textToRender = new String[3];
        oldText = new String[chatLength];
    }

    public void render(Graphics g, GameContainer gc) {

        g.setColor(new Color(50, 50, 50, 200));
        g.fillRect(5, Main.ScreenHeight / 2, Main.ScreenWidth / 5, Main.ScreenHeight);
        g.setColor(Color.white);
        g.setFont(font);
        Font fontMetrics = g.getFont();

        try {

            int fontWidth0 = fontMetrics.getWidth(PlayerStats.textToRender[0].substring(0, PlayerStats.textToRender[0].indexOf(": ")));
            font.drawString(10 + fontWidth0/*PlayerStats.textToRender[0].substring(0, PlayerStats.textToRender[0].indexOf(": ")).length()*/,
                    Main.ScreenHeight - 100,
                    PlayerStats.textToRender[0].substring(PlayerStats.textToRender[0].lastIndexOf(": "), PlayerStats.textToRender[0].length()));

            font.drawString(10, Main.ScreenHeight - 80, PlayerStats.textToRender[1]);
            font.drawString(10, Main.ScreenHeight - 60, PlayerStats.textToRender[2]);

            for (int i = 0; i < PlayerStats.names.length; i++) {
                if (PlayerStats.textToRender[0].contains(PlayerStats.names[i] + ": ")) {
                    font.drawString(10, Main.ScreenHeight - 100, PlayerStats.textToRender[0].substring(0,
                            PlayerStats.textToRender[0].indexOf(": ")), PlayerStats.playerColors[i + 1]);
                    break;
                }
            }

            for (int i = chatLength-1; i >= 0; i--) {

                if(PlayerStats.oldText[i].contains(PlayerStats.names[0])) {
                    int fontWidth1 = fontMetrics.getWidth(PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")));

                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")), PlayerStats.playerColors[0 + 1]);

                    font.drawString(10+fontWidth1, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(PlayerStats.oldText[i].lastIndexOf(": "),PlayerStats.oldText[i].length()));
                    break;


                } else if(PlayerStats.oldText[i].contains(PlayerStats.names[1])) {
                    int fontWidth2 = fontMetrics.getWidth(PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")));

                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")), PlayerStats.playerColors[1 + 1]);

                    font.drawString(10+fontWidth2, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(PlayerStats.oldText[i].lastIndexOf(": "),PlayerStats.oldText[i].length()));
                    break;


                } else if(PlayerStats.oldText[i].contains(PlayerStats.names[2])) {
                    int fontWidth3 = fontMetrics.getWidth(PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")));

                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")), PlayerStats.playerColors[2 + 1]);

                    font.drawString(10+fontWidth3, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(PlayerStats.oldText[i].lastIndexOf(": "),PlayerStats.oldText[i].length()));
                    break;


                } else if(PlayerStats.oldText[i].contains(PlayerStats.names[3])) {
                    int fontWidth4 = fontMetrics.getWidth(PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")));

                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")), PlayerStats.playerColors[3 + 1]);

                    font.drawString(10+fontWidth4, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(PlayerStats.oldText[i].lastIndexOf(": "),PlayerStats.oldText[i].length()));
                    break;
                } else {
                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i, PlayerStats.oldText[i]);
                    break;
                }

            /*for (int i = 0; i < PlayerStats.names.length; i++) {
                for (int j = 0; j < chatLength; j++) {
                    if (PlayerStats.oldText[j].contains(PlayerStats.names[i])) {
                        font.drawString(10, Main.ScreenHeight - 120 - 20 * j,
                                PlayerStats.oldText[j].substring(0, PlayerStats.oldText[j].indexOf(": ")), PlayerStats.playerColors[i + 1]);
                    }
                }*/

            }


        } catch (Exception e) {}
    }

    public void newMessage(String chatText, String name){

        //updateOldMessages();
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
        PlayerStats.textSent = true;
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
    void displayText(){

    }
}