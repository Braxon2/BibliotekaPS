/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import cordinator.Cordinator;
import domain.Bibliotekar;
import domain.Clan;
import domain.Iznajmljivanje;
import domain.Knjiga;
import domain.Primerak;
import domain.StavkaIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.PrimerakTableModel;
import model.StavkaIznajmljivanjaTableModel;
import view.FrmIznajmiKnjigu;

/**
 *
 * @author Dusan
 */
public class IznajmiKnjiguController {

    private final FrmIznajmiKnjigu forma;

    public IznajmiKnjiguController(FrmIznajmiKnjigu forma) {
        this.forma = forma;
        addActionListeners();
    }

    public void otvoriFormu() {
        ArrayList<Clan> clanovi = Communication.getInstance().vratiClanove();
        forma.getjComboBox1().removeAllItems();
        for (Clan clan : clanovi) {
            forma.getjComboBox1().addItem(clan);
        }
        forma.getTblKnjige().setModel(new PrimerakTableModel());
        forma.getTblPrimerci().setModel(new StavkaIznajmljivanjaTableModel());
        forma.setVisible(true);
    }

    private void addActionListeners() {

        forma.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naslov = forma.getTxtNaslov().getText().trim();

                if (naslov.isEmpty()) {
                    JOptionPane.showMessageDialog(forma, "Naslov ne sme biti prazan string!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                

                String uslov = " WHERE k.naslov='" + naslov + "'";
                ArrayList<Knjiga> knjiga = Communication.getInstance().vratiKnjigu(uslov);
                if(!knjiga.isEmpty()){
                JOptionPane.showMessageDialog(forma, "Sistem je nasao knjigu po zadatoj vrednosti!", "GRESKA", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da nadje knjigu po zadatoj vrednosti!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
                ArrayList<Primerak> primerci = new ArrayList<>();
                for (Knjiga k : knjiga) {
                    for (Primerak primerak : k.getPrimerci()) {
                        if (!primerak.isIznajmljen()) {
                            primerci.add(primerak);
                        }
                    }
                }

                forma.getTblKnjige().setModel(new PrimerakTableModel(primerci));

            }
        });

        forma.iznajmiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblKnjige().getSelectedRow();
                if (red != -1) {
                    PrimerakTableModel ptm = (PrimerakTableModel) forma.getTblKnjige().getModel();
                    Primerak p = ptm.vratiPrimerak(red);
                    StavkaIznajmljivanja stavkaIznajmljivanja = new StavkaIznajmljivanja(-1, p, null);
                    StavkaIznajmljivanjaTableModel stm = (StavkaIznajmljivanjaTableModel) forma.getTblPrimerci().getModel();
                    ArrayList<StavkaIznajmljivanja> stavke = stm.getLista();
                    stm.dodajStavku(stavkaIznajmljivanja);

                    ptm.obrisi(p);

                }
            }
        });

        forma.obrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblPrimerci().getSelectedRow();
                if (red != -1) {
                    StavkaIznajmljivanjaTableModel stm = (StavkaIznajmljivanjaTableModel) forma.getTblPrimerci().getModel();
                    StavkaIznajmljivanja stavka = stm.vratiStavku(red);
                    stm.obrisiRed(red);
                    PrimerakTableModel ptm = (PrimerakTableModel) forma.getTblKnjige().getModel();
                    ptm.dodajPrimerak(stavka.getPrimerak());

                }
            }
        });

        forma.iznajmiKnjige(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clan clan = (Clan) forma.getjComboBox1().getSelectedItem();
                String datumOd = forma.getDatumOd().getText().trim();
                String datumDo = forma.getDatumDo().getText().trim();

                if (datumOd.isEmpty()) {
                    JOptionPane.showMessageDialog(forma, "Datum ne sme biti prazan");
                    return;
                }
                if (datumDo.isEmpty()) {
                    JOptionPane.showMessageDialog(forma, "Datum ne sme biti prazan");
                    return;
                }

                //provera da datum od bude manji od do
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date datumOdIznajmljivanja = null;
                Date datumDoIznajmljivanja = null;
                try {
                    datumOdIznajmljivanja = sdf.parse(datumOd);
                    datumDoIznajmljivanja = sdf.parse(datumDo);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(forma, "Datum/i moraju biti u formatu yyyy-MM-dd");
                    return;
                }

                if (datumOdIznajmljivanja.after(datumDoIznajmljivanja)) {
                    JOptionPane.showMessageDialog(forma, "Datum od mora biti pre datuma do.");
                    return;
                }

                StavkaIznajmljivanjaTableModel stm = (StavkaIznajmljivanjaTableModel) forma.getTblPrimerci().getModel();
                ArrayList<StavkaIznajmljivanja> stavke = stm.getLista();

                for (StavkaIznajmljivanja s : stavke) {
                    if (datumOdIznajmljivanja.getTime() < new Date().getTime()
                            && datumDoIznajmljivanja.getTime() > new Date().getTime()) {
                        System.out.println("True");
                        s.getPrimerak().setIznajmljen(true);
                    }else{
                        s.getPrimerak().setIznajmljen(false);
                        System.out.println("False");
                    }
                }

                for (StavkaIznajmljivanja st1 : stavke) {
                    System.out.println(st1.getPrimerak().isIznajmljen());
                }
                
                Bibliotekar bibliotekar = Cordinator.getInstance().getKp().getBibliotekar();

                Iznajmljivanje iznajmljivanje = new Iznajmljivanje(-1, clan, stavke, datumOdIznajmljivanja, datumDoIznajmljivanja, bibliotekar);

                Iznajmljivanje iz = Communication.getInstance().dodajIznajmljivanje(iznajmljivanje);

                if (iz != null) {
                    JOptionPane.showMessageDialog(forma, "Odabrene knjige su iznajmljene!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    forma.dispose();
                } else {
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da iznajmi odabrane knjige!", "Neuspeh", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

    }

}
