import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "testdb";
        String databaseUser = "root";
        String databasePassword = "Jahvin18";
        
        String connectionString = "jdbc:mysql://localhost/" + databaseName + "?user=" + databaseUser + "&password=" + databasePassword + "&useUnicode=true&characterEncoding=UTF-8";
     
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
