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
public final class MapperDictionary extends HashMap<Class<?>, IMapper>
{
    /**
     * Initializes a new instance of the MapperDictionary class.
     */
    public MapperDictionary()
    {
    }

    /**
     * * Initializes a new instance of the MapperDictionary class.
     *
     * @param mappers A collection of mappers the dictionary will hold.
     */
    public MapperDictionary(IMapper... mappers)
    {
        for (IMapper mapper : mappers)
        {
            this.put(mapper.GetMappedType(), mapper);
        }
    }

    @Override
    public IMapper put(Class key, IMapper value)
    {
        if (!value.GetMappedType().isAssignableFrom(key))
        {
            throw new UnsupportedOperationException(
                    "You cannot add a mapper to this dictionary with a key of different type to what it maps.");
        }

        return super.put(key, value);
    }
}
