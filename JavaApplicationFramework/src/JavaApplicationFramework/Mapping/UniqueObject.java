// --------------------------------------------------------------------------------------------------------------------
// <copyright file="UniqueObject.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------

package JavaApplicationFramework.Mapping;

/**
 * Extending classes are a base for an object with a primary key.
 * @param <TKey> The type of the primary key.
 */
public abstract class UniqueObject<TKey> extends PersistableObject implements IUniqueObject<TKey> {

    private TKey _id;
    
    protected UniqueObject(boolean isNewObject, TKey id){
        super(isNewObject);
        this._id = id;
    }
    
    @Override
    public TKey GetId() {
        return this._id;
    }
    
    protected void SetId(TKey id){
        this._id = id;
    }
}