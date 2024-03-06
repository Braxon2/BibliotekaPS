/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import domain.Clan;
import domain.Knjiga;
import java.util.List;
import javax.swing.JOptionPane;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class IzmeniKnjiguSO extends AbstractSO {

    @Override
    public void validate(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Knjiga)) {
            throw new Exception("Nije poslat clan");
        }
        Knjiga knjiga = (Knjiga) obj;
        if (knjiga.getNaslov() == null || knjiga.getNaslov().isEmpty()) {
            throw new Exception("Naslov ne sme biti null ili prazan String!");
        }
        if (knjiga.getPisac() == null || knjiga.getPisac().isEmpty()) {
            throw new Exception("Pisac ne sme biti null ili prazan String!");
        }
        if (knjiga.getBrojStrana() <= 0) {
            throw new Exception("Broj strana ne sme biti manje ili jednak nuli!");
        }
        if (knjiga.getIzdavac() == null || knjiga.getIzdavac().isEmpty()) {
            throw new Exception("Izdavac ne sme biti null ili prazan String!");
        }
        if (knjiga.getIsbn() == null || knjiga.getIsbn().isEmpty()) {
            throw new Exception("ISBN ne sme biti null ili prazan String!");
        }
        
        
        List<Knjiga> lista = broker.getAll(new Knjiga());
        
        if(!lista.contains(knjiga)){
            throw new Exception("Knjiga ne posotji!");
        }
        

    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        broker.edit((Knjiga) param);
    }

}
