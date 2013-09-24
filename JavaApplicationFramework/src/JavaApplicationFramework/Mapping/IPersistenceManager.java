/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplicationFramework.Mapping;

/**
 *
 * @author james
 */
public interface IPersistenceManager {
    <T extends IPersistableObject> T Find(IPersistenceSearcher<T> searcher);
    
    <T extends IPersistableObject> Iterable<T> FindCollectionOf(IPersistenceSearcher<T> searcher);
    
    void Add(IPersistableObject objectToSave);
    
    void Change(IPersistableObject objectToSave);
    
    void Commit();
}