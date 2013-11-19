// --------------------------------------------------------------------------------------------------------------------
// <copyright file="MethodFilter.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class MethodFilter implements IActionFilter
{
    @Override
    public boolean CanApplyAction(Method action, HttpServletRequest request, HttpMethod method, String path)
    {
        if((path.equals("/") || path.equals("")) && (action.getName().equalsIgnoreCase("Index") || action.getName().equalsIgnoreCase("Default")))
        {
            return true;
        }
        
        return action.getName().equalsIgnoreCase(path.replace("/", ""));
    }

    @Override
    public IActionResult ApplyAction(Controller controller, Method action, HttpServletRequest request)
    {
        IActionResult result = null;
        
        try        
        {
            result = (IActionResult)action.invoke(controller, request);
        }
        finally
        {
            return result;
        }
    }
}
