/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaApplicationFramework.Mapping;

/**
 *
 * @author james
 */
public interface IUniqueObject<T> extends IPersistableObject {
    T GetId();
}
