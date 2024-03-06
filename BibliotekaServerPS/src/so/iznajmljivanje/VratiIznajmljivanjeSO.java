/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.iznajmljivanje;

import domain.Iznajmljivanje;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class VratiIznajmljivanjeSO extends AbstractSO{

    private List<Iznajmljivanje> lista;

    public List<Iznajmljivanje> getLista() {
        return lista;
    }
    
    @Override
    public void validate(Object obj) throws Exception {

    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        lista = broker.getAll(new Iznajmljivanje(), kljuc);
    }
    
    
    
}
