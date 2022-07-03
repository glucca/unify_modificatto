package it.univaq.disim.oop.unify.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.unify.business.AccountNotFoundException;
import it.univaq.disim.oop.unify.business.AccountService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.view.ViewDispatcher;
import it.univaq.disim.oop.unify.view.ViewException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable,DataInitializable<Object>{
	
	private ViewDispatcher dispatcher;
	
	private AccountService accountService;
	
	private UnifyBusinessFactory factory;

	@FXML
    private Button loginButton;

    @FXML
    private Label loginErrorLabel;

    @FXML
    private PasswordField password;

    @FXML
    private Button registrazioneBottone;

    @FXML
    private TextField username;
    
    
    public LoginController() {
    	factory = UnifyBusinessFactory.getIstance();
    	accountService = factory.getAccountService();
    	dispatcher= ViewDispatcher.getInstance();
    }
    	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	loginButton.disableProperty().bind(username.textProperty().isEmpty()
    								  .or(password.textProperty().isEmpty()));
    }


    @FXML
    private void loginAction(ActionEvent event) {
    	try {
    		Account account = accountService.authenticate(username.getText(),password.getText());
    		dispatcher.loggedIn(account);
    	} catch(AccountNotFoundException e) {
    		loginErrorLabel.setText("Username e/o password errati");
    	} catch (BusinessException e) {
    		dispatcher.renderError(e);
    	}
    }
    
    @FXML
    private void register(ActionEvent event) throws ViewException{
    	dispatcher.register();
    }

}