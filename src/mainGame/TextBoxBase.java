package mainGame;

import java.awt.Font;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;

public class TextBoxBase{
    TrueTypeFont trueTypeFont;
    Image textBox;

    int size = 0;
    int chatLength = 10;
    static final int TEXTBOXWIDTH = 25;

    String textToRender[];
    String oldText[];
    static String personTalking = "";

    public TextBoxBase(){
        try{
            trueTypeFont = new TrueTypeFont(new Font("Verdana", Font.BOLD, 14), true);
            textBox = new Image("resources/butt.png");
        }catch(SlickException e){}
    }

    public void render(Graphics g, GameContainer gc){

        //textBox.draw(5, Main.ScreenHeight/2, Main.ScreenWidth/4-5, Main.ScreenHeight);

        try{
            trueTypeFont.drawString(10, Main.ScreenHeight-80, personTalking+": "+textToRender[0], Color.black);
            trueTypeFont.drawString(10, Main.ScreenHeight-60, textToRender[1] , Color.black);
            trueTypeFont.drawString(10, Main.ScreenHeight-40, textToRender[2] , Color.black);

            /*for (int i = 0; i < chatLength; i++){

                trueTypeFont.drawString(10, Main.ScreenHeight - 100 - 20 * i, oldText[i], Color.black);
            }*/
            }catch(Exception e){}
        }

    public void newMessage(String text, String name){
        int mark = 0;
        int mark2 = 0;
        personTalking = name;

        size = text.length()/TEXTBOXWIDTH;
        textToRender = new String[size+10];
        oldText = new String[chatLength];

        if(text.length()<TEXTBOXWIDTH) {
            textToRender[0] = text;
            textToRender[1] = "";
            textToRender[2] = "";

        } else if (text.length()>TEXTBOXWIDTH && text.length()<TEXTBOXWIDTH*2) {
            if (text.charAt(TEXTBOXWIDTH) != ' ') {
                for (int i = TEXTBOXWIDTH; i< TEXTBOXWIDTH*2; i++) {
                    if (text.charAt(i)==' ') {
                        mark = i;
                        textToRender[0] = text.substring(0,mark);
                        textToRender[1] = text.substring(mark+1,text.length());
                        textToRender[2] = "";
                        break;
                    }
                }
            }

        } else if (text.length()>TEXTBOXWIDTH*2) {
            if (text.charAt(TEXTBOXWIDTH) != ' ') {
                for (int i = TEXTBOXWIDTH; i < TEXTBOXWIDTH * 2; i++) {
                    if (text.charAt(i) == ' ') {
                        mark = i;
                        textToRender[0] = text.substring(0,mark);
                        break;
                    }
                }
            }
            if (text.charAt(mark + TEXTBOXWIDTH) != ' ') {
                for (int i = mark + TEXTBOXWIDTH; i < TEXTBOXWIDTH * 3; i++) {
                    if (text.charAt(i) == ' ') {
                        mark2 = i;
                        textToRender[1] = text.substring(mark+1,mark2);
                        textToRender[2] = text.substring(mark2+1, text.length());
                        break;
                    }
                }
            }
            //textToRender[0] = text.substring(0,mark);
            //textToRender[1] = text.substring(mark+1,mark2);
            //textToRender[2] = text.substring(mark2+1, text.length());
        }


        /*for (int x = 0; x < text.length(); x++){
            if (x > mark+TEXTBOXWIDTH){
                int newMark = x;

                if (text.charAt(x) != ' ' && newMark == x){
                    System.out.println("Case 1");
                    for (int a = x; a < text.length(); a++){
                        if (text.charAt(a) == ' '){
                            newMark = a;
                            break;
                        }
                    }
                }

                if (text.charAt(x) != ' ' && newMark == x){
                    System.out.println("Case 2");
                    for (int a = x; a > mark; a--){
                        if (text.charAt(a) == ' '){
                            newMark = a;
                            break;
                        }
                    }
                }

                textToRender[mark/TEXTBOXWIDTH] = text.substring(mark, newMark);
                textToRender[(mark/TEXTBOXWIDTH)+1] = "";
                textToRender[(mark/TEXTBOXWIDTH)+2] = "";

                mark = newMark;
            }else if (mark+50 > text.length()){
                    //System.out.println("Mark+50 is higher than text length");
                    System.out.println("Case 3");


                    textToRender[(mark/TEXTBOXWIDTH)] = text.substring(mark, text.length());
                    //System.out.println(textToRender[mark/TEXTBOXLENGTH]);

                    for (int i = chatLength-1; i>0; i--){
                        oldText[i] = oldText[i-1];
                        System.out.println(oldText[i]);
                    }
                    oldText[0] = textToRender[0];
                    break;
                }*/
        }
    }
