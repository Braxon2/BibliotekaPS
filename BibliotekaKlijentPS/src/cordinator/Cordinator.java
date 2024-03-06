/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import controllers.DodajKnjiguController;
import controllers.DodajPrimerakController;
import controllers.DodajclanaController;
import controllers.IzmenaClanaController;
import controllers.IzmenaKnjigeController;
import controllers.IznajmiKnjiguController;
import controllers.LogInController;
import controllers.MainFormController;
import controllers.PrikazClanaController;
import controllers.PrikazIznajmljivanjaController;
import controllers.PrikazKnjigeController;
import domain.Clan;
import domain.Knjiga;
import domain.KorisnickiProfil;
import view.FrmDodajClana;
import view.FrmDodajKnjigu;
import view.FrmDodajPrimerak;
import view.FrmIzmeniClana;
import view.FrmIzmeniKnjigu;
import view.FrmIznajmiKnjigu;
import view.FrmLogIn;
import view.FrmMain;
import view.FrmPrikazClana;
import view.FrmPrikazIznajmljivanja;
import view.FrmPrikazKnjiga;

/**
 *
 * @author Dusan
 */
public class Cordinator {
    
    private static Cordinator instance;
    
    private KorisnickiProfil kp;
    private LogInController logInController;
    
    private DodajclanaController dodajclanaController;
    private MainFormController mainFormController;
    private PrikazClanaController prikazClanaController;
    private IzmenaClanaController izmenaClanaController;
    
    private DodajKnjiguController dodajKnjiguController;
    private PrikazKnjigeController prikazKnjigeController;
    private IzmenaKnjigeController izmenaKnjigeController;
    
    private PrikazIznajmljivanjaController prikazIznajmljivanjaController;
    
    private IznajmiKnjiguController iznajmiKnjiguController;
    
    private DodajPrimerakController dodajPrimerakController;

    private Cordinator() {
    }

    public static Cordinator getInstance() {
        if(instance == null){
            instance = new Cordinator();
        }
        return instance;
    }

    public void start() {
        logInController = new LogInController(new FrmLogIn());
        logInController.otvoriFormu();
    }

    public void setKp(KorisnickiProfil kp) {
        this.kp = kp;
    }

    public KorisnickiProfil getKp() {
        return kp;
    }

    public void otvoriFormuDodajClana(FrmMain aThis) {
        dodajclanaController = new DodajclanaController(new FrmDodajClana(aThis,true));
        dodajclanaController.otvoriFormu();
    }

    public void otvoriMainFormu() {
        mainFormController = new MainFormController(new FrmMain());
        mainFormController.otvoriFormu();
    }

    public void otvoriFormuPrikazClana() {
        prikazClanaController = new PrikazClanaController(new FrmPrikazClana(mainFormController.getForma(),true));
        prikazClanaController.otvoriFormu();
    }

    public void otvoriFormuIzmenaClana(Clan clanZaIzmenu) {
        izmenaClanaController = new IzmenaClanaController(new FrmIzmeniClana(mainFormController.getForma(), true));
        izmenaClanaController.otvoriFormu(clanZaIzmenu);
    }
    
   public void otvoriFormuDodajKnjigu(FrmMain aThis){
       dodajKnjiguController = new DodajKnjiguController(new FrmDodajKnjigu(aThis, true));
       dodajKnjiguController.otvoriFormu();
   }
    
   public void otvooriFormuPrikazKnjige(){
       prikazKnjigeController = new PrikazKnjigeController(new FrmPrikazKnjiga(mainFormController.getForma(), true));
       prikazKnjigeController.otvoriFormu();
   }
   
   public void otovriFormuZaIzmenuKnjige(Knjiga knjigaZaIzmenu){
       izmenaKnjigeController = new IzmenaKnjigeController(new FrmIzmeniKnjigu(mainFormController.getForma(), true));
       izmenaKnjigeController.otvoriFormu(knjigaZaIzmenu);
   }

    public void otvoriFormuPrikazIznajmljivanja() {
        prikazIznajmljivanjaController = new PrikazIznajmljivanjaController(new FrmPrikazIznajmljivanja(mainFormController.getForma(), true));
        prikazIznajmljivanjaController.otvoriFormu();
    }

    public void otvoriFormuIznajmi() {
        iznajmiKnjiguController = new IznajmiKnjiguController(new FrmIznajmiKnjigu(mainFormController.getForma(), true));
        iznajmiKnjiguController.otvoriFormu();
    }

    public void otvoriFormuPrimerak(Knjiga knjiga) {
        dodajPrimerakController = new DodajPrimerakController(new FrmDodajPrimerak(mainFormController.getForma(), true));
        dodajPrimerakController.setujKnjigu(knjiga);
        dodajPrimerakController.otvoriFormu(knjiga);
    }
    
}
