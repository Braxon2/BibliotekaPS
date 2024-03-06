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
public class PretraziKnjiguSO extends AbstractSO{
    private List<Knjiga> knjiga;

    public List<Knjiga> getKnjiga() {
        return knjiga;
    }
    
    

    @Override
    public void validate(Object obj) throws Exception {

    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        knjiga = broker.getAll(new Knjiga(), kljuc);
    }
    
}
