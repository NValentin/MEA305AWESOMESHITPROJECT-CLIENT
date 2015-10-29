package mainGame;

import javax.swing.*;
import java.awt.*;

public class LobbyPlayers extends JPanel {
    JLabel name;
    JLabel playerColor = new JLabel();


    LobbyPlayers(String _name, Color color) {
        setLayout(null);
        name = new JLabel(_name);
        name.setBounds(50, 10, 100, 20);
        playerColor.setOpaque(true);
        playerColor.setBackground(color);
        playerColor.setBounds(10, 10, 20, 20);
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(playerColor);
        add(name);
    }
}