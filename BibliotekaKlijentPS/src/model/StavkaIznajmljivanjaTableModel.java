/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.StavkaIznajmljivanja;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dusan
 */
public class StavkaIznajmljivanjaTableModel extends AbstractTableModel {

    ArrayList<StavkaIznajmljivanja> lista;
    String[] kolone = {"Rb", "Knjiga", "Barcode", "Ostecen", "Bibliotekar"};
    int rb = 0;

    public StavkaIznajmljivanjaTableModel() {
        lista = new ArrayList<>();
    }

    public StavkaIznajmljivanjaTableModel(ArrayList<StavkaIznajmljivanja> lista) {
        this.lista = lista;
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

        StavkaIznajmljivanja stavka = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return rowIndex+1;
            case 1: return stavka.getPrimerak().getKnjiga().getNaslov();
            case 2: return stavka.getPrimerak().getBarCode();
            case 3: return stavka.getPrimerak().isOstecen();
            case 4: return stavka.getPrimerak().getKnjiga().getBibliotekar();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 3){
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public ArrayList<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void dodajStavku(StavkaIznajmljivanja stavkaIznajmljivanja) {
        lista.add(stavkaIznajmljivanja);
//        srediRb();
        fireTableDataChanged();
    }

    private void srediRb() {
        rb = 0;
        for (StavkaIznajmljivanja st : lista) {
            st.setRbr(rb++);
        }
    }

    public StavkaIznajmljivanja vratiStavku(int red) {
        return lista.get(red);
    }

    public void obrisiRed(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

    
    
}
