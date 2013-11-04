// --------------------------------------------------------------------------------------------------------------------
// <copyright file="InjectAttribute.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectAttribute 
{
}
