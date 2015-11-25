package mainGame;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Kingo on 03-Nov-15.
 */
public class SQLiteJDBC {
    Connection c;
    Statement stmt;
    int cardID;
    int type;
    String headline;
    String text;
    int imageID;

    public SQLiteJDBC() {
        c = null;
        stmt = null;
    }

    public void GetInfo(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = getASingleConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM cardsDB;" );
            while (rs.next()) {
                if (rs.getInt(1) == id) {
                    cardID = rs.getInt(1);
                    type = rs.getInt(2);
                    headline = rs.getString(3);
                    text = rs.getString(4);
                    imageID = rs.getInt(5);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully!");
    }


    private static Connection getASingleConnection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:C:/Users/Kingo/Documents/GitHub/MEA305AWESOMESHITPROJECT-CLIENT/database/Settlers.sqlite");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }


    /*private ArrayList<String> getMulti(Connection c, String cardName) {
        assert c != null;
        ArrayList<String> strArr = new ArrayList<>();
        try {

            String query = "SELECT * FROM cards WHERE CardN = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, cardName);
            ResultSet rs = ps.executeQuery();
            //g�r over all items 1 gang!
            if (rs.next()) {
                strArr.add(rs.getString("Description")); //Kan v�re alt really, det er dependant p� dine column names
            }
            rs.close(); //husk at lukke connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            c.close(); // gotta close them connections
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strArr;
    } */
}