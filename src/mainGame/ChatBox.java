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

            for (int i = 0; i < PlayerStats.names.length; i++) { // Check string for all player names
                if (PlayerStats.textToRender[0].contains(PlayerStats.names[i] + ": ")) { // If it contains a name
                    font.drawString(10, Main.ScreenHeight - 100, PlayerStats.textToRender[0].substring(0,
                            PlayerStats.textToRender[0].indexOf(": ")), PlayerStats.playerColors[i + 1]); // Draw the name in the player's color
                    font.drawString(10 + fontWidth[i], // At this x
                            Main.ScreenHeight - 100, // And this y

                            // And write the rest of the string, starting at the x coordinate where the name ends
                            PlayerStats.textToRender[0].substring(PlayerStats.textToRender[0].lastIndexOf(": "), PlayerStats.textToRender[0].length()));

                    // Draw the other (sub)strings of textToRender
                    font.drawString(10, Main.ScreenHeight - 80, PlayerStats.textToRender[1]);
                    font.drawString(10, Main.ScreenHeight - 60, PlayerStats.textToRender[2]);
                    break;
                }
            }

            // This loop basically does the same as the one above it, but for the oldText strings.
            // If it finds a name in a string, it prints that in color, then the rest of the string in white.
            // If it doesn't find a name, it just prints the string in white.
            for (int i = 0; i < chatLength; i++) {

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

    /**
     *
     * @param chatText Text to be rendered in a new message
     * @param name name of the player creating the message
     */
    public void newMessage(String chatText, String name){

        //updateOldMessages();
        int mark = 0; // Represents the number of the final character in the first line
        int mark2 = 0; // Represents the number of the final character in the second line
        String text = name+": "+chatText; // Text to be rendered, name + message

        if(text.length()<=TEXTBOXWIDTH) { // If there is only one line of text

            textToRender[0] = text;
            textToRender[1] = "";
            textToRender[2] = "";


        } else if (text.length()>TEXTBOXWIDTH && text.length()<=TEXTBOXWIDTH*2) { // If there are two lines of text
            for (int i = TEXTBOXWIDTH; i <= text.length(); i++) {
                if (i == text.length()) { // If the text is precisely long enough to be longer than the set line width, but only with one word
                    textToRender[0] = text.substring(0, text.length());
                    textToRender[1] = "";
                    textToRender[2] = "";
                } else if (text.charAt(i) == ' ') { // Else if the code runs into a space, it splits the text in two lines
                    mark = i;
                    textToRender[0] = text.substring(0, mark);
                    textToRender[1] = text.substring(mark + 1, text.length());
                    textToRender[2] = "";
                    break;
                }
            }
        } else if (text.length()>TEXTBOXWIDTH*2) { // If there are three or more lines of text
                for (int i = TEXTBOXWIDTH; i < TEXTBOXWIDTH * 2; i++) {
                    if (text.charAt(i) == ' ') { //Creates the first line in a three-line sentence
                        mark = i;
                        textToRender[0] = text.substring(0, mark);
                        break;
                    }
                }
                for (int i = mark + TEXTBOXWIDTH; i <= text.length(); i++) {
                    if (i==text.length()){ // If the second line is precisely long enough to be longer than the set line width, but only with one word
                        textToRender[1] = text.substring(mark+1,text.length());
                        textToRender[2] = "";
                        break;
                    }
                    else if (text.charAt(i) == ' ') { // If a space is found after the set line width, creates the last two lines of text
                        mark2 = i;
                        textToRender[1] = text.substring(mark+1,mark2);
                        if(text.length()<mark2+1+TEXTBOXWIDTH){
                            textToRender[2] = text.substring(mark2+1, text.length());
                        } else if(text.length()>mark2+1+TEXTBOXWIDTH){
                            textToRender[2] = text.substring(mark2+1, mark2+1+TEXTBOXWIDTH); // Messages longer than this are cut at this point
                        }
                        break;
                    }
                }
            //}
        }
        PlayerStats.textPackage = textToRender; // Update the PlayerStats text arrays with the message
        PlayerStats.textSent = true; // Tell the server that a message has been created
    }

    /**
     * Method for updating the old messages array. Not currently in use, since it was moved to the server. Could still be useful
     * in a case with no server connection
     */
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