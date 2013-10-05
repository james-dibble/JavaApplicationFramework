// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Mapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Extending classes define methods to interact with a persitable object.
 *
 * @param <T> The type this mapper can persist.
 */
public abstract class Mapper<T extends IPersistableObject> implements IMapper<T>
{
    /**
     * Gets the type this IMapper maps.
     *
     * @return The type this IMapper maps.
     */
    @Override
    public abstract Class GetMappedType();

    /**
     * Map a &gtT&lt; from the first element of the results.
     *
     * @param results The ResultSet from a GetFindQuery.
     * @return A populated &gtT&lt;.
     */
    @Override
    public T FindSingle(ResultSet results)
    {
        if (Mapper.IsEmptyResultSet(results))
        {
            return null;
        }

        T mappedObject = this.MapFromResultSet(results);

        return mappedObject;
    }

    /**
     * Map all the &gtT&lt;'s from the results.
     *
     * @param results The ResultSet from a GetFindQuery.
     * @return A collection of populated &gtT&lt;s.
     */
    @Override
    public Iterable<T> FindCollectionOf(ResultSet results)
    {
        if (Mapper.IsEmptyResultSet(results))
        {
            return new ArrayList<>();
        }

        ArrayList<T> mappedObjects = new ArrayList<>();

        try
        {
            do
            {
                T mappedObject = this.MapFromResultSet(results);

                mappedObjects.add(mappedObject);
            }
            while (results.next());
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Mapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mappedObjects;
    }

    /**
     * Create a query that gets &gtT&lt; from the persistence source.
     *
     * @param searcher The parameters to conduct the search for the &gtT&lt;.
     * @return The query in string format.
     */
    @Override
    public abstract String GetFindQuery(IPersistenceSearcher<T> searcher);

    /**
     * Create a set of queries to persist a given object.
     *
     * @param objectToSave The object to place into the persistence source.
     * @return A collection of queries to save the given object.
     */
    @Override
    public abstract Iterable<String> GetObjectCreateQueries(T objectToSave);

    /**
     * Create a set of queries to persist changes to a given object.
     *
     * @param objectToSave The object to change in the persistence source.
     * @return A collection of queries to save the given object.
     */
    @Override
    public abstract Iterable<String> GetObjectSaveQueries(T objectToSave);

    /**
     * Create a domain object from the results of a query.
     *
     * @param results The results of a query from the persistence source.
     * @return A populated domain object.
     */
    protected abstract T MapFromResultSet(ResultSet results);

    private static boolean IsEmptyResultSet(ResultSet results)
    {
        try
        {
            return !results.first();
        }
        catch (SQLException ex)
        {
            return true;
        }
    }
}