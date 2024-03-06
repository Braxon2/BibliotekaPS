/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Primerak;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dusan
 */
public class PrimerakTableModel extends AbstractTableModel {

    private ArrayList<Primerak> lista;
    private String[] kolone = {"Pisac", "Ostecen", "Barcode"};

    public PrimerakTableModel() {
        lista = new ArrayList<>();
    }

    public PrimerakTableModel(ArrayList<Primerak> lista) {
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
        Primerak primerak = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return primerak.getKnjiga().getPisac();
            case 1: return primerak.isOstecen();
            case 2: return primerak.getBarCode();
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
        if (columnIndex == 1) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Primerak vratiPrimerak(int red) {
        return lista.get(red);
    }

    public void obrisi(Primerak p) {
        lista.remove(p);
        fireTableDataChanged();
    }

    public void dodajPrimerak(Primerak primerak) {
        lista.add(primerak);
        fireTableDataChanged();
    }

}
