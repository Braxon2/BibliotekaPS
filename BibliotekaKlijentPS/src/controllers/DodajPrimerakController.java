/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import domain.Knjiga;
import domain.Primerak;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.FrmDodajPrimerak;

/**
 *
 * @author Dusan
 */
public class DodajPrimerakController {
    
    
    private final FrmDodajPrimerak forma;
    
    private Knjiga knjiga;

    public DodajPrimerakController(FrmDodajPrimerak forma) {
        this.forma = forma;
        addActionListeners();
    }


    private void addActionListeners() {
        
        forma.dodajaddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String barCode = forma.getTxtBarcode().getText().trim();
                boolean ostecen = forma.getChbOstecen().isSelected();
                
                if(barCode.isEmpty() || barCode == null){
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da kreira primerak","Neuspeh",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if(barCode.length() != 13){
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da kreira primerak","Neuspeh",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Primerak primerak = new Primerak();
                primerak.setPrimerakId(-1);
                primerak.setBarCode(barCode);
                primerak.setOstecen(ostecen);
                primerak.setIznajmljen(false);
                primerak.setKnjiga(forma.getKnjiga());
                
                
                JOptionPane.showMessageDialog(forma, "Sistem je kreirao primerak","Kreiran",JOptionPane.INFORMATION_MESSAGE);
                    
                
                Primerak dodatiPrimerak = Communication.getInstance().dodajPrimerak(primerak);
                
                if(dodatiPrimerak != null){
                    JOptionPane.showMessageDialog(forma, "Sistem je zapamtio primerak","Dodato",JOptionPane.INFORMATION_MESSAGE);
                    forma.dispose();
                }else{
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti primerak","Neuspeh",JOptionPane.ERROR_MESSAGE);
                  
                }
                
            }
        });
        
    }

    public void otvoriFormu(Knjiga knjiga) {
        forma.setVisible(true);
//        forma.setKnjiga(knjiga);
    }

    public void setujKnjigu(Knjiga knjiga) {
        forma.setKnjiga(knjiga);
    }
    
    
    
}
