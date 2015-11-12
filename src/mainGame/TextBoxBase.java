package mainGame;

import java.awt.Font;
import java.util.List;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

public class TextBoxBase{
    TrueTypeFont trueTypeFont;
    Image textBox;
    Image textDown;

    int line = 0;
    int size = 0;
    static final int TEXTBOXLENGTH = 25;

    String textToRender[];
    String personTalking = "";
    List <String> textQueue = new ArrayList<String>();
    List <String> nameQueue = new ArrayList<String>();

    public TextBoxBase(){
        try{
            trueTypeFont = new TrueTypeFont(new Font("Arial", Font.ITALIC, 14), true);
            textBox = new Image("resources/butt.png");
            textDown = new Image("resources/TemplateButton.jpg");
        }catch(SlickException e){}
    }

    public void render(Graphics g, GameContainer gc){

        textBox.draw(5, Main.ScreenHeight/2, Main.ScreenWidth/4-5, Main.ScreenHeight);

        try{
            trueTypeFont.drawString(10, Main.ScreenHeight/2+5, personTalking+": "+textToRender[line], Color.black);
            trueTypeFont.drawString(10, Main.ScreenHeight/2+25, textToRender[line+1] , Color.black);
            trueTypeFont.drawString(10, Main.ScreenHeight/2+45, textToRender[line+2] , Color.black);
            }catch(Exception e){}
        }

    public void newMessage(String text, String name){
        int mark = 0;
        personTalking = name;

        size = text.length()/TEXTBOXLENGTH;
        textToRender = new String[size+10];

        for (int x = 0; x < text.length(); x++){
            if (x > mark+TEXTBOXLENGTH){
                int newMark = x;

                if (text.charAt(x) != ' ' && newMark == x){
                    for (int a = x; a < text.length(); a++){
                        if (text.charAt(a) == ' '){
                            newMark = a;
                            break;
                        }
                    }
                }

                if (text.charAt(x) != ' ' && newMark == x){
                    for (int a = x; a > mark; a--){
                        if (text.charAt(a) == ' '){
                            newMark = a;
                            break;
                        }
                    }
                }

                textToRender[mark/TEXTBOXLENGTH] = text.substring(mark, newMark);
                textToRender[(mark/TEXTBOXLENGTH)+1] = "";
                textToRender[(mark/TEXTBOXLENGTH)+2] = "";

                mark = newMark;
            }else{
                if (mark+50 > text.length()){
                    textToRender[mark/TEXTBOXLENGTH] = text.substring(mark, text.length());
                }
            }
        }
    }
}