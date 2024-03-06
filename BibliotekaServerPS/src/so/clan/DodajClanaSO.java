/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clan;

import domain.Clan;
import java.util.List;
import javax.swing.JOptionPane;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class DodajClanaSO extends AbstractSO {

    @Override
    public void validate(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Clan)) {
            throw new Exception("Nije poslat clan");
        }
        Clan clan = (Clan) obj;
        if (clan.getIme() == null || clan.getIme() .isEmpty()) {
            throw new Exception("Ime ne sme biti null ili prazan string!");
        }
        if (clan.getPrezime()== null || clan.getPrezime().isEmpty()) {
             throw new Exception("Prezime ne sme biti null ili prazan string!");
        }
        if (clan.getJmbg() == null || clan.getJmbg().isEmpty()) {
            throw new Exception("JMBG ne sme biti null ili prazan string!");
        }
        if (clan.getJmbg().length() != 13) {
            throw new Exception("JMBG mora imati 13 karaktera!");
        }
        if (!(clan.getJmbg().matches("[0-9]*"))) {
            throw new Exception("JMBG mora imati samo cifre!");
        }
        
        List<Clan> lista = broker.getAll(new Clan());
        for (Clan c : lista) {
            if(c.equals(clan)){
                throw new Exception("Vec postoji taj clan u bazi!!!");
            }
        }
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        broker.add((Clan)param);
    }

}
