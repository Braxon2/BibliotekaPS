/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import cordinator.Cordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.FrmMain;

/**
 *
 * @author Dusan
 */
public class MainFormController {
    
    private final FrmMain forma;

    public MainFormController(FrmMain forma) {
        this.forma = forma;
        addActionListeners();
    }
    
    public void otvoriFormu(){
        forma.setVisible(true);
        forma.getLblUlogovani().setText(Cordinator.getInstance().getKp().getBibliotekar().toString());
        
    }

    public FrmMain getForma() {
        return forma;
    }

    private void addActionListeners() {
        forma.logOutaddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Communication.getInstance().logout();
                forma.dispose();
            }
        });
    }
    
    
    
    
    
}
