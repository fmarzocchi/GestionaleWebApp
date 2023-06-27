package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Ruolo;

public class RuoloDaoImpl implements RuoloDao{
	
	
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/gestionale";
	
	
	public RuoloDaoImpl() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//-----metodi-------
	
	public Ruolo ricercaPerId(int id) {
		
		String query = "SELECT * FROM ruolo WHERE idRuolo = ?";
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, "root", "root");
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Ruolo ruolo = new Ruolo();
                ruolo.setDescrizione(resultSet.getString("descrizione"));
                ruolo.setIdRuolo(id);
                return ruolo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
       
        return null;

	}
	
	public void inserisci(Ruolo r) {
		
		String query = "INSERT INTO ruolo (idRuolo, descrizione) VALUES (?, ?)";
	    try (Connection cn = DriverManager.getConnection(DB_URL,"root","root");
	         PreparedStatement ps = cn.prepareStatement(query)) {
	        ps.setInt(1, r.getIdRuolo());
	        ps.setString(2, r.getDescrizione());
	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Ruolo inserito correttamente.");
	        } else {
	            System.out.println("Nessuna riga inserita.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public boolean aggiorna(Ruolo r) {
		
		Ruolo ruoloTrovato = ricercaPerId(r.getIdRuolo());
		
		if (ruoloTrovato!=null) {
			String query ="UPDATE ruolo SET descrizione = ? WHERE idRuolo = ? ";
			try ( Connection cn = DriverManager.getConnection(DB_URL);
					PreparedStatement ps = cn.prepareStatement(query)) {
				
				ps.setInt(2, r.getIdRuolo());
				ps.setString(1, r.getDescrizione());
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
	
	public boolean elimina(int id) {
		
		Ruolo ruoloTrovato = ricercaPerId(id);
		
		if (ruoloTrovato!=null) {
			String query = "DELETE FROM ruolo WHERE idRuolo = ? ";
			try(Connection cn = DriverManager.getConnection(DB_URL);
					PreparedStatement ps = cn.prepareStatement(query)) {
				
				ps.setInt(1, id);
				ps.executeUpdate();
				
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}

}
