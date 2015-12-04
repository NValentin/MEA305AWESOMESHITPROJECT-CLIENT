package mainGame;

import org.newdawn.slick.*;

public class ChatBox {
    Font font; // Font used for chat messages

    int chatLength = 10;
    static final int TEXTBOXWIDTH = 30;

    String textToRender[];
    String oldText[];
    int fontWidth[] = new int[4];

    /**
     * Constructor for the chatbox. Creates instances of arrays and font.
     */
    public ChatBox(){

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.BOLD, 12), true); // Font used for chat messages

        textToRender = new String[3]; // Bottom three lines of chat text, the 'current' message
        oldText = new String[chatLength]; // Array of old messages, updated on the server
    }

    /**
     * Render method. Renders elements in the game window
     * @param g Graphics component
     *          @see Graphics and slick2D
     * @param gc GameContainer component
     *          @see GameContainer and Slick2D
     */
    public void render(Graphics g, GameContainer gc) {

        g.setColor(new Color(50, 50, 50, 200)); //Dark grey, opaque color. Used for..
        g.fillRect(5, Main.ScreenHeight / 2, Main.ScreenWidth / 5, Main.ScreenHeight); // -the chat window
        g.setColor(Color.white); // Text color
        g.setFont(font); // Chat font
        Font fontMetrics = g.getFont(); // fontMetrics is used for calculating the width of names in pixels

        // Int width of player names
        fontWidth[0] = fontMetrics.getWidth(PlayerStats.names[0]);
        fontWidth[1] = fontMetrics.getWidth(PlayerStats.names[1]);
        fontWidth[2] = fontMetrics.getWidth(PlayerStats.names[2]);
        fontWidth[3] = fontMetrics.getWidth(PlayerStats.names[3]);


        try {

            for (int i = 0; i < PlayerStats.names.length; i++) {
                if (PlayerStats.textToRender[0].contains(PlayerStats.names[i] + ": ")) {
                    font.drawString(10, Main.ScreenHeight - 100, PlayerStats.textToRender[0].substring(0,
                            PlayerStats.textToRender[0].indexOf(": ")), PlayerStats.playerColors[i + 1]);
                    font.drawString(10 + fontWidth[i]/*PlayerStats.textToRender[0].substring(0, PlayerStats.textToRender[0].indexOf(": ")).length()*/,
                            Main.ScreenHeight - 100,
                            PlayerStats.textToRender[0].substring(PlayerStats.textToRender[0].lastIndexOf(": "), PlayerStats.textToRender[0].length()));

                    font.drawString(10, Main.ScreenHeight - 80, PlayerStats.textToRender[1]);
                    font.drawString(10, Main.ScreenHeight - 60, PlayerStats.textToRender[2]);
                    break;
                }
            }

            for (int i = 0; i < chatLength; i++) {

                //font.drawString(10, Main.ScreenHeight - 120 - 20 * i, PlayerStats.oldText[i]); //Worst case scenario, THIS works, outcomment rest

                if(PlayerStats.oldText[i].contains(PlayerStats.names[0]+": ")) {

                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")), PlayerStats.playerColors[0 + 1]);

                    font.drawString(10+fontWidth[0], Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(PlayerStats.oldText[i].lastIndexOf(": "),PlayerStats.oldText[i].length()));

                } else if(PlayerStats.oldText[i].contains(PlayerStats.names[1]+": ")) {

                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")), PlayerStats.playerColors[1 + 1]);

                    font.drawString(10+fontWidth[1], Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(PlayerStats.oldText[i].lastIndexOf(": "),PlayerStats.oldText[i].length()));


                } else if(PlayerStats.oldText[i].contains(PlayerStats.names[2]+": ")) {

                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")), PlayerStats.playerColors[2 + 1]);

                    font.drawString(10+fontWidth[2], Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(PlayerStats.oldText[i].lastIndexOf(": "),PlayerStats.oldText[i].length()));


                } else if(PlayerStats.oldText[i].contains(PlayerStats.names[3]+": ")) {

                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(0, PlayerStats.oldText[i].indexOf(": ")), PlayerStats.playerColors[3 + 1]);

                    font.drawString(10+fontWidth[3], Main.ScreenHeight - 120 - 20 * i,
                            PlayerStats.oldText[i].substring(PlayerStats.oldText[i].lastIndexOf(": "),PlayerStats.oldText[i].length()));
                } else {
                    font.drawString(10, Main.ScreenHeight - 120 - 20 * i, PlayerStats.oldText[i]);
                }
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