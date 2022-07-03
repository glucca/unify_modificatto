module it.univaq.disim.oop.unify {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires com.google.gson;
	requires javafx.media;
	

	opens it.univaq.disim.oop.unify.business to javafx.fxml;
	opens it.univaq.disim.oop.unify.business.impl.file to javafx.fxml;
	opens it.univaq.disim.oop.unify.business.impl.ram to javafx.fxml;
	opens it.univaq.disim.oop.unify.controller to javafx.fxml;
	opens it.univaq.disim.oop.unify.domain to javafx.fxml, javafx.base;
	opens it.univaq.disim.oop.unify.view to javafx.fxml;
	opens it.univaq.disim.oop.unify to javafx.fxml, javafx.base;

	
    exports it.univaq.disim.oop.unify ;
}
