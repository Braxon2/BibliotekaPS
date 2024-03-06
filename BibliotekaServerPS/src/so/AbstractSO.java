/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;


import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.DBRepositoryGeneric;

/**
 *
 * @author Dusan
 */
public abstract class AbstractSO {
    protected final Repository broker;

    public AbstractSO() {
        this.broker = new DBRepositoryGeneric();
    }

    public final void execute(Object obj,String kljuc) throws Exception{
        try{
            validate(obj);
            startTransaction();
            executeOperation(obj, kljuc);
            commitTransaction();
        }catch (Exception ex){
            rollbackTransaction();
            throw ex;
        }finally{
            disconnect();
        }
    }
    
    public abstract void validate(Object obj) throws Exception;
    
    private void startTransaction() throws Exception {
        ((DBRepository)broker).connect();
    }

    protected abstract void executeOperation(Object param,String kljuc) throws Exception;

    private void commitTransaction() throws Exception {
        ((DBRepository)broker).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DBRepository)broker).rollback();
    }

    private void disconnect() throws Exception {
        ((DBRepository)broker).disconnect();
    }
    
    
}
