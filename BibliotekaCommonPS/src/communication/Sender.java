/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author Dusan
 */
public class Sender implements Serializable{
      private final Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public void send(Object obj) {
        try{
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(obj);
        out.flush();
        } catch (IOException ex) {
             ex.printStackTrace();
        }
        
    }
}
