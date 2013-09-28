// --------------------------------------------------------------------------------------------------------------------
// <copyright file="PersistableObject.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------

package JavaApplicationFramework.Mapping;

/**
 * Extending classes are a base for an object in a database.
 */
public abstract class PersistableObject implements IPersistableObject {

    private boolean _isNewObject;
    
    protected PersistableObject(boolean isNewObject){
        this._isNewObject = isNewObject;
    }
    
    @Override
    public boolean IsNewObject() {
        return _isNewObject;
    }
}
