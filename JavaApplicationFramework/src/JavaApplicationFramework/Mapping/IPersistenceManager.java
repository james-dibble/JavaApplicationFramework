// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IPersistenceManager.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.sql.SQLException;

/**
 * Implementing class define methods for managing interactions with a persistence source.
 */
public interface IPersistenceManager {
    /**
     * Get a persisted object that matches the given criteria.
     * @param <T> The type of the object to find.
     * @param searcher The criteria to find the object with.
     * @return The object that matches the criteria or null if it could not be identified.
     */
    <T extends IPersistableObject> T Find(IPersistenceSearcher<T> searcher);
    
    /**
     * Get a collection of persisted objects that match the given criteria.
     * @param <T> The type of the objects to find.
     * @param searcher The criteria to find the objects with.
     * @return The objects that matches the criteria.
     */
    <T extends IPersistableObject> Iterable<T> FindCollectionOf(IPersistenceSearcher<T> searcher);
    
    /**
     * Put the given object into the persistence source.
     * @param objectToSave The object to save.
     */
    void Add(IPersistableObject objectToSave);
     /**
      * Save the changes to a given object into the persistence source.
      * @param objectToSave The object to save.
      */
    void Change(IPersistableObject objectToSave);
    
     /**
      * Run the queries for this context to add or change objects.
      * @throws SQLException
      */
    void Commit() throws SQLException;
    
    /**
     * Clean up any resources of the persistence manager.
     * @throws SQLException 
     */
    void Dispose() throws SQLException;
}