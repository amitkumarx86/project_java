package networking;


import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception{
	     
              // Creating socket
	      DatagramSocket clientSocket = new DatagramSocket();
	      
	      // Getting server ip address
	      InetAddress IPAddress = InetAddress.getByName("localhost");
	      
	      // Arrays for sending and receiving data
	      byte[] sendData = new byte[1024];
	      byte[] receiveData = new byte[1024];
	      
	      
	      // Sending data to server
	      String payload = "Z X C V B N M A S D F G";
	      sendData = payload.getBytes();
	      
	      DatagramPacket sendPacket = new DatagramPacket (sendData, sendData.length, IPAddress, 1027); // preparing header
	      clientSocket.send(sendPacket); // sending data
	      
	      // Getting data from sever
	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      clientSocket.receive(receivePacket);	// receiving data
	      String dataFromSever = new String(receivePacket.getData());
	      
	      System.out.println("FROM SERVER:" + dataFromSever);
	      
	      clientSocket.close(); // close connection
    }
}
