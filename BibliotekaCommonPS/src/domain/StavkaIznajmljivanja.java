/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Dusan
 */
public class StavkaIznajmljivanja implements GenericEntity {

    private long rbr;
    private Primerak primerak;
    private Iznajmljivanje iznajmljivanje;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(long rbr, Primerak primerak, Iznajmljivanje iznajmljivanje) {
        this.rbr = rbr;
        this.primerak = primerak;
        this.iznajmljivanje = iznajmljivanje;
    }

    public long getRbr() {
        return rbr;
    }

    public void setRbr(long rbr) {
        this.rbr = rbr;
    }

    public Primerak getPrimerak() {
        return primerak;
    }

    public void setPrimerak(Primerak primerak) {
        this.primerak = primerak;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_iznajmljivanja";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "primerak_id,iznajmljivanje_id";
    }

    @Override
    public ArrayList<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("s.stavka_id");
            long idPrimerak = rs.getLong("s.primerak_id");

            boolean ostecen = rs.getBoolean("p.ostecen");
            boolean iznajmljen = rs.getBoolean("p.iznajmljen");
            String barCode = rs.getString("p.barcode");
            long idKnjige = rs.getLong("p.knjiga_id");

            String naslov = rs.getString("k.naslov");
            String pisac = rs.getString("k.pisac");
            int brStrana = rs.getInt("k.brojStrana");
            String izdavac = rs.getString("k.izdavac");
            String isbn = rs.getString("k.ISBN");

            long idBibliotekar = rs.getLong("b.bibliotekar_id");
            String ime = rs.getString("b.ime");
            String prezime = rs.getString("b.prezime");

            Bibliotekar bibliotekar = new Bibliotekar(idBibliotekar, ime, prezime, null);
            Knjiga knjiga = new Knjiga(idKnjige, naslov, pisac, brStrana, izdavac, isbn, bibliotekar, null);
            Primerak primerak = new Primerak(idPrimerak, ostecen, iznajmljen, barCode, knjiga);
            StavkaIznajmljivanja stavkaIznajmljivanja = new StavkaIznajmljivanja(id, primerak, null);

            lista.add(stavkaIznajmljivanja);

        }

        return lista;
    }

    @Override
    public String vratiVrednostiZAUbacivanje() {
        return "" + primerak.getPrimerakId() + "," + iznajmljivanje.getIznajmljivanjeId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavka_iznajmljivanja.stavka_id =" + rbr;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "primerak_id=" + primerak.getPrimerakId() + "', iznajmljivanje_id" + iznajmljivanje.getIznajmljivanjeId();
    }

    @Override
    public String vratiUslovPovezivanja() {
        return " s JOIN primerak p ON s.primerak_id = p.primerak_id"
                + " JOIN knjiga k ON p.knjiga_id = k.knjiga_id\n"
                + "JOIN bibliotekar b ON b.bibliotekar_id = k.bibliotekar_id";
    }

}
