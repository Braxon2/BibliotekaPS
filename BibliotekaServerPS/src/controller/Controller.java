/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Clan;
import domain.Iznajmljivanje;
import domain.Knjiga;
import domain.KorisnickiProfil;
import domain.Primerak;
import domain.StavkaIznajmljivanja;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.clan.DodajClanaSO;
import so.clan.IzmenaClanSO;
import so.clan.ObrisiClanaSO;
import so.clan.PretraziClanaSO;
import so.clan.VratiClanoveSO;
import so.iznajmljivanje.DodajIznajmljivanjeSO;
import so.iznajmljivanje.VratiIznajmljivanjeSO;
import so.iznajmljivanje.VratiSvaIznajmljivanjaSO;
import so.knjiga.DodajKnjiguSO;
import so.knjiga.IzmeniKnjiguSO;
import so.knjiga.ObrisiKnjiguSO;
import so.knjiga.PretraziKnjiguSO;
import so.knjiga.VratiKnjigeSO;
import so.login.LoginSO;
import so.primerak.DodajPrimerakSO;
import so.primerak.ObrisiPrimerakSO;
import so.primerak.VratiPrimerkeSO;
import so.stavkaiznajm.VratiSveStavkeIznajmljivanjaSO;
import thread.Server;

/**
 *
 * @author Dusan
 */
public class Controller {
    
    private static Controller instance;
    
    private List<KorisnickiProfil> ulogovani;
    
    private Server server;

    private Controller() {
        ulogovani = new ArrayList<>();
    }

