/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clan;

import domain.Clan;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class ObrisiClanaSO extends AbstractSO {

    @Override
    public void validate(Object obj) throws Exception {
        
        Clan c = (Clan) obj;
        
        List<Clan> lista = broker.getAll(new Clan());
        
        if(!lista.contains(c)){
            throw new Exception("Clan ne posotji!");
        }
        
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        broker.delete((Clan) param);
    }
    
}
