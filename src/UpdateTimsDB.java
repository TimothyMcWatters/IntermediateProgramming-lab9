import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
This program:
Creates a new DB, Updates the DB, and Reads the DB using derby

@author Timothy McWatters
@version 1.0

COP3022    Lab 9
File Name: UpdateTimsDB.java
*/

public class UpdateTimsDB {

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
			System.out.println("Connecting to the database...");
        	connection = DriverManager.getConnection(protocol + dbName);
			System.out.println("Connected to the database.");

			System.out.println("Enter the name of the movie to change its rating:");
			Scanner scan = new Scanner(System.in);
			String movieNameToChange = scan.nextLine();
			System.out.println("Enter the new Rotten Tomatoes rating for this movie: ");
			int newRating = scan.nextInt();

			Statement statement = connection.createStatement();
			statement.execute("UPDATE movies " +
				"SET rottenTomatoesRating = " + newRating + " WHERE movieName = '" + movieNameToChange + "\'" );

			System.out.println("Rotton Tomatoes rating changed to " + newRating);
			connection.close();
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
}