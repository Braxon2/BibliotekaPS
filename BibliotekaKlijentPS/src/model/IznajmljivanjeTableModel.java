/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Iznajmljivanje;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dusan
 */
public class IznajmljivanjeTableModel extends AbstractTableModel {

    ArrayList<Iznajmljivanje> lista;
    String[] kolone = {"Clan", "DatumOd", "DatumDo", "Bibliotekar"};
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public IznajmljivanjeTableModel() {
        lista = new ArrayList<>();
    }

    public IznajmljivanjeTableModel(ArrayList<Iznajmljivanje> lista) {
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

        Iznajmljivanje iznajmljivanje = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return iznajmljivanje.getClan();
            case 1: return sdf.format(iznajmljivanje.getDatumIznajmljivanja());
            case 2: return sdf.format(iznajmljivanje.getDatumVracanja());
            case 3: return iznajmljivanje.getBibliotekar();
            default:
                return "N/A";
        }

    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public ArrayList<Iznajmljivanje> getLista() {
        return lista;
    }

    public Iznajmljivanje vratiIznajmljivanje(int red) {
        return lista.get(red);
    }
    
    

}
