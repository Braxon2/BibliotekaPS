/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import domain.Clan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.FrmDodajClana;

/**
 *
 * @author Dusan
 */
public class DodajclanaController {

    private final FrmDodajClana forma;

    public DodajclanaController(FrmDodajClana forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners() {

        forma.dodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                Clan clanZaDodavanje = new Clan(-1, ime, prezime, jmbg);
                JOptionPane.showMessageDialog(forma, "Sistem je kreirao novog clana", "Kreiran", JOptionPane.INFORMATION_MESSAGE);

                Clan dodatiClan = Communication.getInstance().dodajClana(clanZaDodavanje);
                if (dodatiClan != null) {
                    JOptionPane.showMessageDialog(forma, "Sistem je zapamtio clana", "Sacuvan", JOptionPane.INFORMATION_MESSAGE);
                    forma.dispose();
                }else{
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti clana!!!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }

}
