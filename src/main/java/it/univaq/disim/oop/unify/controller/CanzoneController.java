package it.univaq.disim.oop.unify.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import it.univaq.disim.oop.unify.business.ArtistaService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.business.CanzoneService;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Artista;
import it.univaq.disim.oop.unify.domain.Canzone;
import it.univaq.disim.oop.unify.domain.Genere;
import it.univaq.disim.oop.unify.view.ViewDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class CanzoneController implements Initializable, DataInitializable<Canzone> {

	private ViewDispatcher dispatcher;

	private CanzoneService canzoneService;

	private ArtistaService artistaService;

	private Canzone canzone;

	private Artista artista;

	private UnifyBusinessFactory factory;

	private Account account;

	@FXML
	private TextField titolo;

	@FXML
	private ComboBox<Artista> nomeArtista;

	@FXML
	private TextArea testo;
	
	@FXML
	private ComboBox<Genere> genere;
	
	@FXML
	private Label path;
	
	@FXML
	private Button upload;


	@FXML
	private Button salvaButton;

	@FXML
	private Button annullaButton;

	public CanzoneController() {
		factory = UnifyBusinessFactory.getIstance();
		dispatcher = ViewDispatcher.getInstance();
		canzoneService = factory.getCanzoneService();
		artistaService = factory.getArtistaService();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// canzone.getItem().addAll(canzone.values());
	}

	@Override
	public void dataInitializable(Canzone canzone) {
		try {

			this.canzone = canzone;
			List<Artista> artisti = artistaService.findAllArtisti();
			ObservableList<Artista> artistiDati = FXCollections.observableArrayList(artisti);
			nomeArtista.setItems(artistiDati);
			
			
			List<Genere> generi = new ArrayList<>();
			Genere gen1 = new Genere();
			gen1.setId(1);
			gen1.setNome("Rock");
			
			Genere gen2 = new Genere();
			gen2.setId(2);
			gen2.setNome("Classic");
			
			generi.add(gen1);
			generi.add(gen2);
			
			ObservableList<Genere> generiDati = FXCollections.observableArrayList(generi);
			genere.setItems(generiDati);
			
			titolo.setText(canzone.getTitolo().toString());
			testo.setText(canzone.getTesto().toString());
			path.setText(canzone.getPath());

		} catch (Exception e) {

			//e.printStackTrace();

		}

	}

	@FXML
	public void conferma(ActionEvent event) {

		try {
			canzone.setTitolo(titolo.getText());
			canzone.setNome(nomeArtista.getSelectionModel().getSelectedItem());
			canzone.setTesto(testo.getText());
			canzone.setGenere(genere.getSelectionModel().getSelectedItem());
			canzone.setPath(path.getText());
			
			if (canzone.getId() < 1) {
				canzoneService.addCanzone(canzone);
			} else {
				canzoneService.updateCanzone(canzone);
			}
			dispatcher.renderView("canzoni", account);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}

	}
	
	

	@FXML
	void indietro(ActionEvent event) {
		dispatcher.renderView("canzoni", account);
	}

	@FXML
	void update(ActionEvent event) {
		canzone.setTitolo(titolo.getText());
		canzone.setNome(artista);
		canzone.setTesto(testo.getText());
	}
	
	@FXML
	public void uploadMp3() {

		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilterMP3 = new FileChooser.ExtensionFilter("MP3 files (*.MP3)", "*.MP3");
		FileChooser.ExtensionFilter extFiltermp3 = new FileChooser.ExtensionFilter("mp3 files (*.mp3)", "*.mp3");
		
		fileChooser.getExtensionFilters().addAll(extFilterMP3, extFiltermp3);
		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {

			
			try {

				path.setText(file.getAbsolutePath());
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}


}