/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaiznajm;

import domain.StavkaIznajmljivanja;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class VratiSveStavkeIznajmljivanjaSO extends AbstractSO{
    
    private List<StavkaIznajmljivanja> lista;

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }
    
    

    @Override
    public void validate(Object obj) throws Exception {

    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        lista = broker.getAll(new StavkaIznajmljivanja(), kljuc);
    }
    
}
