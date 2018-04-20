package it.polito.tdp.lab04.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i nomi dei corsi salvati den Db
	 */
	public List<String> getNomiCorsi(){
		
		final String sql = "SELECT nome FROM corso";

		List<String> nomiCorsi = new LinkedList<String>();
		nomiCorsi.add("");

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String nome = rs.getString("nome");
				
				nomiCorsi.add(nome);
				
			}
			
			conn.close();
			
			
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return nomiCorsi;
		
	}
	

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);
				
			}
			
			conn.close();
			
			
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		return corsi;
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		
		final String sql = "SELECT codins, crediti, nome, pd\n" + 
				"FROM corso\n" + 
				"WHERE codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setString(1, corso.getCodiceIns());
			
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Corso c = new Corso(res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"),
						res.getInt("pd")
						);
				
			}
			
			conn.close();
			
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/*
	 * Dato un nomeCorso, ottengo il corso
	 */
	public Corso getCorsoByNome(String nomeCorso) {
		
		final String sql = "SELECT codins, crediti, nome, pd\n" + 
				"FROM corso\n" + 
				"WHERE nome = ?";
		
		Corso c = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setString(1, nomeCorso);
			
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				 c = new Corso(res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"),
						res.getInt("pd")
						);
				
			}
			
			conn.close();
			
			
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return c;
		
		
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql = "SELECT s.matricola, nome, cognome, s.cds\n" + 
				"FROM iscrizione as i, studente as s\n" + 
				"WHERE i.matricola = s.matricola AND i.codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setString(1, corso.getCodiceIns());
			
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Studente s = new Studente(res.getInt("matricola"),
						res.getString("nome"),
						res.getString("cognome"),
						res.getString("cds")
						);
				
			}
			
			conn.close();
			
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		// ritorna true se l'iscrizione e' avvenuta con successo
		
		final String sql = "INSERT INTO iscritticorsi.iscrizione (matricola,codins) VALUES(?,?)";
		boolean iscritto = false;
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodiceIns());
			
			int res = st.executeUpdate();
			
			if(res == 1)
				iscritto = true;
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		if(iscritto) return true;
		
		else return false;
		
		
	}
	
	/*
	 * Dato il nome di un corso ottengo tutti gli studenti iscritti
	 */
	
	public String getStudentiIscritti(String nomeCorso) {
		
		final String sql = "SELECT s.matricola, s.nome, cognome, s.cds " + 
				"FROM iscrizione as i, studente as s, corso as c " + 
				"WHERE i.matricola = s.matricola AND i.codins = c.codins AND c.nome = ?";
		String iscritti = "";
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setString(1, nomeCorso);
			
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Studente s = new Studente(res.getInt("matricola"),
						res.getString("nome"),
						res.getString("cognome"),
						res.getString("cds")
						);
				iscritti += s.toString()+"\n"; 
			}
			conn.close();
			return iscritti;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean isRegistered(int matricola) {
		
		final String sql ="SELECT matricola, nome, cognome, cds " +
				"FROM studente " +
				"WHERE matricola = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setInt(1, matricola);
			
			ResultSet res = st.executeQuery();
			
			if (res.next()) return true;
			else return false;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	public String getCorsiByMatricola(int matricola) {
		
		final String sql = "SELECT c.codins, c.nome, c.crediti, c.pd\n" + 
				"FROM studente as s, corso as c, iscrizione as i\n" + 
				"WHERE s.matricola = i.matricola AND i.codins = c.codins AND i.matricola = ?";
		
		String corsi = "";
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setInt(1, matricola);
			
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				Corso c = new Corso(res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"),
						res.getInt("pd")
						);
				
				corsi += c.toString()+"\n";
			} 
			
			conn.close();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return corsi;
		
		
	}
	
	/*
	 * Cerca se uno studente e' iscritto a quel corso
	 */
	
	public boolean studenteIscrittoAlCorso(int matricola, String nomeCorso) {
		
		final String sql = "SELECT i.matricola, i.codins\n" + 
				"FROM studente as s, corso as c, iscrizione as i\n" + 
				"WHERE s.matricola = i.matricola AND i.codins = c.codins AND i.matricola = ? AND c.nome = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
		
			st.setInt(1, matricola);
			st.setString(2, nomeCorso);
			
			ResultSet res = st.executeQuery();
			
			if (res.next()) return true;
			
			else return false;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