    public static Controller getInstance() {
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public void pokreniServer() {
        
        if(server == null || !server.isAlive()){
            server = new Server();
            server.start();
        }
        
    }

    public void stopServer() {
        if(server.getServerSocket() != null){
            server.zaustaviServer();
           /* try {
                server.zaustaviServer();
//                server.getServerSocket().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }*/
        }
    }

    public Object login(KorisnickiProfil korisnickiProfil) throws Exception {
        
        LoginSO logovani = new LoginSO();
        logovani.execute(korisnickiProfil, null);
        if(ulogovani.contains(logovani.getKp())){
            return "Korisnik je vec ulgoovan!";
        }
        ulogovani.add(logovani.getKp());
        return logovani.getKp();
    }

    public void dodajClana(Clan clan) throws Exception {
        DodajClanaSO clanZaDodavanje = new DodajClanaSO();
        clanZaDodavanje.execute(clan, null);
    }

    public ArrayList<Clan> vratiSveClanove() throws Exception {
        VratiClanoveSO vratiClanove = new VratiClanoveSO();
        vratiClanove.execute(null, null);
        return (ArrayList<Clan>) vratiClanove.getClanovi();
    }

    public ArrayList<Clan> vratiClana(Object argument) throws Exception {
        PretraziClanaSO pretraziClana = new PretraziClanaSO();
        String kljuc = "";
        kljuc = (String) argument;
        pretraziClana.execute(null, kljuc);
        return (ArrayList<Clan>) pretraziClana.getClan();
    }

    public void obrisiClana(Clan clanZaBrisanje) throws Exception {
        ObrisiClanaSO obrisiClana = new ObrisiClanaSO();
        obrisiClana.execute(clanZaBrisanje, null);
    }

    public void izmeniClana(Clan clanZaIzmenu) throws Exception {
        IzmenaClanSO izmenaClana = new IzmenaClanSO();
        izmenaClana.execute(clanZaIzmenu, null);
    }

    public void dodajKnjigu(Knjiga knjigaZadodavanje) throws Exception {
        DodajKnjiguSO dodajKnjigu = new DodajKnjiguSO();
        dodajKnjigu.execute(knjigaZadodavanje, null);
    }

    public ArrayList<Knjiga> vratiSveKnjige() throws Exception {
        VratiKnjigeSO vratiKnjige = new VratiKnjigeSO();
        vratiKnjige.execute(null, null);
        return (ArrayList<Knjiga>) vratiKnjige.getKnjige();
    }

    public ArrayList<Knjiga> vratKnjigu(Object argument) throws Exception {
        PretraziKnjiguSO pretraziKnjiguSO = new PretraziKnjiguSO();
        String kljuc = "";
        kljuc = (String) argument;
        pretraziKnjiguSO.execute(null, kljuc);
        return (ArrayList<Knjiga>) pretraziKnjiguSO.getKnjiga();
    }

    public void obrisiKnjigu(Knjiga knjigaZaBrisanje) throws Exception {
        ObrisiKnjiguSO obrisiKnjiguSO = new ObrisiKnjiguSO();
        obrisiKnjiguSO.execute(knjigaZaBrisanje, null);
    }

    public void izmeniKnjigu(Knjiga knjigaZaIzmenu) throws Exception {
        IzmeniKnjiguSO izmeniKnjiguSO = new IzmeniKnjiguSO();
        izmeniKnjiguSO.execute(knjigaZaIzmenu, null);
    }

    public ArrayList<Iznajmljivanje> vratiSvaIznajmljivanja() throws Exception {
        VratiSvaIznajmljivanjaSO vratiSvaIznajmljivanja = new VratiSvaIznajmljivanjaSO();
        vratiSvaIznajmljivanja.execute(null, null);
        return (ArrayList<Iznajmljivanje>) vratiSvaIznajmljivanja.getLista();
    }

    public ArrayList<StavkaIznajmljivanja> vratiSveStavke(Iznajmljivanje stavka) throws Exception {
        String kljuc = " WHERE s.iznajmljivanje_id = " + stavka.getIznajmljivanjeId();
        VratiSveStavkeIznajmljivanjaSO vratiSveStavkeIznajmljivanjaSO = new VratiSveStavkeIznajmljivanjaSO();
        vratiSveStavkeIznajmljivanjaSO.execute(null, kljuc);
        return (ArrayList<StavkaIznajmljivanja>) vratiSveStavkeIznajmljivanjaSO.getLista();
    }

    public ArrayList<Iznajmljivanje> vratiIznajmljivanje(Object argument) throws Exception {
        VratiIznajmljivanjeSO vratiIznajmljivanjeSO = new VratiIznajmljivanjeSO();
        vratiIznajmljivanjeSO.execute(null, (String) argument);
        return (ArrayList<Iznajmljivanje>) vratiIznajmljivanjeSO.getLista();
    }

    public ArrayList<Primerak> vratiPrimerke(Knjiga knjiga) throws Exception {
        VratiPrimerkeSO vratiPrimerkeSO = new VratiPrimerkeSO();
        String uslov = " WHERE p.knjiga_id="+knjiga.getKnjigaId();
        vratiPrimerkeSO.execute(null, uslov);
        return (ArrayList<Primerak>) vratiPrimerkeSO.getLista();
    }

    public void iznajmiKnjige(Iznajmljivanje iznajmljivanjeZaDodati) throws Exception {
        DodajIznajmljivanjeSO dodajIznajmljivanjeSO = new DodajIznajmljivanjeSO();
        dodajIznajmljivanjeSO.execute(iznajmljivanjeZaDodati, null);
    }

    public void logout(KorisnickiProfil odjavljeni) {
        ulogovani.remove(odjavljeni);
    }

  /*  public void dodajPrimerak(Primerak primerakZaDodati) throws Exception {
        
        DodajPrimerakSO dodajPrimerakSO = new DodajPrimerakSO();
        dodajPrimerakSO.execute(primerakZaDodati, null);
        
        
    }*/

    public void dodajPrimerak(Primerak primerakZaDodati) throws Exception {
        DodajPrimerakSO dodajPrimerakSO = new DodajPrimerakSO();
        dodajPrimerakSO.execute(primerakZaDodati, null);
    }
    
    public void obrisiPrimerak(Primerak primerazZaObrisati) throws Exception{
        ObrisiPrimerakSO obrisiPrimerakSO = new ObrisiPrimerakSO();
        obrisiPrimerakSO.execute(primerazZaObrisati, null);
    }
    
    
}
