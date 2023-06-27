package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.Storico;

public class StoricoDaoImpl implements StoricoDao {
	
	
final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
final String DB_URL = "jdbc:mysql://localhost:3306/gestionale";


	public StoricoDaoImpl() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	//-----metodi-------
	
	public Storico ricercaPerIdStorico(int idStorico) {
		
		String query = "SELECT * FROM storico WHERE idStorico = ?";
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, "root", "root");
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idStorico);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Storico storico = new Storico();
                storico.setIdStorico(resultSet.getInt("idStorico"));
                storico.setMatricola(resultSet.getInt("matricola"));
                storico.setIdRuolo(resultSet.getInt("idRuolo"));
                storico.setDataInizio(resultSet.getDate("dataInizio"));
                storico.setDataFine(resultSet.getDate("dataFine"));
                return storico;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return null;

	}
	
	public void inserisci(Storico s) {
		
		
		String query = "INSERT INTO storico (idStorico, matricola, idRuolo, dataInizio,"
				+ "dataFine) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DriverManager.getConnection(DB_URL,"root","root");
				PreparedStatement preparedStatement = connection.prepareStatement(query)) 
		{
			preparedStatement.setInt(1, s.getIdStorico());
			preparedStatement.setInt(2, s.getMatricola());
			preparedStatement.setInt(3, s.getIdRuolo());
			if (s.getDataInizio() != null)
				preparedStatement.setDate(4, s.getDataInizio());
			else preparedStatement.setDate(4, null);
			
			if (s.getDataFine() != null)
				preparedStatement.setDate(5, s.getDataFine());
			else preparedStatement.setDate(5, null);
			
	        int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
	            System.out.println("Ruolo inserito correttamente.");
	        } else {
	            System.out.println("Nessuna riga inserita.");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public boolean aggiorna(Storico s) {
		
		Storico storicoTrovato = ricercaPerIdStorico(s.getIdStorico());
		
		if (storicoTrovato!=null) {
			
			String query = "UPDATE storico SET idStorico = ? , matricola = ? , "
					+ "idRuolo = ? , dataInizio = ? , dataFine = ? "
					+ "WHERE idStorico = ? " ;
			try(Connection cn = DriverManager.getConnection(DB_URL,"root","root");
					PreparedStatement ps = cn.prepareStatement(query)) {
				
				ps.setInt(1, s.getIdStorico());
				ps.setInt(2, s.getMatricola());
				ps.setInt(3, s.getIdRuolo());
				ps.setDate(4, new java.sql.Date(s.getDataInizio().getTime()));
				ps.setDate(5, new java.sql.Date(s.getDataFine().getTime()));
				ps.setInt(6, s.getIdStorico());
				ps.executeUpdate();
				
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		}
		
		return false;
	}
	
	public boolean elimina(int idStorico) {
		
		Storico storicoTrovato = ricercaPerIdStorico(idStorico);
		
		if (storicoTrovato!=null) {
			
			String query = "DELETE FROM storico WHERE idStorico = ? ";
			try(Connection cn = DriverManager.getConnection(DB_URL,"root","root");
					PreparedStatement ps = cn.prepareStatement(query)) {
				
				ps.setInt(1, idStorico);
				ps.executeUpdate();
				
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		
		return false;
	}
	
	
		
		

}
