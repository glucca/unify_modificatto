package it.univaq.disim.oop.unify.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.univaq.disim.oop.unify.business.AccountService;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Amministratore;
import it.univaq.disim.oop.unify.domain.Utente;
import it.univaq.disim.oop.unify.view.MenuElement;
import it.univaq.disim.oop.unify.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class LayoutController implements Initializable,DataInitializable<Account>{
	//da ricontrollare
	//MENU HOME
	private static final MenuElement MENU_HOME = new MenuElement("Home", "home");
	
	//MENU ADMIN
	private static final MenuElement[] MENU_AMMINISTRATORI = {
			new MenuElement ("Gestione Utente", "register"),
			new MenuElement ("Artisti", "artisti"),
			new MenuElement ("Album", "album"),
			new MenuElement ("Canzoni", "canzoni")
		};
	
	//MENU USER
	private static final MenuElement[] MENU_UTENTI = {
			new MenuElement ("Artisti", "artisti"),
			new MenuElement ("Album", "album"),
			new MenuElement ("Canzoni", "canzoni"),
			new MenuElement ("Playlist", "playlist")
		};
	
	private Account account;
	
	private ViewDispatcher dispatcher;
	
	private UnifyBusinessFactory factory;
	
	private AccountService accountService;
	
	@FXML
	private ImageView esciButton;
	
	@FXML
	private VBox menuBar;
	
	public LayoutController() {
		factory = UnifyBusinessFactory.getIstance();
		accountService = factory.getAccountService();
		dispatcher = ViewDispatcher.getInstance();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
	@Override
	public void dataInitializable(Account account) {
		this.account = account;
System.out.println("ciaoooo");
		menuBar.getChildren().addAll(createButton(MENU_HOME));
		
		menuBar.getChildren().add(new Separator());
		
	
		if (account instanceof Amministratore) {
			for (MenuElement menu : MENU_AMMINISTRATORI) {
				menuBar.getChildren().add(createButton(menu));
			}
		}
		if (account instanceof Utente) {
			for (MenuElement menu : MENU_UTENTI) {
				menuBar.getChildren().add(createButton(menu));
			}
		}
	}

	
	@FXML
    private void logoutAction(MouseEvent event) {
    	ViewDispatcher dispatcher = ViewDispatcher.getInstance();
    	dispatcher.logout();
    	
    }
	
	private Button createButton(MenuElement viewItem) {
		System.out.println("ciaoooofdfdfdfdfdfdfdfddffdf");
		Button button = new Button(viewItem.getNome());
		button.setStyle("-fx-background-color: transparent; -fx-font-size: 14;");
		button.setTextFill(Paint.valueOf("black"));
		button.setPrefHeight(10);
		button.setPrefWidth(180);
		button.setOnAction((ActionEvent event) -> {
			dispatcher.renderView(viewItem.getVista(),account);
		});
	return button;
	}

}