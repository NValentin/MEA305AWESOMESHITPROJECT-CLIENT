package mainGame;

import java.awt.Font;
import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;

public class TextBoxBase{
    TrueTypeFont trueTypeFont;
    Image textBox;
    Image textDown;

    int line = 0;
    int size = 0;
    int chatLength = 10;
    static final int TEXTBOXLENGTH = 25;

    String textToRender[];
    String oldText[] = new String[chatLength];
    String personTalking = "";

    public TextBoxBase(){
        try{
            trueTypeFont = new TrueTypeFont(new Font("Arial", Font.ITALIC, 14), true);
            textBox = new Image("resources/butt.png");
        }catch(SlickException e){}
    }

    public void render(Graphics g, GameContainer gc){

        textBox.draw(5, Main.ScreenHeight/2, Main.ScreenWidth/4-5, Main.ScreenHeight);

        try{
            trueTypeFont.drawString(10, Main.ScreenHeight-80, personTalking+": "+textToRender[line], Color.black);
            trueTypeFont.drawString(10, Main.ScreenHeight-60, textToRender[line+1] , Color.black);
            trueTypeFont.drawString(10, Main.ScreenHeight-40, textToRender[line+2] , Color.black);

            for (int i = 0; i < chatLength; i++){

                trueTypeFont.drawString(10, Main.ScreenHeight - 100 - 20 * i, oldText[i], Color.black);
            }
            }catch(Exception e){}
        }

    public void newMessage(String text, String name){
        int mark = 0;
        personTalking = name;

        size = text.length()/TEXTBOXLENGTH;
        //System.out.println("text length= "+text.length());
        //System.out.println("Size= "+size);
        textToRender = new String[size+10];
        //System.out.println(textToRender.length);

        for (int x = 0; x < text.length(); x++){
            System.out.println(x);
            if (x > mark+TEXTBOXLENGTH){
                int newMark = x;
                System.out.println("x is bigger than mark+TBLength");

                if (text.charAt(x) != ' '/* && newMark == x*/){
                    System.out.println("Case 1");
                    for (int a = x; a < text.length(); a++){
                        if (text.charAt(a) == ' '){
                            System.out.println("Case 1.1");
                            newMark = a;
                            break;
                        }
                    }
                }

                if (text.charAt(x) != ' ' && newMark == x){
                    System.out.println("Case 2");
                    for (int a = x; a > mark; a--){
                        if (text.charAt(a) == ' '){
                            System.out.println("Case 2.1");
                            newMark = a;
                            break;
                        }
                    }
                }

                textToRender[mark/TEXTBOXLENGTH] = text.substring(mark, newMark);
                System.out.println(textToRender[mark/TEXTBOXLENGTH]);
                textToRender[(mark/TEXTBOXLENGTH)+1] = "";
                System.out.println(textToRender[(mark/TEXTBOXLENGTH)+1]);
                textToRender[(mark/TEXTBOXLENGTH)+2] = "";
                System.out.println(textToRender[(mark/TEXTBOXLENGTH)+2]);

                mark = newMark;
            }else{
                if (mark+50 > text.length()){
                    //System.out.println("Mark+50 is higher than text length");
                    //System.out.println("Case 3");


                    textToRender[(mark/TEXTBOXLENGTH)] = text.substring(mark, text.length());
                    //System.out.println(textToRender[mark/TEXTBOXLENGTH]);

                    for (int i = chatLength-1; i>0; i--){
                        oldText[i] = oldText[i-1];
                        //System.out.println(oldText[i]);
                    }
                    oldText[0] = textToRender[0];
                    break;
                }
            }
        }
    }
}