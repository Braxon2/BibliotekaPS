/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import domain.Knjiga;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class VratiKnjigeSO extends AbstractSO{
    
    private List<Knjiga> knjige;

    @Override
    public void validate(Object obj) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        knjige = broker.getAll(new Knjiga());
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
    
    
}
