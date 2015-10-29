package mainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Kingo on 29-10-2015.
 */
public class GUI_Navigator extends JFrame implements ActionListener{
    //------|Navigator|------------------------------------------------
    JPanel main = new JPanel(null);
    CardLayout cardLayout = new CardLayout();
    JPanel centerPanel = new JPanel(cardLayout);
    JPanel mainMenu;
    JPanel options;
    JPanel host;
    JPanel playing;
    Font buttonFont = new Font("Arial", Font.BOLD, 20);
    Font headlineFont = new Font("Arial", Font.BOLD, 40);
    Font SecondFont = new Font("Arial", Font.BOLD, 30);
    //------|Main Menu|------------------------------------------------
    JButton B_host = new JButton("Game Lobby");
    JButton B_join = new JButton("Join Game");
    JButton B_options = new JButton("Options");
    JButton B_quit = new JButton("Quit");
    JLabel L_Headline = new JLabel("Settlers of Catan");
    JToolBar J_bar = new JToolBar();
    //------|Options|--------------------------------------------------
    JButton B_options_back = new JButton("Back");
    //------|Host Gmae|------------------------------------------------
    JButton B_start = new JButton("Start Game");
    JButton B_host_back = new JButton("Back");
    JLabel L_host_headlines = new JLabel("Host Game");
    ArrayList<LobbyPlayers> players = new ArrayList<>();
    //------|ect.|--------------------------------------------------
    String[] panels = {"mainMenu", "options", "host", "playing"};

    GUI_Navigator() {
        mainMenu = MainMenu();
        options = Options();
        host = HostGame();
        playing = Playing();
        main.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(mainMenu, panels[0]);
        centerPanel.add(options, panels[1]);
        centerPanel.add(host, panels[2]);
        centerPanel.add(playing, panels[3]);
        add(main);

        pack();
        setBounds(50, 50, 800, 600);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //------|Main Menu|------------------------------------------------
    public JPanel MainMenu() {
        JPanel tmp = new JPanel(null);
        L_Headline.setBounds(250, 50, 400, 75);
        L_Headline.setFont(headlineFont);
        tmp.add(L_Headline);
        B_host.setBounds(300, 120, 200, 50);
        B_host.setFont(buttonFont);
        B_host.addActionListener(this);
        tmp.add(B_host);
        B_join.setBounds(300, 190, 200, 50);
        B_join.setFont(buttonFont);
        B_join.addActionListener(this);
        tmp.add(B_join);
        B_options.setBounds(300, 260, 200, 50);
        B_options.setFont(buttonFont);
        B_options.addActionListener(this);
        tmp.add(B_options);
        B_quit.setBounds(300, 330, 200, 50);
        B_quit.setFont(buttonFont);
        B_quit.addActionListener(this);
        tmp.add(B_quit);
        return tmp;
    }

    //------|Options|------------------------------------------------
    public JPanel Options() {
        JPanel tmp = new JPanel(null);
        B_options_back.setBounds(100, 50, 100, 25);
        B_options_back.addActionListener(this);
        tmp.add(B_options_back);
        return tmp;
    }

    //------|Host Game|------------------------------------------------
    public JPanel HostGame() {
        JPanel tmp = new JPanel(null);
        L_host_headlines.setBounds(50, 25, 400, 75);
        L_host_headlines.setFont(SecondFont);
        tmp.add(L_host_headlines);
        B_host_back.setBounds(25, 500, 150, 50);
        B_host_back.addActionListener(this);
        tmp.add(B_host_back);
        B_start.setBounds(625, 500, 150, 50);
        B_start.addActionListener(this);
        tmp.add(B_start);
        players.add(new LobbyPlayers("Empty", Color.BLUE));
        players.add(new LobbyPlayers("Empty", Color.RED));
        players.add(new LobbyPlayers("Empty", Color.YELLOW));
        players.add(new LobbyPlayers("Empty", Color.GREEN));
        players.add(new LobbyPlayers("Empty", Color.orange));
        players.add(new LobbyPlayers("Empty", Color.black));
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setBounds(50, 100 + (45*i), 400, 40);
            tmp.add(players.get(i));
        }


        return tmp;
    }
    //------|Playing|------------------------------------------------
    public JPanel Playing() {
        JPanel tmp = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Playing");
        label.setFont(headlineFont);
        tmp.add(label, BorderLayout.CENTER);
        return tmp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //------|Buttons on Main Menu|------------------------------------------------
        if (e.getSource() == B_host) {
            System.out.println("Host Pressed!");
            cardLayout.show(centerPanel, panels[2]);
        }
        if (e.getSource() == B_join) {
            System.out.println("Join Pressed!");
        }
        if (e.getSource() == B_options) {
            System.out.println("Options Pressed!");
            cardLayout.show(centerPanel, panels[1]);
        }
        if (e.getSource() == B_quit) {
            System.out.println("Quit Pressed!");
            System.exit(0);
        }
        //------|Buttons on Options Menu|------------------------------------------------
        if (e.getSource() == B_options_back) {
            System.out.println("Back Pressed!");
            cardLayout.show(centerPanel, panels[0]);
        }
        //------|Buttons on Host Menu|------------------------------------------------
        if (e.getSource() == B_host_back) {
            System.out.println("Back pressed!");
            cardLayout.show(centerPanel, panels[0]);
        }
        if (e.getSource() == B_start) {
            System.out.println("Start pressed!");
            cardLayout.show(centerPanel, panels[3]);
        }
    }
}
