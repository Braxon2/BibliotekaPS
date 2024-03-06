/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import domain.Iznajmljivanje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.IznajmljivanjeTableModel;
import model.StavkaIznajmljivanjaTableModel;
import view.FrmPrikazIznajmljivanja;

/**
 *
 * @author Dusan
 */
public class PrikazIznajmljivanjaController {

    private final FrmPrikazIznajmljivanja forma;

    public PrikazIznajmljivanjaController(FrmPrikazIznajmljivanja forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners() {

        forma.pretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datum = forma.getTxtDatum().getText();

                if (datum.isEmpty() || datum == null) {
                    JOptionPane.showMessageDialog(forma, "Unesite datum!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
              
                String uslov = " WHERE i.datumiznajmljivanje ='" + datum + "'";
                ArrayList<Iznajmljivanje> iznajmljivanje = Communication.getInstance().vratiIznajmljivanje(uslov);
                srediFormu(iznajmljivanje);
            }

            private void srediFormu(ArrayList<Iznajmljivanje> iznajmljivanje) {
                forma.getTblIznajmljivanja().setModel(new IznajmljivanjeTableModel(iznajmljivanje));
                forma.getTblStavka().setModel(new StavkaIznajmljivanjaTableModel());
                if(iznajmljivanje.isEmpty()){
                    JOptionPane.showMessageDialog(forma, "Sistem nije mogao da nadje iznajmljivanje po zadatoj vrednosti!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(forma, "Sistem je nasao iznajmljivanje po zadatoj vrednosti!", "GRESKA", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }

    public void otvoriFormu() {
        ArrayList<Iznajmljivanje> iznajmjivanja = Communication.getInstance().vratiSvaIznajmljivanja();
        forma.getTblIznajmljivanja().setModel(new IznajmljivanjeTableModel(iznajmjivanja));
        forma.getTblStavka().setModel(new StavkaIznajmljivanjaTableModel());
        forma.setVisible(true);
    }

}
