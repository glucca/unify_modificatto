package it.univaq.disim.oop.unify.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.unify.business.ArtistaService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Amministratore;
import it.univaq.disim.oop.unify.domain.Artista;
import it.univaq.disim.oop.unify.domain.Canzone;
import it.univaq.disim.oop.unify.domain.Utente;
import it.univaq.disim.oop.unify.view.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ArtistiController implements Initializable, DataInitializable<Account> {

	@FXML
	private TableView<Artista> artistiTabella;

	@FXML
	private TableColumn<Artista, Integer> id ;

	@FXML
	private TableColumn<Artista, String> nome;

	@FXML
	private TableColumn<Artista, String> biografia;

	

	private ViewDispatcher dispatcher;
	private ArtistaService artistaService;
	private Account account;
	private Object object;

	public ArtistiController() {
		dispatcher = ViewDispatcher.getInstance();
		UnifyBusinessFactory factory = UnifyBusinessFactory.getIstance();
		artistaService = factory.getArtistaService();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {// ricontrollare che non va bene
		id.setCellValueFactory(new PropertyValueFactory<Artista, Integer>("id"));
		nome.setCellValueFactory(new PropertyValueFactory<Artista, String>("nome"));
		biografia.setCellValueFactory(new PropertyValueFactory<Artista, String>("biografia"));
		
	}

	@Override
	public void dataInitializable(Account account) {
		this.account = account;
		if (account instanceof Utente) {
			id.setVisible(false);
			nome.setVisible(false);
			biografia.setVisible(false);
			
		} else if (account instanceof Amministratore) {
			id.setVisible(true);
			nome.setVisible(true);
			biografia.setVisible(true);
			
		}

		try {

			List<Artista> artisti = artistaService.findAllArtisti();
			ObservableList<Artista> artistiDati = FXCollections.observableArrayList(artisti);
			artistiTabella.setItems(artistiDati);

		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}
	}

	@FXML
	public void aggiungiArtista(ActionEvent event) {
		Artista artista = new Artista();
		artista.setId(0);
		dispatcher.renderView("artista", artista);
	}
	
	@FXML
	public void modificaArtista(ActionEvent event) {
		Artista artista = artistiTabella.getSelectionModel().getSelectedItem();
		dispatcher.renderView("artista", artista);
	}
	
	@FXML
	public void annullaArtista(ActionEvent event) throws BusinessException {
		Artista artista = artistiTabella.getSelectionModel().getSelectedItem();
		artistaService.removeArtista(artista);
		List<Artista> artisti = artistaService.findAllArtisti();
		
		ObservableList<Artista> artistiDati = FXCollections.observableArrayList(artisti);
		artistiTabella.setItems(artistiDati);
		
	}
	
	

}
