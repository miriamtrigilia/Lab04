package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		int matricola = 146101;
		String result = model.getNomeCognomeByMatricola(matricola);
		System.out.println("Matricola: "+ matricola + " nome cognome: " + result);
		
		String iscritti = model.getIscritti("Gestione dell'innovazione e sviluppo prodotto ICT");
		System.out.println(iscritti);
		
		

	}

}
