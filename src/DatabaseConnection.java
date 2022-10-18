import java.sql.Connection;
import java.sql.DriverManager;

/* Code that connects tot he local database. */

public class DatabaseConnection {
    
    public Connection databaseLink;

    public Connection getConnection(){

        /* Local host security and inforamtion
           to connect to the correct local host database. */

        String databaseName = "testdb";
        String databaseUser = "root";
        String databasePassword = "Jahvin18";
        
        String connectionString = "jdbc:mysql://localhost/" + databaseName + "?user=" + databaseUser + "&password=" + databasePassword + "&useUnicode=true&characterEncoding=UTF-8";
     
        /* Try and Catch methotds, top ensure we're connected to a database
           or if the connection fails prints out an error statement. */

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(connectionString, databaseUser, databasePassword);

        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
