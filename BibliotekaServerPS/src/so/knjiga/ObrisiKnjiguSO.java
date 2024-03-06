/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import domain.Clan;
import domain.Knjiga;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class ObrisiKnjiguSO extends AbstractSO{

    @Override
    public void validate(Object obj) throws Exception {

        
        Knjiga k = (Knjiga) obj;
        List<Knjiga> lista = broker.getAll(new Knjiga());
        
        if(!lista.contains(k)){
            throw new Exception("Knjiga ne posotji!");
        }
        
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        broker.delete((Knjiga) param);
    }
    
}
