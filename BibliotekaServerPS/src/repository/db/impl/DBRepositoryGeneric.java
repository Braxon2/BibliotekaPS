/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.GenericEntity;
import java.util.ArrayList;
import java.util.List;
import repository.db.DBRepository;
import java.sql.*;
import repository.db.DbConnectFactory;

/**
 *
 * @author Dusan
 */
public class DBRepositoryGeneric implements DBRepository<GenericEntity>{

    @Override
    public List<GenericEntity> getAll(GenericEntity param, String uslov) throws Exception {
        
        List<GenericEntity> lista = new ArrayList<>();
        String querry = "SELECT * FROM " + param.vratiNazivTabele() + param.vratiUslovPovezivanja() + uslov;
        System.out.println(querry);
        Statement s = DbConnectFactory.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(querry);
        lista = param.vratiListu(rs);
        rs.close();
        s.close();
        return lista;
        
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        String querry = "SELECT * FROM " + param.vratiNazivTabele() + param.vratiUslovPovezivanja();
        System.out.println(querry);
        Statement s = DbConnectFactory.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(querry);
        lista = param.vratiListu(rs);
        rs.close();
        s.close();
        return lista;
    }

    @Override
    public void add(GenericEntity param) throws Exception {
        String querry = "INSERT INTO "+param.vratiNazivTabele()+
                " ("+param.vratiKoloneZaUbacivanje()+") VALUES ("+
                param.vratiVrednostiZAUbacivanje()+" )";
        System.out.println(querry);
        Statement s = DbConnectFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(querry);
        s.close();
    }

    @Override
    public void edit(GenericEntity param) throws Exception {
        String querry = "UPDATE "+param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu()+ " WHERE "+ param.vratiPrimarniKljuc();
        System.out.println(querry);
        Statement s = DbConnectFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(querry);
        s.close();
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        
        String querry = "DELETE FROM " + param.vratiNazivTabele() + " WHERE "+ param.vratiPrimarniKljuc();
        System.out.println(querry);
        Statement s = DbConnectFactory.getInstance().getConnection().createStatement();
        s.executeUpdate(querry);
        s.close();
        
    }
    
}
