package mapClasses;

import mainGame.PlayingWindow;
import org.newdawn.slick.Image;

import java.util.ArrayList;

/**
 * Created by Niels on 01-11-2015.
 */
public class Tile
{
    private char tileType;

    private float centerX, centerY;
    private int tileSize;

    //float centerX, float centerY, int tileSize, char tileType

    Tile(int q, int r, int s)
    {
        this.q = q;
        this.r = r;
        this. s = s;

        this.tileType = tileType;

        this.centerX = centerX;
        this.centerY = centerY;
        this.tileSize = tileSize;

        //createCorners();
    }
    public final int q;
    public final int r;
    public final int s;

    static public Tile add(Tile a, Tile b)
    {
        return new Tile(a.q + b.q, a.r + b.r, a.s + b.s);
    }

    static public Tile subtract(Tile a, Tile b)
    {
        return new Tile(a.q - b.q, a.r - b.r, a.s - b.s);
    }

    static public Tile scale(Tile a, int k)
    {
        return new Tile(a.q * k, a.r * k, a.s * k);
    }

    static public ArrayList<Tile> diagonals = new ArrayList<Tile>()
    {
        {
            add(new Tile(2, -1, -1));
            add(new Tile(1, -2, 1));
            add(new Tile(-1, -1, 2));
            add(new Tile(-2, 1, 1));
            add(new Tile(-1, 2, -1));
            add(new Tile(1, 1, -2));
        }
    };

    static public Tile diagonalNeighbor(Tile tile, int direction)
    {
        return Tile.add(tile, tile.diagonals.get(direction));
    }

    static public int length(Tile tile)
    {
        return (int)((Math.abs(tile.q) + Math.abs(tile.r) + Math.abs(tile.s)) / 2);
    }

    static public int distance(Tile a, Tile b)
    {
        return Tile.length(Tile.subtract(a, b));
    }
    /*
    private void createCorners()
    {
        for (int i = 0; i < 6; i++)
        {
            float angle_deg = 60 * i + 30;
            float angle_rad = (float) Math.PI / 180 * angle_deg;

            this.addPoint((int) (centerX + tileSize * Math.cos(angle_rad)),
                    (int) (centerY + tileSize * Math.sin(angle_rad)));
        }
    } */

    public Image returnTextureByType()
    {
        Image tmpTexture;
        switch (tileType)
        {
            case ('G'):
                tmpTexture = PlayingWindow.textures.getGrassTex();
                break;
            case ('R'):
                tmpTexture = PlayingWindow.textures.getDoge();
                break;
            default:
                tmpTexture = PlayingWindow.textures.getButt();
                break;
        }
        return tmpTexture;
    }
}
