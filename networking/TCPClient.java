package networking;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String argv[]) throws Exception
    {
     String serverName = "localhost";
     int port = 1025;
     try{
	 

	  System.out.println("Connecting to " + serverName + " on port " + port+" ..");	  // Connection request
          Socket client = new Socket(serverName, port);                                   // Connection reply
         

          System.out.println("Just connected to "  + client.getRemoteSocketAddress());   // get local port from server
          
          OutputStream outToServer = client.getOutputStream();                           // get output stream towards server
          DataOutputStream out = new DataOutputStream(outToServer);
          
          System.out.println("sending : Z X C V B N M A to server :"+serverName);	
          out.writeUTF("Z X C V B N M A");						// sending data to server
          
          InputStream inFromServer = client.getInputStream();				
          DataInputStream in = new DataInputStream(inFromServer);
          System.out.println("Server replied: "+in.readUTF());				// getting reply from server
          
          client.close();
     }
     catch(IOException e){
	 e.printStackTrace();
     }
    }
}
