// --------------------------------------------------------------------------------------------------------------------
// <copyright file="Controller.java" company="James Dibble">
//    Copyright 2013 James Dibble
// </copyright>
// --------------------------------------------------------------------------------------------------------------------
package JavaApplicationFramework.Servlet;

import JavaApplicationFramework.Servlet.ActionAttribute.HttpMethod;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class Controller extends HttpServlet
{
    protected abstract String ControllerPath();

    @Override
    public void init()
    {
        Field[] fields = this.getClass().getDeclaredFields();

        ServletContext context = this.getServletContext();

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(InjectAttribute.class))
            {
                try
                {
                    field.setAccessible(true);
                    String className = field.getType().getName();
                    Object objectToInject = context.getAttribute(className);
                    field.set(this, objectToInject);
                }
                catch (IllegalArgumentException | IllegalAccessException ex)
                {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    protected final void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response, HttpMethod.GET);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected final void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response, HttpMethod.POST);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, HttpMethod httpMethod)
            throws ServletException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        String path = request.getServletPath().replace(this.ControllerPath(), "");

        Method[] methods = this.getClass().getMethods();

        for (Method method : methods)
        {
            if (method.isAnnotationPresent(ActionAttribute.class))
            {
                ActionAttribute attr = GetAction(method);

                if (attr.Method() == httpMethod && attr.Path().toLowerCase().equals(path.toLowerCase()))
                {
                    IActionResult action = (IActionResult) method.invoke(this, request, response);
                    action.DoAction(request, response);
                    break;
                }
            }
        }
    }

    protected static int GetRequestParam(HttpServletRequest request, String param)
    {
        return Integer.parseInt(request.getParameter(param));
    }

    protected static <T> T GetSessionAttribute(HttpSession session, String attribute)
    {
        Object sessionObject;

        if ((sessionObject = session.getAttribute("orders")) != null)
        {
            return (T) sessionObject;
        }

        return null;
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
