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
public class PersistenceSearcher<T extends IPersistableObject> extends HashMap<String, Object> implements IPersistenceSearcher<T> {
    private final Class<T> _type;
    
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
