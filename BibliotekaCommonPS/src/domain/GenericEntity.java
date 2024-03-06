/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Dusan
 */
public interface GenericEntity extends Serializable{
    
    public String vratiNazivTabele();
    
    public String vratiKoloneZaUbacivanje();
    
    public ArrayList<GenericEntity> vratiListu(ResultSet rs) throws Exception;
    
    public String vratiVrednostiZAUbacivanje();
    
    public String vratiPrimarniKljuc();
    
    public String vratiVrednostiZaIzmenu();
    
    public String vratiUslovPovezivanja();
    
}
