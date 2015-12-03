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

    Image thief;
    Circle circle;

    public Thief(Circle circle){
        thief = Texture.thief;
        this.circle = circle;
    }

    public void setThief(Circle circle)
    {
        this.circle = circle;
    }

    public void render(Graphics g){
        thief.setFilter(Image.FILTER_LINEAR);
        g.texture(this.circle, thief, true);

    }
}
