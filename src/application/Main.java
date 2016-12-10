package application;
	
import org.pcap4j.Pcap4jPropertiesLoader;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.ReadPacketFile;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	//Controller controller = new Controller();
	FXMLLoader loader = new FXMLLoader();

	@Override
	public void start(Stage primaryStage) {
		try {
			//this.primaryStage = primaryStage;
			//Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			loader.setLocation(Main.class.getResource("DPiInterface.fxml"));
			AnchorPane Arp = (AnchorPane) loader.load();
			ScrollPane scroll=new ScrollPane();
		    scroll.setPrefHeight(Arp.getHeight());
		    scroll.prefWidth(Arp.getWidth());
			Controller controller = (Controller) loader.getController();
			loader.setController(controller);
			String path =controller.FileChooserPcap();
			ReadPacketFile readPacketFile= new ReadPacketFile(path);
			controller.ParserAndOrganiser();
			Scene scene = new Scene(Arp);
			primaryStage.setScene(scene);
			primaryStage.show();
			} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
