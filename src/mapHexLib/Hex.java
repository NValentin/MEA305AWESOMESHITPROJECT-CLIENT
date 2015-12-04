package mapHexLib;

import java.util.ArrayList;

/**
 * Part of DIY Hex-library from Author of: http://www.redblobgames.com/grids/hexagons/
 * Methods are derived directly from library, documentation comments if not.
 */
public class Hex
{
    /**
     * A hex have cubical coordinates, instead of pixel coordinates, to better understand where each hex is
     * in "grid-space". Its parameters represents XYZ, but in 2D space.
     *
     * @param q represents X.
     * @param r represents Y.
     * @param s represents Z.
     */
    public Hex(int q, int r, int s)
    {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    public final int q;
    public final int r;
    public final int s;

    /**
     * Method for returning a new Hex by adding two Hexs' coordinates.
     * Ex. Hex A(1, -1, 2) + Hex B(0, 3, -2) returns new Hex(1, 2, 0) in the grid.
     *
     * @param a Represents first hex.
     * @param b Represents second hex
     * @return returns a new Hex that is the end-result of adding two hexs.
     */
    static public Hex add(Hex a, Hex b)
    {
        return new Hex(a.q + b.q, a.r + b.r, a.s + b.s);
    }

    /**
     * Method for returning a new Hex by subtracting two Hexs' coordinates.
     *
     * @param a Represents first hex.
     * @param b Represents second hex.
     * @return returns a new hex from the subtraction.
     */
    static public Hex subtract(Hex a, Hex b)
    {
        return new Hex(a.q - b.q, a.r - b.r, a.s - b.s);
    }

    /**
     * Method for returning a new Hex by multiplying Hex A with a constant K.
     *
     * @param a Represents first hex.
     * @param k Represents constant
     * @return returns a new hex from scaling Hex a, with constant K
     */
    static public Hex scale(Hex a, int k)
    {
        return new Hex(a.q * k, a.r * k, a.s * k);
    }

    /**
     * Generate ArrayList representing the diagonals of Hex(0,0,0)
     * to get any hex's diagonals, use Hex add with center Hex as Hex A,
     * and for every hex in list as Hex B
     */
    static public ArrayList<Hex> diagonals = new ArrayList<Hex>()
    {
        {
            add(new Hex(2, -1, -1));
            add(new Hex(1, -2, 1));
            add(new Hex(-1, -1, 2));
            add(new Hex(-2, 1, 1));
            add(new Hex(-1, 2, -1));
            add(new Hex(1, 1, -2));
        }
    };

    /**
     * created a specific diagonal neighbor to a defined hex.
     *
     * @param hex       input hex, that you want a diagonal to.
     * @param direction specify in which direction you want the diagonal
     * @return returns a new Hex in the desired direction.
     */
    static public Hex diagonalNeighbor(Hex hex, int direction)
    {
        return Hex.add(hex, Hex.diagonals.get(direction));
    }

    /**
     * @param hex
     * @return
     */
    static public int length(Hex hex)
    {
        return (int) ((Math.abs(hex.q) + Math.abs(hex.r) + Math.abs(hex.s)) / 2);
    }

    /**
     * @param a
     * @param b
     * @return
     */
    static public int distance(Hex a, Hex b)
    {
        return Hex.length(Hex.subtract(a, b));
    }
}
