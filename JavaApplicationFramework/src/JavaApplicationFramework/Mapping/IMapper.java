// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IMapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.sql.ResultSet;

/**
 * Implementing classes define methods to interact with a persitable object.
 *
 * @param <T> The type this mapper can persist.
 */
public interface IMapper<T extends IPersistableObject>
{
    /**
     * Gets the type this IMapper maps.
     *
     * @return The type this IMapper maps.
     */
    Class GetMappedType();

    /**
     * Create a query that gets &gtT&lt; from the persistence source.
     *
     * @param searcher The parameters to conduct the search for the &gtT&lt;.
     * @return The query in string format.
     */
    String GetFindQuery(IPersistenceSearcher<T> searcher);

    /**
     * Map a &gtT&lt; from the first element of the results.
     *
     * @param results The ResultSet from a GetFindQuery.
     * @return A populated &gtT&lt;.
     */
    T FindSingle(ResultSet results);

    /**
     * Map all the &gtT&lt;'s from the results.
     *
     * @param results The ResultSet from a GetFindQuery.
     * @return A collection of populated &gtT&lt;s.
     */
    Iterable<T> FindCollectionOf(ResultSet results);

    /**
     * Create a set of queries to persist a given object.
     *
     * @param objectToSave The object to place into the persistence source.
     * @return A collection of queries to save the given object.
     */
    Iterable<String> GetObjectCreateQueries(T objectToSave);

    /**
     * Create a set of queries to persist changes to a given object.
     *
     * @param objectToSave The object to change in the persistence source.
     * @return A collection of queries to save the given object.
     */
    Iterable<String> GetObjectSaveQueries(T objectToSave);
}