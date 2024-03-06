/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.primerak;

import domain.Primerak;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class DodajPrimerakSO extends AbstractSO{

    @Override
    public void validate(Object obj) throws Exception {
        
        Primerak primerak = (Primerak) obj;
        
        if(primerak.getBarCode().isEmpty()){
            throw new Exception("Barkod ne sme biti prazan!!!");
        }
        
        List<Primerak> lista = broker.getAll(new Primerak());
      for (Primerak p : lista) {
            if(p.getBarCode().equals(primerak.getBarCode())){
                throw new Exception("Vec posotji primerak sa tim barkodom");
            }
        }
        
        
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        broker.add((Primerak) param);
    }
    
}
