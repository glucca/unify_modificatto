package it.univaq.disim.oop.unify.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.unify.business.AccountService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Utente;
import it.univaq.disim.oop.unify.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController implements Initializable, DataInitializable<Object>{

    @FXML
    private Button backButton;

    @FXML
    private TextField cognome;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    private ViewDispatcher dispatcher;
    
    private AccountService accountService;
    
    private Account account;
    
    private Object object;
    
    private UnifyBusinessFactory factory;
    
    public RegisterController() {
    	factory = UnifyBusinessFactory.getIstance();
    	accountService = factory.getAccountService();
    	dispatcher= ViewDispatcher.getInstance();
    }

    @FXML
    void back(ActionEvent event) {
    	dispatcher.logout();
    }

    @FXML
    void confirm(ActionEvent event) {
    	try {
    		if(password.getText() != null) {
    			
    			this.account = new Utente();
    			this.account.setNome(nome.getText());
    			this.account.setCognome(cognome.getText());
    			this.account.setUsername(username.getText());
    			this.account.setPassword(password.getText());
    			accountService.registrazioneUtente(account);
    			dispatcher.logout();
    		}
    	} catch (BusinessException e) {
    		e.printStackTrace();
		}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	confirmButton.setVisible(false);
		
			
	}

	@Override
	public void dataInitializable(Object t) {
		
		this.object = t;
		System.out.println("gfgfgfgfgfgfgfgfgfgfgfgfgf");
		if(nome.textProperty().isEmpty().or(cognome.textProperty().isEmpty().
				or(username.textProperty().isEmpty().
						or(password.textProperty().isEmpty()))) == null) {
			confirmButton.setDisable(true);
		}
		
		
		
	}
	

}
