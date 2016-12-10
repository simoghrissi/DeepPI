package model;

public class PacketARP {

	String hardwareType;
	String protocolType;
	String hardwareAdressLenght;
	String protocolAdressLength;
	String operation;
	String sourceHardwarAdress;
	String sourceProtocolAdress;
	String destinationHardwareAdress;
	String destinationProtocolAdress;
	String Hexa;
	String HexaPacket;
	
	PacketHeader packetHeader;
	
	public String getHexaPacket() {
		return HexaPacket;
	}
	public void setHexaPacket(String hexaPacket) {
		HexaPacket = hexaPacket;
	}
	public String getHardwareType() {
		return hardwareType;
	}
	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}
	public String getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}
	public String getHardwareAdressLenght() {
		return hardwareAdressLenght;
	}
	public void setHardwareAdressLenght(String hardwareAdressLenght) {
		this.hardwareAdressLenght = hardwareAdressLenght;
	}
	public String getProtocolAdressLength() {
		return protocolAdressLength;
	}
	public void setProtocolAdressLength(String protocolAdressLength) {
		this.protocolAdressLength = protocolAdressLength;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSourceHardwarAdress() {
		return sourceHardwarAdress;
	}
	public void setSourceHardwarAdress(String sourceHardwarAdress) {
		this.sourceHardwarAdress = sourceHardwarAdress;
	}
	public String getSourceProtocolAdress() {
		return sourceProtocolAdress;
	}
	public void setSourceProtocolAdress(String sourceProtocolAdress) {
		this.sourceProtocolAdress = sourceProtocolAdress;
	}
	public String getDestinationHardwareAdress() {
		return destinationHardwareAdress;
	}
	public void setDestinationHardwareAdress(String destinationHardwareAdress) {
		this.destinationHardwareAdress = destinationHardwareAdress;
	}
	public String getDestinationProtocolAdress() {
		return destinationProtocolAdress;
	}
	public void setDestinationProtocolAdress(String destinationProtocolAdress) {
		this.destinationProtocolAdress = destinationProtocolAdress;
	}
	public String getHexa() {
		return Hexa;
	}
	public void setHexa(String hexa) {
		Hexa = hexa;
	}
	public PacketHeader getPacketHeader() {
		return packetHeader;
	}
	public void setPacketHeader(PacketHeader packetHeader) {
		this.packetHeader = packetHeader;
	}
	
}
