/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import cordinator.Cordinator;
import domain.Clan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ClanTableModel;
import view.FrmPrikazClana;

/**
 *
 * @author Dusan
 */
public class PrikazClanaController {

    private final FrmPrikazClana forma;

    public PrikazClanaController(FrmPrikazClana forma) {
        this.forma = forma;
        addActionListeners();
    }

    public void otvoriFormu() {
        popunitabelu();
        forma.setVisible(true);
    }

    private void popunitabelu() {
        ArrayList<Clan> clanovi = Communication.getInstance().vratiClanove();
        ClanTableModel ctm = new ClanTableModel(clanovi);
        forma.getTblClan().setModel(ctm);
    }

    private void addActionListeners() {
        forma.pretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jmbg = forma.getTxtJMBG().getText().trim();
                if (jmbg.isEmpty() || jmbg == null) {
                    JOptionPane.showMessageDialog(forma, "Unesite JMBG!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    popunitabelu();
                    return;
                }
                String uslov = " WHERE jmbg = '" + jmbg + "'";
                ArrayList<Clan> clan = Communication.getInstance().vratiClana(uslov);
                popuniTabelu(clan);
            }

            private void popuniTabelu(ArrayList<Clan> clan) {
                ClanTableModel ctm = new ClanTableModel(clan);
                forma.getTblClan().setModel(ctm);
                if (clan.isEmpty()) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da nadje clana po zadatoj vrednosti!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(forma, "Sistem je nasao clana po zadatoj vrednosti!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        forma.obrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblClan().getSelectedRow();
                if (red != -1) {
                    ClanTableModel ctmObrisi = (ClanTableModel) forma.getTblClan().getModel();
                    Clan clanZaBrisanje = ctmObrisi.vratiClana(red);
                    boolean obrisano = Communication.getInstance().obrisiClana(clanZaBrisanje);
                    if (obrisano) {
                        ctmObrisi.obrisi(clanZaBrisanje);
                        popunitabelu();
                        JOptionPane.showMessageDialog(forma, "Sistem je obrisao clana!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        
                    } else {
                        JOptionPane.showMessageDialog(forma, "Sistem ne moze da obrise clana!", "GRESKA", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(forma, "Morate da izaberete red u tabeli!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        forma.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblClan().getSelectedRow();
                if(red != -1){
                    ClanTableModel ctmIzmeni = (ClanTableModel) forma.getTblClan().getModel();
                    Clan clanZaIzmenu = ctmIzmeni.vratiClana(red);
                    forma.dispose();
                    Cordinator.getInstance().otvoriFormuIzmenaClana(clanZaIzmenu);
                }
            }
        });
    }
    
    

}
