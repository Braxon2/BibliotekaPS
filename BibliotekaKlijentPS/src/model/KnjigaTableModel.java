/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Knjiga;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dusan
 */
public class KnjigaTableModel extends AbstractTableModel {

    ArrayList<Knjiga> lista;
    String[] kolone = {"Naslov", "Pisac", "Broj strana", "Izdavac", "ISBN", "Bibliotekar"};

    public KnjigaTableModel(ArrayList<Knjiga> lista) {
        this.lista = lista;
    }
    
    public KnjigaTableModel() {
        lista = new ArrayList<>();
    }

    public ArrayList<Knjiga> getLista() {
        return lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga k = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return k.getNaslov();
            case 1:
                return k.getPisac();
            case 2:
                return k.getBrojStrana();
            case 3:
                return k.getIzdavac();
            case 4:
                return k.getIsbn();
            case 5:
                return k.getBibliotekar().toString();
            default:
                return "N/A";
            }

    }
    
    public Knjiga vratiKnjigu(int red){
        return lista.get(red);
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void obrisi(Knjiga knjigaZaBrisanje) {
        lista.remove(knjigaZaBrisanje);
        fireTableDataChanged();
    }

    
    
    

}
