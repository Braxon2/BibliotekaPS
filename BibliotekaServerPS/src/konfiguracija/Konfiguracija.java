/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dusan
 */
public class Konfiguracija {
    
    private static Konfiguracija konfiguracija;
    private Properties properties;
    
    private Konfiguracija(){
        try {
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\Dusan\\Documents\\NetBeansProjects\\BibliotekaServerPS\\config\\dbconfig.properties"));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static Konfiguracija getKonfiguracija() {
        if(konfiguracija == null){
            konfiguracija = new Konfiguracija();
        }
        return konfiguracija;
    }
    
    public String getProperty(String key){
        return properties.getProperty(key,"n/a");
    }
    
    public void setProperty(String key, String value){
        properties.setProperty(key, value);
    }
    
    public void sacuvaj(){
        try {
            properties.store(new FileOutputStream("C:\\Users\\Dusan\\Documents\\NetBeansProjects\\BibliotekaServerPS\\config\\dbconfig.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
