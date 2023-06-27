package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Impiegato;

public class ImpiegatoDaoImpl implements ImpiegatoDao {

    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/gestionale";
    
    public ImpiegatoDaoImpl() {
    	try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    // ----- metodi -------
    
    public List<Impiegato> ricercaPerCognome(String cognome) {
    	
    	
    	List<Impiegato> listaImpiegatiTrovati = new ArrayList<>();
    	String query = "SELECT * FROM impiegato WHERE cognome = ?";
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, "root", "root");
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cognome);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Impiegato impiegato = new Impiegato();
                impiegato.setNome(resultSet.getString("nome"));
                impiegato.setCognome(resultSet.getString("cognome"));
                impiegato.setCodiceFiscale(resultSet.getString("codiceFiscale"));
                impiegato.setMatricola(resultSet.getInt("matricola"));
                listaImpiegatiTrovati.add(impiegato);
            }
            return listaImpiegatiTrovati;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    	
    	return null;
    }

    public Impiegato ricercaPerCodiceFiscale(String codiceFiscale) {

        String query = "SELECT * FROM impiegato WHERE codicefiscale = ?";
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, "root", "root");
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codiceFiscale);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Impiegato impiegato = new Impiegato();
                impiegato.setNome(resultSet.getString("nome"));
                impiegato.setCognome(resultSet.getString("cognome"));
                impiegato.setCodiceFiscale(resultSet.getString("codiceFiscale"));
                impiegato.setMatricola(resultSet.getInt("matricola"));
                return impiegato;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public void inserisci(Impiegato i) {

        String query = "INSERT INTO impiegato (nome, cognome, codicefiscale, matricola)"
        		+ " VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, "root", "root");
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, i.getNome());
            statement.setString(2, i.getCognome());
            statement.setString(3, i.getCodiceFiscale());
            statement.setInt(4, i.getMatricola());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean aggiorna(Impiegato i) {

        if (i != null) {

            if (ricercaPerCodiceFiscale(i.getCodiceFiscale()) != null) {
                String query = "UPDATE impiegato SET nome = ?, cognome = ?, matricola = ?"
                		+ " WHERE codicefiscale = ?";
                try (Connection connection = DriverManager.getConnection
                		(DB_URL, "root", "root");
                        PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, i.getNome());
                    statement.setString(2, i.getCognome());
                    statement.setInt(3, i.getMatricola());
                    statement.setString(4, i.getCodiceFiscale());
                    statement.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean elimina(String codiceFiscale) {

        if (codiceFiscale != null) {

            if (ricercaPerCodiceFiscale(codiceFiscale) != null) {
                String query = "DELETE FROM impiegato WHERE codicefiscale = ?";
                try (Connection connection = DriverManager.getConnection
                		(DB_URL, "root", "root");
                        PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, codiceFiscale);
                    statement.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
    
}

