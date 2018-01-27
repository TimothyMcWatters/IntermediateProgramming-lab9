import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
This program:
Creates a new DB, Updates the DB, and Reads the DB using derby

@author Timothy McWatters
@version 1.0

COP3022    Lab 9
File Name: CreateTimsDB.java
*/

public class CreateTimsDB {
	private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String protocol = "jdbc:derby:";

	public static void main(String[] args) {
		try {
			Class.forName(driver).newInstance();
			System.out.println("Loaded the embedded driver.");
		}
		catch (Exception err) {
			System.err.println("Unable to load the embedded driver.");
			err.printStackTrace(System.err);
			System.exit(0);
        }

        String dbName = "Movies";
        Connection connection = null;
        try {
			System.out.println("Connecting to and creating the database...");
        	connection = DriverManager.getConnection(protocol + dbName + ";create=true");
			System.out.println("Database has been created.");

			Statement statement = connection.createStatement();
			
			statement.execute("CREATE TABLE movies" +
					  "(movieName varchar(80), yearReleased int, director varchar(50), rottenTomatoesRating int)");
			System.out.println("Created 'movies' table.");

			System.out.println("Inserting movies.");
			statement.execute("INSERT INTO movies " + "VALUES ('The Godfather', 1972, 'Francis Ford Coppola', 99)");
			statement.execute("INSERT INTO movies " + "VALUES ('Natural Born Killers', 1994, 'Oliver Stone', 46)");
			statement.execute("INSERT INTO movies " + "VALUES ('The Lion King', 1994, 'Roger Allers', 92)");
			statement.execute("INSERT INTO movies " + "VALUES ('A Christmas Story', 1983, 'Bob Clark', 89)");

			System.out.println("Movies inserted.");

			connection.close();
		}
		catch (SQLException err)
		{
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
}