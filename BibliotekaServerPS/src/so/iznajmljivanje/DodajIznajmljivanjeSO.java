/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.iznajmljivanje;

import domain.Iznajmljivanje;
import domain.Primerak;
import domain.StavkaIznajmljivanja;
import java.util.List;
import so.AbstractSO;
import java.sql.*;
import java.util.ArrayList;
import repository.db.DbConnectFactory;

/**
 *
 * @author Dusan
 */
public class DodajIznajmljivanjeSO extends AbstractSO {

    @Override
    public void validate(Object obj) throws Exception {
        if (obj == null || !(obj instanceof Iznajmljivanje)) {
            throw new Exception("Nije poslato iznajmljivanje!");
        }

        Iznajmljivanje iz = (Iznajmljivanje) obj;

        if (iz.getClan() == null) {
            throw new Exception("Clan ne moze biti prazan tj null!");
        }
        if (iz.getBibliotekar() == null) {
            throw new Exception("Bibliotekar ne moze biti prazan tj null!");
        }

        if (iz.getDatumVracanja() == null) {
            throw new Exception("Datum vracanja ne moze biti prazan tj null!");
        }

        if (iz.getDatumIznajmljivanja() == null) {
            throw new Exception("Datum iznajmljivanja ne moze biti prazan tj null!");
        }

        if (iz.getDatumIznajmljivanja().after(iz.getDatumVracanja())) {
            throw new Exception("Datum iznajmljivanja ne moze biti posle datum vracanja!");
        }

        if (iz.getStavke().isEmpty()) {
            throw new Exception("Stavka ne smeju biti prazne!");
        }

        
        /*ArrayList<Primerak> lista = (ArrayList<Primerak>) broker.getAll(new Primerak());
        for (StavkaIznajmljivanja stavka : iz.getStavke()) {
            for (Primerak primerak : lista) {
                if (stavka.getPrimerak().equals(primerak) && primerak.isIznajmljen()) {
                    throw new Exception("Primerak je vec iznajmljen!");
                }
            }
        }*/

    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) param;
        Connection connection = DbConnectFactory.getInstance().getConnection();
        try{
        String upit = "INSERT INTO " + iznajmljivanje.vratiNazivTabele()
                + " (" + iznajmljivanje.vratiKoloneZaUbacivanje()
                + ") VALUES (" + iznajmljivanje.vratiVrednostiZAUbacivanje() + ")";
            System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            long id = rs.getLong(1);
            iznajmljivanje.setIznajmljivanjeId(id);
        }
        ps.close();
        rs.close();
        
        for (StavkaIznajmljivanja stavka : iznajmljivanje.getStavke()) {
            stavka.setIznajmljivanje(iznajmljivanje);
//            stavka.getPrimerak().setIznajmljen(true);
            String upitStavke = "INSERT INTO " + stavka.vratiNazivTabele()
                + " (" + stavka.vratiKoloneZaUbacivanje()
                + ") VALUES (" + stavka.vratiVrednostiZAUbacivanje() + ")";
            System.out.println(upitStavke);
            PreparedStatement psStavka = connection.prepareStatement(upitStavke);
            psStavka.executeUpdate();
            
        }
        
            ArrayList<StavkaIznajmljivanja> st1 = (ArrayList<StavkaIznajmljivanja>) iznajmljivanje.getStavke();
            ArrayList<Primerak> pr1 = new ArrayList<>();
            for (StavkaIznajmljivanja s : st1) {
                pr1.add(s.getPrimerak());
            }
            System.out.println(pr1);
            for (Primerak pr : pr1) {
                String upitPrimerak = "UPDATE " +pr.vratiNazivTabele() +
                            " SET " +pr.vratiVrednostiZaIzmenu()
                            + " WHERE " +pr.vratiPrimarniKljuc();
                    System.out.println(upitPrimerak);
                    PreparedStatement psPrimerak = connection.prepareStatement(upitPrimerak);
                    psPrimerak.executeUpdate();
            }
        
            /*for (StavkaIznajmljivanja st : iznajmljivanje.getStavke()) {
                for (Primerak primerak : st.getPrimerak()) {
                    String upitPrimerak = "UPDATE " +primerak.vratiNazivTabele() +
                            " (" +primerak.vratiKoloneZaUbacivanje() +") "
                            + "VALUES (" +primerak.vratiVrednostiZAUbacivanje() + ")";
                    System.out.println(upitPrimerak);
                    PreparedStatement psPrimerak = connection.prepareStatement(upitPrimerak);
                    psPrimerak.executeUpdate();
                }
            }*/
        
        connection.commit();
        }catch(Exception ex){
            connection.rollback();
            throw new Exception("Ne moze da se sacuva");
        }
        
    }

}
