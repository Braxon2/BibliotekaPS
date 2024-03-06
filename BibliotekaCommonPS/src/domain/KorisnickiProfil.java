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
public class KorisnickiProfil implements GenericEntity {

    private long userId;
    private String username;
    private String password;
    private Bibliotekar bibliotekar;

    public KorisnickiProfil() {
    }

    public KorisnickiProfil(long userId, String username, String password, Bibliotekar bibliotekar) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.bibliotekar = bibliotekar;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }


    

    @Override
    public String vratiNazivTabele() {
        return "user_profile";
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
        final KorisnickiProfil other = (KorisnickiProfil) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "username,password,bibliotekar_id";
    }

    @Override
    public ArrayList<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        ArrayList<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            long korisnickiProfilId = rs.getLong("k.user_id");
            String username = rs.getString("k.username");
            String password = rs.getString("k.password");
            long bibliotekar_id = rs.getLong("b.bibliotekar_id");
            String ime = rs.getString("b.ime");
            String prezime = rs.getString("b.prezime");
            Bibliotekar bibliotekar = new Bibliotekar();
            bibliotekar.setBibliotekarId(bibliotekar_id);
            bibliotekar.setIme(ime);
            bibliotekar.setPrezime(prezime);
            KorisnickiProfil kp = new KorisnickiProfil(korisnickiProfilId, username, password, bibliotekar);
            lista.add(kp);
        }
        return lista;
    }

    @Override
    public String vratiVrednostiZAUbacivanje() {
         return "'"+username+"','"+password+ "'," + bibliotekar.getBibliotekarId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "user_profile.user_id=" + userId;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "username=' "+username+"' , password=' "+password+ "' , bibliotekar_id = " + bibliotekar.getBibliotekarId();
    }

    @Override
    public String vratiUslovPovezivanja() {
        return " k JOIN bibliotekar b ON k.bibliotekar_id=b.bibliotekar_id";
    }

    
    
}
