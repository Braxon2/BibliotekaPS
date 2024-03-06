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
import view.FrmIzmeniKnjigu;

/**
 *
 * @author Dusan
 */
public class IzmenaKnjigeController {

    private final FrmIzmeniKnjigu forma;

    public IzmenaKnjigeController(FrmIzmeniKnjigu forma) {
        this.forma = forma;
        
        addActionListeners();
    }

    private void addActionListeners() {
        forma.izmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long id = Long.parseLong(forma.getTxtID().getText().trim());
                String naslov = forma.getTxtNaslov().getText().trim();
                String pisac = forma.getTxtPisac().getText().trim();
                int brojStrana=-1;
                try{
                 brojStrana = Integer.parseInt(forma.getTxtBrojStrana().getText().trim());
                }catch(NumberFormatException ex){
                    ex.printStackTrace();
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
               if(brojStrana <= 0){
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
                
                Bibliotekar bibliotekar = forma.getKnjiga().getBibliotekar();
                Knjiga knjigaZaIzmenu = new Knjiga(id, naslov, pisac, brojStrana, izdavac, isbn, bibliotekar, null);
                
                Knjiga izmenjenaKnjiga = Communication.getInstance().izmeniKnjigu(knjigaZaIzmenu);
                
                if(izmenjenaKnjiga != null){
                    JOptionPane.showMessageDialog(forma, "Sistem je zapamtio knjigu", "Kreiran", JOptionPane.INFORMATION_MESSAGE);
                    forma.dispose();
                    Cordinator.getInstance().otvooriFormuPrikazKnjige();
                }else{
                    JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti knjigu!!!", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu(Knjiga knjiga) {
        forma.getTxtID().setEnabled(false);
        forma.getTxtBibliotekar().setEnabled(false);
        forma.getTxtID().setText(knjiga.getKnjigaId() + "");
        forma.getTxtNaslov().setText(knjiga.getNaslov());
        forma.getTxtPisac().setText(knjiga.getPisac());
        forma.getTxtIzdavac().setText(knjiga.getIzdavac());
        forma.getTxtISBN().setText(knjiga.getIsbn());
        forma.getTxtBrojStrana().setText(knjiga.getBrojStrana() + "");
        forma.getTxtBibliotekar().setText(knjiga.getBibliotekar().toString());
        forma.setKnjiga(knjiga);
        forma.setVisible(true);

    }

}
