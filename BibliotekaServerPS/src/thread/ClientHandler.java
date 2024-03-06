/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Clan;
import domain.Iznajmljivanje;
import domain.Knjiga;
import domain.KorisnickiProfil;
import domain.Primerak;
import domain.StavkaIznajmljivanja;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dusan
 */
public class ClientHandler extends Thread {

    private final Socket client;

    private final Sender sender;

    private final Receiver receiver;

    private boolean end = false;

    public ClientHandler(Socket client) {
        this.client = client;
        sender = new Sender(client);
        receiver = new Receiver(client);
    }

    @Override
    public void run() {
//        end = false;
        while (!end) {

            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();

                switch (request.getOperation()) {
                    case LOGIN:
                        KorisnickiProfil korisnickiProfil = (KorisnickiProfil) request.getArgument();
                        Object ulogovani = Controller.getInstance().login(korisnickiProfil);
                        response.setResult(ulogovani);
                        break;
                    case KREIRAJ_CLANA:
                        Clan clan = (Clan) request.getArgument();
                        try {
                            Controller.getInstance().dodajClana(clan);
                            response.setResult(clan);
                            response.setException(null);
                        } catch (Exception ex) {
                            response.setResult(null);
                            response.setException(ex);
                        }
                        break;
                    case VRATI_CLANOVE:
                        ArrayList<Clan> clanovi = Controller.getInstance().vratiSveClanove();
                        response.setResult(clanovi);
                        break;
                    case PRETRAZI_CLANA:
                        ArrayList<Clan> clanPojedinacni = Controller.getInstance().vratiClana(request.getArgument());
                        response.setResult(clanPojedinacni);
                        break;
                    case OBRISI_CLANA:
                        Clan clanZaBrisanje = (Clan) request.getArgument();
                        boolean obrisano;
                        try {
                            Controller.getInstance().obrisiClana(clanZaBrisanje);
                            obrisano = true;
                            response.setResult(obrisano);
                            response.setException(null);
                        } catch (Exception ex) {
                            obrisano = false;
                            response.setResult(obrisano);
                            response.setException(ex);
                        }
                        break;
                    case IZMENI_CLANA:
                        Clan clanZaIzmenu = (Clan) request.getArgument();
                        try {
                            Controller.getInstance().izmeniClana(clanZaIzmenu);
                            response.setException(null);
                            response.setResult(clanZaIzmenu);
                        } catch (Exception ex) {
                            response.setException(ex);
                            response.setResult(null);
                        }
                        break;
                    case DODAJ_KNJIGU:
                        Knjiga knjigaZadodavanje = (Knjiga) request.getArgument();
                        try {
                            Controller.getInstance().dodajKnjigu(knjigaZadodavanje);
                            response.setException(null);
                            response.setResult(knjigaZadodavanje);
                        } catch (Exception ex) {
                            response.setException(ex);
                            response.setResult(null);
                        }
                        break;
                    case VRATI_SVE_KNJIGE:
                        ArrayList<Knjiga> knjige = Controller.getInstance().vratiSveKnjige();
                        for (Knjiga k : knjige) {
                            ArrayList<Primerak> primerciLista = Controller.getInstance().vratiPrimerke(k);
                            k.setPrimerci(primerciLista);
                        }
                        response.setResult(knjige);
                        break;
                    case PRETRAZI_KNJIGU:
                        ArrayList<Knjiga> pojedinacnaKnjiga = Controller.getInstance().vratKnjigu(request.getArgument());
                        for (Knjiga knjiga : pojedinacnaKnjiga) {
                            ArrayList<Primerak> primerci = Controller.getInstance().vratiPrimerke(knjiga);
                            knjiga.setPrimerci(primerci);
                        }
                        response.setResult(pojedinacnaKnjiga);
                        break;
                    case OBRISI_KNJIGU:
                        Knjiga knjigaZaBrisanje = (Knjiga) request.getArgument();
                        boolean obrisanaKnjiga;
                        try{
                            List<Primerak> primerciKnjige = knjigaZaBrisanje.getPrimerci();
                            for (Primerak pr : primerciKnjige) {
                                Controller.getInstance().obrisiPrimerak(pr);
                            }
                            Controller.getInstance().obrisiKnjigu(knjigaZaBrisanje);
                            obrisanaKnjiga = true;
                            response.setResult(obrisanaKnjiga);
                            response.setException(null);
                        }catch(Exception ex){
                            obrisanaKnjiga = false;
                            response.setResult(obrisanaKnjiga);
                            response.setException(null);
                        }
                        break;
                    case IZMENI_KNJIGU:
                        Knjiga knjigaZaIzmenu = (Knjiga) request.getArgument();
                        try{
                         Controller.getInstance().izmeniKnjigu(knjigaZaIzmenu);
                        response.setResult(knjigaZaIzmenu);
                        response.setException(null);
                        }catch(Exception ex){
                            response.setException(ex);
                            response.setResult(null);
                        }
                        break;
                    case VRATI_SVA_IZNAJMLJIVANJA:
                        ArrayList<Iznajmljivanje> svaIznajmljivanja = Controller.getInstance().vratiSvaIznajmljivanja();
                        for (Iznajmljivanje iznajm : svaIznajmljivanja) {
                            ArrayList<StavkaIznajmljivanja> stavke = Controller.getInstance().vratiSveStavke(iznajm);
                            iznajm.setStavke(stavke);
                        }
                        response.setResult(svaIznajmljivanja);
                        break;
                    case VRATI_IZNAJMLJIVANJE:
                         ArrayList<Iznajmljivanje>pojedinacnoIznajmljivanje = Controller.getInstance().vratiIznajmljivanje(request.getArgument());
                         for (Iznajmljivanje pi : pojedinacnoIznajmljivanje) {
                            ArrayList<StavkaIznajmljivanja> st = Controller.getInstance().vratiSveStavke(pi);
                            pi.setStavke(st);
                        }
                         response.setResult(pojedinacnoIznajmljivanje);
                        break;
                    case IZNAJMI:
                        Iznajmljivanje iznajmljivanjeZaDodati = (Iznajmljivanje) request.getArgument();
                        try{
                            Controller.getInstance().iznajmiKnjige(iznajmljivanjeZaDodati);
                            response.setResult(iznajmljivanjeZaDodati);
                            response.setException(null);
                        }catch(Exception ex){
                            response.setResult(null);
                            response.setException(ex);
                        }
                        break;
                    case DODAJ_PRIMERAK:
                        Primerak primerakZaDodati = (Primerak) request.getArgument();
                        try{
                            System.out.println("pre");
                            Controller.getInstance().dodajPrimerak(primerakZaDodati);
                            response.setResult(primerakZaDodati);
                            response.setException(null);
                        }catch(Exception ex){
                            response.setResult(null);
                            response.setException(ex);
                        }
                        break;
                    case LOGOUT:
                        KorisnickiProfil odjavljeni = (KorisnickiProfil) request.getArgument();
                        Controller.getInstance().logout(odjavljeni);
                        break;
                    default:
                        throw new AssertionError();
                }

                sender.send(response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    public void endThread() {
        end = true;
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void zatvori() {
        try {
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
