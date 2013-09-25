// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IPersistenceSearcher.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

/**
 * Implementing classes define methods for searching a persistence source.
 */
public interface IPersistenceSearcher<T extends IPersistableObject> {
    /**
     * Get the type to be searched for.
     * @return The type to be searched for.
     */
    Class<T> Type();
    
    /**
     * Get a known value to be used in a mapper.
     * @param key The value to find the argument by.
     * @return The value to map with.
     */
    Object GetArgument(String key);
    
    /**
     * Gets a value indicating whether a given key exists.
     * @param key The key to of the value to verify.
     * @return A value indicating whether a given key exists.
     */
    boolean HasArgument(String key);
}