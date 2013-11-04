// --------------------------------------------------------------------------------------------------------------------
// <copyright file="ActionAttribute.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import java.lang.annotation.*;

@Target(value={ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionAttribute 
{
    public enum HttpMethod 
    { 
        GET, 
        POST 
    }
    
    public HttpMethod Method();
    
    public String Path();
}
