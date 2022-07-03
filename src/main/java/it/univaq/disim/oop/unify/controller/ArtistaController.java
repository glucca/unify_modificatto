package it.univaq.disim.oop.unify.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.unify.business.ArtistaService;
import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Artista;
import it.univaq.disim.oop.unify.domain.Canzone;
import it.univaq.disim.oop.unify.view.ViewDispatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class ArtistaController implements Initializable, DataInitializable<Artista> {

	private ViewDispatcher dispatcher;

	private Artista artista;

	private Account account;

	private ArtistaService artistaService;

	private UnifyBusinessFactory factory;

	private byte[] fotoBytes;

	@FXML
	private Button annullaBottone;

	@FXML
	private TextArea biografia;

	@FXML
	private Button confermaBottone;

	@FXML
	private Label discografia;

	@FXML
	private Label erroreLabel;

	@FXML
	private ImageView foto;

	@FXML
	private Label id;

	@FXML
	private TextField nome;

	@FXML
	private Button caricare;

	public ArtistaController() {
		factory = UnifyBusinessFactory.getIstance();
		dispatcher = ViewDispatcher.getInstance();
		artistaService = factory.getArtistaService();

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	}

	@Override
	public void dataInitializable(Artista artista) {
		try {
			this.artista = artista;
			id.setText(Integer.toString(artista.getId()));
			nome.setText(artista.getNome().toString());
			biografia.setText(artista.getBiografia().toString());
			
			System.out.println(artista.getFoto());
			
			Image img = new Image(new ByteArrayInputStream(artista.getFoto()));
			foto.setImage(img);
			
			

			// foto.setImage(artista.getFoto()); da gestire come far visualizzare le foto (
			// sennò è pronta),
			// forse da cambiare la var foto in artista
		} catch (Exception e) {

		}

	}

	public void back(ActionEvent event) {
		dispatcher.renderView("artisti", account);
	}

	@FXML
	public void uploadImage() {

		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
		FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
		FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		if (file != null) {

			FileInputStream fl;
			try {

				fl = new FileInputStream(file);
				fotoBytes = fl.readAllBytes();
				fl.close();
				
				Image img = new Image(new ByteArrayInputStream(fotoBytes));
				foto.setImage(img);
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@FXML
	public void conferma(ActionEvent event) {

		try {
			artista.setNome(nome.getText());
			;
			artista.setBiografia(biografia.getText());
			artista.setFoto(fotoBytes);
			artista.setDiscografia(null);
			artista.setAmministratore(null);
			if (artista.getId() < 1) {
				artista.setId(increment());
				artistaService.addArtista(artista);
			} else {
				artistaService.updateArtista(artista);
			}
			dispatcher.renderView("artisti", account);
		} catch (BusinessException e) {
			dispatcher.renderError(e);
		}

	}

	@FXML
	public void indietro(ActionEvent event) {
		dispatcher.renderView("artisti", account);
	}

	public int increment() throws BusinessException {

		List<Artista> artisti = artistaService.findAllArtisti();

		int id = 1;

		if (artisti.size() > 0) {
			id = artisti.get(artisti.size() - 1).getId() + 1;
		}
		return id;

	}

	// da cambiare
	/*
	 * public void confirm(ActionEvent event) { if(pagamento.isSelected()) { try {
	 * artistaService.updateArtista(artista); } catch (BusinessException e) {
	 * e.printStackTrace(); } dispatcher.renderView("aggiungiChilometraggio",
	 * artista); } else { if(ora.getValue().toString().isBlank() ||
	 * minuto.getValue().toString().isBlank() || descrizione.getText().isBlank())
	 * erroreLabel.setText("Inserisci tutti i parametri"); else {
	 * artista.setStatoNoleggio(Stato_Noleggio.IN_CORSO);
	 * artista.setOrarioRitiro(LocalTime.parse(ora.getValue().toString() + ":" +
	 * minuto.getValue().toString())); try { artistaService.updateArtista(artista);
	 * dispatcher.renderView("artisti", artista.getArtista()); } catch
	 * (BusinessException e) { dispatcher.renderError(e); } } } }
	 */

}