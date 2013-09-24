/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplicationFramework.Mapping;

import java.sql.ResultSet;

/**
 *
 * @author james
 */
public interface IMapper<T extends IPersistableObject> {
    Class GetMappedType();
    
    String GetFindQuery(IPersistenceSearcher<T> searcher);
    
    T FindSingle(ResultSet results);
    
    Iterable<T> FindCollectionOf(ResultSet results);
}