package last;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDoa {
	private String dburl = "jdbc:mysql://localhost:3306/user_management";
    private String dbuser = "root";
    private String dbpass = "";
    private String dbdriver = "com.mysql.cj.jdbc.Driver"; // Updated driver
    //loading the driver
    public void loadDriver(String dbDriver) {
        try {
            Class.forName(dbDriver); // Ensure the driver is loaded
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // make the connection
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl, dbuser, dbpass); // Assign connection here
        } catch (SQLException e) {
            e.printStackTrace(); // Log connection errors
        }
        return conn;
    }
    //do the post
    public String insert(User user) {
        loadDriver(dbdriver);
        Connection con = getConnection();
        if (con == null) return "Database connection failed.";
        String result = "Added successfully";
        String sql = "INSERT INTO `users` (`username`, `email`, `password`) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            result = "Not added due to an error.";
        }
        return result;
    }
   // do the get
    public List<User> getAllUsers() {
        loadDriver(dbdriver);
        Connection con = getConnection();
        List<User> users = new ArrayList<>();
        
        if (con == null) {
            return null; // Handle null connection
        }

        String sql = "SELECT username, password , email FROM users";

        try {
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                users.add(new User(username,email,password)); // Add user to list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close(); // Ensure connection is closed
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

}
