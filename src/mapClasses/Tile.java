package mapClasses;

import mainGame.Texture;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Tile
{
    private String tileType;
    private int yieldNumber;

    Tile(int q, int r, int s)
    {
        this.q = q;
        this.r = r;
        this. s = s;

        tileType = "default";
        yieldNumber = 0;
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
        return Tile.add(tile, Tile.diagonals.get(direction));
    }

    static public int length(Tile tile)
    {
        return (int)((Math.abs(tile.q) + Math.abs(tile.r) + Math.abs(tile.s)) / 2);
    }

    static public int distance(Tile a, Tile b)
    {
        return Tile.length(Tile.subtract(a, b));
    }

    public void setTileType(String tileType)
    {
        this.tileType = tileType;
    }

    public String getTileType()
    {
        return tileType;
    }

    public void setYieldNumber(int yieldNumber) {this.yieldNumber = yieldNumber; }

    public int getYieldNumber()
    {
        return yieldNumber;
    }

    public Image returnTextureByType() throws SlickException
    {
        Image tmpTexture;

        switch (tileType)
        {
            case ("Grain"):
                tmpTexture = Texture.tileSprites.getSprite(6, 0);
                break;
            case ("Lumber"):
                tmpTexture = Texture.tileSprites.getSprite(4, 0);
                break;
            case ("Wool"):
                tmpTexture = Texture.tileSprites.getSprite(3, 0);
                break;
            case ("Ore"):
                tmpTexture = Texture.tileSprites.getSprite(0, 0);
                break;
            case ("Brick"):
                tmpTexture = Texture.tileSprites.getSprite(1, 0);
                break;
            case ("Desert"):
                tmpTexture = Texture.tileSprites.getSprite(2, 0);
                break;
            case ("Water"):
                tmpTexture = Texture.tileSprites.getSprite(5, 0);
                break;
            default:
                tmpTexture = Texture.butt;
                break;
        }
        return tmpTexture;
    }
}
