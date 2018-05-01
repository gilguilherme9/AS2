package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gilguilherme
 */
public class server {
    private static UUID serverid;
    private DataOutputStream logfile=null;
    private Socket serverSocket = null;
    private Socket clientSocket = null;
    private int monitorportnr,serverport,queuemax;
    private String ip;
    
    public server(int monitorportnr, int serverport, String ip, int queuemax){
        this.serverid=UUID.randomUUID();
        this.monitorportnr=monitorportnr;
        this.ip=ip;
        this.serverport=serverport;
        this.queuemax=queuemax;
    }
    
    public void monitorConnect(){
        try {
            clientSocket = new Socket(ip, monitorportnr);
            logfile=new DataOutputStream(clientSocket.getOutputStream());
            logfile.writeBytes(String.valueOf(serverid));
            logfile.flush();
             
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    
    
    
    public static void main( String[] args ) throws Exception{
        // create a srevr socket
        
        serverSocket = new Socket("", port);
        System.out.println( "Server is listening on port: " + port );
        while (true ) {
            System.out.println( "Server is accepting a new connection" );
            // wait for a new connection/client
            clientSocket = serverSocket.accept();
            // create a new thread to deal with the new client
            ThreadEcho te=new ThreadEcho(clientSocket);
            System.out.println("Socket: " + clientSocket.getLocalPort());
            te.start();
        }
    }
    
}
