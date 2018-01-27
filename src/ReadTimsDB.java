import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
This program:
Creates a new DB, Updates the DB, and Reads the DB using derby

@author Timothy McWatters
@version 1.0

COP3022    Lab 9
File Name: ReadTimsDB.java
*/

public class ReadTimsDB {
	private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String protocol = "jdbc:derby:";

	public static void displayNameRecord(ResultSet resultSet) throws SQLException {
		String movieName = resultSet.getString("movieName");
		int yearReleased = resultSet.getInt("yearReleased");
		String director = resultSet.getString("director");
		int rottenTomatoesRating = resultSet.getInt("rottenTomatoesRating");

		System.out.println("Movie Name = " + movieName + ", Year Released = " + yearReleased + ", Director = "
				+ director + ", Rotten Tomatoes Rating = " + rottenTomatoesRating);
	}

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

			Statement statement = connection.createStatement();

			ResultSet resultSet = null;
			System.out.println("All records:");
			resultSet = statement.executeQuery("SELECT movieName, yearReleased, director, rottenTomatoesRating FROM movies");
			while( resultSet.next() ) {
				displayNameRecord(resultSet);
			}
			resultSet.close();

			System.out.println();
			System.out.println("All records with a rating > 90:");
			resultSet = statement.executeQuery("SELECT movieName, yearReleased, director, rottenTomatoesRating " +
					"FROM movies WHERE rottenTomatoesRating > 90");
			while( resultSet.next() ) {
				displayNameRecord(resultSet);
			}
			resultSet.close();
			connection.close();
		}
		catch (SQLException err) {
			System.err.println("SQL error.");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
}