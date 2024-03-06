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
public class Clan implements GenericEntity{
    
    private long clanId;
    private String ime;
    private String prezime;
    private String jmbg; 

    public Clan() {
    }

    public Clan(long clanId, String ime, String prezime, String jmbg) {
        this.clanId = clanId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
    }

    

    public long getClanId() {
        return clanId;
    }

    public void setClanId(long clanId) {
        this.clanId = clanId;
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    

    @Override
    public String vratiNazivTabele() {
        return "clan";
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
        final Clan other = (Clan) obj;
        return Objects.equals(this.jmbg, other.jmbg);
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return  "ime,prezime,jmbg";
    }

    @Override
    public ArrayList<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            long id =rs.getLong("clan_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String jmbgg = rs.getString("jmbg");
            Clan clan = new Clan(id, ime, prezime,jmbgg);
            lista.add(clan);
        }
        return  lista;
    }

    @Override
    public String vratiVrednostiZAUbacivanje() {
        return "'"+ime + "','" +prezime + "','"+jmbg+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "clan.clan_id=" + clanId;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime + "',prezime='" +prezime + "',jmbg='"+jmbg+"'";
    }

    @Override
    public String vratiUslovPovezivanja() {
        return "";
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Override
    public String toString() {
        return ime+ " " + prezime;
    }
    
    
    
}
