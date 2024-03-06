/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.primerak;

import domain.Iznajmljivanje;
import domain.Primerak;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class ObrisiPrimerakSO extends AbstractSO{

    @Override
    public void validate(Object obj) throws Exception {
        
        Primerak p = (Primerak) obj;
        if(p.isIznajmljen()){
            throw new Exception("Trenutno iznajmljeni primerak se ne moze izbrisati");
        }
        
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        broker.delete((Primerak) param);
    }
    
}
