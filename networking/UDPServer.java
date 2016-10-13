package networking;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;


public class UDPServer {
    public static void main(String args[]) throws Exception
    {
          DatagramSocket serverSocket = new DatagramSocket(1027); // open UDP socket
       	  serverSocket.setSoTimeout(100000); // setting time out for server socket
          
       	  byte[] receiveData = new byte[1024];
          byte[] sendData = new byte[1024];
          
          System.out.println("UDP Server Started..");
          while(true)
             {
                try
                {
                    
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket); // blocks until packet is received
                    
                    String data = new String( receivePacket.getData());
                    System.out.println("RECEIVED: " + data);
                    InetAddress IPAddress = receivePacket.getAddress(); // getting ip address of client
                    int port = receivePacket.getPort(); // getting port of client
                    
                    //sort data 
                    data = data.replaceAll(" ", "");
                     
                    char[] arr = data.trim().toCharArray();
                    Arrays.sort(arr);
                     
                    data = "";
                    for(char c: arr) data += c;
                    System.out.println("SENDING: " + data);
                    //sending data back to client
                    sendData = data.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendPacket);
                     
                }
                catch(SocketTimeoutException s)
                {
                    System.out.println("UDP Socket timed out!");
                    serverSocket.close();
                    break;
                }
                catch(IOException e)
                {
                   e.printStackTrace();
                   break;
                }
             }
   }
}
