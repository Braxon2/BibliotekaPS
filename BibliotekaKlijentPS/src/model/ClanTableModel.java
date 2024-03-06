/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Clan;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dusan
 */
public class ClanTableModel extends AbstractTableModel {

    private ArrayList<Clan> lista;
    private String[] kolone = {"ID","Ime", "Prezime", "JMBG"};

    public ClanTableModel() {
        lista = new ArrayList<>();
    }

    public ClanTableModel(ArrayList<Clan> lista) {
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
        Clan clan = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return clan.getClanId();
            case 1:
                return clan.getIme();
            case 2:
                return clan.getPrezime();
            case 3:
                return clan.getJmbg();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public Clan vratiClana(int red) {
        return lista.get(red);
    }

    public void obrisi(Clan clanZaBrisanje) {
        lista.remove(clanZaBrisanje);
        fireTableDataChanged();
    }
    
    

}
