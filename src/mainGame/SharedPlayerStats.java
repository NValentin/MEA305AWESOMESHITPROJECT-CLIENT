package mainGame;

import Network.PlayerStats;

public class SharedPlayerStats {
    public int nsID;
    public String nsname;
    public int nspoint;
    public int nsknights_played;
    public int nslength_of_road;
    public int nsresources_on_hand;
    public boolean nslobbyReady;

    public SharedPlayerStats(){
        nsID = this.nsID;
        nsname = this.nsname;
        nspoint = this.nspoint;
        nsknights_played = this.nsknights_played;
        nslength_of_road = this.nslength_of_road;
        nsresources_on_hand = this.nsresources_on_hand;
        nslobbyReady = this.nslobbyReady;
    }

    public void updateStats(){
        nsID = PlayerStats.ID;
        nsname = PlayerStats.name;
        nspoint = PlayerStats.point;
        nsknights_played = PlayerStats.knights_played;
        nslength_of_road = PlayerStats.length_of_road;
        nsresources_on_hand = PlayerStats.resources_on_hand;
        nslobbyReady = PlayerStats.lobbyReady;
    }

}
