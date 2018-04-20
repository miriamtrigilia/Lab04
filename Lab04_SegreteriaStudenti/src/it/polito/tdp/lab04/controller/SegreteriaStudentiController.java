

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;



import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {

	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="choiceBox"
    private ChoiceBox<String> choiceBox; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscritti"
    private Button btnCercaIscritti; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtInfo"
    private TextArea txtInfo; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    private Model model ;
    private CorsoDAO corsoDAO = new CorsoDAO();
    private StudenteDAO studenteDAO = new StudenteDAO();
    
    @FXML
    void cercaStudenteDataMatricola(MouseEvent event) {
    	
    		int matricola = 0;
    		try {
    		matricola = Integer.parseInt(this.txtMatricola.getText());
    		} catch(NumberFormatException e) {
    			 e.printStackTrace();
    	         this.txtInfo.setText("La matricola e' un campo numerico");
    	         return;
    	    }
    		Studente s = this.studenteDAO.getStudenteByMatricola(matricola);
    		this.txtNome.setText(s.getNome());
    		this.txtCognome.setText(s.getCognome());
    		
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricola = Integer.parseInt(this.txtMatricola.getText());
    	if(this.corsoDAO.isRegistered(matricola)) {
    		this.txtInfo.setText(this.corsoDAO.getCorsiByMatricola(matricola));
    		
    	}	else this.txtInfo.setText("Alla matricola "+matricola+" non e' associato nessun studente");
    		
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    		if(this.txtMatricola.getText().equals("")) {
    			if(this.choiceBox.getValue().equals(""))
    				this.txtInfo.setText("Nessun corso selezionato");
    			else {
    			String nomeCorso = this.choiceBox.getValue();
			this.txtInfo.setText(this.corsoDAO.getStudentiIscritti(nomeCorso));
    			}
    		} else {
    			int matricola = Integer.parseInt(this.txtMatricola.getText());
    			String nomeCorso = this.choiceBox.getValue();
    			if(this.corsoDAO.studenteIscrittoAlCorso(matricola, nomeCorso))
    				this.txtInfo.setText("Studente gia' iscritto al corso");
    			else 
    				this.txtInfo.setText("Studente non ancora iscritto al corso");
    		}
    			

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	int matricola = 0;
		try {
		matricola = Integer.parseInt(this.txtMatricola.getText());
		} catch(NumberFormatException e) {
			 e.printStackTrace();
	         this.txtInfo.setText("La matricola e' un campo numerico");
	         return;
	    }
	String nomeCorso = this.choiceBox.getValue();
	
	Studente studente = this.studenteDAO.getStudenteByMatricola(matricola);
	Corso corso = this.corsoDAO.getCorsoByNome(nomeCorso);
	
	if(this.corsoDAO.inscriviStudenteACorso(studente, corso))
		this.txtInfo.setText("Iscrizione avvenuta con successo");
	else
		this.txtInfo.setText("Si e' riscontrato qualche problema con l'iscrizione");
		
	
	
    }
    
    void doCercaIscrizione(ActionEvent event) {
		int matricola = Integer.parseInt(this.txtMatricola.getText());
		String nomeCorso = this.choiceBox.getValue();
		if(this.corsoDAO.studenteIscrittoAlCorso(matricola, nomeCorso))
			this.txtInfo.setText("Studente gia' iscritto al corso");
		else 
			this.txtInfo.setText("Studente non ancora iscritto al corso");
		
    }

    @FXML
    void doReset(ActionEvent event) {
    		this.txtCognome.clear();
    		this.txtInfo.clear();
    		this.txtMatricola.clear();
    		this.txtNome.clear();
    }
    
   

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtInfo != null : "fx:id=\"txtInfo\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
		choiceBox.getItems().addAll(this.corsoDAO.getNomiCorsi());
		
	}
}
