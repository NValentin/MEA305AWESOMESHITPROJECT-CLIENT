package mainGame;

import mapClasses.Tile;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Bjørn on 30-11-2015.
 */
public class Thief {

    Image thief;
    Circle circle;
    boolean moveThief;

    public Point getPoint() {
        return point;
    }

    Point point;

    public Thief(Point point){
        this.point = point;
        thief = Texture.thief;
        circle = new Circle(point.getX(), point.getY(), 20);
    }

    public void remove(Tile tile) {

        tile.hasThief = false;
        PlayingWindowState.gameInfo = "Place the Thief on a resource tile";
    }

    public void render(Graphics g){
        thief.setFilter(Image.FILTER_LINEAR);
        g.texture(circle, thief, true);

    }

    public void moveThief(Point point)
    {
        this.point = point;
    }

}
