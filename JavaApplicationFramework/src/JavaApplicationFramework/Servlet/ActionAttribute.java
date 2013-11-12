// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ActionAttribute.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import java.lang.annotation.*;

/**
 * An annotation to place upon methods that service controller requests.
 */
@Target(value={ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionAttribute 
{
    /**
     * An enumeration to define the HTTP request type.
     */
    public enum HttpMethod 
    { 
        GET, 
        POST 
    }
    
    /**
     * Gets the HTTP request type that this method services.
     * @return The HTTP request type that this method services.
     */
    public HttpMethod Method();
    
    /**
     * Gets the path of the controller action that this method services.
     * @return The path of the controller action that this method services.
     */
    public String Path();
}
