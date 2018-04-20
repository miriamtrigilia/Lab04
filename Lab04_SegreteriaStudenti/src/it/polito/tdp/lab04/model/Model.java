package it.polito.tdp.lab04.model;


import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}
	
	public String getNomeCognomeByMatricola(int matricola) {
		
		Studente studente = studenteDAO.getStudenteByMatricola(matricola);
		if( studente == null) {
			return "Non ho trovato nessuno studente associato a quella matricola";
		}
		return studente.getNome()+" "+studente.getCognome();
	}
	
	public String getIscritti(String nomeCorso) {
		
		String iscritti = corsoDAO.getStudentiIscritti(nomeCorso);
		if( iscritti == null) {
			return "Non ho trovato nessuno studente associato a quel nomeCorso";
		}
		return iscritti;
	}
	
	

}
