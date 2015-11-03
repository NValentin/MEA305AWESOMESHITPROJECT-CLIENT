package mainGame;

import java.sql.*;

/**
 * Created by Kingo on 03-Nov-15.
 */
public class SQLiteJDBC {
    public static void main( String args[] )
    {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public SQLiteJDBC() {
    }
}
