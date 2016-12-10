package model;

import java.util.ArrayList;

public class PacketHeader {
	
	String EthernetHeader; 
	String type ; 
	String destinationAdress;
	String sourceAdress;
	
	public String getEthernetHeader() {
		return EthernetHeader;
	}
	public void setEthernetHeader(String ethernetHeader) {
		EthernetHeader = ethernetHeader;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDestinationAdress() {
		return destinationAdress;
	}
	public void setDestinationAdress(String destinationAdress) {
		this.destinationAdress = destinationAdress;
	}
	public String getSourceAdress() {
		return sourceAdress;
	}
	public void setSourceAdress(String sourceAdress) {
		this.sourceAdress = sourceAdress;
	}
	
}
