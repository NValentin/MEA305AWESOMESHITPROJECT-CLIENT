package mainGame;

import mapClasses.GameMap;
import mapClasses.Tile;

import java.util.ArrayList;

/**
 * Created by Kingo on 17-Nov-15.
 */
public class GatheringResources {
    ArrayList<Tile> tiles;
    GameMap map;
    String[] resourceTypes = new String[] {"Wool", "Ore", "Lumber", "Brick", "Grain"};

    GatheringResources(GameMap map) {
        this.map = map;
    }

    public int[] Addresources(int[] input, int diceRoll,boolean isCity) {
        tiles = map.tilesYieldingResource(diceRoll);
        for (int i = 0; i < tiles.size(); i++) {
            String tmp_res = tiles.get(i).getTileType();
            for (int j = 0; j < 5; j++) {
                if (tmp_res.equals(resourceTypes[j])) {
                    if (isCity) {
                        input[i] += 2;
                    } else {
                        input[i]++;
                    }
                }
            }
        }
        return input;
    }
}
