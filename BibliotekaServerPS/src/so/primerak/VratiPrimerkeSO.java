/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.primerak;

import domain.Primerak;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class VratiPrimerkeSO extends AbstractSO{
    
    private List<Primerak> lista;

    public VratiPrimerkeSO() {
        lista = new ArrayList<>();
    }

    public VratiPrimerkeSO(List<Primerak> lista) {
        this.lista = lista;
    }

    public List<Primerak> getLista() {
        return lista;
    }
    
    

    @Override
    public void validate(Object obj) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        lista = broker.getAll(new Primerak(), kljuc);
    }
    
    
    
}
