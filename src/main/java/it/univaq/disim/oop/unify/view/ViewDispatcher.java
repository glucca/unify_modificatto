package it.univaq.disim.oop.unify.view;

import java.io.IOException;

import it.univaq.disim.oop.unify.controller.DataInitializable;
import it.univaq.disim.oop.unify.domain.Account;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewDispatcher {
	
	private static final String FXML_SUFFIX = ".fxml";
	
	private static final String RESOURCE_BASE = "/viste/";
	
	private static ViewDispatcher instance = new ViewDispatcher();
	
	private Stage stage;
	
	private BorderPane layout;
	
//	private ViewDispatcher() {
	//}
	
	public static ViewDispatcher getInstance() {
		return instance;
	}
	
	
	
	public void loginView(Stage stage) throws ViewException {
		this.stage = stage;
		Parent loginView = loadView("login").getView();
		Scene scene = new Scene(loginView);
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	public void loggedIn(Account account) {
		try {			
			
			View<Account>layoutView = loadView("layout");
			DataInitializable<Account> layoutController = layoutView.getController();
			layoutController.dataInitializable(account);
			layout = (BorderPane) layoutView.getView();
			renderView("home", account);
			Scene scene = new Scene(layout);
			scene.getStylesheets().add(getClass().getResource(RESOURCE_BASE + "styles.css").toExternalForm());
			stage.setScene(scene);
			
		} catch (ViewException e) {
			e.printStackTrace();
			renderError(e);
		}
	}
	
	public void logout() {
		try {
			Parent loginView = loadView("login").getView();
			Scene scene = new Scene(loginView);
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
	}
	
	public void callView(String nameView) {
		
		try {
			Parent view = loadView(nameView).getView();
			Scene scene = new Scene(view);
			stage.setScene(scene);
		} catch (ViewException e) {
			renderError(e);
		}
		
	}
	
	public <T,D> void renderView(String viewName, T data) {
		try {
			View<T> view = loadView(viewName);
			DataInitializable<T> controller = view.getController();
			controller.dataInitializable(data);
			layout.setCenter(view.getView());
		} catch (ViewException e) {
			renderError(e);
		}
	}
	
	
	public void renderError(Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
	
	private <T,D> View<T> loadView(String view) throws ViewException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(RESOURCE_BASE + view + FXML_SUFFIX));
			Parent parent = (Parent) loader.load();
			return new View<>(parent, loader.getController());
		} catch (IOException e) {
			throw new ViewException(e);
		}
	}
	
	public void register() throws ViewException{
		try {
			Parent registerView = loadView("register").getView();
			Scene scene = new Scene(registerView);
			stage.setScene(scene);
			//stage.centerOnScreen();
		} catch(ViewException e) {
			renderError(e);
		}
	}
}























