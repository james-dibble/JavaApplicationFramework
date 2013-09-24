/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplicationFramework.Mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author james
 */
public abstract class Mapper<T extends IPersistableObject> implements IMapper<T> {

    @Override
    public abstract Class GetMappedType();

    @Override
    public T FindSingle(ResultSet results) {
        T mappedObject = this.MapFromResultSet(results);
        
        return mappedObject;
    }

    @Override
    public Iterable<T> FindCollectionOf(ResultSet results) {
        if (Mapper.IsEmptyResultSet(results)) {
            return new ArrayList<>();
        }

        ArrayList<T> mappedObjects = new ArrayList<>();
        
        try {
            do {
                T mappedObject = this.MapFromResultSet(results);

                mappedObjects.add(mappedObject);
            } while (results.next());
        } catch (SQLException ex) {
            Logger.getLogger(Mapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mappedObjects;
    }

    @Override
    public abstract String GetFindQuery(IPersistenceSearcher<T> searcher);

    protected abstract T MapFromResultSet(ResultSet results);

    protected abstract Iterable<String> GetObjectSaveQueries(T objectToSave);

    private static boolean IsEmptyResultSet(ResultSet results) {
        try {
            return !results.first();
        } catch (SQLException ex) {
            return true;
        }
    }
}