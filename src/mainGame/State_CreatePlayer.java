package mainGame;

import org.newdawn.slick.*;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class State_CreatePlayer extends BasicGameState {

    Texture texture;
    int sizeX = Main.ScreenWidth/4; // Standard width of buttons
    int sizeY = Main.ScreenHeight/10; // Standard height of buttons

    Button back;
    Button forward;

    TextField nameField;

    Font font; // Font used in the textfield

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
            throws SlickException {

        texture = new Texture();
        texture.initCreatePlayerTextures(); //Takes textures from the texture class

        font = new TrueTypeFont(new java.awt.Font("Verdana",
                java.awt.Font.PLAIN, 24), true); // Font used in the textfield

        nameField = new TextField(gc, font, Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.55f), sizeX, (int)(sizeY*0.7)); //Field for name
        nameField.setMaxLength(12); // Sets a max limit to number of characters in the textfield

        back = new Button(Main.ScreenWidth/2-sizeX-10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, "templateButton");
        forward = new Button(Main.ScreenWidth/2+10,(int)(Main.ScreenHeight*0.85f), sizeX, sizeY, "templateButton");

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

        if (gc.getInput().isKeyPressed(Input.KEY_2)) { //Shortcut for changing states. Used when testing
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
            System.out.println("Joined Lobby");
        }
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE) || back.isPressed(gc)) { // Press Esc or button to return to main menu
            sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }

        if ((forward.isPressed(gc) || gc.getInput().isKeyPressed(Input.KEY_ENTER)) // Press enter or button to enter lobby
                && nameField.getText().length() != 0 && !nameField.getText().substring(0,1).matches(" ")) {
            PlayerStats.name = nameField.getText(); // Updates the player name variable

            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
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
        g.drawString("Create a name!", 100, 100);
        Texture.menuBackground.draw(0, 0, Main.ScreenWidth, Main.ScreenHeight);
        back.draw(g);
        back.AddText("Back", Color.white);
        forward.draw(g);
        forward.AddText("Join", Color.white);
        nameField.render(gc, g);
        g.drawString("Write your name (Max. 12 characters):", Main.ScreenWidth/2-sizeX/2, (int)(Main.ScreenHeight*0.5f));
    }

    @Override
    public int getID() {
        return 1;
    } //Returns the ID of the state. Useful for switching between states
}