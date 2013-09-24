/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplicationFramework.Mapping;

/**
 *
 * @author james
 */
public interface IPersistenceSearcher<T extends IPersistableObject> {
    Object GetArgument(String key);
    
    boolean HasArgument(String key);
}