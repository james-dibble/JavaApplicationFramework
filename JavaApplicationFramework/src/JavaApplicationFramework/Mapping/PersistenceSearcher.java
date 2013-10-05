// --------------------------------------------------------------------------------------------------------------------
// <copyright file="PersistenceSearcher.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.util.HashMap;

/**
 * A class to use to search a persistence source.
 *
 * @param <T> The type of the object to find in the persistence source.
 */
public class PersistenceSearcher<T extends IPersistableObject> extends HashMap<String, Object> implements IPersistenceSearcher<T>
{
    private final Class<T> _type;

    /**
     * Creates an instance of the PersistenceSearcher class.
     *
     * @param type The type this persistence searcher is being used to find.
     */
    public PersistenceSearcher(Class<T> type)
    {
        this._type = type;
    }

    /**
     * Get the type to be searched for.
     *
     * @return The type to be searched for.
     */
    @Override
    public Class<T> Type()
    {
        return this._type;
    }

    /**
     * Get a known value to be used in a mapper.
     *
     * @param key The value to find the argument by.
     * @return The value to map with.
     */
    @Override
    public Object GetArgument(String key)
    {
        return this.get(key);
    }

    /**
     * Gets a value indicating whether a given key exists.
     *
     * @param key The key to of the value to verify.
     * @return A value indicating whether a given key exists.
     */
    @Override
    public boolean HasArgument(String key)
    {
        return this.containsKey(key);
    }
}
