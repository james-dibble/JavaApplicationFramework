// --------------------------------------------------------------------------------------------------------------------
// <copyright file="AnnotationFilter.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

public class AnnotationFilter implements IActionFilter
{
    @Override
    public boolean CanApplyAction(Method action, HttpServletRequest request, HttpMethod method, String path)
    {
        if (!action.isAnnotationPresent(ActionAttribute.class))
        {
            return false;
        }

        ActionAttribute attr = AnnotationFilter.GetAction(action);

        return attr.Method() == method && attr.Path().toLowerCase().equals(path.toLowerCase());
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
    
    private static ActionAttribute GetAction(Method method)
    {
        for (Annotation attr : method.getDeclaredAnnotations())
        {
            if (attr.annotationType() == ActionAttribute.class)
            {
                return (ActionAttribute) attr;
            }
        }

        throw new IllegalStateException("This method has no action attribute.");
    }
}
