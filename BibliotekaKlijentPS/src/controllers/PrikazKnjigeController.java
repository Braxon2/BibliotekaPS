/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import cordinator.Cordinator;
import domain.Knjiga;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.KnjigaTableModel;
import view.FrmPrikazKnjiga;

/**
 *
 * @author Dusan
 */
public class PrikazKnjigeController {
    
    private final FrmPrikazKnjiga forma;

    public PrikazKnjigeController(FrmPrikazKnjiga forma) {
        this.forma = forma;
        addActionListeners();
    }
    
    public void otvoriFormu(){
        popuniTabelu();
        forma.setVisible(true);
    }

    private void addActionListeners() {
        forma.pretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naslov = forma.getTxtNaslov().getText().trim();
                if(naslov.isEmpty() || naslov == null){
                    JOptionPane.showMessageDialog(forma, "Unesite Naslov knjige!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    popuniTabelu();
                    return;
                }
                
                String uslov = " WHERE naslov = '" + naslov + "'";
                ArrayList<Knjiga> pronadjenaKnjiga = Communication.getInstance().vratiKnjigu(uslov);
                popuniTabeluu(pronadjenaKnjiga);
                
            }

            private void popuniTabeluu(ArrayList<Knjiga> pronadjenaKnjiga) {
                KnjigaTableModel ktm = new KnjigaTableModel(pronadjenaKnjiga);
                forma.getTblKnjiga().setModel(ktm);
                if (pronadjenaKnjiga.isEmpty()) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da nadje knjigu po zadatoj vrednosti!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(forma, "Sistem je nasao knjigu po zadatoj vrednosti!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                }
            }  
        });
        
        forma.obrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblKnjiga().getSelectedRow();
                if(red != -1){
                    KnjigaTableModel ktm = (KnjigaTableModel) forma.getTblKnjiga().getModel();
                    Knjiga knjigaZaBrisanje = ktm.vratiKnjigu(red);
                    boolean obrisano = Communication.getInstance().obrisiKnjigu(knjigaZaBrisanje);
                    if(obrisano){
                        ktm.obrisi(knjigaZaBrisanje);
                        popuniTabelu();
                        JOptionPane.showMessageDialog(forma, "Sistem je obrisao knjigu!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(forma, "Sistem ne moze da obrise knjigu!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(forma, "Morate da izaberete red u tabeli!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        forma.izmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getTblKnjiga().getSelectedRow();
                if(red != -1){
                    KnjigaTableModel ktm1 = (KnjigaTableModel) forma.getTblKnjiga().getModel();
                    Knjiga knjigaZaIzmenu = ktm1.vratiKnjigu(red);
                    forma.dispose();
                    Cordinator.getInstance().otovriFormuZaIzmenuKnjige(knjigaZaIzmenu);
                }else{
                    JOptionPane.showMessageDialog(forma, "Morate da izaberete red u tabeli!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        forma.dodajPrimerakActionListner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int red = forma.getTblKnjiga().getSelectedRow();
                if(red != -1){
                    KnjigaTableModel ktm2 = (KnjigaTableModel) forma.getTblKnjiga().getModel();
                    Knjiga knjiga = ktm2.vratiKnjigu(red);
                    forma.dispose();
                    Cordinator.getInstance().otvoriFormuPrimerak(knjiga);
                }else{
                    JOptionPane.showMessageDialog(forma, "Morate da izaberete red u tabeli!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }

    private void popuniTabelu() {
        ArrayList<Knjiga> sveKnjige = Communication.getInstance().vratiSveKnjige();
        KnjigaTableModel ktm = new KnjigaTableModel(sveKnjige);
        forma.getTblKnjiga().setModel(ktm);
    }
    
    
    
}
