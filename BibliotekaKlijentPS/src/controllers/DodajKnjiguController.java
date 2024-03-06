/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import cordinator.Cordinator;
import domain.Bibliotekar;
import domain.Knjiga;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.FrmDodajKnjigu;

/**
 *
 * @author Dusan
 */
public class DodajKnjiguController {
    
    private final FrmDodajKnjigu forma;

    public DodajKnjiguController(FrmDodajKnjigu forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners( ) {
        forma.dodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naslov = forma.getTxtNaslov().getText().trim();
                String pisac = forma.getTxtPisac().getText().trim();
                int brojStana=-1;
                try{
                 brojStana =Integer.parseInt(forma.getTxtBrojStrana().getText().trim());
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira knjigu!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String izdavac = forma.getTxtIzdavac().getText().trim();
                String isbn = forma.getTxtISBN().getText().trim();
                
                if(naslov == null || naslov.isEmpty()){
                     JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira knjigu!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
               if(pisac == null || pisac.isEmpty()){
                     JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira knjigu!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
               if(brojStana <= 0){
                     JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira knjigu!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
               if(izdavac == null || izdavac.isEmpty()){
                     JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira knjigu!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
               if(isbn == null || isbn.isEmpty()){
                     JOptionPane.showMessageDialog(forma, "Sistem ne mozeda kreira knjigu!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Bibliotekar bibliotekar = Cordinator.getInstance().getKp().getBibliotekar();
                Knjiga knjigaZaDodavanje = new Knjiga(-1,naslov,pisac,brojStana,izdavac,isbn,bibliotekar,null);
                JOptionPane.showMessageDialog(forma, "Sistem je kreirao novu knjigu", "Kreiran", JOptionPane.INFORMATION_MESSAGE);
                
                Knjiga dodataKnjiga = Communication.getInstance().dodajKnjigu(knjigaZaDodavanje);
                if(dodataKnjiga != null){
                    JOptionPane.showMessageDialog(forma, "Sistem je zapamtio knjigu", "Sacuvan", JOptionPane.INFORMATION_MESSAGE);
                    forma.dispose();
                }else{
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti knjigu!!!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
    
    
    
}
