package mainGame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;


/**
 * Thief class, thief is placed on a tile, and uses a circle from thiefPositions in gameMap
 */
public class Thief {

    //circle representing the thief
    Circle circle;

    /**
     * Constructor of the thief
     * @param circle Circle
     *               @see Circle and Slick2D javadoc
     */
    public Thief(Circle circle){
        this.circle = circle;
    }

    /**
     * Gets the circle representing the thief
     * @return Circle
     * @see Circle
     */
    public Circle getCircle()
    {
        return circle;
    }

    /**
     * Sets a new Circle as the thief, moving it
     * @param circle Circle of the thief
     */
    public void setThief(Circle circle)
    {
        this.circle = circle;
    }

    /**
     * Thief render class, draws the thief on the screen
     * @param g Graphics component
     *          @see Graphics
     */
    public void render(Graphics g){
        Texture.thief.setFilter(Image.FILTER_LINEAR);
        g.texture(circle, Texture.thief, true);

    }
}
