package last;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnexion {
	 public static void main(String[] args) {
	        try (Connection connection = DatabaseConnection.getConnection()) {
	            System.out.println("Connexion réussie !");
	        } catch (SQLException e) {
	            System.err.println("Échec de la connexion : " + e.getMessage());
	        }      }   }