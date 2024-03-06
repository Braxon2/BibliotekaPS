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
public class PretraziClanaSO extends AbstractSO{
    private List<Clan> clan;

    public List<Clan> getClan() {
        return clan;
    }
    
    

    @Override
    public void validate(Object obj) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        clan = broker.getAll(new Clan(), kljuc);
    }
    
    
    
}
