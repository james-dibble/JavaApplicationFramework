// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Mapper.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
     * Map a &ltT&gt; from the first element of the results.
     *
     * @param results The ResultSet from a GetFindQuery.
     * @return A populated &ltT&gt;.
     */
    @Override
    public final T FindSingle(ResultSet results)
    {
        if (Mapper.IsEmptyResultSet(results))
        {
            return null;
        }

        T mappedObject = this.MapFromResultSet(results);

        return mappedObject;
    }

    /**
     * Map all the &ltT&gt;'s from the results.
     *
     * @param results The ResultSet from a GetFindQuery.
     * @return A collection of populated &ltT&gt;s.
     */
    @Override
    public final Iterable<T> FindCollectionOf(ResultSet results)
    {
        ArrayList<T> mappedObjects = new ArrayList<>();

        if (Mapper.IsEmptyResultSet(results))
        {
            return mappedObjects;
        }

        try
        {
            do
            {
                T mappedObject = this.MapFromResultSet(results);

                if (mappedObject != null)
                {
                    mappedObjects.add(mappedObject);
                }
            }
            while (results.next());
        }
        finally
        {
            return mappedObjects;
        }
    }

    /**
     * Create a domain object from the results of a query.
     *
     * @param results The results of a query from the persistence source.
     * @return A populated domain object or null if the object could not be
     * mapped.
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