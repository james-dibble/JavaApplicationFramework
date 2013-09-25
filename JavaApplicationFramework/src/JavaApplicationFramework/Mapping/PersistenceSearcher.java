// --------------------------------------------------------------------------------------------------------------------
// <copyright file="PersistenceSearcher.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.util.HashMap;

/**
 * A class to use to search a persistence source.
 * @param <T> The type of the object to find in the persistence source.
 */
public class PersistenceSearcher<T extends IPersistableObject> extends HashMap<String, Object> implements IPersistenceSearcher<T> {
    private final Class<T> _type;
    
    /**
     * Creates an instance of the PersistenceSearcher class.
     * @param type The type this persistence searcher is being used to find.
     */
    public PersistenceSearcher(Class<T> type) {
        this._type = type;
    }
    
    @Override
    public Class<T> Type() {
        return this._type;
    }
    
    @Override
    public Object GetArgument(String key) {
        return this.get(key);
    }

    @Override
    public boolean HasArgument(String key) {
        return this.containsKey(key);
    }
}
