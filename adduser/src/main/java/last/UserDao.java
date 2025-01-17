package last;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
	  public void addUser(User user) throws SQLException {
	        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, user.getUsername());
	            statement.setString(2, user.getEmail());
	            statement.setString(3, user.getPassword());
	            statement.executeUpdate();
	        }
	    }
	}