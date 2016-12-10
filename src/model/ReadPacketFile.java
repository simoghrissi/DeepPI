package model;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.ByteArrays;

@SuppressWarnings("javadoc")
public class ReadPacketFile {
	public static ArrayList<PacketARP> listpacketARP = new ArrayList<>();
	public ReadPacketFile(String file) throws PcapNativeException, NotOpenException {

		String PCAP_FILE_KEY = ReadPacketFile.class.getName() + ".pcapFile";
		String PCAP_FILE = System.getProperty(PCAP_FILE_KEY, file);

		PcapHandle handle;
		try {
			handle = Pcaps.openOffline(PCAP_FILE);
		} catch (PcapNativeException e) {
			handle = Pcaps.openOffline(PCAP_FILE);
		}
		 int i=0;
		String chaine;
		 while(PCAP_FILE!="EOF"){
			try {
				PacketHeader packetHeader = new PacketHeader();
				PacketARP packetArp = new PacketARP();
				Packet packet = handle.getNextPacketEx();
				chaine = handle.getNextPacketEx().getHeader().toString();
				String parameters[] = chaine.split("\n");
				packetHeader.setEthernetHeader(parameters[0]);
				packetHeader.setDestinationAdress(parameters[1]);
				packetHeader.setSourceAdress(parameters[2]);
				packetHeader.setType(parameters[3]);
				if(parameters[3].contains("ARP")){
					String chaineArp = packet.toString();
					String parametersArp[] = chaineArp.split("\n");
					packetArp.setHardwareType(parametersArp[5]);
					packetArp.setProtocolType(parametersArp[6]);
					packetArp.setHardwareAdressLenght(parametersArp[7]);
					packetArp.setProtocolAdressLength(parametersArp[8]);
					packetArp.setOperation(parametersArp[9]);
					packetArp.setSourceHardwarAdress(parametersArp[10]);
					packetArp.setSourceProtocolAdress(parametersArp[11]);
					packetArp.setDestinationHardwareAdress(parametersArp[12]);
					packetArp.setDestinationProtocolAdress(parametersArp[13]);
					packetArp.setHexa(parametersArp[15]);
					packetArp.setPacketHeader(packetHeader);
					packetArp.setHexaPacket(ByteArrays.toHexString(packet.getRawData(),""));
					listpacketARP.add(packetArp);
				}else{
					continue;
				}
				
			} catch (TimeoutException e) {
			} catch (EOFException e) {
				System.out.println("EOF");
				break;
			}

		}
		handle.close();
	}
}