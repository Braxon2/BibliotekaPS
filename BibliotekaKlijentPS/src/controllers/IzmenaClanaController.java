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
import javax.swing.JOptionPane;
import view.FrmIzmeniClana;

/**
 *
 * @author Dusan
 */
public class IzmenaClanaController {
    
    private final FrmIzmeniClana forma;

    public IzmenaClanaController(FrmIzmeniClana forma) {
        this.forma = forma;
        addActionListeners();
    }
    
    public void otvoriFormu(Clan clan){
        forma.setClan(clan);
        forma.getTxtID().setText(clan.getClanId()+"");
        forma.getTxtID().setEnabled(false);
        forma.getTxtIme().setText(clan.getIme());
        forma.getTxtPrezime().setText(clan.getPrezime());
        forma.getTxtJmbg().setText(clan.getJmbg());
        forma.setVisible(true);
    }

    private void addActionListeners() {
        forma.izmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long id = Long.parseLong(forma.getTxtID().getText().trim());
                String ime = forma.getTxtIme().getText().trim();
                String prezime = forma.getTxtPrezime().getText().trim();
                String jmbg = forma.getTxtJmbg().getText().trim();
                
                  if (ime == null || ime.isEmpty()) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira novog clana!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (prezime == null || prezime.isEmpty()) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira novog clana!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (jmbg == null || jmbg.isEmpty()) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira novog clana!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (jmbg.length() != 13) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira novog clana!!!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!(jmbg.matches("[0-9]*"))) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira novog clana!!!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Clan clanZaIzmenu = new Clan(id, ime, prezime, jmbg);
                
                Clan izmenjeniClan = Communication.getInstance().izmeniClana(clanZaIzmenu);
                if(izmenjeniClan != null){
                    JOptionPane.showMessageDialog(forma, "Sistem je zapamtio clana", "Kreiran", JOptionPane.INFORMATION_MESSAGE);
                    forma.dispose();
                    Cordinator.getInstance().otvoriFormuPrikazClana();
                }else{
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti clana!!!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
    }
    
}
