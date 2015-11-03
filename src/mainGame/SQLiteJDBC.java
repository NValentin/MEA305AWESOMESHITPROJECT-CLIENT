package mainGame;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Kingo on 03-Nov-15.
 */
public class SQLiteJDBC {
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = getASingleConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM cardsDB;" );
            while (rs.next()) {
                int id  = rs.getInt(1);
                int type = rs.getInt(2);
                String headline = rs.getString(3);
                String text = rs.getString(4);
                int image = rs.getInt(5);
                System.out.println( "ID = " + id );
                System.out.println( "Type = " + type );
                System.out.println( "Headline = " + headline );
                System.out.println( "Text = " + text );
                System.out.println( "Image ID = " + image );
                System.out.println();
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

    public SQLiteJDBC() {
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

    private ArrayList<String> getMulti(Connection c, String cardName) {
        assert c != null;
        ArrayList<String> strArr = new ArrayList<>();
        try {

            String query = "SELECT * FROM cards WHERE CardName = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, cardName);
            ResultSet rs = ps.executeQuery();
            //går over all items 1 gang!
            if (rs.next()) {
                strArr.add(rs.getString("Description")); //Kan være alt really, det er dependant på dine column names
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
    }
}