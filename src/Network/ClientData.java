package Network;

import mainGame.PlayerStats;

public class ClientData {
    public int nsID;
    public String nsname;
    public int nspoint;
    public int nsknights_played;
    public int nslength_of_road;
    public int nsresources_on_hand;
    public boolean nslobbyReady;
    public String[] nstextPackage = new String[]{"","",""};
    public boolean nsTextSent;

    public ClientData(){
    }

    public void pack(){
        nsID = PlayerStats.ID;
        nsname = PlayerStats.name;
        nspoint = PlayerStats.point;
        nsknights_played = PlayerStats.knights_played;
        nslength_of_road = PlayerStats.length_of_road;
        nsresources_on_hand = PlayerStats.resources_on_hand;
        nslobbyReady = PlayerStats.lobbyReady;
        nstextPackage = PlayerStats.textPackage;
        nsTextSent = PlayerStats.textSent;
    }

}
