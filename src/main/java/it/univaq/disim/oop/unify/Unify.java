package it.univaq.disim.oop.unify;

import it.univaq.disim.oop.unify.view.ViewDispatcher;
import it.univaq.disim.oop.unify.view.ViewException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Unify extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try{
			ViewDispatcher viewDispatcher= ViewDispatcher.getInstance();
			viewDispatcher.loginView(stage);
		} catch (ViewException e) {
				e.printStackTrace();
		  }
	}
	
}