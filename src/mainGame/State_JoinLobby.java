package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class State_JoinLobby extends BasicGameState {

    Texture texture;

    int sizeX = Main.ScreenWidth/4; // Standard width of buttons
    int sizeY = Main.ScreenHeight/10; // Standard height of buttons

    boolean checkIfReady = false; // Conditional used to prevent spamming the 'ready' button
    float countdown; // Countdown used to start game

    Button back;
    Button forward;

    ChatBox chatBox;
    TextField lobbyChat; // Textfield used for the chatbox
    Font font; // Font used for the textfield

    /**
     * Initiation method. creates/assigns variables and objects
     * @param gc GameContainer component
     *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @throws SlickException
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException
    {
        texture = new Texture();
        texture.initJoinLobbyTextures(); //Takes textures from the texture class

        back = new Button(Main.ScreenWidth/2-sizeX-10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, Texture.templateButton);
        forward = new Button(Main.ScreenWidth/2+10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, Texture.templateButton);

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 12), true); // Font for the textfield
        chatBox = new ChatBox();
        lobbyChat = new TextField(gc, font, 5, Main.ScreenHeight-sizeY/2-5, Main.ScreenWidth/5, sizeY/2); // Textfield for chatBox

        countdown = 6f;

    }
    /**
     * Update method. Listens for conditionals
     * @param gc GameContainer component
     *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @param i Update parameter
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i)
            throws SlickException {

        if(gc.getInput().isKeyPressed(Input.KEY_3)) { // Shortcut used for starting game. Used in testing
            PlayerStats.StartGame=true;
        }

        if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE) || back.isWithin(gc)){ // Key used to go back to previous state. Used in testing
            sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }

        if(gc.getInput().isKeyPressed(Input.KEY_ENTER) && lobbyChat.getText()!="") { //sends messages when enter is pressed
            chatBox.newMessage(lobbyChat.getText(),PlayerStats.name); // Takes the text from the textfield and creates a message from it
            lobbyChat.setText(""); // resets the text in the textfield
        }
        if(forward.isWithin(gc) && !checkIfReady) { // Marks player as being ready to play
            PlayerStats.lobbyReady=true;
            checkIfReady = true;
        }
        if(PlayerStats.StartGame) { // Starts the game after countdown reaches 0.5
            //countdown -= 0.0012f;
            //if (countdown < 0.5f) {
            lobbyChat.setLocation(Main.ScreenWidth+200,Main.ScreenHeight+200); //Moves the textfield out of the window. Somehow, if not doing this,
                                                                               //the textfield is in the way of writing text in the next state
                sbg.enterState(3, new FadeOutTransition(), new FadeInTransition()); // Changes state; starts game
            //}
        }
    }

    /**
     * Render method. Renders elements in the game window
     @param gc GameContainer component
      *          @see GameContainer and Slick2D
     * @param sbg StateBasedGame component
     *          @see StateBasedGame and Slick2D
     * @param g Graphics component
     *          @see Graphics and slick2D
     * @throws SlickException
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        Texture.menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        back.draw(g);
        back.AddText("Back", Color.white);
        forward.draw(g);
        forward.AddText("Ready", Color.white);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString("PLAYERS:", Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f));

        //Draw player box 1
        g.setColor(new Color(50,50,50,200));
        if(PlayerStats.lobbyReadyAll[0]){ // If the player has pressed ready, the part of the grey box behind their name turns green
            g.setColor(new Color(0,100,0,200));
        }
        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+20,sizeX,40);
        //Draw player box 2
        g.setColor(new Color(50,50,50,200));
        if(PlayerStats.lobbyReadyAll[1]){ // If the player has pressed ready, the part of the grey box behind their name turns green
            g.setColor(new Color(0,100,0,200));
        }
        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+60,sizeX,40);
        //Draw player box 3
        g.setColor(new Color(50,50,50,200));
        if(PlayerStats.lobbyReadyAll[2]){ // If the player has pressed ready, the part of the grey box behind their name turns green
            g.setColor(new Color(0,100,0,200));
        }
        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+100,sizeX,40);
        //Draw player box 4
        g.setColor(new Color(50,50,50,200));
        if(PlayerStats.lobbyReadyAll[3]){ // If the player has pressed ready, the part of the grey box behind their name turns green
            g.setColor(new Color(0,100,0,200));
        }
        g.fillRect(Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+140,sizeX,40);
        g.setColor(Color.white);
        for (int i=0; i<4;i++){
            g.drawString(PlayerStats.names[i], Main.ScreenWidth/2-sizeX/2+10, (int)(Main.ScreenHeight*0.45f)+30+40*i); //Draw names
        }
        g.setColor(Color.white);
        chatBox.render(g, gc);
        lobbyChat.render(gc, g);

        if(PlayerStats.StartGame){
                g.drawString("Game Starts in "+(int)countdown, Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.45f)+200); // Displays countdown

        }
    }

    @Override
    public int getID() { //Returns the ID of the state. Useful for switching between states, although we haven't used it yet
        return 2;
    } //Returns the ID of the state. Useful for switching between states
}
