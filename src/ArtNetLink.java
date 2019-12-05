import java.net.InetAddress;
import java.net.UnknownHostException;

import ch.bildspur.artnet.ArtNetClient;

public class ArtNetLink {
	public ArtNetClient artnet;
	public byte[] dmxData = new byte[512];
	
	public ArtNetLink(int universe) {
		artnet = new ArtNetClient();
		  try {
			artnet.start(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		(new Thread() {
		  public void run() {
			  while(true) {
				  dmxData = artnet.readDmxData(0, universe);
			  }
		  }
		 }).start();
	}
}
