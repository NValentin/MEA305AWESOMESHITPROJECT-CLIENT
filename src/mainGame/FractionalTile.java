package mainGame;

import java.util.ArrayList;

/**
 * Created by Niels on 02-11-2015.
 */
class FractionalTile
{
    public FractionalTile(double q, double r, double s)
    {
        this.q = q;
        this.r = r;
        this.s = s;
    }

    public final double q;
    public final double r;
    public final double s;

    static public Tile tileRound(FractionalTile h)
    {
        int q = (int) (Math.round(h.q));
        int r = (int) (Math.round(h.r));
        int s = (int) (Math.round(h.s));
        double q_diff = Math.abs(q - h.q);
        double r_diff = Math.abs(r - h.r);
        double s_diff = Math.abs(s - h.s);
        if (q_diff > r_diff && q_diff > s_diff)
        {
            q = -r - s;
        } else if (r_diff > s_diff)
        {
            r = -q - s;
        } else
        {
            s = -q - r;
        }
        return new Tile(q, r, s);
    }

    static public FractionalTile tileLerp(Tile a, Tile b, double t)
    {
        return new FractionalTile(a.q + (b.q - a.q) * t, a.r + (b.r - a.r) * t, a.s + (b.s - a.s) * t);
    }

    static public ArrayList<Tile> tileLinedraw(Tile a, Tile b)
    {
        int N = Tile.distance(a, b);
        ArrayList<Tile> results = new ArrayList<Tile>(){{}};
        double step = 1.0 / Math.max(N, 1);
        for (int i = 0; i <= N; i++)
        {
            results.add(FractionalTile.tileRound(FractionalTile.tileLerp(a, b, step * i)));
        }
        return results;
    }

}
