/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dusan
 */
public class Server extends Thread{
    
    private ServerSocket serverSocket;
    
    private List<ClientHandler> soketi;
    

    public Server() {
        try {
            int port = Integer.parseInt(konfiguracija.Konfiguracija.getKonfiguracija().getProperty("port"));
            serverSocket = new ServerSocket(port);
            soketi = new ArrayList<>();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        
        while(!serverSocket.isClosed()){
            try {
                System.out.println("Ceka se klijent...");
                Socket klijent = serverSocket.accept();
                System.out.println("Klijent se povezao");
                ClientHandler clientHandler = new ClientHandler(klijent);
                soketi.add(clientHandler);
                clientHandler.start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    public void zaustaviServer(){
        try {
            for (ClientHandler s : soketi) {
                s.endThread();
//                s.zatvori();
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
