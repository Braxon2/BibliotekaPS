/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Dusan
 */
public class Primerak implements GenericEntity{
    
    private long primerakId;
    private boolean ostecen;
    private boolean iznajmljen;
    private String barCode;
    private Knjiga knjiga;

    public Primerak(long primerakId, boolean ostecen, boolean iznajmljen, String barCode, Knjiga knjiga) {
        this.primerakId = primerakId;
        this.ostecen = ostecen;
        this.iznajmljen = iznajmljen;
        this.barCode = barCode;
        this.knjiga = knjiga;
    }

    public Primerak() {
    }

    public long getPrimerakId() {
        return primerakId;
    }

    public void setPrimerakId(long primerakId) {
        this.primerakId = primerakId;
    }

    public boolean isOstecen() {
        return ostecen;
    }

    public void setOstecen(boolean ostecen) {
        this.ostecen = ostecen;
    }

    public boolean isIznajmljen() {
        return iznajmljen;
    }

    public void setIznajmljen(boolean iznajmljen) {
        this.iznajmljen = iznajmljen;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Primerak other = (Primerak) obj;
        return this.primerakId == other.primerakId;
    }

    

    @Override
    public String vratiNazivTabele() {
        return "primerak";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ostecen,iznajmljen,barcode,knjiga_id";
    }

    @Override
    public ArrayList<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            long id = rs.getLong("p.primerak_id");
            boolean ostecen = rs.getBoolean("p.ostecen");
            boolean iznajmljen = rs.getBoolean("p.iznajmljen");
            String barcode = rs.getString("p.barcode");
            long knjigaId = rs.getLong("p.knjiga_id");
            
            String naslovKnjige = rs.getString("k.naslov");
            String pisacKnjige = rs.getString("k.pisac");
            int brojStranaKnjige = rs.getInt("k.brojStrana");
            String izdavacKnjige = rs.getString("k.izdavac");
            String isbnKnjige = rs.getString("k.ISBN");
            
            long bibliotekarId = rs.getLong("b.bibliotekar_id");
            String ime = rs.getString("b.ime");
            String prezime = rs.getString("b.prezime");
            
            Bibliotekar bibliotekar = new Bibliotekar(bibliotekarId, ime, prezime, null);
            
            Knjiga knjiga = new Knjiga(knjigaId, naslovKnjige, pisacKnjige, brojStranaKnjige, izdavacKnjige, isbnKnjige, bibliotekar, null);
            Primerak primerak = new Primerak(id, ostecen, iznajmljen, barcode, knjiga);
            lista.add(primerak);
        }
        return lista;
    }

    @Override
    public String vratiVrednostiZAUbacivanje() {
        return ""+ostecen+", " + iznajmljen + ", '"+barCode + "',"+knjiga.getKnjigaId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "primerak.primerak_id="+primerakId;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "iznajmljen= " + iznajmljen;
    }

    @Override
    public String vratiUslovPovezivanja() {
        return " p JOIN knjiga k ON p.knjiga_id=k.knjiga_id JOIN bibliotekar b ON k.bibliotekar_id=b.bibliotekar_id";
    }
    
    
    
}
