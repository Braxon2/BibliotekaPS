/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dusan
 */
public class Bibliotekar implements GenericEntity{
    
    private long bibliotekarId;
    private String ime;
    private String prezime;
    private List<Knjiga> dodateKnjige;

    public Bibliotekar(long bibliotekarId, String ime, String prezime, List<Knjiga> dodateKnjige) {
        this.bibliotekarId = bibliotekarId;
        this.ime = ime;
        this.prezime = prezime;
        this.dodateKnjige = dodateKnjige;
    }

    public Bibliotekar() {
    }

    public long getBibliotekarId() {
        return bibliotekarId;
    }

    public void setBibliotekarId(long bibliotekarId) {
        this.bibliotekarId = bibliotekarId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public List<Knjiga> getDodateKnjige() {
        return dodateKnjige;
    }

    public void setDodateKnjige(List<Knjiga> dodateKnjige) {
        this.dodateKnjige = dodateKnjige;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bibliotekar other = (Bibliotekar) obj;
        return this.bibliotekarId == other.bibliotekarId;
    }

    @Override
    public String vratiNazivTabele() {
        return "bibliotekar";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
         return "";
    }

    @Override
    public ArrayList<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
              long bibliotekarId = rs.getLong("bibliotekar_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Bibliotekar bibliotekar = new Bibliotekar(bibliotekarId, ime, prezime, null);
            lista.add(bibliotekar);
        }
        return lista;
    }

    @Override
    public String vratiVrednostiZAUbacivanje() {
        return "";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "";
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "";
    }

    @Override
    public String vratiUslovPovezivanja() {
        return "";
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    
}
