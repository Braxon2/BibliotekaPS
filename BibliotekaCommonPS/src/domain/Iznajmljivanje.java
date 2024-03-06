/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.sql.*;

/**
 *
 * @author Dusan
 */
public class Iznajmljivanje implements GenericEntity{
    private long iznajmljivanjeId;
    private Clan clan;
    private List<StavkaIznajmljivanja> stavke;
    private Date datumIznajmljivanja;
    private Date datumVracanja;
    private Bibliotekar bibliotekar;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(long iznajmljivanjeId, Clan clan, List<StavkaIznajmljivanja> stavke, Date datumIznajmljivanja, Date datumVracanja, Bibliotekar bibliotekar) {
        this.iznajmljivanjeId = iznajmljivanjeId;
        this.clan = clan;
        this.stavke = stavke;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.datumVracanja = datumVracanja;
        this.bibliotekar = bibliotekar;
    }

    

    public long getIznajmljivanjeId() {
        return iznajmljivanjeId;
    }

    public void setIznajmljivanjeId(long iznajmljivanjeId) {
        this.iznajmljivanjeId = iznajmljivanjeId;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Date getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(Date datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        if (this.iznajmljivanjeId != other.iznajmljivanjeId) {
            return false;
        }
        if (!Objects.equals(this.clan, other.clan)) {
            return false;
        }
        if (!Objects.equals(this.stavke, other.stavke)) {
            return false;
        }
        if (!Objects.equals(this.datumIznajmljivanja, other.datumIznajmljivanja)) {
            return false;
        }
        if (!Objects.equals(this.datumVracanja, other.datumVracanja)) {
            return false;
        }
        return Objects.equals(this.bibliotekar, other.bibliotekar);
    }

   

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "clan_id,datumiznajmljivanje,datumvracanja,bibliotekar_id";
    }

    @Override
    public ArrayList<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        
        ArrayList<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            long id = rs.getLong("i.iznajmljivanje_id");
            long clanId = rs.getLong("i.clan_id");
            Date datumIznajm = rs.getDate("i.datumiznajmljivanje");
            Date datumVracanja = rs.getDate("i.datumvracanja");
            long bibliotekarId = rs.getLong("i.bibliotekar_id");
            
            String ime = rs.getString("c.ime");
            String prezime = rs.getString("c.prezime");
            String jmbg = rs.getString("c.jmbg");
            
            Clan clan = new Clan(clanId, ime, prezime, jmbg);
            
            String imeB = rs.getString("b.ime");
            String prezimeB = rs.getString("b.prezime");
            
            Bibliotekar bibliotekar = new Bibliotekar(bibliotekarId, imeB, prezimeB, null);
            
            Iznajmljivanje iznajmljivanje = new Iznajmljivanje(id, clan, null, datumIznajm, datumVracanja, bibliotekar);
            lista.add(iznajmljivanje);
            
        }
        
        return lista;
        
    }

    @Override
    public String vratiVrednostiZAUbacivanje() {
        return ""+clan.getClanId()+",'" + new java.sql.Date(datumIznajmljivanja.getTime())+"','"+
               new java.sql.Date(datumVracanja.getTime())+"',"+bibliotekar.getBibliotekarId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.iznajmljivanje_id="+iznajmljivanjeId;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "clan_id="+clan.getClanId()+",datumiznajmljivanje='" + new java.sql.Date(datumIznajmljivanja.getTime())+"',"+
                 "datumvracanja='"+new java.sql.Date(datumVracanja.getTime())+"',bibliotekar_id="+bibliotekar.getBibliotekarId();
    }

    @Override
    public String vratiUslovPovezivanja() {
        return " i JOIN clan c ON i.clan_id=c.clan_id JOIN bibliotekar b ON i.bibliotekar_id=b.bibliotekar_id";
    }
    
    
    
    
    
}
