/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import repository.Repository;

/**
 *
 * @author Dusan
 */
public interface DBRepository<T> extends Repository<T> {

    default public void connect() throws Exception {
        DbConnectFactory.getInstance().getConnection();
    }

    default public void disconnect() throws Exception {
        DbConnectFactory.getInstance().getConnection().close();
    }

    default public void commit() throws Exception {
        DbConnectFactory.getInstance().getConnection().commit();
    }

    default public void rollback() throws Exception {
        DbConnectFactory.getInstance().getConnection().rollback();
    }

}
