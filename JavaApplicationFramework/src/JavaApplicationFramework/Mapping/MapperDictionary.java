/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplicationFramework.Mapping;

import java.util.HashMap;

/**
 *
 * @author james
 */
public final class MapperDictionary extends HashMap<Class, IMapper> {
    @Override
    public IMapper put(Class key, IMapper value){
        if(key != value.GetMappedType()){
            throw new UnsupportedOperationException(
                    "You cannot add a mapper to this dictionary with a key of different type to what it maps.");
        }
        
        return value;
    }
}
