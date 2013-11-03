// --------------------------------------------------------------------------------------------------------------------
// <copyright file="UniqueObject.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

/**
 * Extending classes are a base for an object with a primary key.
 *
 * @param <TKey> The type of the primary key.
 */
public abstract class UniqueObject<TKey> extends PersistableObject implements IUniqueObject<TKey>
{
    private TKey _id;

    /**
     * Initialises a new instance of the UniqueObject class.
     * @param isNewObject A value indicating whether this is an object that has previously been persisted.
     * @param id The unique identifier of this object.
     */
    protected UniqueObject(boolean isNewObject, TKey id)
    {
        super(isNewObject);
        this._id = id;
    }

    /**
     * Gets the value to the unique key of this object.
     *
     * @return
     */
    @Override
    public TKey GetId()
    {
        return this._id;
    }
    
    /**
     * Gets the value to the unique key of this object.
     *
     * @return
     */
    @Override
    public TKey getId()
    {
        return this._id;
    }

    /**
     * Sets the value of the unique identifier of this object.
     * @param id The new unique identifier of this object.
     */
    protected void SetId(TKey id)
    {
        this._id = id;
    }
}