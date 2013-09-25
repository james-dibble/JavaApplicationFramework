// --------------------------------------------------------------------------------------------------------------------
// <copyright file="MapperDictionary.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Mapping;

import java.util.HashMap;

/**
 * A class to associate an instance of a mapper to the type it maps.
 */
public final class MapperDictionary extends HashMap<Class<?>, IMapper> {
    @Override
    public IMapper put(Class key, IMapper value){
        if(key != value.GetMappedType()){
            throw new UnsupportedOperationException(
                    "You cannot add a mapper to this dictionary with a key of different type to what it maps.");
        }
        
        return super.put(key, value);
    }
}
