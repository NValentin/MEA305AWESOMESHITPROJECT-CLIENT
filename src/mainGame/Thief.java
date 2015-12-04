package mainGame;

import mapClasses.Tile;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;

/**
 * Created by Bjørn on 30-11-2015.
 */
public class Thief {

    Circle circle;

    public Thief(Circle circle){
        this.circle = circle;
    }

    public Circle getCircle()
    {
        return circle;
    }

    public void setThief(Circle circle)
    {
        this.circle = circle;
    }

    public void render(Graphics g){
        Texture.thief.setFilter(Image.FILTER_LINEAR);
        g.texture(circle, Texture.thief, true);

    }
}
