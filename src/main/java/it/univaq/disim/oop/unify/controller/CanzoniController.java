package it.univaq.disim.oop.unify.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.ResourceBundle;

import it.univaq.disim.oop.unify.business.BusinessException;
import it.univaq.disim.oop.unify.business.CanzoneService;
import it.univaq.disim.oop.unify.business.UnifyBusinessFactory;
import it.univaq.disim.oop.unify.domain.Account;
import it.univaq.disim.oop.unify.domain.Artista;
import it.univaq.disim.oop.unify.domain.Canzone;
import it.univaq.disim.oop.unify.view.ViewDispatcher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class CanzoniController implements Initializable, DataInitializable<Account>{

	@FXML
	private Button aggiungiButton;
	
    @FXML
    private Label canzoniLabel;
    
    @FXML
    private TableView<Canzone> tabellaCanzoni;
    
    @FXML
    private TableColumn<Canzone, String> titoloTableColumn;

    @FXML
    private TableColumn<Canzone, Artista> nomeArtistaTableColumn;

    @FXML
    private TableColumn<Canzone, String> testoTableColumn;
    
    //@FXML
    //private TableColumn<Canzone, Button> azioniTableColumn;

    private ViewDispatcher dispatcher;
    private CanzoneService canzoneService;
    
    private Media media;
	private MediaPlayer mediaPlayer;
    
    private Account account;
    
    public CanzoniController() {
    	dispatcher = ViewDispatcher.getInstance();
    	UnifyBusinessFactory factory = UnifyBusinessFactory.getIstance();
    	canzoneService = factory.getCanzoneService();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
	    titoloTableColumn.setCellValueFactory(new PropertyValueFactory<>("titolo"));
	    nomeArtistaTableColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    testoTableColumn.setCellValueFactory(new PropertyValueFactory<>("testo"));
	    /*
	    azioniTableColumn.setStyle("-fx-alignment: CENTER;");
	    azioniTableColumn.setCellValueFactory((CellDataFeatures<Canzone, Button> param) -> {
		    final Button canzoniButton = new Button("Modifica");
		    canzoniButton.setOnAction((ActionEvent event) -> {
		    	dispatcher.renderView("canzone", param.getValue());
		    });
		    return new SimpleObjectProperty<Button>(canzoniButton);
	    });
	    */
    }
      
    @Override
    public void dataInitializable(Account account) {
	   this.account = account;
	   try {
		   List<Canzone> canzoni = canzoneService.findAllCanzoni();
		   ObservableList<Canzone> canzoniDati = FXCollections.observableArrayList(canzoni);
		   tabellaCanzoni.setItems(canzoniDati);
	   } catch(BusinessException e) {
		   dispatcher.renderError(e);
	   }
    }

    @FXML
    void aggiungiCanzone(ActionEvent event) {
    	Canzone canzone = new Canzone();
    	dispatcher.renderView("canzone", canzone);
    }
    
    @FXML
	public void modificaCanzone(ActionEvent event) {
		Canzone canzone = tabellaCanzoni.getSelectionModel().getSelectedItem();
		dispatcher.renderView("canzone", canzone);
	}
	
	@FXML
	public void annullaCanzone(ActionEvent event) throws BusinessException {
		Canzone canzone = tabellaCanzoni.getSelectionModel().getSelectedItem();
		canzoneService.removeCanzone(canzone);
		List<Canzone> canzoni = canzoneService.findAllCanzoni();
		
		ObservableList<Canzone> canzoniDati = FXCollections.observableArrayList(canzoni);
		tabellaCanzoni.setItems(canzoniDati);
		
	}
	
	@FXML
	public void play(ActionEvent event) throws UnsupportedEncodingException {
		
		Canzone canzone = tabellaCanzoni.getSelectionModel().getSelectedItem();
		
		File file = new File(canzone.getPath());
		
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		
	}
	
	@FXML
	protected void pause(ActionEvent event) {
		
		mediaPlayer.pause();
	}

	@FXML
	protected void reset(ActionEvent event) {
		
		mediaPlayer.seek(Duration.seconds(0));
	}

}
