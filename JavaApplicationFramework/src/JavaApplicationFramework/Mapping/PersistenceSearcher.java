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

    @Override
    public Object GetArgument(String key) {
        return this.get(key);
    }

    @Override
    public boolean HasArgument(String key) {
        return this.containsKey(key);
    }
}
