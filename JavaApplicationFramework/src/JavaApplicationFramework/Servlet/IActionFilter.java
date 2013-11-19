// --------------------------------------------------------------------------------------------------------------------
// <copyright file="IActionFilter.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

public interface IActionFilter 
{
    boolean CanApplyAction(Method action, HttpServletRequest request, HttpMethod method, String path);
    
    IActionResult ApplyAction(Controller controller, Method action, HttpServletRequest request);
}
