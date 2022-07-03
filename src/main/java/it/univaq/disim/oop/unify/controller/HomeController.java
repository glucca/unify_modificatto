package it.univaq.disim.oop.unify.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.unify.domain.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HomeController implements Initializable,DataInitializable<Account>{

    @FXML
    private Label benvenutoLabel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    benvenutoLabel.setText("Benvenuto!");
    }

    @Override
	public void dataInitializable(Account account) {
		StringBuilder testo = new StringBuilder();
		testo.append("Benvenuto ");
		testo.append(account.getNome());
		testo.append(" ");
		testo.append(account.getCognome());
		benvenutoLabel.setText(testo.toString());
	}
}
