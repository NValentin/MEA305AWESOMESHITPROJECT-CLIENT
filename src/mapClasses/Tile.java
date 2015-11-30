package mapClasses;

import mainGame.Texture;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * This "Tile"-class, extends class Hex, from the DIY Hex Library from author of:
 * http://www.redblobgames.com/grids/hexagons/ and add additional specific functionality to Hexes, so they can be used
 * as tiles in the game.
 */
public class Tile extends Hex
{
    //Tile variables, for their individual type and number
    private String tileType;
    private int yieldNumber;
    public boolean hasThief;

    /**
     * A tile have cubical coordinates, instead of pixel coordinates, to better understand where each tile is
     * in "grid-space". Its parameters represents XYZ, but in 2D space.
     * @param q represents X.
     * @param r represents Y.
     * @param s represents Z.
     */
    Tile(int q, int r, int s)
    {
        super(q, r, s);

        tileType = "default";
        yieldNumber = 0;
        hasThief = false;
    }

    /**
     * Sets a tiles TileType. (corresponding to the desired texture)
     * @param tileType input string of tileType, can be Grain, Wool, Brick, Wood, Harbour, Ore, Desert, and Water.
     */
    public void setTileType(String tileType)
    {
        this.tileType = tileType;
    }

    /**
     * Gets a tile's TileType
     * @return returns String with its TileType.
     */
    public String getTileType()
    {
        return tileType;
    }

    /**
     * Set a Tile's yieldNumber, the number a tile gets that when rolled in the game, it will yield resource on.
     * @param yieldNumber int var, that should be from 2-12, as it has to be rolled from 2 dice.
     */
    public void setYieldNumber(int yieldNumber)
    {
        this.yieldNumber = yieldNumber;
    }

    /**
     * returns a tile's yieldnumber.
     * @return returns int of yieldNumber (int between 2-12)
     */
    public int getYieldNumber()
    {
        if(hasThief){
            return 0;
        } else {
            return yieldNumber;
        }
    }

    /**
     * Method for fetching correct texture in Texture class, from a tile's TileType. if input string, does not match
     * it defaults to a texture that is not part of the game.
     * @return Image from a spriteSheet, tileSprites in Texture.
     * @throws SlickException
     * @see Image Slick2D doc.
     */
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
            case ("Harbour"):
                tmpTexture = Texture.doge;
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
