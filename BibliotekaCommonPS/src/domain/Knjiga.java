/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dusan
 */
public class Knjiga implements GenericEntity{

    private long knjigaId;
    private String naslov;
    private String pisac;
    private int brojStrana;
    private String izdavac;
    private String isbn;
    private Bibliotekar bibliotekar;
    private List<Primerak> primerci;

    public Knjiga(long knjigaId, String naslov, String pisac, int brojStrana, String izdavac, String isbn, Bibliotekar bibliotekar, List<Primerak> primerci) {
        this.knjigaId = knjigaId;
        this.naslov = naslov;
        this.pisac = pisac;
        this.brojStrana = brojStrana;
        this.izdavac = izdavac;
        this.isbn = isbn;
        this.bibliotekar = bibliotekar;
        this.primerci = primerci;
    }

    public Knjiga() {
    }

    public long getKnjigaId() {
        return knjigaId;
    }

    public void setKnjigaId(long knjigaId) {
        this.knjigaId = knjigaId;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getPisac() {
        return pisac;
    }

    public void setPisac(String pisac) {
        this.pisac = pisac;
    }

    public int getBrojStrana() {
        return brojStrana;
    }

    public void setBrojStrana(int brojStrana) {
        this.brojStrana = brojStrana;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setIzdavac(String izdavac) {
        this.izdavac = izdavac;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
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
        final Knjiga other = (Knjiga) obj;
        return Objects.equals(this.isbn, other.isbn);
    }

    

    public List<Primerak> getPrimerci() {
        return primerci;
    }

    public void setPrimerci(List<Primerak> primerci) {
        this.primerci = primerci;
    }

    @Override
    public String vratiNazivTabele() {
        return "knjiga";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naslov,pisac,brojStrana,izdavac,ISBN,bibliotekar_id";
    }

    @Override
    public ArrayList<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            long id = rs.getInt("k.knjiga_id");
            String naslovKnjige = rs.getString("k.naslov");
            String pisacKnjige = rs.getString("k.pisac");
            int brojStranaKnjige = rs.getInt("k.brojStrana");
            String izdavacKnjige = rs.getString("k.izdavac");
            String isbnKnjige = rs.getString("k.ISBN");
            
            long bibliotekarId = rs.getLong("b.bibliotekar_id");
            String ime = rs.getString("b.ime");
            String prezime = rs.getString("b.prezime");
            
            Bibliotekar b = new Bibliotekar(bibliotekarId, ime, prezime, null);
            Knjiga k = new Knjiga(id, naslovKnjige, pisacKnjige, brojStranaKnjige, izdavacKnjige, isbnKnjige, b, null);
            lista.add(k);
        }
        
        return lista;
    }

    @Override
    public String vratiVrednostiZAUbacivanje() {
        return "'" + naslov + "','"+pisac+"',"+brojStrana+",'"+izdavac+"','"+isbn+"',"+bibliotekar.getBibliotekarId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "knjiga.knjiga_id=" + knjigaId;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naslov='" + naslov + "' , pisac='"+pisac + 
                "' , brojStrana= " + brojStrana + ", izdavac='"+izdavac+"' , isbn= '"+isbn+"' , bibliotekar_id = " +bibliotekar.getBibliotekarId();
    }

    @Override
    public String vratiUslovPovezivanja() {
        return " k JOIN bibliotekar b ON k.bibliotekar_id = b.bibliotekar_id";
    }

    
    
    
}
