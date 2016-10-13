package networking;


import java.net.*;
import java.util.Arrays;
import java.io.*;


public class TCPServer{
    private  ServerSocket serverSocket;
    
    public TCPServer(int port) throws IOException
    {
       serverSocket = new ServerSocket(port); // setting port number @server
       serverSocket.setSoTimeout(10000); // timeout of socket @server
    }

    
    public static void main(String [] args) throws IOException
    {
       int port = Integer.parseInt("1025");
       TCPServer tcpServer = new TCPServer(port);
       while(true)
       {
          try
          {
             System.out.println("Waiting for client on port " +
             tcpServer.serverSocket.getLocalPort() + "...");
             
             Socket server = tcpServer.serverSocket.accept();		// Con is established
             
             System.out.println("Just connected to " + server.getRemoteSocketAddress());
             
             DataInputStream in =  new DataInputStream(server.getInputStream());
             
             String[] arr = in.readUTF().split(" ");			// getting data from client
             
             // Performing sorting operation
             String line="";
             Arrays.sort(arr);
             for(String a: arr) line += a+" " ;
             
             DataOutputStream out = new DataOutputStream(server.getOutputStream()); 
             
             out.writeUTF(line);	// sending data to client
             server.close();             
             
          }catch(SocketTimeoutException s)
          {
             System.out.println("Socket timed out!");
             break;
          }catch(IOException e)
          {
             e.printStackTrace();
             break;
          }
       }
        
    }
}
