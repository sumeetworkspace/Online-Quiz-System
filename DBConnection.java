import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quiz_system";
    private static final String USER = "sumeetjadhav10";
    private static final String PASSWORD = "Saj@100903";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}