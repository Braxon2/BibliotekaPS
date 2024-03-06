/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import cordinator.Cordinator;
import domain.Clan;
import domain.Iznajmljivanje;
import domain.Knjiga;
import domain.KorisnickiProfil;
import domain.Primerak;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dusan
 */
public class Communication {

    private Socket s;
    private static Communication instance;
    private Sender sender;
    private Receiver receiver;

    private Communication() {
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public void connect() {
        try {
            s = new Socket("localhost", 7777);
            sender = new Sender(s);
            receiver = new Receiver(s);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object logIn(KorisnickiProfil korisnickiProfil) {

        Request request = new Request(korisnickiProfil, Operation.LOGIN);
        sender.send(request);
        Response response = (Response) receiver.receive();
       Object kp1 =  response.getResult();
        return kp1;

    }

    public Clan dodajClana(Clan clanZaDodavanje) {
        Request request = new Request(clanZaDodavanje, Operation.KREIRAJ_CLANA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            System.out.println(response.getException() + "");
        } else {
            System.out.println("Kreiran!");
        }
        return (Clan) response.getResult();

    }

    public ArrayList<Clan> vratiClanove() {
        ArrayList<Clan> clanovi = new ArrayList<>();
        Request request = new Request(null, Operation.VRATI_CLANOVE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        clanovi = (ArrayList<Clan>) response.getResult();
        return clanovi;
    }

    public ArrayList<Clan> vratiClana(String uslov) {
        ArrayList<Clan> clan = new ArrayList<>();
        Request request = new Request(uslov, Operation.PRETRAZI_CLANA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        clan = (ArrayList<Clan>) response.getResult();
        return clan;
    }

    public boolean obrisiClana(Clan clanZaBrisanje) {
        Request request = new Request(clanZaBrisanje, Operation.OBRISI_CLANA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        boolean obrisano = (boolean) response.getResult();
        if (response.getException() != null) {
            System.out.println(response.getException() + "");
        } else {
            System.out.println("Obrisan!");
        }
        return obrisano;
    }

    public Clan izmeniClana(Clan clanZaIzmenu) {
        Request request = new Request(clanZaIzmenu, Operation.IZMENI_CLANA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            System.out.println(response.getException() + "");
        } else {
            System.out.println("Izmenjen!");
        }
        return (Clan) response.getResult();
    }

    public Knjiga dodajKnjigu(Knjiga knjigaZaDodavanje) {
        Request request = new Request(knjigaZaDodavanje, Operation.DODAJ_KNJIGU);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            System.out.println(response.getException() + "");
        } else {
            System.out.println("Dodato!");
        }
        return (Knjiga) response.getResult();
    }

    public ArrayList<Knjiga> vratiSveKnjige() {
        ArrayList<Knjiga> knjige = new ArrayList<>();
        Request request = new Request(null, Operation.VRATI_SVE_KNJIGE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        knjige = (ArrayList<Knjiga>) response.getResult();
        return knjige;
    }

    public ArrayList<Knjiga> vratiKnjigu(String uslov) {
        ArrayList<Knjiga> knjiga = new ArrayList<>();
        Request request = new Request(uslov, Operation.PRETRAZI_KNJIGU);
        sender.send(request);
        Response response = (Response) receiver.receive();
        knjiga = (ArrayList<Knjiga>) response.getResult();
        return knjiga;
    }

    public boolean obrisiKnjigu(Knjiga knjigaZaBrisanje) {
        Request request = new Request(knjigaZaBrisanje, Operation.OBRISI_KNJIGU);
        sender.send(request);
        Response response = (Response) receiver.receive();
        boolean obrisano = (boolean) response.getResult();
        if (response.getException() != null) {
            System.out.println(response.getException() + "");
        } else {
            System.out.println("Obrisan!");
        }

        return obrisano;
    }

    public Knjiga izmeniKnjigu(Knjiga knjigaZaIzmenu) {
        Request request = new Request(knjigaZaIzmenu, Operation.IZMENI_KNJIGU);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            System.out.println(response.getException() + "");
        } else {
            System.out.println("Izmenjen!");
        }
        return (Knjiga) response.getResult();
    }

    public ArrayList<Iznajmljivanje> vratiSvaIznajmljivanja() {
        
        Request request = new Request(null, Operation.VRATI_SVA_IZNAJMLJIVANJA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (ArrayList<Iznajmljivanje>) response.getResult();
    }

    public ArrayList<Iznajmljivanje> vratiIznajmljivanje(String uslov) {
        Request request = new Request(uslov, Operation.VRATI_IZNAJMLJIVANJE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (ArrayList<Iznajmljivanje>) response.getResult();
    }

    public Iznajmljivanje dodajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        Request request = new Request(iznajmljivanje, Operation.IZNAJMI);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (Iznajmljivanje) response.getResult();
    }

    public void logout() {
        Request request = new Request(Cordinator.getInstance().getKp(), Operation.LOGOUT);
        sender.send(request);
        Response response = (Response) receiver.receive();
    }

    public Primerak dodajPrimerak(Primerak primerak) {
        Request request = new Request(primerak, Operation.DODAJ_PRIMERAK);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()!=null){
            System.out.println(response.getException());
        }else{
            System.out.println("Dodat!");
        }
        
        return (Primerak) response.getResult();
    }

    

}
