/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import cordinator.Cordinator;
import domain.KorisnickiProfil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.FrmLogIn;

/**
 *
 * @author Dusan
 */
public class LogInController {
    private final FrmLogIn forma;

    public LogInController(FrmLogIn forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners() {
        forma.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = forma.getTxtUsername().getText();
                String passsword = String.valueOf(forma.getTxtPassword().getPassword());
                if(username.isEmpty() || passsword.isEmpty()){
                    JOptionPane.showMessageDialog(forma, "Morate uneti username i password","GRESKA",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Communication.getInstance().connect();
                KorisnickiProfil korisnickiProfil = new KorisnickiProfil();
                korisnickiProfil.setUsername(username);
                korisnickiProfil.setPassword(passsword);
                korisnickiProfil.setUserId(-1);
               Object kp = Communication.getInstance().logIn(korisnickiProfil);
               if(kp == null){
                   JOptionPane.showMessageDialog(forma, "Losi kredecijali");
                   return;
               }
               if(kp instanceof KorisnickiProfil){
                   JOptionPane.showMessageDialog(forma, "USPEH");
                   Cordinator.getInstance().setKp((KorisnickiProfil) kp);
                   Cordinator.getInstance().otvoriMainFormu();
                   forma.dispose();
               }else{
                   String poruka = (String) kp;
                   if(poruka.equals("Korisnik je vec ulgoovan!")){
                       JOptionPane.showMessageDialog(forma, poruka);
                   }
                   
               }
            }
        });
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
    
    
    
}
