/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import domain.Knjiga;
import java.util.List;
import javax.swing.JOptionPane;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class DodajKnjiguSO extends AbstractSO {

    @Override
    public void validate(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Knjiga)) {
            throw new Exception("Nije poslat clan");
        }
        Knjiga k = (Knjiga) obj;

        if (k.getNaslov() == null || k.getNaslov().isEmpty()) {
            throw new Exception("Naslov ne sme biti null ili prazan string!");
        }
        if (k.getPisac() == null || k.getPisac().isEmpty()) {
            throw new Exception("Pisac ne sme biti null ili prazan string!");
        }
        if (k.getBrojStrana() <= 0) {
            throw new Exception("Broj strana ne moze biti manje ili jednak nuli!");
        }
        if (k.getIzdavac() == null || k.getIzdavac().isEmpty()) {
            throw new Exception("Izdavac ne sme biti null ili prazan string!");
        }
        if (k.getIsbn() == null || k.getIsbn().isEmpty()) {
            throw new Exception("ISBN ne sme biti null ili prazan string!");
        }
        
        List<Knjiga> lista = broker.getAll(new Knjiga());
        for (Knjiga knjiga : lista) {
            if(knjiga.equals(k)){
            throw new Exception("Knjiga vec postoji u bazi");
        }
        }
                
        
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        broker.add((Knjiga) param);
    }

}
