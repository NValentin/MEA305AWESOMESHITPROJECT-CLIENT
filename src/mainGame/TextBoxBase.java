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

    int line = 2;
    int size = 0;
    int cooldown = 0;
    static final int COOLDOWNSET = 20;
    static final int TEXTBOXLENGTH = 40;

    float boxFader = 0;
    int boxSlider = 160;
    int textFader = 0;

    public boolean rendering = false;

    String textToRender[];
    String personTalking = "";
    List <String> textQueue = new ArrayList<String>();
    List <String> nameQueue = new ArrayList<String>();

    public TextBoxBase(){
        try{
            trueTypeFont = new TrueTypeFont(new Font("Arial", Font.ITALIC, 16), true);
            textBox = new Image("resources/butt.png");
            textDown = new Image("resources/butt.png");
        }catch(SlickException e){}
    }

    public void render(Graphics g, GameContainer gc){
        Input input = gc.getInput();

        g.drawImage(textBox, 90, 360+boxSlider, new Color(1f,1f,1f,boxFader));

        if (cooldown == 0){
            g.drawImage(textDown, 650, 500+boxSlider,new Color(1f,1f,1f,boxFader));
        }

        if (rendering){
            if (boxFader < 1){
                boxFader += 0.10;
                boxSlider -= 15;
            }

            if (textFader != 255){
                textFader += 15;
            }

            try{
                trueTypeFont.drawString(170, 410, personTalking, Color.black);
                trueTypeFont.drawString(200, 430, textToRender[line] , new Color(0, 0, 0, textFader));
                trueTypeFont.drawString(200, 450, textToRender[line+1] , new Color(0, 0, 0, textFader));
                trueTypeFont.drawString(200, 470, textToRender[line+2] , new Color(0, 0, 0, textFader));
            }catch(Exception e){}

            if (input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)){
                if (line <= size && cooldown == 0){line+=3; cooldown = COOLDOWNSET; textFader = 0;}
            }

            if (line > size){
                rendering = false;
            }

            if (cooldown != 0){cooldown--;}
        }

        if (!rendering){
            line = 0;

            if (!textQueue.isEmpty()){
                spliceText(textQueue.get(0), nameQueue.get(0));
                textQueue.remove(0);
                nameQueue.remove(0);
            }else{
                if (boxFader != 0){
                    boxFader -= 0.30;
                    boxSlider+=40;
                }
            }

            textFader = 0;
        }
    }

    public void spliceText(String text, String name){
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
        rendering = true;
    }

    public void newMessage(String text, String name){
        if (rendering == false){
            spliceText(text, name);
        }else if (rendering){
            textQueue.add(text);
            nameQueue.add(name);
        }
    }
}