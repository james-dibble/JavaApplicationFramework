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

/**
 * A base class for all controllers.
 */
public abstract class Controller extends HttpServlet
{
    /**
     * Gets the base path of this controller.
     * @return The base path of this controller. 
     */
    protected abstract String ControllerPath();

    /**
     * Inject objects into controllers defined by annotations.
     */
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

    /**
     * Process an HTTP GET request.
     * @param request The request.
     * @param response The response.
     * @throws ServletException
     * @throws IOException
     */
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
            throw new ServletException(ex);
        }
    }

     /**
     * Process an HTTP POST request.
     * @param request The request.
     * @param response The response.
     * @throws ServletException
     * @throws IOException
     */
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
            throw new ServletException(ex);
        }
    }

    /**
     * Retrieve an integer value from the request parameters.
     * @param request The request.
     * @param param The parameter to find.
     * @return The parameter as an integer.
     */
    protected static int GetRequestParam(HttpServletRequest request, String param)
    {
        return Integer.parseInt(request.getParameter(param));
    }

    /**
     * Pull a named object from a session and cast it to it's original type.
     * @param <T> The type of the object in the session.
     * @param session The session.
     * @param attribute The key for the object in the session.
     * @return The named object from the session.
     */
    protected static <T> T GetSessionAttribute(HttpSession session, String attribute)
    {
        Object sessionObject;

        if ((sessionObject = session.getAttribute(attribute)) != null)
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
}
