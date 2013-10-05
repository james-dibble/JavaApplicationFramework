// --------------------------------------------------------------------------------------------------------------------
// <copyright file="PersistableObject.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

/**
 * Extending classes are a base for an object in a database.
 */
public abstract class PersistableObject implements IPersistableObject
{
    private boolean _isNewObject;

    /**
     * Initializes a new instance of the PersistebleObject class.
     * @param isNewObject A value indicating whether this object is new.
     */
    protected PersistableObject(boolean isNewObject)
    {
        this._isNewObject = isNewObject;
    }

    /**
     * Gets a value indicating whether this is a new object.
     *
     * @return A boolean value whether this is a new object.
     */
    @Override
    public boolean IsNewObject()
    {
        return _isNewObject;
    }
}
