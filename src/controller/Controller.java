package controller;

import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ReadPacketFile;
import javafx.scene.control.TitledPane;

public class Controller implements Serializable {
	public static ArrayList<String> listheader = new ArrayList<>();
	public static ArrayList<String> listAttribute = new ArrayList<>();
	private static final String defaultFileName = "MyFile.txt";

	private Stage savedStage;
	@FXML
	TextArea AreaText;
	@FXML
	ScrollPane PaneHaut;
	@FXML
	AnchorPane accordionProtocole;
	@FXML
	VBox box;
	@FXML
	TextField AreaHexa;
	VBox packetComplet;
	ArrayList<TitledPane> listTilde = new ArrayList<>();

	synchronized public void ParserAndOrganiser() {

		PaneHaut = makeScrollable(box);
		TitledPane t1 = null;
		for (int i = 0; i < ReadPacketFile.listpacketARP.size(); i++) {

			String EthernetHeader = ReadPacketFile.listpacketARP.get(i).getPacketHeader().getEthernetHeader();
			String type = ReadPacketFile.listpacketARP.get(i).getPacketHeader().getType();
			String destinationAdress = ReadPacketFile.listpacketARP.get(i).getPacketHeader().getDestinationAdress();
			String sourceAdress = ReadPacketFile.listpacketARP.get(i).getPacketHeader().getSourceAdress();
			String header = EthernetHeader + "\n" + type + "\n" + destinationAdress + "\n" + sourceAdress + "\n";
			String headerFile = "\n" + EthernetHeader + "\n" + type + "\n" + destinationAdress + "\n" + sourceAdress
					+ "\n";

			listheader.add(header);
			String hardwareType = ReadPacketFile.listpacketARP.get(i).getHardwareType();
			String protocolType = ReadPacketFile.listpacketARP.get(i).getProtocolType();
			String hardwareAdressLenght = ReadPacketFile.listpacketARP.get(i).getHardwareAdressLenght();
			String protocolAdressLength = ReadPacketFile.listpacketARP.get(i).getProtocolAdressLength();
			String operation = ReadPacketFile.listpacketARP.get(i).getOperation();
			String sourceHardwarAdress = ReadPacketFile.listpacketARP.get(i).getSourceHardwarAdress();
			String sourceProtocolAdress = ReadPacketFile.listpacketARP.get(i).getSourceProtocolAdress();
			String destinationHardwareAdress = ReadPacketFile.listpacketARP.get(i).getDestinationHardwareAdress();
			String destinationProtocolAdress = ReadPacketFile.listpacketARP.get(i).getDestinationProtocolAdress();
			String Hexa = ReadPacketFile.listpacketARP.get(i).getHexa();
			String HexaPacket = ReadPacketFile.listpacketARP.get(i).getHexaPacket();
			String ParamARP = hardwareType + "\n" + protocolType + "\n" + hardwareAdressLenght + "\n"
					+ protocolAdressLength + "\n" + operation + "\n" + sourceHardwarAdress + "\n" + sourceProtocolAdress
					+ "\n" + destinationHardwareAdress + "\n" + destinationProtocolAdress + "\n" + Hexa + "\n";
			
			listAttribute.add(ParamARP);
			TextArea ARPcomposant = new TextArea(ParamARP);
			ARPcomposant.setMinSize(100, 100);
			ARPcomposant.setEditable(false);
			packetComplet = new VBox();
			t1 = new TitledPane("Packet" + i, packetComplet);
			t1.expandedProperty()
					.addListener((obs, oldHeight, newHeight) -> AreaHexa.setText("" + HexaPacket));
			listTilde.add(t1);

			TextArea headerArea = new TextArea(header);
			headerArea.setEditable(false);
			TitledPane t2 = new TitledPane("header", headerArea);
			t2.setMinSize(100, 100);
			packetComplet.getChildren().add(t2);
			packetComplet.getChildren().add(ARPcomposant);
			box.getChildren().add(t1);
			t1.setExpanded(false);
		}

	}



	public void save() {
		showSaveFileChooser();
	}
public void openfile() throws PcapNativeException, NotOpenException {
	String path = FileChooserPcap();
	if(!path.equals("-1")){
		ReadPacketFile.listpacketARP.clear();
		listheader.clear();
		listAttribute.clear();
		box.getChildren().clear();
		
		ReadPacketFile readPacketFile= new ReadPacketFile(path);
		ParserAndOrganiser();	
	}
	
}
public String FileChooserPcap() throws PcapNativeException, NotOpenException {
	
	FileChooser fileChooser = new FileChooser();
	File selectedFile = fileChooser.showOpenDialog(null);

	if (selectedFile != null) {
		//System.out.println(selectedFile.getAbsolutePath());
		
		return selectedFile.getAbsolutePath();
	}
	else {
		System.exit(0);
		return "-1";
		
	}
}	
	
	private void showSaveFileChooser() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save file");
		fileChooser.setInitialFileName(defaultFileName);
		File savedFile = fileChooser.showSaveDialog(savedStage);
		if (savedFile != null) {
			try {
				enregistrer(savedFile);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("File saved: " + savedFile.toString());
		}
		
	}
	private void enregistrer(File file)
			throws IOException{
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		for (int i = 0; i < listheader.size(); i++) {
			writer.write(listheader.get(i) + "\n" + listAttribute.get(i) + "\n");
		}
		writer.close();
	}
	private ScrollPane makeScrollable(final VBox node) {
		final ScrollPane scroll = new ScrollPane();
		scroll.setContent(node);
		scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
				node.setPrefWidth(bounds.getWidth());
			}
		});
		return scroll;
	}

}
