// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IPersistableObject.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

/**
 * An object that has association with a persisted entity.
 */
public interface IPersistableObject {
    
    /**
     * Gets a value indicating whether this is a new object.
     * @return A boolean value whether this is a new object. 
     */
    boolean IsNewObject();
}
