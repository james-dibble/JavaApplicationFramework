/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplicationFramework.Mapping;

import java.sql.Connection;

/**
 *
 * @author james
 */
public final class MySqlPersistenceManager implements IPersistenceManager{
    private final Connection _connection; 
    private final MapperDictionary _mappers;
    
    public MySqlPersistenceManager(Connection connection, MapperDictionary mappers) {
        this._connection = connection;
        this._mappers = mappers;
    }
    
    @Override
    public <T extends IPersistableObject> T Find(IPersistenceSearcher<T> searcher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T extends IPersistableObject> Iterable<T> FindCollectionOf(IPersistenceSearcher<T> searcher) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Add(IPersistableObject objectToSave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Change(IPersistableObject objectToSave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Commit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
